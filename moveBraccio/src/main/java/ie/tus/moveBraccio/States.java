package ie.tus.moveBraccio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class States {
    static boolean ready = false;
    static boolean finished = false;

    final static BlockingQueue<String> dataFromArduino = new ArrayBlockingQueue<>(5);
}
