package de.fhg.iais.roberta.worker;

import de.fhg.iais.roberta.bean.LanguageFeatureBean;
import de.fhg.iais.roberta.visitor.IVisitor;
import de.fhg.iais.roberta.visitor.collect.MbedLanguageFeatureCollectorVisitor;

public class MbedLanguageFeatureCollectorWorker extends AbstractLanguageFeatureCollectorWorker {
    @Override
    protected IVisitor getVisitor(LanguageFeatureBean.Builder builder) {
        return new MbedLanguageFeatureCollectorVisitor(builder);
    }
}
