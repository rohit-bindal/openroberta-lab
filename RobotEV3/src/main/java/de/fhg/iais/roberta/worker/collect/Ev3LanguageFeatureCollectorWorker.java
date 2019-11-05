package de.fhg.iais.roberta.worker.collect;

import de.fhg.iais.roberta.bean.LanguageFeatureBean;
import de.fhg.iais.roberta.visitor.IVisitor;
import de.fhg.iais.roberta.visitor.collect.Ev3LanguageFeatureCollectorVisitor;
import de.fhg.iais.roberta.worker.AbstractLanguageFeatureCollectorWorker;

public class Ev3LanguageFeatureCollectorWorker extends AbstractLanguageFeatureCollectorWorker {
    @Override
    protected IVisitor getVisitor(LanguageFeatureBean.Builder builder) {
        return new Ev3LanguageFeatureCollectorVisitor(builder);
    }
}
