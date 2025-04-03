package ie.tus.moveBraccio;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.google.common.eventbus.EventBus;
import ie.tus.moveBraccio.event.ArduinoReadyEvent;
import ie.tus.moveBraccio.singleton.EventBusSingleton;

import java.util.concurrent.TimeUnit;

public class ComPortListener implements SerialPortDataListener {

    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }

    private String temp="";


    @Override
    public void serialEvent(SerialPortEvent event)
    {
        byte[] buffer = new byte[event.getSerialPort().bytesAvailable()];
        event.getSerialPort().readBytes(buffer, buffer.length);
        String s = new String(buffer);
        temp += s;
        System.out.print(s);

        if(s.contains(">")){
            States.ready = true;
            System.out.println("\nReceived ready char "+States.ready);
            EventBusSingleton.getEventBus().post(new ArduinoReadyEvent());
        }
        if(s.contains("/")) {
            System.out.println("\nGOT HERE: "+temp.substring(temp.lastIndexOf("@") + 1, temp.lastIndexOf("/") - 1));
            try {
                boolean offer = States.dataFromArduino.offer(temp.substring(temp.lastIndexOf("@") + 1, temp.lastIndexOf("/") - 1),
                        1, TimeUnit.SECONDS);
                System.out.println("\nOFFER: "+offer);
            } catch (Exception e){
                System.out.println("\n\n FAILED");
            } finally {
                temp = "";
            }
        }
    }
}