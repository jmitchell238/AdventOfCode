package org.jmitchell238.aoc.aoc2023.day01;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import org.jmitchell238.aoc.generalutilities.LogLevel;

/**
 * Advent of Code 2023 - Day 1: Trebuchet?!
 * <p>
 * This class contains the solution logic for Day 1 of Advent of Code 2023.
 * Part 1 extracts digits from calibration values, while Part 2 also handles
 * spelled-out numbers like "one", "two", etc.
 * </p>
 */
@SuppressWarnings({"java:S106", "java:S1118", "java:S1940", "java:S2589", "java:S100"})
public class Day01 {

    // Configuration flags
    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    public static void runDay01() throws FileNotFoundException {
        String dayOneInputPath = "src/main/java/org/jmitchell238/aoc/aoc2023/day01/day1.txt";

        logOutput(LogLevel.INFO, true, "\n --- Day 1: Trebuchet?! ---\n");

        File dayOneInputFile = new File(dayOneInputPath);
        int partOneTotal = solvePart1(dayOneInputFile);

        logOutput(LogLevel.INFO, true, "Part 1: Answer: " + partOneTotal);

        int partTwoTotal = solvePart2(dayOneInputFile);
        logOutput(LogLevel.INFO, true, "Part 2: Answer: " + partTwoTotal);
    }

    /**
     * Solves Part 1 by extracting only numeric digits from each line.
     */
    public static int solvePart1(File inputFile) throws FileNotFoundException {
        int totalSum = 0;

        try (Scanner fileScanner = new Scanner(inputFile)) {
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Processing line: " + currentLine);

                StringBuilder digitsOnly = new StringBuilder(currentLine.replaceAll("\\D", ""));
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Extracted digits: " + digitsOnly);

                totalSum = calculateLineTotal(digitsOnly, totalSum);
            }
        } catch (FileNotFoundException fileNotFound) {
            System.err.println("Input file not found: " + inputFile.getPath());
            throw fileNotFound;
        }

        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Part 1 total sum: " + totalSum);
        return totalSum;
    }

    /**
     * Solves Part 2 by extracting both numeric digits and spelled-out numbers.
     */
    public static int solvePart2(File inputFile) throws FileNotFoundException {
        int totalSum = 0;

        try (Scanner fileScanner = new Scanner(inputFile)) {
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Processing line: " + currentLine);

                StringBuilder extractedNumbers = extractAllNumbers(currentLine);
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Extracted numbers: " + extractedNumbers);

                totalSum = calculateLineTotal(extractedNumbers, totalSum);
            }
        } catch (FileNotFoundException fileNotFound) {
            System.err.println("Input file not found: " + inputFile.getPath());
            throw fileNotFound;
        }

        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Part 2 total sum: " + totalSum);
        return totalSum;
    }

    /**
     * Extracts all numbers (digits and spelled-out) from a line of text.
     */
    private static StringBuilder extractAllNumbers(String inputLine) {
        Map<String, Integer> wordToNumberMap = createWordToNumberMapping();
        StringBuilder extractedNumbers = new StringBuilder();
        int currentIndex = 0;

        for (char currentChar : inputLine.toCharArray()) {
            if (Character.isDigit(currentChar)) {
                extractedNumbers.append(currentChar);
                logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Found digit: " + currentChar);
                currentIndex++;
                continue;
            }

            boolean foundWordNumber = checkForWordNumber(inputLine, currentIndex, wordToNumberMap, extractedNumbers);
            if (foundWordNumber) {
                logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Found word number at index: " + currentIndex);
            }

            currentIndex++;
        }

        return extractedNumbers;
    }

    /**
     * Checks if a word number starts at the current index and appends it if found.
     */
    private static boolean checkForWordNumber(
            String inputLine, int currentIndex, Map<String, Integer> wordToNumberMap, StringBuilder extractedNumbers) {
        for (Map.Entry<String, Integer> wordEntry : wordToNumberMap.entrySet()) {
            String numberWord = wordEntry.getKey();
            Integer numberValue = wordEntry.getValue();
            try {
                int wordLength = numberWord.length();
                String lineSubstring = inputLine.substring(currentIndex, currentIndex + wordLength);
                if (Objects.equals(numberWord, lineSubstring)) {
                    extractedNumbers.append(numberValue);
                    return true;
                }
            } catch (StringIndexOutOfBoundsException _) {
                logOutput(
                        LogLevel.VERBOSE,
                        ENABLE_VERBOSE_LOGGING,
                        "Index out of bounds checking word: " + numberWord + " at index: " + currentIndex);
            }
        }
        return false;
    }

    /**
     * Calculates the calibration value for a line by taking first and last digits.
     */
    private static int calculateLineTotal(StringBuilder numbersFromLine, int runningTotal) {
        if (numbersFromLine.isEmpty()) {
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "No numbers found in line, skipping");
            return runningTotal;
        }

        int firstDigit = Integer.parseInt(numbersFromLine.substring(0, 1));
        int lastDigit = Integer.parseInt(numbersFromLine.substring(numbersFromLine.length() - 1));

        int calibrationValue = Integer.parseInt(String.valueOf(firstDigit) + lastDigit);
        logOutput(
                LogLevel.DEBUG,
                ENABLE_DEBUG_LOGGING,
                "First: " + firstDigit + ", Last: " + lastDigit + ", Calibration: " + calibrationValue);

        return runningTotal + calibrationValue;
    }

    /**
     * Creates a mapping from word representations of numbers to their integer values.
     */
    private static Map<String, Integer> createWordToNumberMapping() {
        Map<String, Integer> wordToNumberMap = new HashMap<>();
        wordToNumberMap.put("one", 1);
        wordToNumberMap.put("two", 2);
        wordToNumberMap.put("three", 3);
        wordToNumberMap.put("four", 4);
        wordToNumberMap.put("five", 5);
        wordToNumberMap.put("six", 6);
        wordToNumberMap.put("seven", 7);
        wordToNumberMap.put("eight", 8);
        wordToNumberMap.put("nine", 9);
        return wordToNumberMap;
    }
}
