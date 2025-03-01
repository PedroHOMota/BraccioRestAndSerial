package ie.tus.moveBraccio;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.google.common.eventbus.EventBus;
import ie.tus.moveBraccio.event.ArduinoReadyEvent;
import ie.tus.moveBraccio.singleton.EventBusSingleton;

public class ComPortListener implements SerialPortDataListener {

    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }

    @Override
    public void serialEvent(SerialPortEvent event)
    {
        byte[] buffer = new byte[event.getSerialPort().bytesAvailable()];
        event.getSerialPort().readBytes(buffer, buffer.length);
        String s = new String(buffer);

        System.out.print(s.replace(">",""));
        if(s.contains(">")){
            States.ready = true;
            System.out.println("\nReceived ready char "+States.ready);
            EventBusSingleton.getEventBus().post(new ArduinoReadyEvent());
        }
    }
}