package org.jmitchell238.aoc.aoc2024.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Day01 {
    private static final Boolean DEBUGGING = false;
    private static final Boolean VERBOSE = false;

    public static void main(String[] args) throws FileNotFoundException {
        Day01Run();
    }

    public static void Day01Run() throws FileNotFoundException {
        System.out.println("\n--- Day 01 Scratchcards ---\n");

        String input = "src/main/java/org/jmitchell238/aoc/aoc2024/day01/input.txt";
        String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2024/day01/input_test_part1.txt";

        int partOneAnswer = part1(inputTest);
        System.out.printf("Part 1: Answer: %d%n", partOneAnswer);

        //        int partTwoAnswer = part2(inputTest);
        //        System.out.printf("Part 2: Answer: %d%n", partTwoAnswer);
    }

    public static int part1(String input) throws FileNotFoundException {
        var inputFile = new File(input);
        try (Scanner scanner = new Scanner(inputFile)) {

            // Initialize lists to store parsed integers
            List<Integer> left = new java.util.ArrayList<>();
            List<Integer> right = new java.util.ArrayList<>();

            if (DEBUGGING) System.out.println("Reading input file: " + input);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (Boolean.TRUE.equals(DEBUGGING)) System.out.println("Processing line: '" + line + "'");

                // If the line is empty, skip it
                if (line.isEmpty()) {
                    if (Boolean.TRUE.equals(DEBUGGING)) System.out.println("Skipping empty line");
                    continue;
                }

                // Split line into parts
                String[] parts = line.split("\\s+");
                if (Boolean.TRUE.equals(DEBUGGING))
                    System.out.printf("Split parts: %s%n", java.util.Arrays.toString(parts));

                // Ensure the line has at least two parts
                if (parts.length < 2) {
                    if (Boolean.TRUE.equals(DEBUGGING))
                        System.err.printf("Invalid line format (expected at least 2 parts): '%s'%n", line);
                    continue;
                }

                try {
                    // Parse integers and add to the lists
                    left.add(Integer.parseInt(parts[0]));
                    right.add(Integer.parseInt(parts[1]));
                } catch (NumberFormatException e) {
                    if (Boolean.TRUE.equals(DEBUGGING)) {
                        System.err.printf("Error parsing numbers from line: '%s'%n", line);
                        e.printStackTrace();
                    }
                }
            }

            // Sort the lists
            left.sort(java.util.Comparator.naturalOrder());
            right.sort(java.util.Comparator.naturalOrder());

            // Debugging output for parsed lists
            if (Boolean.TRUE.equals(DEBUGGING)) {
                System.out.println("Left list: " + left);
                System.out.println("Right list: " + right);
            }

            int total = 0;

            // Calculate the total difference
            for (int i = 0; i < left.size(); i++) {
                int diff = Math.abs(left.get(i) - right.get(i));
                if (Boolean.TRUE.equals(DEBUGGING))
                    System.out.printf("Difference for index %d: |%d - %d| = %d%n", i, left.get(i), right.get(i), diff);
                total += diff;
            }

            System.out.println("Total difference: " + total);

            return total;

        } catch (FileNotFoundException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public static int part2(String input) {
        return -1;
    }
}
