package ie.tus.moveBraccio.event;

import ie.tus.moveBraccio.Movement;

public class QueueDataToSendEvent {
    private final Movement movement;

    public QueueDataToSendEvent(Movement move) {
        this.movement = move;
    }

    public Movement getMovement(){
        return movement;
    }

}
