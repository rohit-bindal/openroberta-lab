package de.fhg.iais.roberta.visitor.collect;

import java.util.HashMap;

import de.fhg.iais.roberta.bean.LanguageFeatureBean;
import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.syntax.lang.blocksequence.ActivityTask;
import de.fhg.iais.roberta.syntax.lang.blocksequence.MainTask;
import de.fhg.iais.roberta.syntax.lang.blocksequence.StartActivityTask;
import de.fhg.iais.roberta.syntax.lang.expr.Binary;
import de.fhg.iais.roberta.syntax.lang.expr.BoolConst;
import de.fhg.iais.roberta.syntax.lang.expr.ColorConst;
import de.fhg.iais.roberta.syntax.lang.expr.ConnectConst;
import de.fhg.iais.roberta.syntax.lang.expr.EmptyExpr;
import de.fhg.iais.roberta.syntax.lang.expr.EmptyList;
import de.fhg.iais.roberta.syntax.lang.expr.ExprList;
import de.fhg.iais.roberta.syntax.lang.expr.ListCreate;
import de.fhg.iais.roberta.syntax.lang.expr.MathConst;
import de.fhg.iais.roberta.syntax.lang.expr.NullConst;
import de.fhg.iais.roberta.syntax.lang.expr.NumConst;
import de.fhg.iais.roberta.syntax.lang.expr.RgbColor;
import de.fhg.iais.roberta.syntax.lang.expr.StringConst;
import de.fhg.iais.roberta.syntax.lang.expr.Unary;
import de.fhg.iais.roberta.syntax.lang.expr.Var;
import de.fhg.iais.roberta.syntax.lang.expr.VarDeclaration;
import de.fhg.iais.roberta.syntax.lang.functions.GetSubFunct;
import de.fhg.iais.roberta.syntax.lang.functions.IndexOfFunct;
import de.fhg.iais.roberta.syntax.lang.functions.LengthOfIsEmptyFunct;
import de.fhg.iais.roberta.syntax.lang.functions.ListGetIndex;
import de.fhg.iais.roberta.syntax.lang.functions.ListRepeat;
import de.fhg.iais.roberta.syntax.lang.functions.ListSetIndex;
import de.fhg.iais.roberta.syntax.lang.functions.MathConstrainFunct;
import de.fhg.iais.roberta.syntax.lang.functions.MathNumPropFunct;
import de.fhg.iais.roberta.syntax.lang.functions.MathOnListFunct;
import de.fhg.iais.roberta.syntax.lang.functions.MathPowerFunct;
import de.fhg.iais.roberta.syntax.lang.functions.MathRandomFloatFunct;
import de.fhg.iais.roberta.syntax.lang.functions.MathRandomIntFunct;
import de.fhg.iais.roberta.syntax.lang.functions.MathSingleFunct;
import de.fhg.iais.roberta.syntax.lang.functions.TextJoinFunct;
import de.fhg.iais.roberta.syntax.lang.functions.TextPrintFunct;
import de.fhg.iais.roberta.syntax.lang.methods.MethodCall;
import de.fhg.iais.roberta.syntax.lang.methods.MethodIfReturn;
import de.fhg.iais.roberta.syntax.lang.methods.MethodReturn;
import de.fhg.iais.roberta.syntax.lang.methods.MethodVoid;
import de.fhg.iais.roberta.syntax.lang.stmt.AssertStmt;
import de.fhg.iais.roberta.syntax.lang.stmt.AssignStmt;
import de.fhg.iais.roberta.syntax.lang.stmt.DebugAction;
import de.fhg.iais.roberta.syntax.lang.stmt.IfStmt;
import de.fhg.iais.roberta.syntax.lang.stmt.MethodStmt;
import de.fhg.iais.roberta.syntax.lang.stmt.RepeatStmt;
import de.fhg.iais.roberta.syntax.lang.stmt.StmtFlowCon;
import de.fhg.iais.roberta.syntax.lang.stmt.StmtList;
import de.fhg.iais.roberta.syntax.lang.stmt.StmtTextComment;
import de.fhg.iais.roberta.syntax.lang.stmt.WaitStmt;
import de.fhg.iais.roberta.syntax.lang.stmt.WaitTimeStmt;
import de.fhg.iais.roberta.typecheck.BlocklyType;
import de.fhg.iais.roberta.visitor.lang.ILanguageVisitor;

public abstract class AbstractLanguageFeatureCollectorVisitor extends AbstractLanguageCollectorVisitor {

    private final LanguageFeatureBean.Builder builder;
    private final HashMap<Integer, Integer> waitsInLoops = new HashMap<>();
    private int loopCounter = 0;
    private int currentLoop = 0;

    public AbstractLanguageFeatureCollectorVisitor(LanguageFeatureBean.Builder builder) {
        this.builder = builder;
    }

    @Override
    public Void visitVarDeclaration(VarDeclaration<Void> var) {
        if ( var.isGlobal() ) {
            this.builder.addVisitedVariable(var);
        }
        if ( var.getVarType().equals(BlocklyType.ARRAY)
             || var.getVarType().equals(BlocklyType.ARRAY_BOOLEAN)
             || var.getVarType().equals(BlocklyType.ARRAY_NUMBER)
             || var.getVarType().equals(BlocklyType.ARRAY_COLOUR)
             || var.getVarType().equals(BlocklyType.ARRAY_CONNECTION)
             || var.getVarType().equals(BlocklyType.ARRAY_IMAGE)
             || var.getVarType().equals(BlocklyType.ARRAY_STRING) ) {
            this.builder.setListsUsed(true);
        }
        this.builder.addGlobalVariable(var.getName());
        this.builder.addDeclaredVariable(var.getName());
        return super.visitVarDeclaration(var);
    }

    @Override
    public Void visitEmptyList(EmptyList<Void> emptyList) {
        this.builder.setListsUsed(true);
        return super.visitEmptyList(emptyList);
    }

    @Override
    public Void visitAssignStmt(AssignStmt<Void> assignStmt) {
        String variableName = assignStmt.getName().getValue();
        if ( this.builder.containsGlobalVariable(variableName) ) {
            this.builder.addMarkedVariableAsGlobal(variableName);
        }
        return super.visitAssignStmt(assignStmt);
    }

    @Override
    public Void visitRepeatStmt(RepeatStmt<Void> repeatStmt) {
        if ( repeatStmt.getExpr().getKind().hasName("EXPR_LIST") ) {
            ExprList<Void> exprList = (ExprList<Void>) repeatStmt.getExpr();
            String varName = ((Var<Void>) exprList.get().get(0)).getValue();
            this.builder.addDeclaredVariable(varName);
            exprList.accept(this);
        } else {
            repeatStmt.getExpr().accept(this);
        }

        if ( repeatStmt.getMode() != RepeatStmt.Mode.WAIT ) {
            increaseLoopCounter();
            repeatStmt.getList().accept(this);
            this.currentLoop--;
        } else {
            repeatStmt.getList().accept(this);
        }
        return null; // do not call super
    }

    @Override
    public Void visitStmtFlowCon(StmtFlowCon<Void> stmtFlowCon) {
        boolean isInWaitStmt = this.waitsInLoops.get(this.currentLoop) != 0;
        this.builder.putLoopLabel(this.currentLoop, isInWaitStmt);
        return super.visitStmtFlowCon(stmtFlowCon);
    }

    @Override
    public Void visitWaitStmt(WaitStmt<Void> waitStmt) {
        if ( this.waitsInLoops.get(this.loopCounter) != null ) {
            increaseWaitStmsInLoop();
            waitStmt.getStatements().accept(this);
            decreaseWaitStmtInLoop();
        } else {
            waitStmt.getStatements().accept(this);
        }
        return null; // do not call super
    }

    @Override
    public Void visitMethodVoid(MethodVoid<Void> methodVoid) {
        this.builder.addUserDefinedMethod(methodVoid);
        return super.visitMethodVoid(methodVoid);
    }

    @Override
    public Void visitMethodReturn(MethodReturn<Void> methodReturn) {
        this.builder.addUserDefinedMethod(methodReturn);
        return super.visitMethodReturn(methodReturn);
    }

    private void increaseLoopCounter() {
        this.loopCounter++;
        this.currentLoop = this.loopCounter;
        this.builder.putLoopLabel(this.loopCounter, false);
        this.waitsInLoops.put(this.loopCounter, 0);
    }

    private void decreaseWaitStmtInLoop() {
        int count;
        count = this.waitsInLoops.get(this.loopCounter);
        this.waitsInLoops.put(this.loopCounter, --count);
    }

    private void increaseWaitStmsInLoop() {
        int count;
        count = this.waitsInLoops.get(this.loopCounter);
        this.waitsInLoops.put(this.loopCounter, ++count);
    }
}
