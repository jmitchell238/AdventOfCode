package org.jmitchell238.aoc.aoc2023.day02;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.jmitchell238.aoc.generalutilities.LogLevel;

/**
 * Advent of Code 2023 - Day 2: Cube Conundrum
 * <p>
 * This class contains the solution logic for Day 2 of Advent of Code 2023.
 * Part 1 determines which games are possible given cube constraints.
 * Part 2 calculates the power of minimum cubes needed for each game.
 * </p>
 */
@SuppressWarnings({"java:S106", "java:S1118", "java:S1940", "java:S2589", "java:S100"})
public class Day02 {

    // Configuration flags
    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    // Constants for repeated strings
    private static final String GAME_PREFIX = "Game ";
    private static final String COLOR_GREEN = "green";
    private static final String COLOR_RED = "red";
    private static final String COLOR_BLUE = "blue";

    public static void runDay02() {
        logOutput(LogLevel.INFO, true, "\n--- Day 2: Cube Conundrum ---\n");

        String actualInputFilePath = "src/main/java/org/jmitchell238/aoc/aoc2023/day02/input.txt";
        String testInputFilePath = "src/main/java/org/jmitchell238/aoc/aoc2023/day02/input_test.txt";

        int partOneAnswer = solvePart1(actualInputFilePath);
        logOutput(LogLevel.INFO, true, "Part 1: Answer: Possible set ID's sum = " + partOneAnswer);

        int partTwoAnswer = solvePart2(testInputFilePath);
        logOutput(LogLevel.INFO, true, "Part 2: Answer: Power of each set minimum cubes sum = " + partTwoAnswer);
    }

    /**
     * Solves Part 1 by finding games that are possible with the given cube constraints.
     */
    public static int solvePart1(String inputFilePath) {
        File inputFile = new File(inputFilePath);
        Map<String, Integer> maximumCubesByColor = createMaximumCubeConstraints();

        List<Integer> possibleGameIds = new ArrayList<>();
        possibleGameIds.add(0); // Start with 0 for sum calculation

        try (Scanner fileScanner = new Scanner(inputFile)) {
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Processing line: " + currentLine);

                String[] lineParts = currentLine.split(":");
                Integer gameId = Integer.parseInt(lineParts[0].split(" ")[1]);
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Checking game ID: " + gameId);

                String[] cubeSets = lineParts[1].split(";");
                boolean isGamePossible = true;

                for (String cubeSet : cubeSets) {
                    if (!isCubeSetPossible(cubeSet, maximumCubesByColor)) {
                        isGamePossible = false;
                        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, GAME_PREFIX + gameId + " is not possible");
                        break;
                    }
                }

                if (isGamePossible) {
                    possibleGameIds.add(gameId);
                    logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, GAME_PREFIX + gameId + " is possible");
                }
            }
        } catch (FileNotFoundException _) {
            logOutput(LogLevel.ERROR, true, "Input file not found: " + inputFilePath);
        }

        int totalSum = possibleGameIds.stream().mapToInt(Integer::intValue).sum();
        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Total sum of possible games: " + totalSum);
        return totalSum;
    }

    /**
     * Solves Part 2 by calculating the power of minimum cubes needed for each game.
     */
    public static int solvePart2(String inputFilePath) {
        File inputFile = new File(inputFilePath);
        List<Integer> gamePowers = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(inputFile)) {
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Processing line: " + currentLine);

                String[] cubeSets = currentLine.split(":")[1].split(";");
                int gameRound = Integer.parseInt(currentLine.split(":")[0].split(" ")[1]);

                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Processing round: " + gameRound);

                Map<String, Integer> minimumCubesRequired = calculateMinimumCubesForGame(cubeSets);
                int gamePower = calculateGamePower(minimumCubesRequired);

                gamePowers.add(gamePower);
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, GAME_PREFIX + gameRound + " power: " + gamePower);
            }
        } catch (FileNotFoundException _) {
            logOutput(LogLevel.ERROR, true, "Input file not found: " + inputFilePath);
        }

        int totalPowerSum = gamePowers.stream().mapToInt(Integer::intValue).sum();
        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Total power sum: " + totalPowerSum);
        return totalPowerSum;
    }

    /**
     * Checks if a cube set is possible given the maximum cube constraints.
     */
    private static boolean isCubeSetPossible(String cubeSet, Map<String, Integer> maximumCubesByColor) {
        String[] cubesWithAmounts = cubeSet.split(",");
        Map<String, Integer> cubesInCurrentSet = new HashMap<>();

        for (String cubeWithAmount : cubesWithAmounts) {
            String trimmedCubeInfo = cubeWithAmount.trim();
            String cubeColor = trimmedCubeInfo.split(" ")[1];
            Integer cubeAmount = Integer.parseInt(trimmedCubeInfo.split(" ")[0]);

            cubesInCurrentSet.put(cubeColor, cubeAmount);

            int currentColorAmount = cubesInCurrentSet.get(cubeColor);
            int maximumAllowedForColor = maximumCubesByColor.get(cubeColor);

            if (currentColorAmount > maximumAllowedForColor) {
                logOutput(
                        LogLevel.VERBOSE,
                        ENABLE_VERBOSE_LOGGING,
                        "Cube set impossible: " + cubeColor + " has " + currentColorAmount + " but max allowed is "
                                + maximumAllowedForColor);
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the minimum cubes required for a game across all sets.
     */
    private static Map<String, Integer> calculateMinimumCubesForGame(String[] cubeSets) {
        Map<String, Integer> minimumCubesRequired = new HashMap<>();
        minimumCubesRequired.put(COLOR_GREEN, 0);
        minimumCubesRequired.put(COLOR_RED, 0);
        minimumCubesRequired.put(COLOR_BLUE, 0);

        for (String cubeSet : cubeSets) {
            String[] cubesWithAmounts = cubeSet.split(",");
            Map<String, Integer> cubesInCurrentSet = initializeCubeMap();

            for (String cubeWithAmount : cubesWithAmounts) {
                String trimmedCubeInfo = cubeWithAmount.trim();
                String cubeColor = trimmedCubeInfo.split(" ")[1];
                Integer cubeAmount = Integer.parseInt(trimmedCubeInfo.split(" ")[0]);

                int totalColorAmount = cubesInCurrentSet.get(cubeColor) + cubeAmount;
                cubesInCurrentSet.put(cubeColor, totalColorAmount);
            }

            // Update minimum required cubes if current set has higher amounts
            minimumCubesRequired.replaceAll((colorKey, currentMinimum) ->
                    Math.max(currentMinimum, cubesInCurrentSet.getOrDefault(colorKey, currentMinimum)));
        }

        logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Minimum cubes required: " + minimumCubesRequired);
        return minimumCubesRequired;
    }

    /**
     * Calculates the power of a game by multiplying all minimum cube amounts.
     */
    private static int calculateGamePower(Map<String, Integer> minimumCubesRequired) {
        int gamePower =
                minimumCubesRequired.values().stream().reduce(1, (accumulator, cubeAmount) -> accumulator * cubeAmount);
        logOutput(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Calculated game power: " + gamePower);
        return gamePower;
    }

    /**
     * Creates the maximum cube constraints for Part 1.
     */
    private static Map<String, Integer> createMaximumCubeConstraints() {
        Map<String, Integer> maximumCubesByColor = new HashMap<>();
        maximumCubesByColor.put(COLOR_RED, 12);
        maximumCubesByColor.put(COLOR_GREEN, 13);
        maximumCubesByColor.put(COLOR_BLUE, 14);
        return maximumCubesByColor;
    }

    /**
     * Initializes a cube map with all colors set to zero.
     */
    private static Map<String, Integer> initializeCubeMap() {
        Map<String, Integer> cubeMap = new HashMap<>();
        cubeMap.put(COLOR_GREEN, 0);
        cubeMap.put(COLOR_RED, 0);
        cubeMap.put(COLOR_BLUE, 0);
        return cubeMap;
    }
}
