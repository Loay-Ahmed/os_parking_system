package org.os;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");

        String input = scanner.nextLine();
        List<String> Gate1Cars = new ArrayList<>();
        List<String> Gate2Cars = new ArrayList<>();
        List<String> Gate3Cars = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Gate 1")) {
                    Gate1Cars.add(line);
                }
                if (line.startsWith("Gate 2")) {
                    Gate2Cars.add(line);
                }
                if (line.startsWith("Gate 3")) {
                    Gate3Cars.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("File not found -> " + input);
            return;
        }
        String[] gate1Array = Gate1Cars.toArray(new String[Gate1Cars.toArray().length]);
        String[] gate2Array = Gate2Cars.toArray(new String[Gate2Cars.toArray().length]);
        String[] gate3Array = Gate3Cars.toArray(new String[Gate3Cars.toArray().length]);
        Thread gateManager = new GateManager(gate1Array, gate2Array, gate3Array);
        gateManager.start();

        try {
            gateManager.join();
        } catch (InterruptedException e) {
            System.out.println("Gate interrupted.");
        }
        scanner.close();

    }
}