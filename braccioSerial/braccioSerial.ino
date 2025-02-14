#include <Braccio.h>
#include <Servo.h>

Servo base;
Servo shoulder;
Servo elbow;
Servo wrist_rot;
Servo wrist_ver;
Servo gripper;

char receivedChar;
int arr[] = {0,0,0,0,0,0,0};
int counter = 0;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  Serial.println("<Arduino is ready>");
  Braccio.begin();
}

void loop() {
  // put your main code here, to run repeatedly:
    recvOneChar();
}

void recvOneChar() {
    if (Serial.available() > 0) {
        //int i = Serial.parseInt();
        int i = Serial.read();
        // Serial.println("counter: ");
        // Serial.println(counter);
        // Serial.println("number: ");
        // Serial.println(i);
        arr[counter] = i;
        counter++;
        if(counter == 7){
          counter = 0;
          Serial.println("Priting fullarray:");
          
          
          moveBraccio(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6]);
          //Braccio.begin();
          //Braccio.ServoMovement(20,           0,  15, 180, 170, 0,  73);
          
        }
        Serial.println(">");
    }
}


void moveBraccio(int delay, int base, int shoulder, int elbow, int wristVertical, int wristRotation, int gripper){
  Serial.println(delay);
          Serial.println(base);
          Serial.println(shoulder);
          Serial.println(elbow);
          Serial.println(wristVertical);
          Serial.println(wristRotation);
          Serial.println("gripper");
          Serial.println(gripper);
  Braccio.ServoMovement(delay, base, shoulder, elbow, wristVertical, wristRotation,  gripper);
  Serial.println("@");  
  //delay(500);
}
