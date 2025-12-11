package org.jmitchell238.aoc.aoc2023.day06;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.jmitchell238.aoc.generalutilities.LogLevel;

/**
 * Advent of Code 2023 - Day 6: Wait For It
 * <p>
 * This class contains the solution logic for Day 6 of Advent of Code 2023.
 * Part 1 calculates ways to win multiple boat races with separate time/distance pairs.
 * Part 2 treats the input as a single race with concatenated time and distance values.
 * </p>
 */
@SuppressWarnings({"java:S106", "java:S1118", "java:S1940", "java:S2589", "java:S100", "java:S3776", "java:S127"})
public class Day06 {

    // Configuration flags
    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    // Constants
    private static final String TIME_LINE_PREFIX = "Time:";
    private static final String DISTANCE_LINE_PREFIX = "Distance:";

    // State variables for Part 1 (multiple races)
    private static final ArrayList<Long> individualRaceTimes = new ArrayList<>();
    private static final ArrayList<Long> individualRaceDistances = new ArrayList<>();
    private static final Map<Long, Long> raceTimeToDistanceRecordMap = new HashMap<>();

    // State variables for Part 2 (single race)
    private static long singleRaceTime = 0L;
    private static long singleRaceDistance = 0L;

    // Java 25: package-private static main; suppress unused args
    @SuppressWarnings({"unused", "java:S1172"})
    static void main(String[] args) throws FileNotFoundException {
        new Day06().runDay06();
    }

    /**
     * Entry point for Day 6 solution.
     */
    public void runDay06() throws FileNotFoundException {
        logOutput(LogLevel.INFO, true, "\n--- Day 6: Wait For It ---\n");

        String actualInputFilePath = "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input.txt";

        long partOneAnswer = solvePart1(actualInputFilePath);
        logOutput(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        long partTwoAnswer = solvePart2(actualInputFilePath);
        logOutput(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    /**
     * Solves Part 1 by parsing multiple race time/distance pairs and calculating winning strategies.
     */
    public long solvePart1(String inputFilePath) throws FileNotFoundException {
        parseInputForMultipleRaces(inputFilePath);
        buildRaceTimeToDistanceMap();

        long waysToWinAllRacesMultiplied = calculateWaysToWinAllRacesMultiplied();

        logOutput(
                LogLevel.DEBUG,
                ENABLE_DEBUG_LOGGING,
                "Part 1 - Ways to win all races multiplied: " + waysToWinAllRacesMultiplied);
        return waysToWinAllRacesMultiplied;
    }

    /**
     * Solves Part 2 by treating the input as a single race with concatenated values.
     */
    public long solvePart2(String inputFilePath) throws FileNotFoundException {
        parseInputForSingleRace(inputFilePath);
        clearAndSetupSingleRaceMap();

        long waysToWinSingleRace = calculateWaysToWinAllRacesMultiplied();

        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Part 2 - Ways to win single race: " + waysToWinSingleRace);
        return waysToWinSingleRace;
    }

    /**
     * Parses input file for Part 1 to extract individual race times and distances.
     */
    private void parseInputForMultipleRaces(String inputFilePath) throws FileNotFoundException {
        try (Scanner fileScanner = new Scanner(new File(inputFilePath))) {
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Processing line: " + currentLine);

                if (currentLine.startsWith(TIME_LINE_PREFIX)) {
                    parseIndividualTimesFromLine(currentLine);
                } else if (currentLine.startsWith(DISTANCE_LINE_PREFIX)) {
                    parseIndividualDistancesFromLine(currentLine);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            String errorMessage = "Input file not found: " + inputFilePath;
            logOutput(LogLevel.ERROR, true, errorMessage);
            logOutput(
                    LogLevel.DEBUG,
                    ENABLE_DEBUG_LOGGING,
                    "FileNotFoundException: " + fileNotFoundException.getMessage());
            throw fileNotFoundException;
        }
    }

    /**
     * Parses input file for Part 2 to extract single concatenated race time and distance.
     */
    private void parseInputForSingleRace(String inputFilePath) throws FileNotFoundException {
        try (Scanner fileScanner = new Scanner(new File(inputFilePath))) {
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Processing line: " + currentLine);

                if (currentLine.startsWith(TIME_LINE_PREFIX)) {
                    parseConcatenatedTimeFromLine(currentLine);
                } else if (currentLine.startsWith(DISTANCE_LINE_PREFIX)) {
                    parseConcatenatedDistanceFromLine(currentLine);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            String errorMessage = "Input file not found: " + inputFilePath;
            logOutput(LogLevel.ERROR, true, errorMessage);
            logOutput(
                    LogLevel.DEBUG,
                    ENABLE_DEBUG_LOGGING,
                    "FileNotFoundException: " + fileNotFoundException.getMessage());
            throw fileNotFoundException;
        }
    }

    /**
     * Parses individual race times from a time line.
     */
    private void parseIndividualTimesFromLine(String timeLine) {
        String[] timeComponents = timeLine.split(" ");

        for (int componentIndex = 1; componentIndex < timeComponents.length; componentIndex++) {
            if (!timeComponents[componentIndex].trim().isEmpty()) {
                long raceTime = Long.parseLong(timeComponents[componentIndex]);
                individualRaceTimes.add(raceTime);
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Added race time: " + raceTime);
            }
        }
    }

    /**
     * Parses individual race distances from a distance line.
     */
    private void parseIndividualDistancesFromLine(String distanceLine) {
        String[] distanceComponents = distanceLine.split(" ");

        for (int componentIndex = 1; componentIndex < distanceComponents.length; componentIndex++) {
            if (!distanceComponents[componentIndex].trim().isEmpty()) {
                long raceDistance = Long.parseLong(distanceComponents[componentIndex]);
                individualRaceDistances.add(raceDistance);
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Added race distance: " + raceDistance);
            }
        }
    }

    /**
     * Parses concatenated time from a time line for Part 2.
     */
    private static void parseConcatenatedTimeFromLine(String timeLine) {
        String[] timeComponents = timeLine.split(":");
        singleRaceTime = Long.parseLong(timeComponents[1].trim().replaceAll("\\s", ""));
        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Concatenated race time: " + singleRaceTime);
    }

    /**
     * Parses concatenated distance from a distance line for Part 2.
     */
    private static void parseConcatenatedDistanceFromLine(String distanceLine) {
        String[] distanceComponents = distanceLine.split(":");
        singleRaceDistance = Long.parseLong(distanceComponents[1].trim().replaceAll("\\s", ""));
        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Concatenated race distance: " + singleRaceDistance);
    }

    /**
     * Builds the race time to distance record map for Part 1.
     */
    private void buildRaceTimeToDistanceMap() {
        for (int raceIndex = 0; raceIndex < individualRaceTimes.size(); raceIndex++) {
            Long raceTime = individualRaceTimes.get(raceIndex);
            Long raceDistance = individualRaceDistances.get(raceIndex);
            raceTimeToDistanceRecordMap.put(raceTime, raceDistance);
        }

        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Built race map: " + raceTimeToDistanceRecordMap);
    }

    /**
     * Clears the race map and sets up for Part 2 single race.
     */
    private void clearAndSetupSingleRaceMap() {
        raceTimeToDistanceRecordMap.clear();
        raceTimeToDistanceRecordMap.put(singleRaceTime, singleRaceDistance);

        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Single race map: " + raceTimeToDistanceRecordMap);
    }

    /**
     * Calculates the number of ways to win all races, multiplied together.
     */
    private long calculateWaysToWinAllRacesMultiplied() {
        long totalWaysToWinMultiplied = 1;

        for (Map.Entry<Long, Long> raceEntry : raceTimeToDistanceRecordMap.entrySet()) {
            long raceTime = raceEntry.getKey();
            long recordDistance = raceEntry.getValue();

            long waysToWinCurrentRace = calculateWaysToWinSingleRace(raceTime, recordDistance);
            totalWaysToWinMultiplied *= waysToWinCurrentRace;

            logOutput(
                    LogLevel.DEBUG,
                    ENABLE_DEBUG_LOGGING,
                    "Race (time=" + raceTime + ", record=" + recordDistance + ") has " + waysToWinCurrentRace
                            + " ways to win");
        }

        return totalWaysToWinMultiplied;
    }

    /**
     * Calculates the number of ways to win a single race given time and record distance.
     */
    private long calculateWaysToWinSingleRace(long totalRaceTime, long recordDistance) {
        long waysToWinThisRace = 0;

        for (long buttonHoldTime = 0; buttonHoldTime <= totalRaceTime; buttonHoldTime++) {
            long remainingTravelTime = totalRaceTime - buttonHoldTime;
            long totalDistanceTraveled = buttonHoldTime * remainingTravelTime;

            if (totalDistanceTraveled > recordDistance) {
                waysToWinThisRace++;

                logOutput(
                        LogLevel.VERBOSE,
                        ENABLE_VERBOSE_LOGGING,
                        "Hold=" + buttonHoldTime + "ms, Speed=" + buttonHoldTime + "mm/ms, " + "Travel="
                                + remainingTravelTime + "ms, Distance=" + totalDistanceTraveled + "mm (WINS!)");
            } else {
                logOutput(
                        LogLevel.VERBOSE,
                        ENABLE_VERBOSE_LOGGING,
                        "Hold=" + buttonHoldTime + "ms, Speed=" + buttonHoldTime + "mm/ms, " + "Travel="
                                + remainingTravelTime + "ms, Distance=" + totalDistanceTraveled + "mm");
            }
        }

        return waysToWinThisRace;
    }

    /**
     * Resets all state for fresh execution (used in testing).
     */
    public static void resetAllRaceData() {
        individualRaceTimes.clear();
        individualRaceDistances.clear();
        raceTimeToDistanceRecordMap.clear();
        singleRaceTime = 0L;
        singleRaceDistance = 0L;

        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "All race data has been reset");
    }
}
