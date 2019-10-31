package de.fhg.iais.roberta.worker.codegen;

import de.fhg.iais.roberta.bean.CodeGeneratorSetupBean;
import de.fhg.iais.roberta.bean.OraBean;
import de.fhg.iais.roberta.bean.UsedHardwareBean;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.visitor.codegen.SenseboxCppVisitor;
import de.fhg.iais.roberta.visitor.lang.codegen.AbstractLanguageVisitor;
import de.fhg.iais.roberta.worker.AbstractLanguageGeneratorWorker;

public class SenseboxCxxGeneratorWorker extends AbstractLanguageGeneratorWorker {

    @Override
    protected AbstractLanguageVisitor getVisitor(Project project, OraBean... beans) {
        return new SenseboxCppVisitor(
            project.getProgramAst().getTree(),
            project.getConfigurationAst(),
            project.getSSID(),
            project.getPassword(),
            beans);
    }
}
