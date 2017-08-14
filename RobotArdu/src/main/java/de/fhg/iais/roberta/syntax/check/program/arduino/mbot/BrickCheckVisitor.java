package de.fhg.iais.roberta.syntax.check.program.arduino.mbot;

import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.syntax.action.arduino.mbot.ExternalLedOffAction;
import de.fhg.iais.roberta.syntax.action.arduino.mbot.ExternalLedOnAction;
import de.fhg.iais.roberta.syntax.action.arduino.mbot.LedOffAction;
import de.fhg.iais.roberta.syntax.action.arduino.mbot.LedOnAction;
import de.fhg.iais.roberta.syntax.check.program.RobotBrickCheckVisitor;
import de.fhg.iais.roberta.syntax.sensor.arduino.botnroll.VoltageSensor;
import de.fhg.iais.roberta.syntax.sensor.arduino.mbot.Accelerometer;
import de.fhg.iais.roberta.syntax.sensor.arduino.mbot.AmbientLightSensor;
import de.fhg.iais.roberta.syntax.sensor.arduino.mbot.FlameSensor;
import de.fhg.iais.roberta.syntax.sensor.arduino.mbot.Joystick;
import de.fhg.iais.roberta.syntax.sensor.arduino.mbot.PIRMotionSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.TemperatureSensor;
import de.fhg.iais.roberta.visitor.arduino.MbotAstVisitor;

public class BrickCheckVisitor extends RobotBrickCheckVisitor implements MbotAstVisitor<Void> {

    public BrickCheckVisitor(Configuration brickConfiguration) {
        super(brickConfiguration);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Void visitLedOnAction(LedOnAction<Void> ledOnAction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitLedOffAction(LedOffAction<Void> ledOffAction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitVoltageSensor(VoltageSensor<Void> voltageSensor) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitExternalLedOnAction(ExternalLedOnAction<Void> externalLedOnAction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitExternalLedOffAction(ExternalLedOffAction<Void> externalLedOffAction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitTemperatureSensor(TemperatureSensor<Void> temperatureSensor) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitAmbientLightSensor(AmbientLightSensor<Void> lightSensor) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitPIRMotionSensor(PIRMotionSensor<Void> motionSensor) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitJoystick(Joystick<Void> joystick) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitAccelerometer(Accelerometer<Void> accelerometer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitFlameSensor(FlameSensor<Void> flameSensor) {
        // TODO Auto-generated method stub
        return null;
    }

}