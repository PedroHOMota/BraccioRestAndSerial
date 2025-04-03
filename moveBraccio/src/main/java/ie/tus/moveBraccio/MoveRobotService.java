package ie.tus.moveBraccio;


import com.google.common.eventbus.EventBus;
import ie.tus.moveBraccio.event.QueueDataToSendEvent;
import ie.tus.moveBraccio.event.ReadArduinoPositionEvent;
import ie.tus.moveBraccio.singleton.ConnectToArduinoSingleton;
import ie.tus.moveBraccio.singleton.EventBusSingleton;

import java.io.IOException;
import java.util.ArrayList;
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
        eventBus.post(new QueueDataToSendEvent(move));
    }

    public RobotMotors readPosition() throws IOException, InterruptedException {
        eventBus.post(new ReadArduinoPositionEvent());
        String poll = null;

        for (int i = 0; i < 2 && poll == null; i++) {
            poll = States.dataFromArduino.poll(3, TimeUnit.SECONDS);
        }
        if (poll == null) {
            throw new IOException();
        }

        String[] move = poll.split(",");

        final RobotMotors robotMotors = convertToMovement(sanitize(move));
        //System.out.println("\n\n\nReading: " + robotMotors.toString());

        return robotMotors;
    }

    public void sendEvent(){
        eventBus.post(new QueueDataToSendEvent(new RobotMotors()));
    }

    private RobotMotors convertToMovement(ArrayList<Integer> move){
        final RobotMotors robotMotors = new RobotMotors();

        robotMotors.setBase(move.get(0));
        robotMotors.setShoulder(move.get(1));
        robotMotors.setElbow(move.get(2));
        robotMotors.setWristRotation(move.get(3));
        robotMotors.setWristVertical(move.get(4));
        robotMotors.setGripper(move.get(5));
        return robotMotors;
    }

    private ArrayList<Integer> sanitize(String[] data){
        ArrayList<Integer> arrayList = new ArrayList<>(data.length);
        for(String s : data){
            arrayList.add(Integer.parseInt(s.replace("\n","").replace("\r","")));
        }

        return arrayList;
    }
}
