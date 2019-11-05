package de.fhg.iais.roberta.visitor.collect;

import de.fhg.iais.roberta.syntax.MotionParam;
import de.fhg.iais.roberta.syntax.MotorDuration;
import de.fhg.iais.roberta.syntax.action.communication.BluetoothCheckConnectAction;
import de.fhg.iais.roberta.syntax.action.communication.BluetoothConnectAction;
import de.fhg.iais.roberta.syntax.action.communication.BluetoothReceiveAction;
import de.fhg.iais.roberta.syntax.action.communication.BluetoothSendAction;
import de.fhg.iais.roberta.syntax.action.communication.BluetoothWaitForConnectionAction;
import de.fhg.iais.roberta.syntax.action.display.ClearDisplayAction;
import de.fhg.iais.roberta.syntax.action.display.ShowTextAction;
import de.fhg.iais.roberta.syntax.action.light.LightAction;
import de.fhg.iais.roberta.syntax.action.light.LightStatusAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorGetPowerAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorOnAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorSetPowerAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorStopAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.CurveAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.DriveAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.MotorDriveStopAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.TurnAction;
import de.fhg.iais.roberta.syntax.action.nao.Animation;
import de.fhg.iais.roberta.syntax.action.nao.ApplyPosture;
import de.fhg.iais.roberta.syntax.action.nao.Autonomous;
import de.fhg.iais.roberta.syntax.action.nao.ForgetFace;
import de.fhg.iais.roberta.syntax.action.nao.GetLanguage;
import de.fhg.iais.roberta.syntax.action.nao.GetVolume;
import de.fhg.iais.roberta.syntax.action.nao.Hand;
import de.fhg.iais.roberta.syntax.action.nao.LearnFace;
import de.fhg.iais.roberta.syntax.action.nao.LedOff;
import de.fhg.iais.roberta.syntax.action.nao.LedReset;
import de.fhg.iais.roberta.syntax.action.nao.MoveJoint;
import de.fhg.iais.roberta.syntax.action.nao.PlayFile;
import de.fhg.iais.roberta.syntax.action.nao.PointLookAt;
import de.fhg.iais.roberta.syntax.action.nao.RandomEyesDuration;
import de.fhg.iais.roberta.syntax.action.nao.RastaDuration;
import de.fhg.iais.roberta.syntax.action.nao.RecordVideo;
import de.fhg.iais.roberta.syntax.action.nao.SetIntensity;
import de.fhg.iais.roberta.syntax.action.nao.SetLeds;
import de.fhg.iais.roberta.syntax.action.nao.SetMode;
import de.fhg.iais.roberta.syntax.action.nao.SetStiffness;
import de.fhg.iais.roberta.syntax.action.nao.SetVolume;
import de.fhg.iais.roberta.syntax.action.nao.Stop;
import de.fhg.iais.roberta.syntax.action.nao.TakePicture;
import de.fhg.iais.roberta.syntax.action.nao.TurnDegrees;
import de.fhg.iais.roberta.syntax.action.nao.WalkAsync;
import de.fhg.iais.roberta.syntax.action.nao.WalkDistance;
import de.fhg.iais.roberta.syntax.action.nao.WalkTo;
import de.fhg.iais.roberta.syntax.action.serial.SerialWriteAction;
import de.fhg.iais.roberta.syntax.action.sound.PlayFileAction;
import de.fhg.iais.roberta.syntax.action.sound.PlayNoteAction;
import de.fhg.iais.roberta.syntax.action.sound.ToneAction;
import de.fhg.iais.roberta.syntax.action.sound.VolumeAction;
import de.fhg.iais.roberta.syntax.action.speech.SayTextAction;
import de.fhg.iais.roberta.syntax.action.speech.SetLanguageAction;
import de.fhg.iais.roberta.syntax.lang.expr.Expr;
import de.fhg.iais.roberta.syntax.sensor.generic.AccelerometerSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.ColorSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.CompassSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.DropSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.EncoderSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.GestureSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.GetSampleSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.GyroSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.HumiditySensor;
import de.fhg.iais.roberta.syntax.sensor.generic.IRSeekerSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.InfraredSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.KeysSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.LightSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.MoistureSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.MotionSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.PinGetValueSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.PinTouchSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.PulseSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.RfidSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.SoundSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.TemperatureSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.TimerSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.TouchSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.UltrasonicSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.VemlLightSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.VoltageSensor;
import de.fhg.iais.roberta.syntax.sensor.nao.DetectFaceSensor;
import de.fhg.iais.roberta.syntax.sensor.nao.DetectMarkSensor;
import de.fhg.iais.roberta.syntax.sensor.nao.DetectedFaceInformation;
import de.fhg.iais.roberta.syntax.sensor.nao.ElectricCurrentSensor;
import de.fhg.iais.roberta.syntax.sensor.nao.FsrSensor;
import de.fhg.iais.roberta.syntax.sensor.nao.NaoMarkInformation;
import de.fhg.iais.roberta.syntax.sensor.nao.RecognizeWord;
import de.fhg.iais.roberta.visitor.hardware.INaoVisitor;

/**
 * Collector for the NAO.
 * Adds the blocks missing from the defaults of {@link ICollectorVisitor}.
 * Defines the specific parent implementation to use (the one of the collector) due to unrelated defaults.
 */
public interface INaoCollectorVisitor extends ICollectorVisitor, INaoVisitor<Void> {

    @Override
    default Void visitSetMode(SetMode<Void> mode) {
        return null;
    }

    @Override
    default Void visitApplyPosture(ApplyPosture<Void> applyPosture) {
        return null;
    }

    @Override
    default Void visitSetStiffness(SetStiffness<Void> setStiffness) {
        return null;
    }

    @Override
    default Void visitHand(Hand<Void> hand) {
        return null;
    }

    @Override
    default Void visitMoveJoint(MoveJoint<Void> moveJoint) {
        moveJoint.getDegrees().accept(this);
        return null;
    }

    @Override
    default Void visitWalkDistance(WalkDistance<Void> walkDistance) {
        walkDistance.getDistanceToWalk().accept(this);
        return null;
    }

    @Override
    default Void visitTurnDegrees(TurnDegrees<Void> turnDegrees) {
        turnDegrees.getDegreesToTurn().accept(this);
        return null;
    }

    @Override
    default Void visitWalkTo(WalkTo<Void> walkTo) {
        walkTo.getWalkToTheta().accept(this);
        walkTo.getWalkToX().accept(this);
        walkTo.getWalkToY().accept(this);
        return null;
    }

    @Override
    default Void visitWalkAsync(WalkAsync<Void> walkAsync) {
        walkAsync.getXSpeed().accept(this);
        walkAsync.getYSpeed().accept(this);
        walkAsync.getZSpeed().accept(this);
        return null;
    }

    @Override
    default Void visitStop(Stop<Void> stop) {
        return null;
    }

    @Override
    default Void visitAnimation(Animation<Void> animation) {
        return null;
    }

    @Override
    default Void visitPointLookAt(PointLookAt<Void> pointLookAt) {
        pointLookAt.getpointX().accept(this);
        pointLookAt.getpointY().accept(this);
        pointLookAt.getpointZ().accept(this);
        pointLookAt.getSpeed().accept(this);
        return null;
    }

    @Override
    default Void visitSetVolume(SetVolume<Void> setVolume) {
        setVolume.getVolume().accept(this);
        return null;
    }

    @Override
    default Void visitGetVolume(GetVolume<Void> getVolume) {
        return null;
    }

    @Override
    default Void visitGetLanguage(GetLanguage<Void> getLanguage) {
        return null;
    }

    @Override
    default Void visitPlayFile(PlayFile<Void> playFile) {
        playFile.getMsg().accept(this);
        return null;
    }

    @Override
    default Void visitSetLeds(SetLeds<Void> setLeds) {
        setLeds.getColor().accept(this);
        return null;
    }

    @Override
    default Void visitLedOff(LedOff<Void> ledOff) {
        return null;
    }

    @Override
    default Void visitLedReset(LedReset<Void> ledReset) {
        return null;
    }

    @Override
    default Void visitRandomEyesDuration(RandomEyesDuration<Void> randomEyesDuration) {
        randomEyesDuration.getDuration().accept(this);
        return null;
    }

    @Override
    default Void visitRastaDuration(RastaDuration<Void> rastaDuration) {
        rastaDuration.getDuration().accept(this);
        return null;
    }

    @Override
    default Void visitFsrSensor(FsrSensor<Void> forceSensor) {
        return null;
    }

    @Override
    default Void visitNaoMark(DetectMarkSensor<Void> naoMark) {
        return null;
    }

    @Override
    default Void visitTakePicture(TakePicture<Void> takePicture) {
        takePicture.getPictureName().accept(this);
        return null;
    }

    @Override
    default Void visitRecordVideo(RecordVideo<Void> recordVideo) {
        recordVideo.getDuration().accept(this);
        recordVideo.getVideoName().accept(this);
        return null;
    }

    @Override
    default Void visitLearnFace(LearnFace<Void> learnFace) {
        learnFace.getFaceName().accept(this);
        return null;
    }

    @Override
    default Void visitForgetFace(ForgetFace<Void> forgetFace) {
        forgetFace.getFaceName().accept(this);
        return null;
    }

    @Override
    default Void visitDetectFace(DetectFaceSensor<Void> detectFace) {
        return null;
    }

    @Override
    default Void visitElectricCurrent(ElectricCurrentSensor<Void> electricCurrent) {
        return null;
    }

    @Override
    default Void visitSetIntensity(SetIntensity<Void> setIntensity) {
        setIntensity.getIntensity().accept(this);
        return null;
    }

    @Override
    default Void visitAutonomous(Autonomous<Void> autonomous) {
        return null;
    }

    @Override
    default Void visitRecognizeWord(RecognizeWord<Void> recognizeWord) {
        recognizeWord.getVocabulary().accept(this);
        return null;
    }

    @Override
    default Void visitNaoMarkInformation(NaoMarkInformation<Void> naoMarkInformation) {
        naoMarkInformation.getNaoMarkId().accept(this);
        return null;
    }

    @Override
    default Void visitDetecedFaceInformation(DetectedFaceInformation<Void> detectedFaceInformation) {
        detectedFaceInformation.getFaceName().accept(this);
        return null;
    }

    @Override
    default Void visitSetLanguageAction(SetLanguageAction<Void> setLanguageAction) {
        return null;
    }

    @Override
    default Void visitSayTextAction(SayTextAction<Void> sayTextAction) {
        sayTextAction.getMsg().accept(this);
        sayTextAction.getPitch().accept(this);
        sayTextAction.getSpeed().accept(this);
        return null;
    }

    @Override
    default Void visitKeysSensor(KeysSensor<Void> keysSensor) {
        return null;
    }

    @Override
    default Void visitColorSensor(ColorSensor<Void> colorSensor) {
        return null;
    }

    @Override
    default Void visitLightSensor(LightSensor<Void> lightSensor) {
        return null;
    }

    @Override
    default Void visitSoundSensor(SoundSensor<Void> soundSensor) {
        return null;
    }

    @Override
    default Void visitEncoderSensor(EncoderSensor<Void> encoderSensor) {
        return null;
    }

    @Override
    default Void visitGyroSensor(GyroSensor<Void> gyroSensor) {
        return null;
    }

    @Override
    default Void visitInfraredSensor(InfraredSensor<Void> infraredSensor) {
        return null;
    }

    @Override
    default Void visitTimerSensor(TimerSensor<Void> timerSensor) {
        return null;
    }

    @Override
    default Void visitTouchSensor(TouchSensor<Void> touchSensor) {
        return null;
    }

    @Override
    default Void visitUltrasonicSensor(UltrasonicSensor<Void> ultrasonicSensor) {
        return null;
    }

    @Override
    default Void visitCompassSensor(CompassSensor<Void> compassSensor) {
        return null;
    }

    @Override
    default Void visitTemperatureSensor(TemperatureSensor<Void> temperatureSensor) {
        return null;
    }

    @Override
    default Void visitVoltageSensor(VoltageSensor<Void> voltageSensor) {
        return null;
    }

    @Override
    default Void visitAccelerometer(AccelerometerSensor<Void> accelerometerSensor) {
        return null;
    }

    @Override
    default Void visitPinTouchSensor(PinTouchSensor<Void> pinTouchSensor) {
        return null;
    }

    @Override
    default Void visitGestureSensor(GestureSensor<Void> gestureSensor) {
        return null;
    }

    @Override
    default Void visitPinGetValueSensor(PinGetValueSensor<Void> pinGetValueSensor) {
        return null;
    }

    @Override
    default Void visitGetSampleSensor(GetSampleSensor<Void> sensorGetSample) {
        return null;
    }

    @Override
    default Void visitIRSeekerSensor(IRSeekerSensor<Void> irSeekerSensor) {
        return null;
    }

    @Override
    default Void visitMoistureSensor(MoistureSensor<Void> moistureSensor) {
        return null;
    }

    @Override
    default Void visitHumiditySensor(HumiditySensor<Void> humiditySensor) {
        return null;
    }

    @Override
    default Void visitMotionSensor(MotionSensor<Void> motionSensor) {
        return null;
    }

    @Override
    default Void visitDropSensor(DropSensor<Void> dropSensor) {
        return null;
    }

    @Override
    default Void visitPulseSensor(PulseSensor<Void> pulseSensor) {
        return null;
    }

    @Override
    default Void visitRfidSensor(RfidSensor<Void> rfidSensor) {
        return null;
    }

    @Override
    default Void visitVemlLightSensor(VemlLightSensor<Void> vemlLightSensor) {
        return null;
    }
}
