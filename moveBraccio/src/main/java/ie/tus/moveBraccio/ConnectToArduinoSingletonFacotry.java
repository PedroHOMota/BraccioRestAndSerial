package ie.tus.moveBraccio;

public class ConnectToArduinoSingletonFacotry {

    private static ConnectToArduino instance = null;

    public static ConnectToArduino getInstance() {
        if(instance == null){
            instance = new ConnectToArduino("COM5");
        }
        return instance;
    }
}
