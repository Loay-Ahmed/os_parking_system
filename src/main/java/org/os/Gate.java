package org.os;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Gate {

    public final Vector<int[]> cars = new Vector<>();
    private final Thread thread = new Thread(this::run);
    private int time = 0;
    private final Semaphore semaphore;

    public Gate(String[] data, Semaphore semaphore) {
        this.semaphore = semaphore;
        for (String car : data) {
            this.cars.add(parseString(car));
        }
        this.sort();
        thread.start();
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

    public synchronized void openGate() {
        if (!cars.isEmpty()) {
            int[] carData = cars.get(0);
            cars.remove(0);
            Thread carThread = new Car(carData, semaphore);
            System.out.println("Car " + carData[1] + " from Gate " + carData[0] +
                    " parked after waiting for " + (time - carData[2]) +
                    " units of time. (Parking Status: " + (4 - semaphore.availablePermits()) + " spots occupied)");
            carThread.start();
        }
    }

    private void run() {
        while (!cars.isEmpty()) {
            synchronized (this) {
                Iterator<int[]> iterator = cars.iterator();
                while (iterator.hasNext()) {
                    int[] car = iterator.next();
                    if (car[2] == time) {
                        System.out.println("Car " + car[1] + " from Gate " + car[0] +
                                " arrived at time " + car[2]);
                        try {
                            Thread.sleep(1000);
                            time++;
                            iterator.remove();  // Safely remove the car
                            break;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}
