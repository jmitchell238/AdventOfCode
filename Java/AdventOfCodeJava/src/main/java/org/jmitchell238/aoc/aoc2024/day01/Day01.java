package org.jmitchell238.aoc.aoc2024.day01;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logException;
import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import org.jmitchell238.aoc.generalutilities.LogLevel;

@SuppressWarnings({"java:S106", "java:S1940", "java:S2589", "java:S1118"})
public class Day01 {
    // Configuration flags
    @SuppressWarnings({"ConstantConditions", "unused"})
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings({"ConstantConditions", "unused"})
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    @SuppressWarnings("unused")
    static void main(String[] args) throws FileNotFoundException {
        day01Run();
    }

    @SuppressWarnings("java:S100")
    public static void day01Run() throws FileNotFoundException {
        logOutput(LogLevel.INFO, true, "\n--- Day 1: Historian Hysteria ---\n");

        String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2024/day01/input_test_part1.txt";

        int partOneAnswer = part1(inputTest);
        logOutput(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        int partTwoAnswer = part2(inputTest);
        logOutput(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    @SuppressWarnings("unused")
    public static int part1(String input) throws FileNotFoundException {
        var inputFile = new File(input);
        try (Scanner scanner = new Scanner(inputFile)) {

            // Initialize lists to store parsed integers
            List<Integer> left = new java.util.ArrayList<>();
            List<Integer> right = new java.util.ArrayList<>();

            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Reading input file: " + input);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Processing line: '" + line + "'");

                // Process non-empty lines with valid format
                if (!line.isEmpty()) {
                    // Split line into parts
                    String[] parts = line.split("\\s+");
                    logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Split parts: " + java.util.Arrays.toString(parts));

                    // Ensure the line has at least two parts
                    if (parts.length >= 2) {
                        parseAndAddNumbers(line, parts, left, right);
                    } else {
                        logOutput(
                                LogLevel.ERROR,
                                true,
                                "Invalid line format (expected at least 2 parts): '" + line + "'");
                    }
                } else {
                    logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Skipping empty line");
                }
            }

            // Sort the lists
            left.sort(java.util.Comparator.naturalOrder());
            right.sort(java.util.Comparator.naturalOrder());

            // Debugging output for parsed lists
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Left list: " + left);
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Right list: " + right);

            int total = 0;

            // Calculate the total difference
            for (int i = 0; i < left.size(); i++) {
                int diff = Math.abs(left.get(i) - right.get(i));
                logOutput(
                        LogLevel.DEBUG,
                        ENABLE_DEBUG_LOGGING,
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

    @SuppressWarnings("unused")
    public static int part2(String input) {
        return -1;
    }

    private static void parseAndAddNumbers(String line, String[] parts, List<Integer> left, List<Integer> right) {
        try {
            // Parse integers and add to the lists
            left.add(Integer.parseInt(parts[0]));
            right.add(Integer.parseInt(parts[1]));
        } catch (NumberFormatException e) {
            String errorMessage = "Error parsing numbers from line: '" + line + "'";
            logOutput(LogLevel.ERROR, true, errorMessage);
            logException(e);
        }
    }
}
