package de.fhg.iais.roberta.visitor.collect;

import java.util.HashMap;

import de.fhg.iais.roberta.bean.LanguageFeatureBean;
import de.fhg.iais.roberta.bean.UsedMethodBean;
import de.fhg.iais.roberta.syntax.lang.expr.EmptyList;
import de.fhg.iais.roberta.syntax.lang.expr.ExprList;
import de.fhg.iais.roberta.syntax.lang.expr.Var;
import de.fhg.iais.roberta.syntax.lang.expr.VarDeclaration;
import de.fhg.iais.roberta.syntax.lang.functions.FunctionNames;
import de.fhg.iais.roberta.syntax.lang.functions.ListRepeat;
import de.fhg.iais.roberta.syntax.lang.functions.MathNumPropFunct;
import de.fhg.iais.roberta.syntax.lang.functions.MathOnListFunct;
import de.fhg.iais.roberta.syntax.lang.functions.MathPowerFunct;
import de.fhg.iais.roberta.syntax.lang.functions.MathRandomFloatFunct;
import de.fhg.iais.roberta.syntax.lang.functions.MathRandomIntFunct;
import de.fhg.iais.roberta.syntax.lang.functions.MathSingleFunct;
import de.fhg.iais.roberta.syntax.lang.methods.MethodReturn;
import de.fhg.iais.roberta.syntax.lang.methods.MethodVoid;
import de.fhg.iais.roberta.syntax.lang.stmt.AssignStmt;
import de.fhg.iais.roberta.syntax.lang.stmt.RepeatStmt;
import de.fhg.iais.roberta.syntax.lang.stmt.StmtFlowCon;
import de.fhg.iais.roberta.syntax.lang.stmt.WaitStmt;
import de.fhg.iais.roberta.typecheck.BlocklyType;

public abstract class AbstractUsedMethodCollectorVisitor extends AbstractLanguageCollectorVisitor {

    protected UsedMethodBean.Builder builder;

    public AbstractUsedMethodCollectorVisitor(UsedMethodBean.Builder builder) {
        this.builder = builder;
    }

    @Override
    public Void visitMathNumPropFunct(MathNumPropFunct<Void> mathNumPropFunct) {
        this.builder.addUsedMethod(mathNumPropFunct.getFunctName());
        return super.visitMathNumPropFunct(mathNumPropFunct);
    }

    @Override
    public Void visitMathOnListFunct(MathOnListFunct<Void> mathOnListFunct) {
        this.builder.addUsedMethod(mathOnListFunct.getFunctName());
        return super.visitMathOnListFunct(mathOnListFunct);
    }

    @Override
    public Void visitMathSingleFunct(MathSingleFunct<Void> mathSingleFunct) {
        if ( mathSingleFunct.getFunctName() == FunctionNames.POW10 ) {
            this.builder.addUsedMethod(FunctionNames.POWER); // combine pow10 and power into one
        } else {
            this.builder.addUsedMethod(mathSingleFunct.getFunctName());
        }
        return super.visitMathSingleFunct(mathSingleFunct);
    }

    @Override
    public Void visitListRepeat(ListRepeat<Void> listRepeat) {
        this.builder.addUsedMethod(FunctionNames.LISTS_REPEAT);
        return super.visitListRepeat(listRepeat);
    }

    @Override
    public Void visitMathPowerFunct(MathPowerFunct<Void> mathPowerFunct) {
        this.builder.addUsedMethod(FunctionNames.POWER);
        return super.visitMathPowerFunct(mathPowerFunct);
    }

    @Override
    public Void visitMathRandomIntFunct(MathRandomIntFunct<Void> mathRandomIntFunct) {
        this.builder.addUsedMethod(FunctionNames.RANDOM);
        return super.visitMathRandomIntFunct(mathRandomIntFunct);
    }

    @Override
    public Void visitMathRandomFloatFunct(MathRandomFloatFunct<Void> mathRandomFloatFunct) {
        this.builder.addUsedMethod(FunctionNames.RANDOM_DOUBLE);
        return super.visitMathRandomFloatFunct(mathRandomFloatFunct);
    }
}
