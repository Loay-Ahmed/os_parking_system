package org.os;

import java.util.Queue;

public class Gate {

    public Queue<int[]> cars;
    public Thread thread = new Thread(this::run);

    public Gate(String[] cars) {
        for (int i = 0; i < cars.length; i++) {
            this.cars.add(parseString(cars[i]));
        }
        thread.start();
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

    public void openGate() {
        int[] carData = cars.poll();
        if (carData != null) {
            Thread car = new Car(carData);
            car.start();
        }
    }

    private void run() {
        int time = 0;
        while (true) {
            for (int[] car : cars) {
                if (car[2] == time) {
                    System.out.println("Car " + car[1] + " from Gate " + car[0] + " arrived at time " + car[2]);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
