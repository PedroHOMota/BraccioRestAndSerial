package ie.tus.moveBraccio;


import com.google.common.eventbus.EventBus;
import ie.tus.moveBraccio.event.QueueDataToSendEvent;
import ie.tus.moveBraccio.singleton.ConnectToArduinoSingleton;
import ie.tus.moveBraccio.singleton.EventBusSingleton;

import java.io.IOException;

public class MoveRobotService {

    private ConnectToArduino com;
    private final EventBus eventBus = EventBusSingleton.getEventBus();

    public MoveRobotService(){
        com = ConnectToArduinoSingleton.getInstance();
        eventBus.register(new WriteToArduinoEventListener());
    }

    public void write(Movement move) throws IOException, InterruptedException {
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

    public void sendEvent(){
        eventBus.post(new QueueDataToSendEvent(new Movement()));
    }
}
