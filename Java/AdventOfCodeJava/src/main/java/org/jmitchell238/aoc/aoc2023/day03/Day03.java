package org.jmitchell238.aoc.aoc2023.day03;

import static java.lang.Character.isDigit;
import static org.jmitchell238.aoc.aoc2025.utilities.Utilities2025.log;
import static org.jmitchell238.aoc.aoc2025.utilities.Utilities2025.logError;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.jmitchell238.aoc.generalutilities.LogLevel;

/**
 * Advent of Code 2023 - Day 3: Gear Ratios
 * <p>
 * This class contains the solution logic for Day 3 of Advent of Code 2023.
 * Part 1 finds part numbers adjacent to symbols and sums them.
 * Part 2 finds gears (asterisks adjacent to exactly two part numbers) and calculates gear ratios.
 * </p>
 */
@SuppressWarnings({
    "java:S106",
    "java:S1118",
    "java:S1940",
    "java:S2589",
    "java:S100",
    "java:S3776",
    "java:S127",
    "java:S1143"
})
public class Day03 {

    // Configuration flags
    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    // Constants
    private static final char EMPTY_SYMBOL = '.';
    private static final char GEAR_SYMBOL = '*';
    private static final int MAX_PART_NUMBER_LENGTH = 5;

    // State variables
    private static Map<Point, Character> gridCoordinateMap;
    private static Map<Long, ArrayList<ArrayList<Point>>> partNumberLocationMap;
    private static ArrayList<Point> gearCandidatePositions;

    @SuppressWarnings("unused")
    public static void main(String[] args) throws FileNotFoundException {
        runDay03();
    }

    /**
     * Entry point for Day 3 solution.
     */
    public static void runDay03() throws FileNotFoundException {
        System.out.println("\n--- Day 03: Gear Ratios ---\n");

        String actualInputFilePath = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input.txt";

        Long partOneAnswer = solvePart1(actualInputFilePath);
        log(LogLevel.INFO, true, "Part 1: Answer: Part Number Sum = " + partOneAnswer);

        Long partTwoAnswer = solvePart2(actualInputFilePath);
        log(LogLevel.INFO, true, "Part 2: Answer: Gear Ratio Sum = " + partTwoAnswer);
    }

    /**
     * Solves Part 1 by finding all part numbers adjacent to symbols.
     */
    public static Long solvePart1(String inputFilePath) throws FileNotFoundException {
        Long partNumberSum = 0L;

        try {
            File inputFile = new File(inputFilePath);
            Scanner fileScanner = new Scanner(inputFile);

            ArrayList<char[]> engineSchematic = new ArrayList<>();
            gridCoordinateMap = createGridCoordinateMap(fileScanner, engineSchematic);

            partNumberSum = calculatePartNumberSum(engineSchematic, partNumberSum);

            log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Part Number Sum: " + partNumberSum);

            return partNumberSum;
        } catch (FileNotFoundException fileNotFound) {
            String errorMessage = "Input file not found: " + inputFilePath;
            logError(errorMessage);
            log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "FileNotFoundException: " + fileNotFound.getMessage());
            return -1L;
        }
    }

    /**
     * Solves Part 2 by finding gear ratios for asterisks adjacent to exactly two part numbers.
     */
    public static Long solvePart2(String inputFilePath) {
        try {
            File inputFile = new File(inputFilePath);
            Scanner fileScanner = new Scanner(inputFile);
            ArrayList<char[]> engineSchematic = new ArrayList<>();
            gridCoordinateMap = createGridCoordinateMap(fileScanner, engineSchematic);
            partNumberLocationMap = buildPartNumberLocationMap(engineSchematic, gridCoordinateMap);

            long totalGearRatioSum = 0L;

            Map<Point, ArrayList<Long>> gearToPartNumbersMap = new HashMap<>();

            for (Point gearPosition : gearCandidatePositions) {
                gearToPartNumbersMap.put(gearPosition, new ArrayList<>());
            }

            for (Long partNumber : partNumberLocationMap.keySet()) {
                ArrayList<ArrayList<Point>> partNumberInstances = partNumberLocationMap.get(partNumber);

                for (ArrayList<Point> partNumberPointsList : partNumberInstances) {
                    boolean partNumberProcessed = false;

                    for (Point partNumberPoint : partNumberPointsList) {
                        Point adjacentGearPosition = findAdjacentGearPosition(partNumberPoint, gridCoordinateMap);

                        if (adjacentGearPosition != null) {
                            gearToPartNumbersMap.get(adjacentGearPosition).add(partNumber);
                            partNumberProcessed = true;
                        }

                        if (partNumberProcessed) {
                            break;
                        }
                    }
                }
            }

            for (Point gearPosition : gearToPartNumbersMap.keySet()) {
                ArrayList<Long> adjacentPartNumbers = gearToPartNumbersMap.get(gearPosition);

                log(
                        LogLevel.DEBUG,
                        ENABLE_DEBUG_LOGGING,
                        "Gear at " + gearPosition + " has adjacent part numbers: " + adjacentPartNumbers);

                if (adjacentPartNumbers.size() == 2) {
                    long gearRatio = adjacentPartNumbers.get(0) * adjacentPartNumbers.get(1);
                    totalGearRatioSum += gearRatio;

                    log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Valid gear found! Ratio: " + gearRatio);
                }
            }

            log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Total Gear Ratio Sum: " + totalGearRatioSum);

            return totalGearRatioSum;
        } catch (FileNotFoundException fileNotFound) {
            String errorMessage = "Input file not found: " + inputFilePath;
            System.err.println(errorMessage);
            log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "FileNotFoundException: " + fileNotFound.getMessage());
            return -1L;
        }
    }

    /**
     * Calculates the sum of all valid part numbers in the engine schematic.
     */
    private static Long calculatePartNumberSum(ArrayList<char[]> engineSchematic, Long currentPartNumberSum) {
        for (int rowIndex = 0; rowIndex < engineSchematic.size(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < engineSchematic.getFirst().length - 1; columnIndex++) {
                Point currentPosition = new Point(columnIndex, rowIndex);
                char currentCharacter = gridCoordinateMap.get(currentPosition);
                boolean isCurrentCharacterDigit = isDigit(currentCharacter);

                StringBuilder partNumberBuilder = new StringBuilder();
                int partNumberLength = 0;
                long partNumberValue;

                if (isCurrentCharacterDigit) {
                    for (int digitIndex = 0; digitIndex < MAX_PART_NUMBER_LENGTH; digitIndex++) {
                        partNumberBuilder.append(currentCharacter);
                        partNumberLength++;
                        currentCharacter = gridCoordinateMap.get(
                                new Point(currentPosition.x + partNumberLength, currentPosition.y));
                        if (currentCharacter == EMPTY_SYMBOL || !isDigit(currentCharacter)) {
                            break;
                        }
                    }

                    log(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Found potential part number: " + partNumberBuilder);

                    String partNumberString = partNumberBuilder.toString();
                    partNumberValue = Long.parseLong(partNumberString);
                    columnIndex += partNumberLength - 1;

                    if (isPartNumberValid(currentPosition, partNumberLength)) {
                        currentPartNumberSum += partNumberValue;
                        log(
                                LogLevel.DEBUG,
                                ENABLE_DEBUG_LOGGING,
                                "Valid part number: " + partNumberValue + " (sum now: " + currentPartNumberSum + ")");
                    }
                }
            }
        }

        return currentPartNumberSum;
    }

    /**
     * Determines if a part number is valid by checking if it's adjacent to any symbol.
     */
    private static boolean isPartNumberValid(Point startPosition, int partNumberLength) {
        return isSymbolToLeft(startPosition)
                || isSymbolToRight(startPosition, partNumberLength)
                || hasSymbolInRowAbove(startPosition, partNumberLength)
                || hasSymbolInRowBelow(startPosition, partNumberLength);
    }

    /**
     * Checks if there's a symbol to the left of the part number.
     */
    private static boolean isSymbolToLeft(Point startPosition) {
        Point leftPosition = new Point(startPosition.x - 1, startPosition.y);
        char leftCharacter = gridCoordinateMap.get(leftPosition);
        return leftCharacter != EMPTY_SYMBOL && !isDigit(leftCharacter);
    }

    /**
     * Checks if there's a symbol to the right of the part number.
     */
    private static boolean isSymbolToRight(Point startPosition, int partNumberLength) {
        try {
            Point rightPosition = new Point(startPosition.x + partNumberLength, startPosition.y);
            char rightCharacter = gridCoordinateMap.get(rightPosition);
            return rightCharacter != EMPTY_SYMBOL && !isDigit(rightCharacter);
        } catch (Exception _) {
            log(
                    LogLevel.VERBOSE,
                    ENABLE_VERBOSE_LOGGING,
                    "Part number is at the end of the line at position: " + startPosition);
            return false;
        }
    }

    /**
     * Checks if there's a symbol in the row above the part number.
     */
    private static boolean hasSymbolInRowAbove(Point startPosition, int partNumberLength) {
        for (int columnOffset = startPosition.x - 1;
                columnOffset <= startPosition.x + partNumberLength;
                columnOffset++) {
            Point abovePosition = new Point(columnOffset, startPosition.y - 1);
            char aboveCharacter = gridCoordinateMap.get(abovePosition);
            boolean isSymbolAbove = aboveCharacter != EMPTY_SYMBOL && !isDigit(aboveCharacter);
            if (isSymbolAbove) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there's a symbol in the row below the part number.
     */
    private static boolean hasSymbolInRowBelow(Point startPosition, int partNumberLength) {
        for (int columnOffset = startPosition.x - 1;
                columnOffset <= startPosition.x + partNumberLength;
                columnOffset++) {
            Point belowPosition = new Point(columnOffset, startPosition.y + 1);
            char belowCharacter = gridCoordinateMap.get(belowPosition);
            boolean isSymbolBelow = belowCharacter != EMPTY_SYMBOL && !isDigit(belowCharacter);
            if (isSymbolBelow) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds an adjacent gear (asterisk) position for a given part number point.
     */
    private static Point findAdjacentGearPosition(Point currentPosition, Map<Point, Character> coordinateMap) {
        ArrayList<Point> adjacentPositions = getAdjacentPositions(currentPosition);

        for (Point adjacentPosition : adjacentPositions) {
            char adjacentCharacter = coordinateMap.get(adjacentPosition);
            if (adjacentCharacter == GEAR_SYMBOL) {
                return adjacentPosition;
            }
        }
        return null;
    }

    /**
     * Gets all 8 adjacent positions around a given point.
     */
    private static ArrayList<Point> getAdjacentPositions(Point centerPosition) {
        ArrayList<Point> adjacentPositions = new ArrayList<>();

        // Add all 8 surrounding positions
        adjacentPositions.add(new Point(centerPosition.x - 1, centerPosition.y - 1)); // top-left
        adjacentPositions.add(new Point(centerPosition.x - 1, centerPosition.y)); // left
        adjacentPositions.add(new Point(centerPosition.x - 1, centerPosition.y + 1)); // bottom-left
        adjacentPositions.add(new Point(centerPosition.x + 1, centerPosition.y - 1)); // top-right
        adjacentPositions.add(new Point(centerPosition.x + 1, centerPosition.y)); // right
        adjacentPositions.add(new Point(centerPosition.x + 1, centerPosition.y + 1)); // bottom-right
        adjacentPositions.add(new Point(centerPosition.x, centerPosition.y - 1)); // top
        adjacentPositions.add(new Point(centerPosition.x, centerPosition.y + 1)); // bottom

        return adjacentPositions;
    }

    /**
     * Creates a coordinate map from the input file, adding padding around the grid.
     */
    private static Map<Point, Character> createGridCoordinateMap(
            Scanner fileScanner, ArrayList<char[]> engineSchematic) {
        gridCoordinateMap = new HashMap<>();
        gearCandidatePositions = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            engineSchematic.add(fileScanner.nextLine().toCharArray());
        }

        int gridHeight = engineSchematic.size();
        int gridWidth = engineSchematic.getFirst().length;

        // Add padding rows (top and bottom)
        addHorizontalPadding(gridHeight, gridWidth);

        // Add padding columns (left and right)
        addVerticalPadding(gridHeight, gridWidth);

        // Process the actual grid data
        populateGridFromSchematic(engineSchematic);

        logGridIfDebuggingEnabled(gridHeight, gridWidth);

        return gridCoordinateMap;
    }

    /**
     * Adds horizontal padding (top and bottom rows) to the coordinate map.
     */
    private static void addHorizontalPadding(int gridHeight, int gridWidth) {
        for (int columnIndex = -1; columnIndex <= gridWidth; columnIndex++) {
            Point topPaddingPoint = new Point(columnIndex, -1);
            Point bottomPaddingPoint = new Point(columnIndex, gridHeight);

            gridCoordinateMap.put(topPaddingPoint, EMPTY_SYMBOL);
            gridCoordinateMap.put(bottomPaddingPoint, EMPTY_SYMBOL);
        }
    }

    /**
     * Adds vertical padding (left and right columns) to the coordinate map.
     */
    private static void addVerticalPadding(int gridHeight, int gridWidth) {
        for (int rowIndex = -1; rowIndex <= gridHeight + 1; rowIndex++) {
            Point leftPaddingPoint = new Point(-1, rowIndex);
            Point rightPaddingPoint = new Point(gridWidth, rowIndex);
            Point extraRightPaddingPoint = new Point(gridWidth + 1, rowIndex);

            gridCoordinateMap.put(leftPaddingPoint, EMPTY_SYMBOL);
            gridCoordinateMap.put(rightPaddingPoint, EMPTY_SYMBOL);
            gridCoordinateMap.put(extraRightPaddingPoint, EMPTY_SYMBOL);
        }
    }

    /**
     * Populates the coordinate map with actual data from the engine schematic.
     */
    private static void populateGridFromSchematic(ArrayList<char[]> engineSchematic) {
        for (int rowIndex = 0; rowIndex < engineSchematic.size(); rowIndex++) {
            char[] currentRow = engineSchematic.get(rowIndex);

            for (int columnIndex = 0; columnIndex < currentRow.length; columnIndex++) {
                char currentCharacter = currentRow[columnIndex];
                Point currentPosition = new Point(columnIndex, rowIndex);

                gridCoordinateMap.put(currentPosition, currentCharacter);

                if (currentCharacter == GEAR_SYMBOL) {
                    gearCandidatePositions.add(currentPosition);
                    log(
                            LogLevel.DEBUG,
                            ENABLE_DEBUG_LOGGING,
                            "Found gear candidate at: " + columnIndex + ", " + rowIndex);
                }
            }
        }
    }

    /**
     * Logs the grid if debugging is enabled.
     */
    private static void logGridIfDebuggingEnabled(int gridHeight, int gridWidth) {
        if (ENABLE_DEBUG_LOGGING) {
            logGridToConsole(gridCoordinateMap, gridHeight, gridWidth);
        } else if (ENABLE_VERBOSE_LOGGING) {
            logGridToConsoleVerbose(gridCoordinateMap, gridHeight, gridWidth);
        }
    }

    /**
     * Builds a map of part numbers to their coordinate locations.
     */
    private static Map<Long, ArrayList<ArrayList<Point>>> buildPartNumberLocationMap(
            ArrayList<char[]> engineSchematic, Map<Point, Character> coordinateMap) {
        partNumberLocationMap = new HashMap<>();

        for (int rowIndex = 0; rowIndex < engineSchematic.size(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < engineSchematic.getFirst().length - 1; columnIndex++) {
                Point currentPosition = new Point(columnIndex, rowIndex);
                char currentCharacter = coordinateMap.get(currentPosition);
                StringBuilder partNumberBuilder = new StringBuilder();
                int partNumberLength = 0;
                Long partNumberValue;
                ArrayList<Point> partNumberCoordinates = new ArrayList<>();

                boolean isCurrentCharacterDigit = isDigit(currentCharacter);

                if (isCurrentCharacterDigit) {
                    for (int digitIndex = 0; digitIndex < MAX_PART_NUMBER_LENGTH; digitIndex++) {
                        partNumberBuilder.append(currentCharacter);
                        partNumberLength++;
                        currentCharacter =
                                coordinateMap.get(new Point(currentPosition.x + partNumberLength, currentPosition.y));

                        Point digitPosition = new Point(currentPosition.x + digitIndex, currentPosition.y);
                        partNumberCoordinates.add(digitPosition);

                        if (currentCharacter == EMPTY_SYMBOL || !isDigit(currentCharacter)) {
                            break;
                        }
                    }

                    partNumberValue = Long.parseLong(partNumberBuilder.toString());

                    if (partNumberLocationMap.containsKey(partNumberValue)) {
                        partNumberLocationMap.get(partNumberValue).add(partNumberCoordinates);
                    } else {
                        ArrayList<ArrayList<Point>> partNumberCoordinatesList = new ArrayList<>();
                        partNumberCoordinatesList.add(partNumberCoordinates);
                        partNumberLocationMap.put(partNumberValue, partNumberCoordinatesList);
                    }

                    log(
                            LogLevel.VERBOSE,
                            ENABLE_VERBOSE_LOGGING,
                            "Mapped part number: " + partNumberBuilder + " at coordinates: " + partNumberCoordinates);

                    columnIndex += partNumberLength - 1;
                }
            }
        }

        return partNumberLocationMap;
    }

    /**
     * Logs the coordinate map to console for debugging.
     */
    private static void logGridToConsole(Map<Point, Character> coordinateMap, int gridHeight, int gridWidth) {
        log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Grid layout:");

        for (int rowIndex = -1; rowIndex <= gridHeight; rowIndex++) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int columnIndex = -1; columnIndex <= gridWidth; columnIndex++) {
                Point currentPosition = new Point(columnIndex, rowIndex);
                Character currentCharacter = coordinateMap.get(currentPosition);
                rowBuilder.append(currentCharacter);
            }
            log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, rowBuilder.toString());
        }
    }

    /**
     * Logs the coordinate map to console with verbose coordinate information.
     */
    private static void logGridToConsoleVerbose(Map<Point, Character> coordinateMap, int gridHeight, int gridWidth) {
        for (int rowIndex = -1; rowIndex <= gridHeight; rowIndex++) {
            for (int columnIndex = -1; columnIndex <= gridWidth; columnIndex++) {
                Point currentPosition = new Point(columnIndex, rowIndex);
                Character currentCharacter = coordinateMap.get(currentPosition);
                log(
                        LogLevel.VERBOSE,
                        ENABLE_VERBOSE_LOGGING,
                        String.format("Coordinate (%d,%d) = %c", columnIndex, rowIndex, currentCharacter));
            }
        }
    }
}
