// This file is automatically generated by the Open Roberta Lab.

#include <Arduino.h>
#include <math.h>
#include <Servo/src/Servo.h>
#include <RobertaFunctions/NEPODefs.h>


Servo _servo_S;
void setup()
{
    Serial.begin(9600); 
    _servo_S.attach(8);
}

void loop()
{
    _servo_S.write(90);
}