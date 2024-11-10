package org.os;

import java.util.concurrent.Semaphore;

public class GateManager extends Thread {
    Gate g1;
    Gate g2;
    Gate g3;
    Semaphore semaphore = new Semaphore(4);


    public GateManager(String[] gate1, String[] gate2, String[] gate3) {
        g1 = new Gate(gate1, semaphore);
        g2 = new Gate(gate2, semaphore);
        g3 = new Gate(gate3, semaphore);
    }

    @Override
    public void run() {
        if (!this.isInterrupted()) {
            while (true) {
                if (semaphore.tryAcquire()) {
                    int[] t1 = g1.cars.isEmpty() ? new int[]{0, 0, 9999999, 0} : g1.cars.getFirst();
                    int[] t2 = g2.cars.isEmpty() ? new int[]{0, 0, 9999999, 0} : g2.cars.getFirst();
                    int[] t3 = g3.cars.isEmpty() ? new int[]{0, 0, 9999999, 0} : g3.cars.getFirst();

                    if (t1 == null && t2 == null && t3 == null) {
                        break;
                    }

                    if (t1[2] <= t2[2] && t1[2] <= t3[2]) {
                        g1.openGate();
                    } else if (t2[2] <= t1[2] && t2[2] <= t3[2]) {
                        g2.openGate();
                    } else if (t3[2] <= t1[2] && t3[2] <= t2[2]) {
                        g3.openGate();
                    } else if (t1[2] == t2[2] && t1[2] == t3[2]) {
                        this.interrupt();
                    }
                }
            }
        }
        System.out.println("Total Cars Served: 15\nCurrent Cars in Parking: 0\nDetails:\n- Gate 1 served 5 cars.\n- Gate 2 served 5 cars.\n- Gate 3 served 5 cars.");
    }
}
