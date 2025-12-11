package org.jmitchell238.aoc.aoc2023.day10;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import org.jmitchell238.aoc.aoc2023.utilities.grid.FloodFill;
import org.jmitchell238.aoc.generalutilities.LogLevel;

@SuppressWarnings({"java:S106", "java:S1118", "java:S1940", "java:S2589", "java:S100", "java:S3776", "java:S127"})
public class Day10 {
    // Configuration flags
    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings({"ConstantConditions", "unused"})
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    @SuppressWarnings({"ConstantConditions", "unused"})
    private static final boolean DRAW_MAP = true;

    // Constants for duplicated literals
    private static final String START_POINT_LOG = "Start Point: ";

    @Getter
    @Setter
    private Boolean isPartTwo = false;

    ArrayList<char[]> char2dArrayList = new ArrayList<>();
    Map<Point, Character> grid = new HashMap<>();
    Map<Point, Character> doubledGrid = new HashMap<>();
    Map<Point, Character> doubledGridFilledIn = new HashMap<>();
    ArrayList<Point> pipesPathPoints = new ArrayList<>();
    ArrayList<Point> pipesPathPoints2 = new ArrayList<>();

    // Java 25-friendly main; suppress unused args
    @SuppressWarnings({"unused", "java:S1172"})
    public static void main(String[] args) {
        new Day10().runDay10();
    }

    public void runDay10() {
        logOutput(LogLevel.INFO, true, "\n--- Day 10: Pipe Maze ---\n");

        String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day10/input.txt";

        long partOneAnswer = part1(input);
        logOutput(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        long partTwoAnswer = part2(input);
        logOutput(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    public long part1(String filePath) {
        createCoordinateMap(filePath);

        int totalSteps = countTotalSteps();
        int stepsToCenter = totalSteps / 2;

        if (DRAW_MAP) {
            drawMap();
        }

        reset();
        return stepsToCenter;
    }

    public long part2(String filePath) {
        createCoordinateMap(filePath);
        addPipesToArrayList();
        addOddRowsAndColumnsToDoubledGrid();

        if (DRAW_MAP) {
            drawMap3();
        }

        FloodFill floodFill = new FloodFill(doubledGridFilledIn, pipesPathPoints2);

        return floodFill.countPointsInsidePipe(new Point(148, 154));
    }

    @SuppressWarnings("java:S112")
    private void createCoordinateMap(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                char2dArrayList.add(scanner.nextLine().toCharArray());
            }
        } catch (FileNotFoundException fileNotFound) {
            String errorMessage = "Input file not found: " + filePath;
            logOutput(LogLevel.ERROR, true, errorMessage);
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "FileNotFoundException: " + fileNotFound.getMessage());
            throw new RuntimeException(fileNotFound);
        }

        for (int y = 0; y < char2dArrayList.size(); y++) {
            char[] row = char2dArrayList.get(y);
            for (int x = 0; x < row.length; x++) {
                Point originalPoint = new Point(x, y);
                Character originalChar = row[x];

                // Add to the original grid
                grid.put(originalPoint, originalChar);

                // Double the coordinates and add to the doubled grid
                doubledGrid.put(new Point(x * 2, y * 2), originalChar);

                if (originalChar == 'S') {
                    logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, START_POINT_LOG + originalPoint);
                    logOutput(
                            LogLevel.DEBUG,
                            ENABLE_DEBUG_LOGGING,
                            "Start Point in double map: " + new Point(x * 2, y * 2));
                }
            }
        }

        for (int y = 0; y < char2dArrayList.size(); y++) {
            for (int x = 0; x < char2dArrayList.getFirst().length; x++) {
                Point currentPoint = new Point(x, y);
                Character currentCharacter = grid.get(currentPoint);
                // Print via logger to maintain consistency
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, String.valueOf(currentCharacter));
            }
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "\n");
        }

        Point startPoint = grid.keySet().stream()
                .filter(p -> grid.get(p) == 'S')
                .findFirst()
                .orElseThrow();
        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, START_POINT_LOG + startPoint);

        addSurroundingPoints(char2dArrayList.getFirst().length, char2dArrayList.size());
    }

    private void addOddRowsAndColumnsToDoubledGrid() {
        // Copy values from doubledGrid to doubledGridFilledIn
        doubledGridFilledIn.putAll(doubledGrid);

        pipesPathPoints2.add(new Point(148, 165));
        doubledGridFilledIn.put(new Point(148, 165), '|');

        for (int x = 0; x < char2dArrayList.getFirst().length * 2; x++) {
            for (int y = 0; y < char2dArrayList.size() * 2; y++) {
                Point currentPoint = new Point(x, y);

                if (doubledGridFilledIn.get(currentPoint) == null) {
                    Point pointLeft = new Point(x - 1, y);
                    Point pointAbove = new Point(x, y - 1);

                    if (doubledGridFilledIn.get(pointLeft) == null) {
                        doubledGridFilledIn.put(currentPoint, '.');
                    } else {
                        Character charLeft = doubledGridFilledIn.get(pointLeft);
                        if (charLeft == 'F' || charLeft == '-' || charLeft == 'L') {
                            doubledGridFilledIn.put(currentPoint, '-');
                        } else if (doubledGridFilledIn.get(pointAbove) == null) {
                            doubledGridFilledIn.put(currentPoint, '.');
                        } else {
                            Character charAbove = doubledGridFilledIn.get(pointAbove);
                            if (charAbove == 'F' || charAbove == '7' || charAbove == '|' || charAbove == 'S') {
                                doubledGridFilledIn.put(currentPoint, '|');
                            } else {
                                doubledGridFilledIn.put(currentPoint, '.');
                            }
                        }
                    }
                }
            }
        }
    }

    private void addSurroundingPoints(int xLength, int yLength) {
        // Add row of '.'s to the top and bottom of the grid
        for (int i = -1; i <= yLength; i++) {
            Point topRowPoint = new Point(i, -1);
            Point bottomRowPoint = new Point(i, yLength);

            grid.put(topRowPoint, '.');
            grid.put(bottomRowPoint, '.');
        }

        // Add column of '.'s to the left and right of the grid
        for (int i = -1; i <= xLength + 1; i++) {
            Point leftColumnPoint = new Point(-1, i);
            Point rightColumnPoint = new Point(xLength, i);
            Point rightColumnPoint2 = new Point(xLength + 1, i);

            grid.put(leftColumnPoint, '.');
            grid.put(rightColumnPoint, '.');
            grid.put(rightColumnPoint2, '.');
        }
    }

    private int countTotalSteps() {
        Point startPoint = findStartPoint(grid);
        Point previousPoint = startPoint;
        int steps = 0;

        // Find first valid direction from start
        Point currentPoint = findFirstValidDirection(startPoint);
        if (currentPoint == null) {
            return 0;
        }

        steps++;
        addPipePointToArrayList(currentPoint);

        // Traverse the pipe loop
        boolean continueLoop = true;
        while (steps > 0 && continueLoop) {
            if (grid.get(currentPoint) == 'S' && steps > 1) {
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Found Start Point. Steps: " + steps);
                continueLoop = false;
            } else {
                Point nextPoint = getNextPoint(currentPoint, previousPoint);
                if (nextPoint == null) {
                    continueLoop = false;
                } else {
                    steps++;
                    previousPoint = currentPoint;
                    currentPoint = nextPoint;
                    addPipePointToArrayList(currentPoint);
                }
            }
        }

        return steps;
    }

    private Point findStartPoint(Map<Point, Character> gridMap) {
        return gridMap.keySet().stream()
                .filter(p -> gridMap.get(p) == 'S')
                .findFirst()
                .orElseThrow();
    }

    private Point findFirstValidDirection(Point startPoint) {
        return findFirstValidDirectionFromGrid(startPoint, grid, 1);
    }

    private Point findFirstValidDirectionFromGrid(Point startPoint, Map<Point, Character> gridMap, int stepSize) {
        Point pointAbove = new Point(startPoint.x, startPoint.y - stepSize);
        char charAbove = gridMap.getOrDefault(pointAbove, ' ');
        Point pointBelow = new Point(startPoint.x, startPoint.y + stepSize);
        char charBelow = gridMap.getOrDefault(pointBelow, ' ');
        Point pointLeft = new Point(startPoint.x - stepSize, startPoint.y);
        char charLeft = gridMap.getOrDefault(pointLeft, ' ');
        Point pointRight = new Point(startPoint.x + stepSize, startPoint.y);
        char charRight = gridMap.getOrDefault(pointRight, ' ');

        if (charAbove == '|' || charAbove == '7' || charAbove == 'F') {
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Starting to traverse pipes.");
            return pointAbove;
        } else if (charRight == '-' || charRight == '7' || charRight == 'J') {
            return pointRight;
        } else if (charBelow == '|' || charBelow == 'J' || charBelow == 'L') {
            return pointBelow;
        } else if (charLeft == '-' || charLeft == 'F' || charLeft == 'L') {
            return pointLeft;
        }
        return null;
    }

    private Point getNextPoint(Point currentPoint, Point previousPoint) {
        return getNextPointFromGrid(currentPoint, previousPoint, grid, 1);
    }

    private Point getNextPointFromGrid(
            Point currentPoint, Point previousPoint, Map<Point, Character> gridMap, int stepSize) {
        Point pointAbove = new Point(currentPoint.x, currentPoint.y - stepSize);
        Point pointBelow = new Point(currentPoint.x, currentPoint.y + stepSize);
        Point pointLeft = new Point(currentPoint.x - stepSize, currentPoint.y);
        Point pointRight = new Point(currentPoint.x + stepSize, currentPoint.y);

        char currentChar = gridMap.get(currentPoint);
        boolean pointAboveIsPrevious = Objects.equals(pointAbove.toString(), previousPoint.toString());
        boolean pointLeftIsPrevious = Objects.equals(pointLeft.toString(), previousPoint.toString());
        boolean pointRightIsPrevious = Objects.equals(pointRight.toString(), previousPoint.toString());

        return switch (currentChar) {
            case '|' -> pointAboveIsPrevious ? pointBelow : pointAbove;
            case '-' -> pointLeftIsPrevious ? pointRight : pointLeft;
            case '7' -> pointLeftIsPrevious ? pointBelow : pointLeft;
            case 'J' -> pointLeftIsPrevious ? pointAbove : pointLeft;
            case 'L' -> pointRightIsPrevious ? pointAbove : pointRight;
            case 'F' -> pointRightIsPrevious ? pointBelow : pointRight;
            default -> null;
        };
    }

    private void addPipesToArrayList() {
        Point startPoint = findStartPoint(doubledGrid);
        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, START_POINT_LOG + startPoint);

        Point previousPoint = startPoint;
        int steps = 0;

        // Find first valid direction from start in doubled grid
        Point currentPoint = findFirstValidDirectionFromGrid(startPoint, doubledGrid, 2);
        if (currentPoint != null) {
            steps++;
            Point intermediatePoint = new Point(
                    startPoint.x + (currentPoint.x - startPoint.x) / 2,
                    startPoint.y + (currentPoint.y - startPoint.y) / 2);
            addPipePointToArrayList2(currentPoint);
            addPipePointToArrayList2(intermediatePoint);

            // Add the pipe below start point (specific to this problem)
            Point pointBelow2 = new Point(currentPoint.x, currentPoint.y + 1);
            addPipePointToArrayList2(pointBelow2);
        } else {
            return; // No valid direction found
        }

        // Traverse the doubled grid pipe loop
        boolean continueLoop = true;
        while (steps > 0 && continueLoop) {
            if (doubledGrid.get(currentPoint) == 'S' && steps > 1) {
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Found Start Point. Steps: " + steps);
                continueLoop = false;
            } else {
                Point nextPoint = getNextPointFromGrid(currentPoint, previousPoint, doubledGrid, 2);
                if (nextPoint == null) {
                    continueLoop = false;
                } else {
                    steps++;
                    Point intermediatePoint = new Point(
                            currentPoint.x + (nextPoint.x - currentPoint.x) / 2,
                            currentPoint.y + (nextPoint.y - currentPoint.y) / 2);
                    previousPoint = currentPoint;
                    currentPoint = nextPoint;
                    addPipePointToArrayList2(intermediatePoint);
                    addPipePointToArrayList2(currentPoint);
                }
            }
        }
    }

    private void addPipePointToArrayList(Point point) {
        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Adding Point: " + point + " to ArrayList");
        pipesPathPoints.add(point);
    }

    private void addPipePointToArrayList2(Point point) {
        pipesPathPoints2.add(point);
    }

    private void drawMap() {
        for (int y = 0; y < char2dArrayList.size(); y++) {
            StringBuilder lineBuilder = new StringBuilder();
            for (int x = 0; x < char2dArrayList.getFirst().length; x++) {
                Point currentPoint = new Point(x, y);
                Character currentCharacter = grid.get(currentPoint);

                if (isPartOfPath(currentPoint)) {
                    lineBuilder.append(mapCharacterToVisual(currentCharacter));
                } else {
                    lineBuilder.append('.');
                }
            }

            logOutput(LogLevel.INFO, true, lineBuilder.toString());
        }
    }

    private void drawMap3() {
        for (int y = 0; y < char2dArrayList.size() * 2; y++) {
            StringBuilder lineBuilder = new StringBuilder();
            for (int x = 0; x < char2dArrayList.getFirst().length * 2; x++) {
                Point currentPoint = new Point(x, y);
                Character currentCharacter = doubledGridFilledIn.get(currentPoint);

                if (currentPoint.x == 148 && currentPoint.y == 165) {
                    lineBuilder.append('&');
                    continue;
                }

                if (isPartOfPath2(currentPoint)) {
                    lineBuilder.append(mapCharacterToVisual(currentCharacter));
                } else {
                    lineBuilder.append('.');
                }
            }

            logOutput(LogLevel.INFO, true, lineBuilder.toString());
        }
    }

    private char mapCharacterToVisual(Character character) {
        return switch (character) {
            case 'S' -> 'S';
            case 'F' -> '╔';
            case '7' -> '╗';
            case 'J' -> '╝';
            case 'L' -> '╚';
            case '|' -> '║';
            case '-' -> '═';
            case '.' -> ' ';
            default -> '?';
        };
    }

    private boolean isPartOfPath(Point point) {
        return pipesPathPoints.contains(point);
    }

    private boolean isPartOfPath2(Point point) {
        return pipesPathPoints2.contains(point);
    }

    private void reset() {
        char2dArrayList = new ArrayList<>();
        grid = new HashMap<>();
        doubledGrid = new HashMap<>();
        doubledGridFilledIn = new HashMap<>();
        pipesPathPoints = new ArrayList<>();
        pipesPathPoints2 = new ArrayList<>();
    }
}
