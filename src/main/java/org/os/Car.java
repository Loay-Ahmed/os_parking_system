package org.os;

import java.util.concurrent.Semaphore;

public class Car extends Thread {
    private int ID;
    private int Time;
    private int GateNumber;
    private Semaphore semaphore;

    public Car(int[] carData, Semaphore semaphore) {
        ID = carData[1];
        Time = carData[3];
        GateNumber = carData[0];
        this.semaphore = semaphore;
    }


    public void run() {
        try {
            System.out.println("Car " + ID + " from Gate " + GateNumber + " left after " + Time + " units of time.");
            Thread.sleep((Time * 1000));
            this.semaphore.release();
        } catch (Exception e) {
            System.out.println("Exception is caught");

        }
    }
}