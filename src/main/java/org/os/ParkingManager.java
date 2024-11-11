package org.os;

import java.util.concurrent.Semaphore;

import static java.lang.System.out;

public class ParkingManager extends Thread {

    public static int carsServed = 0;
    private Gate gate1, gate2, gate3;
    public Semaphore semaphore = new Semaphore(4);

    public ParkingManager(String[] gate1, String[] gate2, String[] gate3) {
        this.gate1 = new Gate(gate1, semaphore);
        this.gate2 = new Gate(gate2, semaphore);
        this.gate3 = new Gate(gate3, semaphore);
    }

    @Override
    public void run() {
        while (true) {
            if (carsServed == (gate1.cars.size() + gate2.cars.size() + gate3.cars.size())) {
                out.println(" Total Cars Served: " + carsServed +
                        """
                                Current Cars in Parking: 0 
                                Details:""" +
                        "- Gate 1 served " + gate1.cars.size() + "cars.\n- Gate 1 served " + gate2.cars.size() + "cars.\n- Gate 1 served " + gate3.cars.size() + "cars.");
                break;
            }
        }
    }
}
