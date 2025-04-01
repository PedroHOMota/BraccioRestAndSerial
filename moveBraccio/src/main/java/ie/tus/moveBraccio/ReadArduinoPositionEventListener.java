package ie.tus.moveBraccio;

import com.google.common.eventbus.Subscribe;
import ie.tus.moveBraccio.event.ArduinoReadyEvent;
import ie.tus.moveBraccio.event.QueueDataToSendEvent;
import ie.tus.moveBraccio.event.ReadArduinoPositionEvent;
import ie.tus.moveBraccio.singleton.ConnectToArduinoSingleton;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ReadArduinoPositionEventListener {

    @Subscribe
    public void SendRequestToReadPosition(ReadArduinoPositionEvent e) {


        try {
            Integer tst = 181;
            char c = 'µ';
            System.out.println("Got here; writting");
            ConnectToArduinoSingleton.getInstance().getOutputStream().write(c);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
