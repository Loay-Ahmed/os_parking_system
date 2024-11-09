package org.os;

import java.util.Arrays;
import java.util.Queue;

public class Gate {

    public Queue<String> cars;
    public Thread thread = new Thread();

    public Gate(String[] cars) {
        this.cars.addAll(Arrays.asList(cars));
        thread.start();
    }

    public void openGate() {
        String carString = cars.poll();

        Thread car = new Car(carString);
    }

    public String[] getCars() {
        return cars.toArray(new String[cars.size()]);
    }
}
