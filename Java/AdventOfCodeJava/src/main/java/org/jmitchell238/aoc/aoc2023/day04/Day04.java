package org.jmitchell238.aoc.aoc2023.day04;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;
import static org.jmitchell238.aoc.generalutilities.LogHelper.logException;
import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import org.jmitchell238.aoc.generalutilities.LogLevel;

/**
 * Advent of Code 2023 - Day 4: Scratchcards
 * <p>
 * This class contains the solution logic for Day 4 of Advent of Code 2023.
 * Part 1 calculates points for matching numbers on scratchcards.
 * Part 2 handles card copying mechanics based on matching numbers.
 * </p>
 */
@SuppressWarnings({"java:S106", "java:S1118", "java:S1940", "java:S2589", "java:S100", "java:S3776", "java:S127"})
public class Day04 {

    // Configuration flags
    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    // Constants
    private static final String CARD_SEPARATOR_REGEX = ": |\\|";

    @SuppressWarnings("unused")
    public void main(String[] args) {
        runDay04();
    }

    /**
     * Entry point for Day 4 solution.
     */
    public void runDay04() {
        logOutput(LogLevel.INFO, true, "\n--- Day 4: Scratchcards ---\n");

        String actualInputFilePath = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input.txt";
        String testInputFilePath = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input_test.txt";

        int partOneAnswer = solvePart1(actualInputFilePath);
        logOutput(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        int partTwoAnswer = solvePart2(actualInputFilePath);
        logOutput(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    /**
     * Solves Part 1 by calculating points for matching numbers on scratchcards.
     */
    public int solvePart1(String inputFilePath) {
        int totalScratchcardPoints = 0;
        ArrayList<ArrayList<String>> parsedScratchcards = new ArrayList<>();

        try {
            File inputFile = new File(inputFilePath);
            Scanner fileScanner = new Scanner(inputFile);

            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                parsedScratchcards.add(new ArrayList<>(Arrays.asList(currentLine.split(CARD_SEPARATOR_REGEX))));

                logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Processing line: " + currentLine);
            }
            fileScanner.close();

            logParsedScratchcardsIfVerbose(parsedScratchcards);

            for (ArrayList<String> scratchcardData : parsedScratchcards) {
                ArrayList<Integer> winningNumbers = parseWinningNumbers(scratchcardData);
                ArrayList<Integer> playerNumbers = parsePlayerNumbers(scratchcardData);
                ArrayList<Integer> matchingNumbers = findMatchingNumbers(winningNumbers, playerNumbers);

                logScratchcardAnalysisIfDebug(scratchcardData, winningNumbers, playerNumbers, matchingNumbers);

                int cardPoints = calculateCardPoints(matchingNumbers);
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Card points: " + cardPoints);

                totalScratchcardPoints += cardPoints;
            }

            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Total scratchcard points: " + totalScratchcardPoints);
            return totalScratchcardPoints;

        } catch (FileNotFoundException fileNotFound) {
            String errorMessage = "Input file not found: " + inputFilePath;
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "FileNotFoundException: " + fileNotFound.getMessage());
            logOutput(LogLevel.ERROR, true, errorMessage);
            logException(fileNotFound);
            throw new RuntimeException(fileNotFound);
        }
    }

    /**
     * Solves Part 2 by handling card copying mechanics based on matching numbers.
     */
    public int solvePart2(String inputFilePath) {
        int totalScratchcardCount = 0;
        ArrayList<ScratchcardWithCount> scratchcardsWithCopies = new ArrayList<>();

        try {
            File inputFile = new File(inputFilePath);
            Scanner fileScanner = new Scanner(inputFile);

            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                totalScratchcardCount++;

                ArrayList<ArrayList<String>> cardData = new ArrayList<>();
                cardData.add(new ArrayList<>(Arrays.asList(currentLine.split(CARD_SEPARATOR_REGEX))));
                scratchcardsWithCopies.add(new ScratchcardWithCount(1, cardData));

                logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Processing line: " + currentLine);
            }
            fileScanner.close();

            logScratchcardCountsIfDebug(scratchcardsWithCopies, "Initial");

            processScratchcardCopying(scratchcardsWithCopies);

            logScratchcardCountsIfDebug(scratchcardsWithCopies, "After processing");

            totalScratchcardCount = calculateTotalScratchcardCount(scratchcardsWithCopies);

            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Final total scratchcard count: " + totalScratchcardCount);
            return totalScratchcardCount;

        } catch (FileNotFoundException fileNotFound) {
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "FileNotFoundException: " + fileNotFound.getMessage());
            String errorMessage = "Input file not found: " + inputFilePath;
            logOutput(LogLevel.ERROR, true, errorMessage);
            logException(fileNotFound);
            throw new RuntimeException(fileNotFound);
        }
    }

    /**
     * Processes the scratchcard copying mechanics for Part 2.
     */
    private void processScratchcardCopying(ArrayList<ScratchcardWithCount> scratchcardsWithCopies) {
        for (int cardIndex = 0; cardIndex < scratchcardsWithCopies.size(); cardIndex++) {
            ArrayList<Integer> winningNumbers = parseWinningNumbers(
                    scratchcardsWithCopies.get(cardIndex).getScratchcardData().get(0));
            ArrayList<Integer> playerNumbers = parsePlayerNumbers(
                    scratchcardsWithCopies.get(cardIndex).getScratchcardData().get(0));
            ArrayList<Integer> matchingNumbers = findMatchingNumbers(winningNumbers, playerNumbers);

            logScratchcardAnalysisIfDebug(
                    scratchcardsWithCopies.get(cardIndex).getScratchcardData().get(0),
                    winningNumbers,
                    playerNumbers,
                    matchingNumbers);

            int numberOfExtraCopies = matchingNumbers.size();
            int currentCardCopies = scratchcardsWithCopies.get(cardIndex).getCopyCount();

            // For each copy of the current card, add copies to subsequent cards
            for (int copyIndex = 0; copyIndex < currentCardCopies; copyIndex++) {
                for (int nextCardOffset = 0; nextCardOffset < numberOfExtraCopies; nextCardOffset++) {
                    int targetCardIndex = cardIndex + nextCardOffset + 1;
                    if (targetCardIndex < scratchcardsWithCopies.size()) {
                        scratchcardsWithCopies.get(targetCardIndex).incrementCopyCount();
                    }
                }
            }

            logOutput(
                    LogLevel.DEBUG,
                    ENABLE_DEBUG_LOGGING,
                    "Card " + (cardIndex + 1) + " had " + matchingNumbers.size() + " matches, " + "current copies: "
                            + currentCardCopies);
        }
    }

    /**
     * Parses winning numbers from scratchcard data.
     */
    private static ArrayList<Integer> parseWinningNumbers(ArrayList<String> scratchcardData) {
        return stream(scratchcardData.get(1).split(" "))
                .filter(numberString -> !Objects.equals(numberString, ""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(toCollection(ArrayList::new));
    }

    /**
     * Parses player numbers from scratchcard data.
     */
    private static ArrayList<Integer> parsePlayerNumbers(ArrayList<String> scratchcardData) {
        return stream(scratchcardData.get(2).split(" "))
                .filter(numberString -> !Objects.equals(numberString, ""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(toCollection(ArrayList::new));
    }

    /**
     * Finds matching numbers between winning numbers and player numbers.
     */
    private static ArrayList<Integer> findMatchingNumbers(
            ArrayList<Integer> winningNumbers, ArrayList<Integer> playerNumbers) {
        ArrayList<Integer> matchingNumbers = new ArrayList<>();

        for (Integer playerNumber : playerNumbers) {
            if (winningNumbers.contains(playerNumber)) {
                matchingNumbers.add(playerNumber);
            }
        }

        return matchingNumbers;
    }

    /**
     * Calculates points for a scratchcard based on matching numbers.
     */
    private static int calculateCardPoints(ArrayList<Integer> matchingNumbers) {
        int cardPoints = 0;

        for (Integer ignoredMatchingNumber : matchingNumbers) {
            if (cardPoints == 0) {
                cardPoints = 1;
            } else {
                cardPoints *= 2;
            }
        }

        return cardPoints;
    }

    /**
     * Calculates the total count of all scratchcards including copies.
     */
    private static int calculateTotalScratchcardCount(ArrayList<ScratchcardWithCount> scratchcardsWithCopies) {
        int totalCount = 0;

        for (ScratchcardWithCount scratchcard : scratchcardsWithCopies) {
            totalCount += scratchcard.getCopyCount();
        }

        return totalCount;
    }

    /**
     * Logs parsed scratchcards if verbose logging is enabled.
     */
    private static void logParsedScratchcardsIfVerbose(ArrayList<ArrayList<String>> parsedScratchcards) {
        if (ENABLE_VERBOSE_LOGGING) {
            for (ArrayList<String> scratchcardData : parsedScratchcards) {
                logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Parsed scratchcard: " + scratchcardData);
            }
        }
    }

    /**
     * Logs scratchcard analysis details if debug logging is enabled.
     */
    private static void logScratchcardAnalysisIfDebug(
            ArrayList<String> scratchcardData,
            ArrayList<Integer> winningNumbers,
            ArrayList<Integer> playerNumbers,
            ArrayList<Integer> matchingNumbers) {
        if (ENABLE_DEBUG_LOGGING) {
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "\n" + scratchcardData.get(0) + ":");
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Winning Numbers: " + winningNumbers);
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Player Numbers: " + playerNumbers);
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Matching Numbers: " + matchingNumbers);
        }
    }

    /**
     * Logs scratchcard counts if debug logging is enabled.
     */
    private static void logScratchcardCountsIfDebug(
            ArrayList<ScratchcardWithCount> scratchcardsWithCopies, String phase) {
        if (ENABLE_DEBUG_LOGGING) {
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "\n" + phase + " card counts:");
            for (int i = 0; i < scratchcardsWithCopies.size(); i++) {
                logOutput(
                        LogLevel.DEBUG,
                        ENABLE_DEBUG_LOGGING,
                        "Card " + (i + 1) + " copies: "
                                + scratchcardsWithCopies.get(i).getCopyCount());
            }
        }
    }

    /**
     * Inner class to represent a scratchcard with its copy count.
     */
    class ScratchcardWithCount {
        private int copyCount;
        private ArrayList<ArrayList<String>> scratchcardData;

        public ScratchcardWithCount(int copyCount, ArrayList<ArrayList<String>> scratchcardData) {
            this.copyCount = copyCount;
            this.scratchcardData = scratchcardData;
        }

        public int getCopyCount() {
            return copyCount;
        }

        public ArrayList<ArrayList<String>> getScratchcardData() {
            return scratchcardData;
        }

        public void incrementCopyCount() {
            this.copyCount++;
        }
    }
}
