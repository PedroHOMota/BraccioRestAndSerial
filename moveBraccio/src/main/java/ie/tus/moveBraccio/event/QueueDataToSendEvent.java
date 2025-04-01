package ie.tus.moveBraccio.event;

import ie.tus.moveBraccio.RobotMotors;

public class QueueDataToSendEvent {
    private final RobotMotors robotMotors;

    public QueueDataToSendEvent(RobotMotors move) {
        this.robotMotors = move;
    }

    public RobotMotors getMovement(){
        return robotMotors;
    }

}
