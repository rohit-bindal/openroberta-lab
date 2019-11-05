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
import de.fhg.iais.roberta.syntax.action.generic.PinWriteValueAction;
import de.fhg.iais.roberta.syntax.action.light.LightAction;
import de.fhg.iais.roberta.syntax.action.light.LightStatusAction;
import de.fhg.iais.roberta.syntax.action.mbed.BothMotorsOnAction;
import de.fhg.iais.roberta.syntax.action.mbed.BothMotorsStopAction;
import de.fhg.iais.roberta.syntax.action.mbed.DisplayGetBrightnessAction;
import de.fhg.iais.roberta.syntax.action.mbed.DisplayGetPixelAction;
import de.fhg.iais.roberta.syntax.action.mbed.DisplayImageAction;
import de.fhg.iais.roberta.syntax.action.mbed.DisplaySetBrightnessAction;
import de.fhg.iais.roberta.syntax.action.mbed.DisplaySetPixelAction;
import de.fhg.iais.roberta.syntax.action.mbed.DisplayTextAction;
import de.fhg.iais.roberta.syntax.action.mbed.FourDigitDisplayClearAction;
import de.fhg.iais.roberta.syntax.action.mbed.FourDigitDisplayShowAction;
import de.fhg.iais.roberta.syntax.action.mbed.LedBarSetAction;
import de.fhg.iais.roberta.syntax.action.mbed.LedOnAction;
import de.fhg.iais.roberta.syntax.action.mbed.PinSetPullAction;
import de.fhg.iais.roberta.syntax.action.mbed.RadioReceiveAction;
import de.fhg.iais.roberta.syntax.action.mbed.RadioSendAction;
import de.fhg.iais.roberta.syntax.action.mbed.RadioSetChannelAction;
import de.fhg.iais.roberta.syntax.action.mbed.SingleMotorOnAction;
import de.fhg.iais.roberta.syntax.action.mbed.SingleMotorStopAction;
import de.fhg.iais.roberta.syntax.action.mbed.SwitchLedMatrixAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorGetPowerAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorOnAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorSetPowerAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorStopAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.CurveAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.DriveAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.MotorDriveStopAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.TurnAction;
import de.fhg.iais.roberta.syntax.action.serial.SerialWriteAction;
import de.fhg.iais.roberta.syntax.action.sound.PlayFileAction;
import de.fhg.iais.roberta.syntax.action.sound.PlayNoteAction;
import de.fhg.iais.roberta.syntax.action.sound.ToneAction;
import de.fhg.iais.roberta.syntax.action.sound.VolumeAction;
import de.fhg.iais.roberta.syntax.action.speech.SayTextAction;
import de.fhg.iais.roberta.syntax.action.speech.SetLanguageAction;
import de.fhg.iais.roberta.syntax.expr.mbed.Image;
import de.fhg.iais.roberta.syntax.expr.mbed.PredefinedImage;
import de.fhg.iais.roberta.syntax.functions.mbed.ImageInvertFunction;
import de.fhg.iais.roberta.syntax.functions.mbed.ImageShiftFunction;
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
import de.fhg.iais.roberta.syntax.sensor.mbed.RadioRssiSensor;
import de.fhg.iais.roberta.visitor.hardware.IMbedVisitor;

/**
 * Collector for the Mbed robots.
 * Adds the blocks missing from the defaults of {@link ICollectorVisitor}.
 * Defines the specific parent implementation to use (the one of the collector) due to unrelated defaults.
 */
public interface IMbedCollectorVisitor extends ICollectorVisitor, IMbedVisitor<Void> {

    @Override
    default Void visitDisplayTextAction(DisplayTextAction<Void> displayTextAction) {
        displayTextAction.getMsg().accept(this);
        return null;
    }

    @Override
    default Void visitPredefinedImage(PredefinedImage<Void> predefinedImage) {
        return null;
    }

    @Override
    default Void visitDisplayImageAction(DisplayImageAction<Void> displayImageAction) {
        displayImageAction.getValuesToDisplay().accept(this);
        return null;
    }

    @Override
    default Void visitImageShiftFunction(ImageShiftFunction<Void> imageShiftFunction) {
        imageShiftFunction.getImage().accept(this);
        imageShiftFunction.getPositions().accept(this);
        return null;
    }

    @Override
    default Void visitImageInvertFunction(ImageInvertFunction<Void> imageInvertFunction) {
        imageInvertFunction.getImage().accept(this);
        return null;
    }

    @Override
    default Void visitImage(Image<Void> image) {
        return null;
    }

    @Override
    default Void visitLedOnAction(LedOnAction<Void> ledOnAction) {
        ledOnAction.getLedColor().accept(this);
        return null;
    }

    @Override
    default Void visitRadioSendAction(RadioSendAction<Void> radioSendAction) {
        radioSendAction.getMsg().accept(this);
        return null;
    }

    @Override
    default Void visitRadioReceiveAction(RadioReceiveAction<Void> radioReceiveAction) {
        return null;
    }

    @Override
    default Void visitPinSetPullAction(PinSetPullAction<Void> pinSetPullAction) {
        return null;
    }

    @Override
    default Void visitDisplaySetBrightnessAction(DisplaySetBrightnessAction<Void> displaySetBrightnessAction) {
        displaySetBrightnessAction.getBrightness().accept(this);
        return null;
    }

    @Override
    default Void visitDisplayGetBrightnessAction(DisplayGetBrightnessAction<Void> displayGetBrightnessAction) {
        return null;
    }

    @Override
    default Void visitDisplaySetPixelAction(DisplaySetPixelAction<Void> displaySetPixelAction) {
        displaySetPixelAction.getBrightness().accept(this);
        displaySetPixelAction.getX().accept(this);
        displaySetPixelAction.getY().accept(this);
        return null;
    }

    @Override
    default Void visitDisplayGetPixelAction(DisplayGetPixelAction<Void> displayGetPixelAction) {
        displayGetPixelAction.getX().accept(this);
        displayGetPixelAction.getY().accept(this);
        return null;
    }

    @Override
    default Void visitRadioSetChannelAction(RadioSetChannelAction<Void> radioSetChannelAction) {
        radioSetChannelAction.getChannel().accept(this);
        return null;
    }

    @Override
    default Void visitSingleMotorOnAction(SingleMotorOnAction<Void> singleMotorOnAction) {
        singleMotorOnAction.getSpeed().accept(this);
        return null;
    }

    @Override
    default Void visitSingleMotorStopAction(SingleMotorStopAction<Void> singleMotorStopAction) {
        return null;
    }

    @Override
    default Void visitFourDigitDisplayShowAction(FourDigitDisplayShowAction<Void> fourDigitDisplayShowAction) {
        fourDigitDisplayShowAction.getColon().accept(this);
        fourDigitDisplayShowAction.getPosition().accept(this);
        fourDigitDisplayShowAction.getValue().accept(this);
        return null;
    }

    @Override
    default Void visitFourDigitDisplayClearAction(FourDigitDisplayClearAction<Void> fourDigitDisplayClearAction) {
        return null;
    }

    @Override
    default Void visitBothMotorsOnAction(BothMotorsOnAction<Void> bothMotorsOnAction) {
        bothMotorsOnAction.getSpeedA().accept(this);
        bothMotorsOnAction.getSpeedB().accept(this);
        return null;
    }

    @Override
    default Void visitBothMotorsStopAction(BothMotorsStopAction<Void> bothMotorsStopAction) {
        return null;
    }

    @Override
    default Void visitRadioRssiSensor(RadioRssiSensor<Void> radioRssiSensor) {
        return null;
    }

    @Override
    default Void visitLedBarSetAction(LedBarSetAction<Void> ledBarSetAction) {
        ledBarSetAction.getBrightness().accept(this);
        ledBarSetAction.getX().accept(this);
        return null;
    }

    @Override
    default Void visitSwitchLedMatrixAction(SwitchLedMatrixAction<Void> switchLedMatrixAction) {
        return null;
    }

    @Override
    default Void visitPinWriteValueAction(PinWriteValueAction<Void> pinWriteValueAction) {
        pinWriteValueAction.getValue().accept(this);
        return null;
    }

    @Override
    default Void visitClearDisplayAction(ClearDisplayAction<Void> clearDisplayAction) {
        return null;
    }

    @Override
    default Void visitLightAction(LightAction<Void> lightAction) {
        lightAction.getRgbLedColor().accept(this);
        return null;
    }

    @Override
    default Void visitLightStatusAction(LightStatusAction<Void> lightStatusAction) {
        return null;
    }

    @Override
    default Void visitMotorGetPowerAction(MotorGetPowerAction<Void> motorGetPowerAction) {
        return null;
    }

    @Override
    default Void visitMotorOnAction(MotorOnAction<Void> motorOnAction) {
        Expr<Void> durationValue = motorOnAction.getDurationValue();
        if ( durationValue != null ) { // TODO why is this necessary?
            motorOnAction.getDurationValue().accept(this);
        }
        MotionParam<Void> param = motorOnAction.getParam();
        MotorDuration<Void> duration = param.getDuration();
        if ( duration != null ) { // TODO why is this necessary?
            duration.getValue().accept(this);
        }
        param.getSpeed().accept(this);
        return null;
    }

    @Override
    default Void visitMotorSetPowerAction(MotorSetPowerAction<Void> motorSetPowerAction) {
        motorSetPowerAction.getPower().accept(this);
        return null;
    }

    @Override
    default Void visitMotorStopAction(MotorStopAction<Void> motorStopAction) {
        return null;
    }

    @Override
    default Void visitSerialWriteAction(SerialWriteAction<Void> serialWriteAction) {
        serialWriteAction.getValue().accept(this);
        return null;
    }

    @Override
    default Void visitToneAction(ToneAction<Void> toneAction) {
        toneAction.getFrequency().accept(this);
        toneAction.getDuration().accept(this);
        return null;
    }

    @Override
    default Void visitPlayNoteAction(PlayNoteAction<Void> playNoteAction) {
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
