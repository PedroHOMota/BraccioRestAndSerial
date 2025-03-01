package ie.tus.moveBraccio.singleton;

import ie.tus.moveBraccio.ConnectToArduino;

public class ConnectToArduinoSingleton {

    private static ConnectToArduino instance = null;

    public static ConnectToArduino getInstance() {
        if(instance == null){
            instance = new ConnectToArduino("COM5");
        }
        return instance;
    }
}
