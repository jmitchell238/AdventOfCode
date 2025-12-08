package org.jmitchell238.aoc.aoc2025.day02;

import static org.jmitchell238.aoc.aoc2025.utilities.Utilities2025.log;
import static org.jmitchell238.aoc.aoc2025.utilities.Utilities2025.readFileAndProcessEachLine;
import static org.jmitchell238.aoc.generalutilities.Utilities.isExactlyTwoRepeats;
import static org.jmitchell238.aoc.generalutilities.Utilities.isRepeatedMultipleTimes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jmitchell238.aoc.generalutilities.LogLevel;

/**
 * Advent of Code 2025 - Day 2: Gift Shop
 * <p>
 * This class contains the solution logic for Day 2 of Advent of Code 2025.
 * It processes ranges of numbers to find invalid IDs that follow specific repeating patterns.
 * Part 1 finds numbers with exactly two repeated halves, while Part 2 finds numbers
 * with any pattern repeated multiple times.
 * </p>
 */
@SuppressWarnings({"java:S106", "java:S1940", "java:S2589", "java:S1118"})
public class Day02 {

    // Configuration flags
    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    // Running totals and state
    private static Long sumTotalOfAllInvalidIds = 0L;
    private static boolean isProcessingPartTwo = false;

    // Cache of invalid IDs found and how many times each was seen
    private static final Map<Long, Long> invalidIdCounts = new HashMap<>();

    @SuppressWarnings("unused")
    static void main(String[] args) {
        run();
    }

    public static void run() {
        System.out.println("\n--- Day 02: Gift Shop ---\n");

        String actualInputFilePath = "src/main/java/org/jmitchell238/aoc/aoc2025/day02/input.txt";
        String testInputFilePath = "src/test/java/org/jmitchell238/aoc/aoc2025/day02/input_test_part1.txt";

        Long partOneAnswer = part1(actualInputFilePath);
        System.out.println("Part 1: Answer: " + partOneAnswer);

        Long partTwoAnswer = part2(testInputFilePath);
        System.out.println("Part 2: Answer: " + partTwoAnswer);
    }

    /**
     * Solve Part 1 of the problem.
     * <p>
     * Total up all numbers in the input ranges that consist of exactly two repeated halves.
     * These form invalid IDs that need to be counted and summed.
     * </p>
     * @param inputFilePath Path to the input file containing ranges to process
     * @return Total sum of all invalid IDs found multiplied by their occurrence counts
     */
    public static Long part1(String inputFilePath) {
        resetProblemState();
        calculateAnswer(inputFilePath);
        return sumTotalOfAllInvalidIds;
    }

    /**
     * Solve Part 2 of the problem.
     * <p>
     * For Part 2, IDs can have patterns repeated multiple times (2 or more).
     * This includes both even-length repeated blocks and odd-length identical digits.
     * </p>
     * @param inputFilePath Path to the input file containing ranges to process
     * @return Total sum of all invalid IDs found multiplied by their occurrence counts
     */
    public static Long part2(String inputFilePath) {
        resetProblemState();
        isProcessingPartTwo = true;
        calculateAnswer(inputFilePath);
        return sumTotalOfAllInvalidIds;
    }

    /**
     * Processes the input file and calculates the answer by finding all invalid IDs.
     * Each line contains comma-separated ranges like: "11-22,1010-1020".
     * For each range, identifies numbers that match the current part's criteria.
     */
    private static void calculateAnswer(String inputFilePath) {
        readFileAndProcessEachLine(inputFilePath, inputLine -> {
            log(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Read line: " + inputLine);

            if (inputLine == null || inputLine.isBlank()) {
                return;
            }

            List<Long[]> rangesToProcess = parseInputLineIntoRanges(inputLine);

            if (isProcessingPartTwo) {
                log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "PART 2: Processing ranges for two or more repeats...");
                processRangesForMultipleRepeats(rangesToProcess);
            } else {
                log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "PART 1: Processing ranges for exactly two repeats...");
                processRangesForExactlyTwoRepeats(rangesToProcess);
            }
        });

        calculateFinalTotal();
        printSummaryIfVerboseEnabled();
    }

    /**
     * Resets all problem state to initial values for a fresh run.
     */
    private static void resetProblemState() {
        isProcessingPartTwo = false;
        sumTotalOfAllInvalidIds = 0L;
        invalidIdCounts.clear();
    }

    // region : Helper methods region

    /**
     * Parses a comma-separated list of numeric ranges like "11-22,100-250" into range pairs.
     * Invalid segments are ignored and logged for debugging purposes.
     */
    private static List<Long[]> parseInputLineIntoRanges(String inputLine) {
        List<Long[]> parsedRanges = new ArrayList<>();
        String[] rangeSegments = inputLine.split(",");

        for (String rawSegment : rangeSegments) {
            String trimmedSegment = rawSegment.trim();
            if (trimmedSegment.isEmpty()) {
                continue;
            }

            Long[] parsedRange = parseIndividualRangeSegment(trimmedSegment);
            if (parsedRange.length > 0) {
                parsedRanges.add(parsedRange);
            }
        }
        return parsedRanges;
    }

    /**
     * Parses a single range segment like "11-22" into a [start, end] pair.
     * Returns an empty array if the segment is malformed.
     */
    private static Long[] parseIndividualRangeSegment(String rangeSegment) {
        int dashPosition = rangeSegment.indexOf('-');
        if (dashPosition <= 0 || dashPosition >= rangeSegment.length() - 1) {
            log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Ignoring malformed range: " + rangeSegment);
            return new Long[0];
        }

        String startText = rangeSegment.substring(0, dashPosition).trim();
        String endText = rangeSegment.substring(dashPosition + 1).trim();

        try {
            long rangeStart = Long.parseLong(startText);
            long rangeEnd = Long.parseLong(endText);

            if (rangeStart > rangeEnd) {
                long temp = rangeStart;
                rangeStart = rangeEnd;
                rangeEnd = temp;
            }

            return new Long[] {rangeStart, rangeEnd};
        } catch (NumberFormatException _) {
            log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Ignoring non-numeric range: " + rangeSegment);
            return new Long[0];
        }
    }

    /**
     * Processes ranges to find numbers that are exactly two repeated halves.
     * Only processes numbers with even digit length greater than or equal to 10.
     */
    private static void processRangesForExactlyTwoRepeats(List<Long[]> rangesToProcess) {
        for (Long[] currentRange : rangesToProcess) {
            Long rangeStart = currentRange[0];
            Long rangeEnd = currentRange[1];

            for (Long numberToCheck = rangeStart; numberToCheck <= rangeEnd; numberToCheck++) {
                if (numberToCheck < 10 || !hasEvenDigitCount(String.valueOf(numberToCheck))) {
                    continue;
                }

                String digitString = String.valueOf(numberToCheck);

                if (isNumberAlreadyTracked(numberToCheck)) {
                    incrementExistingCount(numberToCheck);
                } else if (isExactlyTwoRepeats(digitString)) {
                    addNewInvalidId(numberToCheck);
                    System.out.println(numberToCheck);
                }
            }
        }
    }

    /**
     * Processes ranges to find numbers with patterns repeated multiple times.
     * This includes any repeated block pattern regardless of even or odd length.
     */
    private static void processRangesForMultipleRepeats(List<Long[]> rangesToProcess) {
        for (Long[] currentRange : rangesToProcess) {
            Long rangeStart = currentRange[0];
            Long rangeEnd = currentRange[1];

            for (Long numberToCheck = rangeStart; numberToCheck <= rangeEnd; numberToCheck++) {
                if (numberToCheck < 10 || hasLeadingZero(String.valueOf(numberToCheck))) {
                    continue;
                }

                String digitString = String.valueOf(numberToCheck);

                if (isNumberAlreadyTracked(numberToCheck)) {
                    incrementExistingCount(numberToCheck);
                } else if (isRepeatedMultipleTimes(digitString)) {
                    addNewInvalidId(numberToCheck);
                    System.out.println(numberToCheck);
                }
            }
        }
    }

    /**
     * Checks if the digit string has an even number of digits.
     */
    private static boolean hasEvenDigitCount(String digitString) {
        return digitString.length() % 2 == 0;
    }

    /**
     * Checks if the digit string starts with a zero (leading zero).
     */
    private static boolean hasLeadingZero(String digitString) {
        return digitString.charAt(0) == '0';
    }

    /**
     * Determines if the number is already being tracked in our invalid ID counts.
     */
    private static boolean isNumberAlreadyTracked(Long numberToCheck) {
        return invalidIdCounts.containsKey(numberToCheck);
    }

    /**
     * Increments the count for an existing invalid ID and logs the action.
     */
    private static void incrementExistingCount(Long numberToCheck) {
        Long currentCount = invalidIdCounts.get(numberToCheck);
        Long newCount = currentCount + 1;
        invalidIdCounts.put(numberToCheck, newCount);
        log(
                LogLevel.VERBOSE,
                ENABLE_VERBOSE_LOGGING,
                "Seen before (incremented): " + numberToCheck + " -> " + newCount);
    }

    /**
     * Adds a new invalid ID to our tracking with an initial count of 1.
     */
    private static void addNewInvalidId(Long numberToCheck) {
        invalidIdCounts.put(numberToCheck, 1L);
        log(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Repeating (added): " + numberToCheck);
    }

    /**
     * Calculates the final total by summing all invalid IDs multiplied by their counts.
     */
    private static void calculateFinalTotal() {
        log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Calculating total of all invalid IDs from tracking map...");

        for (Map.Entry<Long, Long> idEntry : invalidIdCounts.entrySet()) {
            Long invalidId = idEntry.getKey();
            Long occurrenceCount = idEntry.getValue();
            sumTotalOfAllInvalidIds += invalidId * occurrenceCount;
            log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Adding " + invalidId + " * " + occurrenceCount + " to total.");
        }

        log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Total of all invalid IDs: " + sumTotalOfAllInvalidIds);
    }

    /**
     * Prints a summary of all found invalid IDs if verbose logging is enabled.
     */
    private static void printSummaryIfVerboseEnabled() {
        if (ENABLE_VERBOSE_LOGGING) {
            log(LogLevel.VERBOSE, true, "Summary of repeating numbers:");
            for (Map.Entry<Long, Long> idEntry : invalidIdCounts.entrySet()) {
                log(LogLevel.VERBOSE, true, idEntry.getKey() + " -> count " + idEntry.getValue());
            }
        }
    }

    // endregion : Helper methods region
}
