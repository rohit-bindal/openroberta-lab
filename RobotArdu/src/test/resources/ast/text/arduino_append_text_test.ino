// This file is automatically generated by the Open Roberta Lab.

#include <Arduino.h>
#include <math.h>
#include <RobertaFunctions/NEPODefs.h>


double ___item;
bool ___item2;
String ___item3;
unsigned int ___item4;
int _led_L = 13;
void setup()
{
    Serial.begin(9600); 
    pinMode(_led_L, OUTPUT);
    ___item = 0;
    ___item2 = true;
    ___item3 = "";
    ___item4 = RGB(0xFF, 0xFF, 0xFF);
}

void loop()
{
    ___item3 += String(___item);
    ___item3 += String(___item2);
    ___item3 += ___item3;
    ___item3 += String(___item4);
}
