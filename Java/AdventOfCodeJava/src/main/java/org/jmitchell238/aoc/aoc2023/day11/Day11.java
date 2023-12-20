package org.jmitchell238.aoc.aoc2023.day11;

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

public class Day11 {
  private static final Boolean DEBUGGING = false;
  @Getter @Setter
  private Boolean isPartTwo = false;
  ArrayList<char[]> characterArrayList = new ArrayList<>();
  private Map<Point, Character> map = new HashMap<>();
  ArrayList<ArrayList<Character>> nextCharacterArrayList = new ArrayList<>();
  private Galaxies galaxies = new Galaxies();
  private int galaxyId = 1;
  private GalaxyPairList galaxyPairList = new GalaxyPairList();


  public void main(String[] args) throws FileNotFoundException {
    Day11Run();
  }

  public void Day11Run() throws FileNotFoundException {
    System.out.println("\n--- Day 9: Mirage Maintenance ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day11/input.txt";

    long partOneAnswer = part1(input);
    System.out.println(STR."Part 1: Answer: \{partOneAnswer}");

    long expansionFactor = 1000000;
    long partTwoAnswer = part2(input, expansionFactor);
    System.out.println(STR."Part 2: Answer: \{partTwoAnswer}");
  }

  public long part1(String filePath) throws FileNotFoundException {
    processFile(filePath);

    expandUniverse();
    convertToGrid();
    findGalaxies();
    findAllGalaxyPairs();

    if (DEBUGGING) {
      printGrid();
    }

    long sumOfShortestDistances = galaxyPairList.getSumOfShortestDistances();

    reset();

    return sumOfShortestDistances;
  }

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
      nextCharacterArrayList.add(row, new ArrayList<>(Collections.nCopies(characterArrayList.get(row).length, '.')));
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

        map.put(new Point((int) xGridValue, (int) yGridValue), nextCharacterArrayList.get(y).get(x));
        ifGalaxyAddToGalaxies(xGridValue, yGridValue, nextCharacterArrayList.get(y).get(x));
      }

      xGridValue = 0;

    }
  }

  private boolean areAllCharactersTheSame(ArrayList<Character> characterList) {
    boolean allSame = false;
    for (int i = 0; i < characterList.size(); i++) {
      if (i == 0) {
        allSame = true;
      } else {
        if (characterList.get(i) != characterList.get(i - 1)) {
          allSame = false;
        }
      }
    }
    return allSame;
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

        boolean allSame = false;
        for (char c : characterArrayList.get(y)) {
          characterList.add(c);
        }

        nextCharacterArrayList.add(characterList);
        allSame = areAllCharactersTheSame(characterList);

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
    int galaxyId = 1;
    for (int y = 0; y < nextCharacterArrayList.size(); y++) {
      for (int x = 0; x < nextCharacterArrayList.get(y).size(); x++) {
        if (map.get(new Point(x, y)) == '#') {
          Galaxy galaxy = new Galaxy(galaxyId, new Point(x, y));
          galaxies.addGalaxy(galaxy);
          galaxyId++;
        }
      }
    }
  }

  private void findAllGalaxyPairs() {
    for (int galaxyId = 1; galaxyId <= galaxies.getGalaxyList().size(); galaxyId++) {
      Galaxy galaxy1 = galaxies.getGalaxyById(galaxyId);

      for (int galaxyId2 = galaxyId + 1; galaxyId2 <= galaxies.getGalaxyList().size(); galaxyId2++) {
        Galaxy galaxy2 = galaxies.getGalaxyById(galaxyId2);

        int distance = calculateShortestDistanceBetweenTwoGalaxies(galaxy1, galaxy2);

        GalaxyPair galaxyPair = new GalaxyPair(galaxy1, galaxy2, distance);
        galaxyPairList.addGalaxyPair(galaxyPair);
      }
    }
  }

  private int calculateShortestDistanceBetweenTwoGalaxies(Galaxy galaxy1, Galaxy galaxy2) {
//    return calculateShortestDistanceBetweenTwoPoints(galaxy1.getLocation(), galaxy2.getLocation());
    return calculateShortestDistanceBetweenTwoPoints(galaxy1.location(), galaxy2.location());
  }

  private int calculateShortestDistanceBetweenTwoPoints(Point point1, Point point2) {
    int xDistance = Math.abs(point1.x - point2.x);
    int yDistance = Math.abs(point1.y - point2.y);

    return xDistance + yDistance;
  }

  private void printGrid() {
    System.out.println("Original Universe:\n\n");

    for (char[] line : characterArrayList) {
      for (char c : line) {
        System.out.print(c);
      }
      System.out.println();
    }

    System.out.println("\n\nExpanded Universe:\n\n");

    for (ArrayList<Character> line : nextCharacterArrayList) {
      for (Character character : line) {
        System.out.print(character);
      }
      System.out.println();
    }

    System.out.println("\n\nGrid:\n\n");

    for (int y = 0; y < nextCharacterArrayList.size(); y++) {
      for (int x = 0; x < nextCharacterArrayList.get(y).size(); x++) {
        System.out.print(map.get(new Point(x, y)));
      }
      System.out.println();
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
