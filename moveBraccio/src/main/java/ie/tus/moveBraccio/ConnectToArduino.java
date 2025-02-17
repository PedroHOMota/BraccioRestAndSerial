package ie.tus.moveBraccio;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectToArduino {

    private String port;
    InputStream inputStream;
    OutputStream outputStream;
    public ConnectToArduino(String port){
        this.port = port;

        System.out.println("\n\n\n\nStarting listener\n\n\n\n");
        SerialPort comm5 = SerialPort.getCommPort("COM5");
        comm5.openPort();
        inputStream = comm5.getInputStream();
        outputStream = comm5.getOutputStream();

        ComPortListener listener = new ComPortListener();
        comm5.addDataListener(listener);
    }



    public InputStream getInputStream(){
        return inputStream;
    }

    public OutputStream getOutputStream(){
        return outputStream;
    }

    public void writeToBraccio(int[] data) throws IOException, InterruptedException {
        int i = 0;

        while (true) {
            if (States.ready) {
                States.ready = false;
                System.out.println("Sending: " + data[i]);
                outputStream.write(data[i]);
                i++;
            }
            if (i > 6 || States.finished) {
                Thread.sleep(1000);
                States.finished = false;
                States.ready = false;
                System.out.println("clearing "+States.finished+" readr: "+States.ready);
                return;
            }

            Thread.sleep(100);
        }
    }


}
