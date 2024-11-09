package org.os;

public class Car extends Thread {
    private int ID;
    private int Time;
    private int GateNumber;

    public Car(String carString) {
        int[] carData = parseString(carString);
        this.ID = ID;
        this.Time = Time;
        this.GateNumber = GateNumber;
    }

    private int[] parseString(String carString) {
        // Gate 1, Car 0, Arrive 0, Parks 3
        String[] carData = carString.split(",");
        int[] carValues = new int[4];
        for (int i = 0; i < carData.length; i++) {
            int carVal = Integer.parseInt(carData[i].substring(carData[i].length() - 1));
            carValues[i] = carVal;
        }
        return carValues;
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