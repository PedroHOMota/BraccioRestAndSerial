#include <Braccio.h>
#include <Servo.h>

Servo base;
Servo shoulder;
Servo elbow;
Servo wrist_rot;
Servo wrist_ver;
Servo gripper;

char receivedChar;
boolean newData = false;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  Serial.println("<Arduino is ready>");
  

}

void loop() {
  // put your main code here, to run repeatedly:
    recvOneChar();
    showNewData();
}

void recvOneChar() {
    if (Serial.available() > 0) {
        receivedChar = Serial.read();
        if(receivedChar == '@'){
          Serial.print("passed check ");
          Braccio.begin();
          Braccio.ServoMovement(20,           0,  15, 180, 170, 0,  73);  
        }
        newData = true;
    }
}

void showNewData() {
    if (newData == true) {
        Serial.print("This just in ... ");
        Serial.println(receivedChar);
        newData = false;
    }
}

void moveBraccio(int delay, int base, int shoulder, int elbow, int wristVertical, int wristRotation, int gripper){
  Braccio.ServoMovement(delay,           base,  shoulder, elbow, wristVertical, wristRotation,  gripper);  
  //delay(1000);
}
