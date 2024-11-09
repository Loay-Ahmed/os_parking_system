package org.os;

public class Car extends Thread {
    private int ID;
    private int Time;
    private int GateNumber;

    public Car(int[] carData) {
        this.ID = ID;
        this.Time = Time;
        this.GateNumber = GateNumber;
    }


    public void run() {
        try {
            System.out.println(
                    "Thread " + Thread.currentThread().getName()
                            + ID);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}