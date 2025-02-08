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
int arr[] = {0,0,0,0,0,0,0};
int counter = 0;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  Serial.println("<Arduino is ready>");
  //Braccio.begin();
}

void loop() {
  // put your main code here, to run repeatedly:
    recvOneChar();
}

void recvOneChar() {
    if (Serial.available() > 0) {
        //int i = Serial.parseInt();
        int i = Serial.read();
        Serial.println("counter: ");
        Serial.println(counter);
        Serial.println("number: ");
        Serial.println(i);
        arr[counter] = i;
        counter++;
        if(counter == 7){
          newData = true;
          counter = 0;
          Serial.println("Priting fullarray:");
          Serial.println(arr[0]);
          Serial.println(arr[1]);
          Serial.println(arr[2]);
          Serial.println(arr[3]);
          Serial.println(arr[5]);
          Serial.println(arr[6]);
          
          //moveBraccio(info[0],info[1],info[2],info[3],info[4],info[5],info[6])
          //Braccio.begin();
          //Braccio.ServoMovement(20,           0,  15, 180, 170, 0,  73);
          Serial.println("@")  
        }
        Serial.println(">");
        //receivedChar = Serial.read();

        
    }
}

   /*
   Step Delay: a milliseconds delay between the movement of each servo.  Allowed values from 10 to 30 msec.
   M1=base degrees. Allowed values from 0 to 180 degrees
   M2=shoulder degrees. Allowed values from 15 to 165 degrees
   M3=elbow degrees. Allowed values from 0 to 180 degrees
   M4=wrist vertical degrees. Allowed values from 0 to 180 degrees
   M5=wrist rotation degrees. Allowed values from 0 to 180 degrees
   M6=gripper degrees. Allowed values from 10 to 73 degrees. 10: the toungue is open, 73: the gripper is closed.
  */
void moveBraccio(int delay, int base, int shoulder, int elbow, int wristVertical, int wristRotation, int gripper){
  Braccio.ServoMovement(delay, base, shoulder, elbow, wristVertical, wristRotation,  gripper);  
  delay(1000);
}
