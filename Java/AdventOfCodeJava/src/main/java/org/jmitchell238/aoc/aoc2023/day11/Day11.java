package org.jmitchell238.aoc.aoc2023.day11;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import org.jmitchell238.aoc.aoc2023.utilities.galaxies.Galaxies;
import org.jmitchell238.aoc.aoc2023.utilities.galaxies.Galaxy;
import org.jmitchell238.aoc.aoc2023.utilities.galaxies.GalaxyPair;
import org.jmitchell238.aoc.aoc2023.utilities.galaxies.GalaxyPairList;
import org.jmitchell238.aoc.generalutilities.LogLevel;

@SuppressWarnings({"java:S106", "java:S1940", "java:S2589", "java:S1118"})
public class Day11 {
    // Configuration flags
    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings({"ConstantConditions", "unused"})
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    @Getter
    @Setter
    private Boolean isPartTwo = false;

    // Instance variables
    private ArrayList<char[]> characterArrayList = new ArrayList<>();
    private Map<Point, Character> map = new HashMap<>();
    private ArrayList<ArrayList<Character>> nextCharacterArrayList = new ArrayList<>();
    private Galaxies galaxies = new Galaxies();
    private int galaxyId = 1;
    private GalaxyPairList galaxyPairList = new GalaxyPairList();

    @SuppressWarnings("unused")
    public static void main(String[] args) throws FileNotFoundException {
        day11Run();
    }

    @SuppressWarnings("java:S100")
    public static void day11Run() throws FileNotFoundException {
        logOutput(LogLevel.INFO, true, "\n--- Day 11: Cosmic Expansion ---\n");

        String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day11/input.txt";

        Day11 day11 = new Day11();
        long partOneAnswer = day11.part1(input);
        logOutput(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        long expansionFactor = 1000000;
        long partTwoAnswer = day11.part2(input, expansionFactor);
        logOutput(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    @SuppressWarnings("unused")
    public long part1(String filePath) throws FileNotFoundException {
        processFile(filePath);

        expandUniverse();
        convertToGrid();
        findGalaxies();
        findAllGalaxyPairs();

        if (ENABLE_DEBUG_LOGGING) {
            printGrid();
        }

        long sumOfShortestDistances = galaxyPairList.getSumOfShortestDistances();

        reset();

        return sumOfShortestDistances;
    }

    @SuppressWarnings("unused")
    public long part2(String filePath, long expansionFactor) throws FileNotFoundException {
        processFile(filePath);

        convertToGridPart2(expansionFactor);
        findAllGalaxyPairs();

        return galaxyPairList.getSumOfShortestDistances();
    }

    private void processFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));

        while (scanner.hasNextLine()) {
            characterArrayList.add(scanner.nextLine().toCharArray());
        }

        scanner.close();
    }

    private void expandUniverse() {
        // Expand/Add Rows
        ArrayList<Integer> rowsToExpand = getRowsToExpand();

        List<Integer> reversedRowsToExpand = rowsToExpand.reversed();

        for (int row : reversedRowsToExpand) {
            nextCharacterArrayList.add(
                    row, new ArrayList<>(Collections.nCopies(characterArrayList.get(row).length, '.')));
        }

        // Expand/Add Columns
        ArrayList<Integer> columnsToExpand = getColumnNumbersToExpand();

        List<Integer> reversedColumnsToExpand = columnsToExpand.reversed();

        for (ArrayList<Character> row : nextCharacterArrayList) {
            for (int column : reversedColumnsToExpand) {
                row.add(column, '.');
            }
        }
    }

    private void convertToGrid() {
        for (int y = 0; y < nextCharacterArrayList.size(); y++) {
            for (int x = 0; x < nextCharacterArrayList.get(y).size(); x++) {
                map.put(new Point(x, y), nextCharacterArrayList.get(y).get(x));
            }
        }
    }

    private void convertToGridPart2(long expansionFactor) {
        List<Integer> rowsToExpand = getRowsToExpand();
        List<Integer> columnsToExpand = getColumnNumbersToExpand();

        long xGridValue = 0;
        long yGridValue = 0;

        for (int y = 0; y < nextCharacterArrayList.size(); y++) {
            if (rowsToExpand.contains(y)) {
                yGridValue += expansionFactor;
            } else {
                yGridValue++;
            }

            for (int x = 0; x < nextCharacterArrayList.get(y).size(); x++) {
                if (columnsToExpand.contains(x)) {
                    xGridValue += expansionFactor;
                } else {
                    xGridValue++;
                }

                map.put(
                        new Point((int) xGridValue, (int) yGridValue),
                        nextCharacterArrayList.get(y).get(x));
                ifGalaxyAddToGalaxies(
                        xGridValue, yGridValue, nextCharacterArrayList.get(y).get(x));
            }

            xGridValue = 0;
        }
    }

    private boolean areAllCharactersTheSame(ArrayList<Character> characterList) {
        if (characterList.isEmpty()) {
            return true;
        }

        Character firstChar = characterList.getFirst();
        return characterList.stream().allMatch(c -> c.equals(firstChar));
    }

    private void ifGalaxyAddToGalaxies(long xGridValue, long yGridValue, Character character) {
        if (character == '#') {
            Galaxy galaxy = new Galaxy(galaxyId, new Point((int) xGridValue, (int) yGridValue));
            galaxies.addGalaxy(galaxy);
            galaxyId++;
        }
    }

    private ArrayList<Integer> getRowsToExpand() {
        ArrayList<Integer> rowsToExpand = new ArrayList<>();

        for (int y = 0; y < characterArrayList.size(); y++) {
            ArrayList<Character> characterList = new ArrayList<>();

            for (char c : characterArrayList.get(y)) {
                characterList.add(c);
            }

            nextCharacterArrayList.add(characterList);
            boolean allSame = areAllCharactersTheSame(characterList);

            if (allSame) {
                rowsToExpand.add(y);
            }
        }

        return rowsToExpand;
    }

    private ArrayList<Integer> getColumnNumbersToExpand() {
        ArrayList<Integer> columnNumbersToExpand = new ArrayList<>();

        for (int x = 0; x < nextCharacterArrayList.getFirst().size(); x++) {
            ArrayList<Character> charactersInColumn = new ArrayList<>();
            for (ArrayList<Character> row : nextCharacterArrayList) {
                charactersInColumn.add(row.get(x));
            }

            boolean allSame = areAllCharactersTheSame(charactersInColumn);
            if (allSame) {
                columnNumbersToExpand.add(x);
            }
        }

        return columnNumbersToExpand;
    }

    private void findGalaxies() {
        int localGalaxyId = 1;
        for (int y = 0; y < nextCharacterArrayList.size(); y++) {
            for (int x = 0; x < nextCharacterArrayList.get(y).size(); x++) {
                if (map.get(new Point(x, y)) == '#') {
                    Galaxy galaxy = new Galaxy(localGalaxyId, new Point(x, y));
                    galaxies.addGalaxy(galaxy);
                    localGalaxyId++;
                }
            }
        }
    }

    private void findAllGalaxyPairs() {
        for (int localGalaxyId = 1; localGalaxyId <= galaxies.getGalaxyList().size(); localGalaxyId++) {
            Galaxy galaxy1 = galaxies.getGalaxyById(localGalaxyId);

            for (int galaxyId2 = localGalaxyId + 1;
                    galaxyId2 <= galaxies.getGalaxyList().size();
                    galaxyId2++) {
                Galaxy galaxy2 = galaxies.getGalaxyById(galaxyId2);

                int distance = calculateShortestDistanceBetweenTwoGalaxies(galaxy1, galaxy2);

                GalaxyPair galaxyPair = new GalaxyPair(galaxy1, galaxy2, distance);
                galaxyPairList.addGalaxyPair(galaxyPair);
            }
        }
    }

    private int calculateShortestDistanceBetweenTwoGalaxies(Galaxy galaxy1, Galaxy galaxy2) {
        return calculateShortestDistanceBetweenTwoPoints(galaxy1.location(), galaxy2.location());
    }

    private int calculateShortestDistanceBetweenTwoPoints(Point point1, Point point2) {
        int xDistance = Math.abs(point1.x - point2.x);
        int yDistance = Math.abs(point1.y - point2.y);

        return xDistance + yDistance;
    }

    private void printGrid() {
        logOutput(LogLevel.INFO, true, "Original Universe:\n");

        for (char[] line : characterArrayList) {
            StringBuilder lineBuilder = new StringBuilder();
            for (char c : line) {
                lineBuilder.append(c);
            }
            logOutput(LogLevel.INFO, true, lineBuilder.toString());
        }

        logOutput(LogLevel.INFO, true, "\nExpanded Universe:\n");

        for (ArrayList<Character> line : nextCharacterArrayList) {
            StringBuilder lineBuilder = new StringBuilder();
            for (Character character : line) {
                lineBuilder.append(character);
            }
            logOutput(LogLevel.INFO, true, lineBuilder.toString());
        }

        logOutput(LogLevel.INFO, true, "\nGrid:\n");

        for (int y = 0; y < nextCharacterArrayList.size(); y++) {
            StringBuilder lineBuilder = new StringBuilder();
            for (int x = 0; x < nextCharacterArrayList.get(y).size(); x++) {
                lineBuilder.append(map.get(new Point(x, y)));
            }
            logOutput(LogLevel.INFO, true, lineBuilder.toString());
        }
    }

    private void reset() {
        characterArrayList = new ArrayList<>();
        map = new HashMap<>();
        nextCharacterArrayList = new ArrayList<>();
        galaxies = new Galaxies();
        galaxyPairList = new GalaxyPairList();
    }
}
