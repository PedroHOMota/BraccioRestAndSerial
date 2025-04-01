package ie.tus.moveBraccio;


import com.google.common.eventbus.EventBus;
import ie.tus.moveBraccio.event.QueueDataToSendEvent;
import ie.tus.moveBraccio.event.ReadArduinoPositionEvent;
import ie.tus.moveBraccio.singleton.ConnectToArduinoSingleton;
import ie.tus.moveBraccio.singleton.EventBusSingleton;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MoveRobotService {

    private ConnectToArduino com;
    private final EventBus eventBus = EventBusSingleton.getEventBus();

    public MoveRobotService(){
        com = ConnectToArduinoSingleton.getInstance();
        eventBus.register(new WriteToArduinoEventListener());
        eventBus.register(new ReadArduinoPositionEventListener());
    }

    public void write(RobotMotors move) throws IOException, InterruptedException {
//        int[] data = {
//                move.getDelay(),
//                move.getBase(),
//                move.getElbow(),
//                move.getShoulder(),
//                move.getWristVertical(),
//                move.getWristRotation(),
//                move.getGripper()
//        };

        eventBus.post(new QueueDataToSendEvent(move));
        //com.writeToBraccio(data);
    }

    public RobotMotors readPosition() throws IOException, InterruptedException {
        eventBus.post(new ReadArduinoPositionEvent());
        String[] move = States.dataFromArduino.poll(1, TimeUnit.SECONDS).split(",");

        final RobotMotors robotMotors = convertToMovement(move);
        System.out.println("\n\n\nReading: "+robotMotors.toString());

        return robotMotors;
    }

    public void sendEvent(){
        eventBus.post(new QueueDataToSendEvent(new RobotMotors()));
    }

    private RobotMotors convertToMovement(String[] move){
        final RobotMotors robotMotors = new RobotMotors();

        robotMotors.setBase(Integer.parseInt(move[0]));
        robotMotors.setShoulder(Integer.parseInt(move[1]));
        robotMotors.setElbow(Integer.parseInt(move[2]));
        robotMotors.setWristRotation(Integer.parseInt(move[3]));
        robotMotors.setWristVertical(Integer.parseInt(move[4]));
        robotMotors.setGripper(Integer.parseInt(move[5]));

        return robotMotors;
    }
}
