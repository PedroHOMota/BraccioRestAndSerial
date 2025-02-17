package ie.tus.moveBraccio;


import java.io.IOException;

public class InteractService {

    ConnectToArduino com;

    public InteractService(){
        com = ConnectToArduinoSingletonFacotry.getInstance();
    }

    public void write(Movement move) throws IOException, InterruptedException {
        int[] data = {
                move.getDelay(),
                move.getBase(),
                move.getElbow(),
                move.getShoulder(),
                move.getWristVertical(),
                move.getWristRotation(),
                move.getGripper()
        };

        com.writeToBraccio(data);
    }
}
