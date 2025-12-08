package org.jmitchell238.aoc.aoc2025.day00;

import static org.jmitchell238.aoc.aoc2025.utilities.Utilities2025.readFileAndProcessEachLine;
import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import org.jmitchell238.aoc.generalutilities.LogLevel;

@SuppressWarnings({"java:S106"})
public class Day00 {

    // Configuration flags
    private static final Boolean DEBUGGING = false;
    private static final Boolean VERBOSE = false;

    // Problem state
    private static boolean partTwo = false;

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        logOutput(LogLevel.INFO, DEBUGGING, "\n--- Day 00: Title ---\n");

        String input = "src/main/java/org/jmitchell238/aoc/aoc2025/day00/input.txt";
        String inputTest = "src/test/java/org/jmitchell238/aoc/aoc2025/day00/input_test_part1.txt";

        int partOneAnswer = part1(inputTest);
        logOutput(LogLevel.INFO, DEBUGGING, "Part 1: Answer: " + partOneAnswer);

        int partTwoAnswer = part2(inputTest);
        logOutput(LogLevel.INFO, DEBUGGING, "Part 2: Answer: " + partTwoAnswer);
    }

    public static int part1(String inputFile) {
        // Reset any problem state here
        resetState();

        // Call some method to find the answer that will process the input file inside of it.
        calculateAnswer(inputFile);

        return -1;
    }

    public static int part2(String inputFile) {
        // Reset any problem state here
        resetState();

        // Call some method to find the answer that will process the input file inside of it.
        calculateAnswer(inputFile);

        return -1;
    }

    /**
     * Read an input file and process each line with the provided action.
     */
    private static void calculateAnswer(String inputFile) {
        readFileAndProcessEachLine(inputFile, line -> {
            // Log the raw line if verbose logging is enabled
            logOutput(LogLevel.VERBOSE, VERBOSE, "Read line: " + line);

            // Parse the line if needed (e.g., "move north 10")

            // Log parsed values
            // logOutput(LogLevel.DEBUG, "Parsed ...");

            // Call processing method/s
            // e.g., processMove(direction, number);
        });
    }

    /**
     * Reset state to initial values for a fresh run.
     */
    private static void resetState() {
        partTwo = false;
    }
}
