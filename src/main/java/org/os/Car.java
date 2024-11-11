package org.os;

import java.util.concurrent.Semaphore;

public class Car extends Thread {
    private int[] carData;
    private Semaphore semaphore;

    public Car(int[] carData, Semaphore semaphore) {
        this.carData = carData;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((carData[2] * 1000L));
            System.out.println("Car " + carData[1] + " from Gate " + carData[0] +
                    " arrived at time " + carData[2]);
            int time = 0;
            while (true) {
                if (semaphore.tryAcquire()) {
                    System.out.println("Car " + carData[1] + " from Gate " + carData[0] +
                            " parked " + (time > 0 ? ("after waiting for " + time +
                            " units of time. ") : "") + "(Parking Status: " + (4 - semaphore.availablePermits()) + " spots occupied)");

                    sleep(carData[3] * 1000L);
                    System.out.println("Car " + carData[1] + " from Gate " + carData[0] + " left after " + carData[3] + " units of time.");
                    this.semaphore.release();
                    return;
                } else {
                    sleep(1000);
                    time++;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}