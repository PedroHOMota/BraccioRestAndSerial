package ie.tus.moveBraccio;

import com.google.common.eventbus.Subscribe;
import ie.tus.moveBraccio.event.ArduinoReadyEvent;
import ie.tus.moveBraccio.event.QueueDataToSendEvent;
import ie.tus.moveBraccio.singleton.ConnectToArduinoSingleton;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WriteToArduinoEventListener {

    BlockingQueue<RobotMotors> dataToProcess = new LinkedBlockingQueue<>();
    Queue<Integer> dataProcessing = new LinkedList<>();
    @Subscribe
    public void writeToArduino(ArduinoReadyEvent e){
        if(dataProcessing.isEmpty()){
            moveToDataProcessingQueue();
            return;
        }

        Integer dataToSend = dataProcessing.poll();
        if(dataToSend == null){
            System.out.println("No data to process");
        } else {
            try {
                ConnectToArduinoSingleton.getInstance().getOutputStream().write(dataToSend);
            } catch (Exception ex){
                System.out.println(ex);
            }
        }

    }


    @Subscribe
    public void addToQueue(QueueDataToSendEvent event){
        try {
            dataToProcess.offer(event.getMovement(), 15, TimeUnit.SECONDS);
            if(dataProcessing.isEmpty()){
                moveToDataProcessingQueue();
            }
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }


    private void moveToDataProcessingQueue(){
        RobotMotors move = dataToProcess.poll();
        if(move == null){
            System.out.println("No queued data");
            return;
        }

        dataProcessing.add(move.getDelay());
        dataProcessing.add(move.getBase());
        dataProcessing.add(move.getShoulder());
        dataProcessing.add(move.getElbow());
        dataProcessing.add(move.getWristVertical());
        dataProcessing.add(move.getWristRotation());
        dataProcessing.add(move.getGripper());

        writeToArduino(null);
    }
}
