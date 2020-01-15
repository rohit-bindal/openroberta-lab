package de.fhg.iais.roberta.worker.compile;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fhg.iais.roberta.bean.CompilerSetupBean;
import de.fhg.iais.roberta.codegen.AbstractCompilerWorkflow;
import de.fhg.iais.roberta.components.Project;
import de.fhg.iais.roberta.util.Key;
import de.fhg.iais.roberta.util.Pair;
import de.fhg.iais.roberta.util.Util;
import de.fhg.iais.roberta.worker.IWorker;

public class GccCompilerWorker implements IWorker {

    private static final Logger LOG = LoggerFactory.getLogger(GccCompilerWorker.class);

    @Override
    public void execute(Project project) {
        CompilerSetupBean compilerWorkflowBean = (CompilerSetupBean) project.getWorkerResult("CompilerSetup");
        final String compilerResourcesDir = compilerWorkflowBean.getCompilerResourcesDir();
        final String tempDir = compilerWorkflowBean.getTempDir();
        String programName = project.getProgramName();
        String token = project.getToken();
        Util.storeGeneratedProgram(tempDir, project.getSourceCode().toString(), token, programName, "." + project.getSourceCodeFileExtension());
        String scriptName = compilerResourcesDir + "arduino.sh";
        String userProgramDirPath = tempDir + token + "/" + programName;

        String gccBinDir = "/home/avinokurov/.arduino15/packages/arduino/tools/avr-gcc/5.4.0-atmel3.6.1-arduino2/bin";
        String boardVariant = "standard";
        String coreIncludes = "/home/avinokurov/.arduino15/packages/arduino/hardware/avr/1.6.23/cores/arduino";
        String variantsIncludes = "/home/avinokurov/.arduino15/packages/arduino/hardware/avr/1.6.23/variants";
        String mmcu = "atmega328p";
        String arduinoVariant = "ARDUINO_UNO";
        String buildDir = tempDir + token + "/" + programName + "/source";

        String[] executableWithParameters =
            {
                scriptName,
                gccBinDir,
                boardVariant,
                coreIncludes,
                variantsIncludes,
                mmcu,
                arduinoVariant,
                buildDir,
                programName,
                compilerResourcesDir
            };
        Pair<Boolean, String> result = AbstractCompilerWorkflow.runCrossCompiler(executableWithParameters);
        Key resultKey = result.getFirst() ? Key.COMPILERWORKFLOW_SUCCESS : Key.COMPILERWORKFLOW_ERROR_PROGRAM_COMPILE_FAILED;
        if ( result.getFirst() ) {
            String base64EncodedHex =
                AbstractCompilerWorkflow.getBase64EncodedHex(Paths.get(userProgramDirPath) + "/target/" + programName + "." + project.getBinaryFileExtension());
            project.setCompiledHex(base64EncodedHex);
            if ( project.getCompiledHex() != null ) {
                resultKey = Key.COMPILERWORKFLOW_SUCCESS;
            } else {
                resultKey = Key.COMPILERWORKFLOW_ERROR_PROGRAM_COMPILE_FAILED;
            }
        }
        project.setResult(resultKey);
        project.addResultParam("MESSAGE", result.getSecond());
        String robot = project.getRobot();
        if ( resultKey == Key.COMPILERWORKFLOW_SUCCESS ) {
            LOG.info("compile {} program {} successful", robot, programName);
        } else {
            LOG.error("compile {} program {} failed with {}", robot, programName, result.getSecond());
        }
    }
}
