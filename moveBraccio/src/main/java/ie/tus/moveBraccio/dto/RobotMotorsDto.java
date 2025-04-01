package ie.tus.moveBraccio.dto;

import ie.tus.moveBraccio.RobotMotors;

public class RobotMotorsDto {
    final RobotMotors intialPosition;
    final RobotMotors finalPosition;

    public RobotMotorsDto(RobotMotors initial,RobotMotors finalPosition){
        this.finalPosition = finalPosition;
        this.intialPosition = initial;
    }

    public RobotMotors getIntialPosition() {
        return intialPosition;
    }

    public RobotMotors getFinalPosition() {
        return finalPosition;
    }

}
