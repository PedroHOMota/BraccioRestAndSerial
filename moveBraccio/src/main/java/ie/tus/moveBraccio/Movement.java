package ie.tus.moveBraccio;

/*
Step Delay: a milliseconds delay between the movement of each servo.  Allowed values from 10 to 30 msec.
M1=base degrees. Allowed values from 0 to 180 degrees
M2=shoulder degrees. Allowed values from 15 to 165 degrees
M3=elbow degrees. Allowed values from 0 to 180 degrees
M4=wrist vertical degrees. Allowed values from 0 to 180 degrees
M5=wrist rotation degrees. Allowed values from 0 to 180 degrees
M6=gripper degrees. Allowed values from 10 to 73 degrees. 10: the toungue is open, 73: the gripper is closed.
*/

//Initialization functions and set up the initial position for Braccio
//All the servo motors will be positioned in the "safety" position:
//Base (M1):90 degrees
//Shoulder (M2): 45 degrees
//Elbow (M3): 180 degrees
//Wrist vertical (M4): 180 degrees
//Wrist rotation (M5): 90 degrees
//gripper (M6): 10 degrees
public class Movement {

    private int delay=0;
    private int base=0;
    private int shoulder=0;
    private int elbow=0;
    private int wristVertical=0;
    private int wristRotation=0;
    private int gripper=10;


    public int getGripper() {
        return gripper;
    }

    public void setGripper(int gripper) {
        this.gripper = gripper;
    }

    public int getWristRotation() {
        return wristRotation;
    }

    public void setWristRotation(int wristRotation) {
        this.wristRotation = wristRotation;
    }

    public int getWristVertical() {
        return wristVertical;
    }

    public void setWristVertical(int wristVertical) {
        this.wristVertical = wristVertical;
    }

    public int getElbow() {
        return elbow;
    }

    public void setElbow(int elbow) {
        this.elbow = elbow;
    }

    public int getShoulder() {
        return shoulder;
    }

    public void setShoulder(int shoulder) {
        this.shoulder = shoulder;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
