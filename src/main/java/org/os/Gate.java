package org.os;

import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Gate extends Thread {

    public final Vector<int[]> cars = new Vector<>();
    //    private final Thread thread = new Thread(this::run);
    private int time = 0;
    private final Semaphore semaphore;

    public Gate(String[] data, Semaphore semaphore) {
        this.semaphore = semaphore;
        for (String car : data) {
            this.cars.add(parseString(car));
        }
        this.sort();
//        thread.start();
    }

    private void sort() {
        cars.sort((o1, o2) -> Integer.compare(o1[2], o2[2]));
    }

    private int[] parseString(String carString) {
        // Example input: "Gate 1, Car 0, Arrive 0, Parks 3"
        String[] carData = carString.split(",");
        int[] carValues = new int[4];
        for (int i = 0; i < carData.length; i++) {
            String[] car = carData[i].trim().split(" ");
            int carVal = Integer.parseInt(car[car.length - 1]);
            carValues[i] = carVal;
        }
        return carValues;
    }

    @Override
    public void run() {
        for (int[] car : this.cars) {
            Thread carThread = new Car(car, semaphore);
            carThread.start();
        }
    }
}
