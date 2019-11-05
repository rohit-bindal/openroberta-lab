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
import de.fhg.iais.roberta.syntax.action.raspberrypi.LedBlinkAction;
import de.fhg.iais.roberta.syntax.action.raspberrypi.LedDimAction;
import de.fhg.iais.roberta.syntax.action.raspberrypi.LedGetAction;
import de.fhg.iais.roberta.syntax.action.raspberrypi.LedSetAction;
import de.fhg.iais.roberta.syntax.action.serial.SerialWriteAction;
import de.fhg.iais.roberta.syntax.action.sound.PlayFileAction;
import de.fhg.iais.roberta.syntax.action.sound.PlayNoteAction;
import de.fhg.iais.roberta.syntax.action.sound.ToneAction;
import de.fhg.iais.roberta.syntax.action.sound.VolumeAction;
import de.fhg.iais.roberta.syntax.action.speech.SayTextAction;
import de.fhg.iais.roberta.syntax.action.speech.SetLanguageAction;
import de.fhg.iais.roberta.syntax.lang.expr.ColorHexString;
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
import de.fhg.iais.roberta.visitor.hardware.IRaspberryPiVisitor;

/**
 * Collector for the Raspberry Pi.
 * Adds the blocks missing from the defaults of {@link ICollectorVisitor}.
 * Defines the specific parent implementation to use (the one of the collector) due to unrelated defaults.
 */
public interface IRaspberryPiCollectorVisitor extends ICollectorVisitor, IRaspberryPiVisitor<Void> {

    @Override
    default Void visitColorHexString(ColorHexString<Void> colorHexString) {
        return null;
    }

    @Override
    default Void visitLedSetAction(LedSetAction<Void> ledSetAction) {
        ledSetAction.getBrightness().accept(this);
        return null;
    }

    @Override
    default Void visitLedBlinkAction(LedBlinkAction<Void> ledBlinkAction) {
        ledBlinkAction.getDuration().accept(this);
        ledBlinkAction.getFrequency().accept(this);
        return null;
    }

    @Override
    default Void visitLedDimAction(LedDimAction<Void> ledDimAction) {
        ledDimAction.getDuration().accept(this);
        ledDimAction.getFrom().accept(this);
        ledDimAction.getTo().accept(this);
        return null;
    }

    @Override
    default Void visitLedGetAction(LedGetAction<Void> ledGetAction) {
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
