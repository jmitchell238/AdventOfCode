package org.jmitchell238.aoc.aoc2023.day09;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.jmitchell238.aoc.generalutilities.LogLevel;
import org.jmitchell238.aoc.generalutilities.Utilities;

/**
 * Advent of Code 2023 - Day 9: Mirage Maintenance
 * <p>
 * This class contains the solution logic for Day 9 of Advent of Code 2023.
 * Part 1 extrapolates the next value in sequences by analyzing differences.
 * Part 2 extrapolates the previous value in sequences using the same technique.
 * </p>
 */
@SuppressWarnings({"java:S106", "java:S1118", "java:S1940", "java:S2589", "java:S100", "java:S3776", "java:S127"})
public class Day09 {

    // Configuration flags
    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    // State variables
    private final List<List<Integer>> analysisSequences = new ArrayList<>();

    // Java 25-friendly main; suppress unused args
    @SuppressWarnings({"unused", "java:S1172"})
    static void main(String[] args) {
        new Day09().runDay09();
    }

    /**
     * Entry point for Day 9 solution.
     */
    public void runDay09() {
        logOutput(LogLevel.INFO, true, "\n--- Day 9: Mirage Maintenance ---\n");

        String actualInputFilePath = "src/main/java/org/jmitchell238/aoc/aoc2023/day09/input.txt";

        long partOneAnswer = solvePart1(actualInputFilePath);
        logOutput(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        long partTwoAnswer = solvePart2(actualInputFilePath);
        logOutput(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    /**
     * Solves Part 1 by extrapolating next values in sequences.
     */
    public long solvePart1(String inputFilePath) {
        parseInputSequences(inputFilePath);

        long totalSumOfNextValues = 0;
        List<List<Integer>> sequencesWithNextValues = new ArrayList<>();

        for (List<Integer> originalSequence : analysisSequences) {
            List<Integer> sequenceWithNextValue = extrapolateNextValueInSequence(originalSequence);
            sequencesWithNextValues.add(sequenceWithNextValue);
        }

        for (List<Integer> sequenceWithNextValue : sequencesWithNextValues) {
            int nextValue = sequenceWithNextValue.getLast();
            totalSumOfNextValues += nextValue;

            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Sequence next value: " + nextValue);
        }

        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Total sum of next values: " + totalSumOfNextValues);
        return totalSumOfNextValues;
    }

    /**
     * Solves Part 2 by extrapolating previous values in sequences.
     */
    public long solvePart2(String inputFilePath) {
        parseInputSequences(inputFilePath);

        long totalSumOfPreviousValues = 0;
        List<List<Integer>> sequencesWithPreviousValues = new ArrayList<>();

        for (List<Integer> originalSequence : analysisSequences) {
            List<Integer> sequenceWithPreviousValue = extrapolatePreviousValueInSequence(originalSequence);
            sequencesWithPreviousValues.add(sequenceWithPreviousValue);
        }

        for (List<Integer> sequenceWithPreviousValue : sequencesWithPreviousValues) {
            int previousValue = sequenceWithPreviousValue.getFirst();
            totalSumOfPreviousValues += previousValue;

            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Sequence previous value: " + previousValue);
        }

        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Total sum of previous values: " + totalSumOfPreviousValues);
        return totalSumOfPreviousValues;
    }

    /**
     * Parses input file and extracts sequences for analysis.
     */
    @SuppressWarnings("java:S112")
    private void parseInputSequences(String inputFilePath) {
        analysisSequences.clear(); // Clear previous data

        try (Scanner fileScanner = new Scanner(new File(inputFilePath))) {
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Processing line: " + currentLine);

                int[] sequenceArray = Utilities.splitToIntArray(currentLine, " ");
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Parsed sequence: " + Arrays.toString(sequenceArray));

                List<Integer> sequenceList =
                        Arrays.stream(sequenceArray).boxed().toList();
                analysisSequences.add(new ArrayList<>(sequenceList)); // Create mutable copy
            }
        } catch (FileNotFoundException fileNotFoundException) {
            logOutput(LogLevel.ERROR, true, "Input file not found: " + inputFilePath);
            throw new RuntimeException(fileNotFoundException);
        }
    }

    /**
     * Calculates the next number in a sequence using recursive difference analysis.
     */
    private int calculateNextNumberInSequence(List<Integer> sequence) {
        List<Integer> differencesSequence = calculateDifferencesSequence(sequence);

        if (hasNonZeroDifferences(differencesSequence)) {
            int nextNumberFromDifferences = calculateNextNumberInSequence(differencesSequence);
            return sequence.getLast() + nextNumberFromDifferences;
        }

        return sequence.getLast();
    }

    /**
     * Extrapolates the next value in a sequence and returns the modified sequence.
     */
    private List<Integer> extrapolateNextValueInSequence(List<Integer> sequence) {
        List<Integer> differencesSequence = calculateDifferencesSequence(sequence);

        if (hasNonZeroDifferences(differencesSequence)) {
            int nextNumberFromDifferences = calculateNextNumberInSequence(differencesSequence);
            sequence.add(sequence.getLast() + nextNumberFromDifferences);

            logOutput(
                    LogLevel.VERBOSE,
                    ENABLE_VERBOSE_LOGGING,
                    "Added next value to sequence: " + (sequence.getLast() + nextNumberFromDifferences));
            return sequence;
        }

        logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "All differences are zero, returning original sequence");
        return sequence;
    }

    /**
     * Calculates the previous number in a sequence using recursive difference analysis.
     */
    private int calculatePreviousNumberInSequence(List<Integer> sequence) {
        List<Integer> differencesSequence = calculateDifferencesSequence(sequence);

        if (hasNonZeroDifferences(differencesSequence)) {
            int previousNumberFromDifferences = calculatePreviousNumberInSequence(differencesSequence);
            return sequence.getFirst() - previousNumberFromDifferences;
        }

        return sequence.getFirst();
    }

    /**
     * Extrapolates the previous value in a sequence and returns the modified sequence.
     */
    private List<Integer> extrapolatePreviousValueInSequence(List<Integer> sequence) {
        List<Integer> differencesSequence = calculateDifferencesSequence(sequence);

        if (hasNonZeroDifferences(differencesSequence)) {
            int previousNumberFromDifferences = calculatePreviousNumberInSequence(differencesSequence);
            int newPreviousValue = sequence.getFirst() - previousNumberFromDifferences;
            sequence.addFirst(newPreviousValue);

            logOutput(
                    LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Added previous value to sequence: " + newPreviousValue);
            return sequence;
        }

        logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "All differences are zero, returning original sequence");
        return sequence;
    }

    /**
     * Calculates the differences between consecutive elements in a sequence.
     */
    private List<Integer> calculateDifferencesSequence(List<Integer> sequence) {
        List<Integer> differencesSequence = new ArrayList<>();

        for (int sequenceIndex = 0; sequenceIndex < sequence.size() - 1; sequenceIndex++) {
            int currentElement = sequence.get(sequenceIndex);
            int nextElement = sequence.get(sequenceIndex + 1);
            int difference = nextElement - currentElement;

            differencesSequence.add(difference);
        }

        logOutput(
                LogLevel.VERBOSE,
                ENABLE_VERBOSE_LOGGING,
                "Calculated differences: " + differencesSequence + " from sequence: " + sequence);

        return differencesSequence;
    }

    /**
     * Checks if there are any non-zero differences in a sequence.
     */
    private boolean hasNonZeroDifferences(List<Integer> differencesSequence) {
        boolean anyNonZero = differencesSequence.stream().anyMatch(difference -> difference != 0);

        logOutput(
                LogLevel.VERBOSE,
                ENABLE_VERBOSE_LOGGING,
                "Has non-zero differences? " + anyNonZero + " for sequence: " + differencesSequence);

        return anyNonZero;
    }

    /**
     * Resets all state for fresh execution (used in testing).
     */
    @SuppressWarnings("unused")
    public void resetSequenceData() {
        analysisSequences.clear();
        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "All sequence data has been reset");
    }
}
