package de.fhg.iais.roberta.visitor.collect;

import de.fhg.iais.roberta.transformer.CodeGeneratorSetupBean;

public class NxtUsedMethodCollectorVisitor extends AbstractUsedMethodCollectorVisitor implements INxtCollectorVisitor {
    public NxtUsedMethodCollectorVisitor(CodeGeneratorSetupBean.Builder builder) {
        super(builder);
    }
}
