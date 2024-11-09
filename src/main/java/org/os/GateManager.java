package org.os;

import java.util.concurrent.Semaphore;

public class GateManager extends Thread {
    Gate g1;
    Gate g2;
    Gate g3;

    public GateManager(String[] cars1, String[] cars2, String[] cars3) {
        g1 = new Gate(cars1);
        g2 = new Gate(cars2);
        g3 = new Gate(cars3);
    }

    @Override
    public void run() {

        Semaphore ss = new Semaphore(4);
        while (true) {
            if (ss.tryAcquire()) {
                int t1 = g1.cars.peek()[2];
                int t2 = g2.cars.peek()[2];
                int t3 = g3.cars.peek()[2];

                if (t1 <= t2 && t1 <= t3) {
                    g1.openGate();
                } else if (t2 <= t1 && t2 <= t3) {
                    g2.openGate();
                } else {
                    g3.openGate();
                }

            }
        }
    }
}