package org.jmitchell238.aoc.aoc2024.day01;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logException;
import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import org.jmitchell238.aoc.generalutilities.LogLevel;

@SuppressWarnings({"java:S106"})
public class Day01 {
    private static final boolean DEBUGGING = false;
    private static final boolean VERBOSE = false;

    public static void main(String[] args) throws FileNotFoundException {
        Day01Run();
    }

    public static void Day01Run() throws FileNotFoundException {
        String day4 = "\n--- Day 4: Scratchcards ---\n";
        logOutput(LogLevel.INFO, true, day4);

        String input = "src/main/java/org/jmitchell238/aoc/aoc2024/day01/input.txt";
        String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2024/day01/input_test_part1.txt";

        int partOneAnswer = part1(inputTest);
        logOutput(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        // int partTwoAnswer = part2(inputTest);
        // logOutput(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    public static int part1(String input) throws FileNotFoundException {
        var inputFile = new File(input);
        try (Scanner scanner = new Scanner(inputFile)) {

            // Initialize lists to store parsed integers
            List<Integer> left = new java.util.ArrayList<>();
            List<Integer> right = new java.util.ArrayList<>();

            logOutput(LogLevel.DEBUG, DEBUGGING, "Reading input file: " + input);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                logOutput(LogLevel.DEBUG, DEBUGGING, "Processing line: '" + line + "'");

                // If the line is empty, skip it
                if (line.isEmpty()) {
                    logOutput(LogLevel.DEBUG, DEBUGGING, "Skipping empty line");
                    continue;
                }

                // Split line into parts
                String[] parts = line.split("\\s+");
                logOutput(LogLevel.DEBUG, DEBUGGING, "Split parts: " + java.util.Arrays.toString(parts));

                // Ensure the line has at least two parts
                if (parts.length < 2) {
                    logOutput(LogLevel.ERROR, true, "Invalid line format (expected at least 2 parts): '" + line + "'");
                    continue;
                }

                try {
                    // Parse integers and add to the lists
                    left.add(Integer.parseInt(parts[0]));
                    right.add(Integer.parseInt(parts[1]));
                } catch (NumberFormatException e) {
                    if (DEBUGGING) {
                        String errorMessage = "Error parsing numbers from line: '" + line + "'";
                        logOutput(LogLevel.ERROR, true, errorMessage);
                        logException(e);
                    }
                }
            }

            // Sort the lists
            left.sort(java.util.Comparator.naturalOrder());
            right.sort(java.util.Comparator.naturalOrder());

            // Debugging output for parsed lists
            logOutput(LogLevel.DEBUG, DEBUGGING, "Left list: " + left);
            logOutput(LogLevel.DEBUG, DEBUGGING, "Right list: " + right);

            int total = 0;

            // Calculate the total difference
            for (int i = 0; i < left.size(); i++) {
                int diff = Math.abs(left.get(i) - right.get(i));
                logOutput(
                        LogLevel.DEBUG,
                        DEBUGGING,
                        "Difference for index " + i + ": |" + left.get(i) + " - " + right.get(i) + "| = " + diff);
                total += diff;
            }

            logOutput(LogLevel.INFO, true, "Total difference: " + total);

            return total;

        } catch (FileNotFoundException e) {
            String errorMessage = "Error reading input file: " + e.getMessage();
            logOutput(LogLevel.ERROR, true, errorMessage);
            logException(e);
            return -1;
        }
    }

    public static int part2(String input) {
        return -1;
    }
}
