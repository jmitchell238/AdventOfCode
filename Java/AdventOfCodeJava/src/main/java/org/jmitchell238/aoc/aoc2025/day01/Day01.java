package org.jmitchell238.aoc.aoc2025.day01;

import static org.jmitchell238.aoc.aoc2025.utilities.Utilities2025.log;
import static org.jmitchell238.aoc.aoc2025.utilities.Utilities2025.readFileAndProcessEachLine;

import org.jmitchell238.aoc.aoc2025.utilities.LogLevel;

/**
 * Advent of Code 2025 - Day 1: Secret Entrance
 * <p>
 * Solution for tracking how many times the dial points to zero after rotations,
 * and how many times it crosses zero during a rotation in Part 2.
 * </p>
 */
@SuppressWarnings({"java:S106"})
public class Day01 {

    // Configuration flags
    private static final boolean DEBUGGING = false;
    private static final boolean VERBOSE = false;

    // Problem state
    private static boolean partTwo = false;
    private static int dialValue = 50;
    private static int timesHittingZero = 0;

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        run();
    }

    /**
     * Entry point for running both parts against an input file.
     */
    public static void run() {
        System.out.println("\n--- Day 01: Secret Entrance ---\n");

        String input = "src/main/java/org/jmitchell238/aoc/aoc2025/day01/input.txt";
        String inputTest = "src/test/java/org/jmitchell238/aoc/aoc2025/day01/input_test_part1.txt";

        int partOneAnswer = part1(input);
        System.out.println("Part 1: Answer: " + partOneAnswer);

        int partTwoAnswer = part2(inputTest);
        System.out.println("Part 2: Answer: " + partTwoAnswer);
    }

    /**
     * Part 1: Count landings on zero.
     */
    public static int part1(String inputFile) {
        resetState();
        calculateAnswer(inputFile);
        return timesHittingZero;
    }

    /**
     * Part 2: Count landings on zero plus crossings through zero during rotation.
     */
    public static int part2(String inputFile) {
        resetState();
        partTwo = true;
        calculateAnswer(inputFile);
        return timesHittingZero;
    }

    /**
     * Read an input file and process each dial movement command.
     */
    private static void calculateAnswer(String inputFile) {
        readFileAndProcessEachLine(inputFile, line -> {
            log(LogLevel.VERBOSE, VERBOSE, "Read line: " + line);

            // Parse a command like "L68" or "R42": first char is direction, rest is distance.
            char direction = line.charAt(0);
            int number = Integer.parseInt(line.substring(1));

            log(LogLevel.DEBUG, DEBUGGING, "Parsed direction: " + direction + ", number: " + number);

            processMove(direction, number);
        });
    }

    /**
     * Apply a single movement (e.g., "L68" or "R42") to the dial.
     */
    private static void processMove(char direction, int number) {
        log(LogLevel.VERBOSE, VERBOSE, "Processing move: " + direction + number);
        log(LogLevel.VERBOSE, VERBOSE, "Dial before move: " + dialValue);

        int startValue = dialValue;
        // Subtract for left (L), add for right (R).
        dialValue += (direction == 'L') ? -number : number;
        log(LogLevel.VERBOSE, VERBOSE, "Dial after move: " + dialValue);

        // Part 2: count zero crossings during the raw movement.
        int boundariesCrossed = partTwo ? calculateZeroCrossings(startValue, dialValue) : 0;

        // Keep value within 0–99.
        normalizeDial();

        // Update counters based on landing/crossing.
        checkIfDialIsAtZero(boundariesCrossed);
    }

    /**
     * Count how many times the dial passes through zero during a raw move.
     */
    private static int calculateZeroCrossings(int startValue, int endValue) {
        log(LogLevel.VERBOSE, VERBOSE, "Calculating boundary crossings for Part 2");

        int crossings = 0;

        if (crossedFromPositiveToNegative(startValue, endValue)) {
            crossings = 1 + countExtraNegativeCrossings(endValue);
        } else if (crossedFromNegativeToPositive(startValue, endValue)) {
            crossings = 1 + countExtraPositiveCrossings(endValue);
        } else if (crossedPositiveBoundariesOnly(startValue, endValue)) {
            crossings = countHowManyHundredsCrossed(endValue);
        } else if (crossedNegativeBoundariesOnly(startValue, endValue)) {
            crossings = countHowManyNegativeHundredsCrossed(endValue);
        } else if (startedAtZeroAndWentPositive(startValue, endValue)) {
            crossings = countHowManyHundredsCrossed(endValue);
        } else if (startedAtZeroAndWentNegative(startValue, endValue)) {
            crossings = countHowManyNegativeHundredsCrossed(endValue);
        }

        log(LogLevel.VERBOSE, VERBOSE, "Boundary crossings from " + startValue + " to " + endValue + ": " + crossings);
        return crossings;
    }

    /**
     * Normalize the dial value into the range [0, 99].
     * <p>
     * Examples: -5 -> 95, 105 -> 5.
     * </p>
     */
    private static void normalizeDial() {
        int originalValue = dialValue;
        dialValue = ((dialValue % 100) + 100) % 100;

        if (dialValue != originalValue) {
            if (originalValue < 0) {
                log(LogLevel.VERBOSE, VERBOSE, "Dial wrapped around from below 0");
            } else {
                log(LogLevel.VERBOSE, VERBOSE, "Dial wrapped around from above 100");
            }
            log(LogLevel.VERBOSE, VERBOSE, "Dial before normalization: " + originalValue);
        }
    }

    /**
     * Update counters for landing on zero and, in Part 2, for crossings.
     */
    private static void checkIfDialIsAtZero(int boundariesCrossed) {
        log(LogLevel.DEBUG, DEBUGGING, "Checking if dial is at zero: " + dialValue);

        // Landing on zero after normalization.
        if (dialValue == 0) {
            log(LogLevel.VERBOSE, VERBOSE, "Dial is at zero! Incrementing counter.");
            log(LogLevel.DEBUG, DEBUGGING, "Times hitting zero before increment: " + timesHittingZero);
            timesHittingZero++;
            log(LogLevel.DEBUG, DEBUGGING, "Times hitting zero after increment: " + timesHittingZero);
        }

        // Part 2: count crossings that occurred during movement.
        if (partTwo && boundariesCrossed > 0) {
            log(LogLevel.VERBOSE, VERBOSE, "Part 2: Adding " + boundariesCrossed + " boundary crossings");
            timesHittingZero += boundariesCrossed;
            log(LogLevel.DEBUG, DEBUGGING, "Total after adding boundary crossings: " + timesHittingZero);
        }
    }

    /**
     * Reset state to initial values for a fresh run.
     */
    private static void resetState() {
        dialValue = 50;
        timesHittingZero = 0;
        partTwo = false;
    }

    // region Helper Methods

    // ========== Boundary Crossing Detection ==========

    private static boolean crossedFromPositiveToNegative(int start, int end) {
        return start > 0 && end < 0;
    }

    private static boolean crossedFromNegativeToPositive(int start, int end) {
        return start < 0 && end > 0;
    }

    private static boolean crossedPositiveBoundariesOnly(int start, int end) {
        return start > 0 && end > 100;
    }

    private static boolean crossedNegativeBoundariesOnly(int start, int end) {
        return start < 0 && end < -100;
    }

    private static boolean startedAtZeroAndWentPositive(int start, int end) {
        return start == 0 && end > 100;
    }

    private static boolean startedAtZeroAndWentNegative(int start, int end) {
        return start == 0 && end < -100;
    }

    // ========== Boundary Crossing Counting ==========

    private static int countExtraNegativeCrossings(int endValue) {
        // Example: endValue -150 crosses -100 once beyond the zero crossing.
        return endValue < -1 ? (Math.abs(endValue) - 1) / 100 : 0;
    }

    private static int countExtraPositiveCrossings(int endValue) {
        // Example: endValue 250 crosses 200 once beyond the 100 crossing.
        return endValue > 100 ? (endValue - 1) / 100 : 0;
    }

    private static int countHowManyHundredsCrossed(int endValue) {
        // Example: endValue 350 crosses 100, 200, 300 => 3 crossings.
        return (endValue - 1) / 100;
    }

    private static int countHowManyNegativeHundredsCrossed(int endValue) {
        // Example: endValue -250 crosses -100, -200 => 2 crossings.
        return (Math.abs(endValue) - 1) / 100;
    }

    // endregion
}
