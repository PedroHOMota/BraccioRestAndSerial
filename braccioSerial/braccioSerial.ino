#include <Braccio.h>
#include <Servo.h>

Servo base;
Servo shoulder;
Servo elbow;
Servo wrist_rot;
Servo wrist_ver;
Servo gripper;

int arr[] = {0,0,0,0,0,0,0};
int counter = 0;
void setup() {
  Serial.begin(9600);
  Serial.println("<Arduino is ready>");
  Braccio.begin();
}

void loop() {
    reciveData();
}

void reciveData() {
    if (Serial.available() > 0) {
        int i = Serial.read();
        arr[counter] = i;
        counter++;
        if(counter == 7){
          counter = 0;
          moveBraccio(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6]);
        }
        Serial.println(">");
    }
}


void moveBraccio(int delay, int base, int shoulder, int elbow, int wristVertical, int wristRotation, int gripper){
  Braccio.ServoMovement(delay, base, shoulder, elbow, wristVertical, wristRotation,  gripper);
  delay(500);
}
