package org.os;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");

        String input;
        input = scanner.nextLine();

        String[] cars = {};

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            cars = reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            System.out.println("File not found -> " + input);
        }
        Arrays.stream(cars).sequential().forEach(System.out::println);

        /// Initialize each gate thread

        // Thread gate1 = new Gate();
        // Thread gate2 = new Gate();
        // Thread gate3 = new Gate();

        /// Start each gate

        // gate1.start(); gate2.start(); gate3.start();

        /// Join all gates

        // gate1.join(); gate2.join(); gate3.join();
    }
}