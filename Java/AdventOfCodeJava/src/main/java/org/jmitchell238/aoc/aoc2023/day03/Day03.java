package org.jmitchell238.aoc.aoc2023.day03;

import static java.lang.Character.isDigit;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day03 {
  private static final Boolean DEBUGGING = false;
  private static final Boolean VERBOSE = false;
  private static Map<Point, Character> coordinateMap;
  private static Map<Long, ArrayList<ArrayList<Point>>> partNumberMap;
  private static ArrayList<Point> asteriskList;

  public static void main(String[] args) throws FileNotFoundException {
    Day03Run();
  }

  public static void Day03Run() throws FileNotFoundException {
    System.out.println("\n--- Day 03: Gear Ratios ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input.txt";
//    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input_test.txt";

    Long partOneAnswer = part1(input);
    System.out.println(STR."Part 1: Answer: Part Number Sum = \{partOneAnswer}");

    Long partTwoAnswer = part2(input);
    System.out.println(STR."Part 2: Answer: Gear Ratio Sum = \{partTwoAnswer}");
  }

  public static Long part1(String inputString) throws FileNotFoundException {
    Long partNumberSum = 0L;

    try {
      File input = new File(inputString);
      Scanner scanner = new Scanner(input);

      ArrayList<char[]> char2dArray = new ArrayList<>();
      coordinateMap = createCoordinateMap(scanner, char2dArray);

      partNumberSum = getPartNumberSum(char2dArray, partNumberSum);

      if (DEBUGGING) {
        System.out.println(STR."Part Number Sum: \{partNumberSum}");
      }

      return partNumberSum;
    } catch (FileNotFoundException e) {
      return -1L;
    }
  }

  public static Long part2(String inputString) {
    try {
      File input = new File(inputString);
      Scanner scanner = new Scanner(input);
      ArrayList<char[]> char2dArray = new ArrayList<>();
      coordinateMap = createCoordinateMap(scanner, char2dArray);
      partNumberMap = createPartNumberMap(char2dArray, coordinateMap);

      long gearRatioSum = 0L;

      Map<Point, ArrayList<Long>> asteriskPartNumberMap = new HashMap<>();

      for (Point asterisk : asteriskList) {
        asteriskPartNumberMap.put(asterisk, new ArrayList<>());
      }

      for (Long partNumber : partNumberMap.keySet()) {
        ArrayList<ArrayList<Point>> partNumberPoints = partNumberMap.get(partNumber);

        for (ArrayList<Point> partNumberPointList : partNumberPoints) {
          boolean partNumberAdded = false;

          for (Point partNumberPoint : partNumberPointList) {
            Point asteriskPoint = checkAroundPartNumberForAsteriskSinglePoint(
                partNumberPoint, coordinateMap);

            if (asteriskPoint != null) {
              asteriskPartNumberMap.get(asteriskPoint).add(partNumber);
              partNumberAdded = true;
            }

            if (partNumberAdded) {
              break;
            }
          }
        }
      }

      for (Point asterisk : asteriskPartNumberMap.keySet()) {
        ArrayList<Long> partNumbers = asteriskPartNumberMap.get(asterisk);
        Long partNumberProduct = 1L;
        if (DEBUGGING) {
          System.out.println(STR."Asterisk: \{asterisk} | Part Numbers: \{partNumbers}");
        }

        if (partNumbers.size() < 2) {
          continue;
        }

        for (Long partNumber : partNumbers) {
          partNumberProduct *= partNumber;
        }

        gearRatioSum += partNumberProduct;
      }

      if (DEBUGGING) {
        System.out.println(STR."Gear Ratio Sum: \{gearRatioSum}");
      }

      return gearRatioSum;
    } catch (FileNotFoundException e) {
      return -1L;
    }
  }

  private static Long getPartNumberSum(ArrayList<char[]> char2dArray, Long partNumberSum) {
    for (int y = 0; y < char2dArray.size(); y++) {
      for (int x = 0; x < char2dArray.getFirst().length - 1; x++) {
        Point currentPoint = new Point(x, y);
        char currentChar = coordinateMap.get(currentPoint);
        boolean isDigit = isDigit(currentChar);

        StringBuilder partNumber = new StringBuilder();
        int partnumberLength = 0;
        long partNumberLong;

        if (isDigit) {
          for (int i = 0; i < 5; i++) {
            partNumber.append(currentChar);
            partnumberLength++;
            currentChar = coordinateMap.get(
                new Point(currentPoint.x + partnumberLength, currentPoint.y));
            if (currentChar == '.' || !isDigit(currentChar)) {
              break;
            }
          }

          if (Boolean.TRUE.equals(DEBUGGING)) {
            System.out.println(STR."Part Number: \{partNumber}");
          }

          String partNumberString = partNumber.toString();
          partNumberLong = Long.parseLong(partNumberString);
          x += partnumberLength - 1;

          // Check the symbol to the left of part number.
          if (checkCharToLeftOfPartNumber(currentPoint, coordinateMap)) {
            partNumberSum += partNumberLong;
            continue;
          }

          // Check the symbol to the right of part number.
          if (checkCharToRightOfPartNumber(currentPoint, partnumberLength, coordinateMap,
              char2dArray.get(x).length)) {
            partNumberSum += partNumberLong;
            continue;
          }

          // Check the row above the part number.
          if (checkRowAbovePartNumber(currentPoint, partnumberLength,
              coordinateMap)) {
            partNumberSum += partNumberLong;
            continue;
          }

          // Check the row below the part number.
          if (checkRowBelowPartNumber(currentPoint, partnumberLength,
              coordinateMap)) {
            partNumberSum += partNumberLong;
          }
        }
      }
    }

    return partNumberSum;
  }

  private static Point checkAroundPartNumberForAsteriskSinglePoint(Point currentPoint, Map<Point, Character> coordinateMap) {
    Point asteriskPoint = new Point(currentPoint.x - 2, currentPoint.y - 2);
    ArrayList<Point> pointsAroundPartNumber = createPointsAroundPartNumberList(currentPoint);

    boolean bordersAsterisk = false;

    for (Point point : pointsAroundPartNumber) {
      char pointChar = coordinateMap.get(point);
      if (pointChar == '*') {
        bordersAsterisk = true;
        asteriskPoint = point;
        break;
      }
    }

    if (bordersAsterisk) {
      return asteriskPoint;
    } else {
      return null;
    }
  }

  private static ArrayList<Point> createPointsAroundPartNumberList(Point currentPoint) {
    ArrayList<Point> pointsAroundPartNumber = new ArrayList<>();
    Point topLeftPoint = new Point(currentPoint.x - 1, currentPoint.y - 1);
    Point leftPoint = new Point(currentPoint.x - 1, currentPoint.y);
    Point bottomLeftPoint = new Point(currentPoint.x - 1, currentPoint.y + 1);
    Point topRightPoint = new Point(currentPoint.x + 1, currentPoint.y - 1);
    Point rightPoint = new Point(currentPoint.x + 1, currentPoint.y);
    Point bottomRightPoint = new Point(currentPoint.x + 1, currentPoint.y + 1);
    Point topPoint = new Point(currentPoint.x, currentPoint.y - 1);
    Point bottomPoint = new Point(currentPoint.x, currentPoint.y + 1);

    pointsAroundPartNumber.add(topLeftPoint);
    pointsAroundPartNumber.add(leftPoint);
    pointsAroundPartNumber.add(bottomLeftPoint);
    pointsAroundPartNumber.add(topRightPoint);
    pointsAroundPartNumber.add(rightPoint);
    pointsAroundPartNumber.add(bottomRightPoint);
    pointsAroundPartNumber.add(topPoint);
    pointsAroundPartNumber.add(bottomPoint);

    return pointsAroundPartNumber;
  }

  private static boolean checkCharToLeftOfPartNumber(Point currentPoint,
      Map<Point, Character> coordinateMap) {
    Point leftPoint = new Point(currentPoint.x - 1, currentPoint.y);
    char leftChar = coordinateMap.get(leftPoint);
    return leftChar != '.' && !isDigit(leftChar);
  }

  private static boolean checkCharToRightOfPartNumber(Point currentPoint, int partNumberLength,
      Map<Point, Character> coordinateMap, int char2dArrayLineLength) {
    try {
      Point rightPoint = new Point(currentPoint.x + partNumberLength, currentPoint.y);
      char rightChar = coordinateMap.get(rightPoint);
      return rightChar != '.' && !isDigit(rightChar);

    } catch (IndexOutOfBoundsException e) {
      System.out.println("Part Number is at the end of the line.");
      System.out.println(STR."Current Point: \{currentPoint}");
      System.out.println(STR."Part Number Length: \{partNumberLength}");
      System.out.println(STR."Char2dArrayLineLength: \{char2dArrayLineLength}");
      return false;
    }
  }

  private static boolean checkRowAbovePartNumber(Point currentPoint, int partNumberLength, Map<Point, Character> coordinateMap) {
    for (int i = currentPoint.x - 1; i <= currentPoint.x + partNumberLength; i++) {
      Point abovePoint = new Point(i, currentPoint.y - 1);
      char aboveChar = coordinateMap.get(abovePoint);
      boolean isAboveCharSymbol = aboveChar != '.' && !isDigit(aboveChar);
      if (isAboveCharSymbol) {
        return true;
      }
    }

    return false;
  }

  private static boolean checkRowBelowPartNumber(Point currentPoint, int partNumberLength, Map<Point, Character> coordinateMap) {
    for (int i = currentPoint.x - 1; i <= currentPoint.x + partNumberLength; i++) {
      Point belowPoint = new Point(i, currentPoint.y + 1);
      char belowChar = coordinateMap.get(belowPoint);
      boolean isBelowCharSymbol = belowChar != '.' && !isDigit(belowChar);
      if (isBelowCharSymbol) {
        return true;
      }
    }

    return false;
  }

  private static Map<Point, Character> createCoordinateMap(Scanner scanner,
      ArrayList<char[]> char2dArray) {
    coordinateMap = new HashMap<>();
    asteriskList = new ArrayList<>();

    while (scanner.hasNextLine()) {
      char2dArray.add(scanner.nextLine().toCharArray());
    }

    // Add row of '.'s to the top and bottom of the grid
    int char2dArraySize = char2dArray.size();
    for (int i = -1; i <= char2dArraySize; i++) {
      Point topRowPoint = new Point(i, -1);
      Point bottomRowPoint = new Point(i, char2dArraySize);

      coordinateMap.put(topRowPoint, '.');
      coordinateMap.put(bottomRowPoint, '.');
    }

    // Add column of '.'s to the left and right of the grid
    int char2dArrayLineLength = char2dArray.getFirst().length;
    for (int i = -1; i <= char2dArrayLineLength + 1; i++) {
      Point leftColumnPoint = new Point(-1, i);
      Point rightColumnPoint = new Point(char2dArrayLineLength, i);
      Point rightColumnPoint2 = new Point(char2dArrayLineLength + 1, i);

      coordinateMap.put(leftColumnPoint, '.');
      coordinateMap.put(rightColumnPoint, '.');
      coordinateMap.put(rightColumnPoint2, '.');
    }

    for (int y = 0; y < char2dArray.size(); y++) {
      char[] currentCharArray = char2dArray.get(y);

      for (int x = 0; x < currentCharArray.length; x++) {
        coordinateMap.put(new Point(x, y), currentCharArray[x]);

        if (currentCharArray[x] == '*') {
          asteriskList.add(new Point(x, y));

          if (DEBUGGING) {
            System.out.println(STR."Asterisk at: \{x},\{y}");
          }
        }
      }
    }

    if (DEBUGGING) {
      printCoordinateMapToConsole(coordinateMap, char2dArraySize, char2dArrayLineLength);
    } else if (VERBOSE) {
      printCoordinateMapToConsoleVerbose(coordinateMap, char2dArraySize, char2dArrayLineLength);
    }

    return coordinateMap;
  }

  private static Map<Long, ArrayList<ArrayList<Point>>> createPartNumberMap(ArrayList<char[]> char2dArray,
      Map<Point, Character> coordinateMap) {
    partNumberMap = new HashMap<>();

    for (int y = 0; y < char2dArray.size(); y++) {
      for (int x = 0; x < char2dArray.getFirst().length - 1; x++) {
        Point currentPoint = new Point(x, y);
        char currentChar = coordinateMap.get(currentPoint);
        StringBuilder partNumber = new StringBuilder();
        int partnumberLength = 0;
        Long partNumberLong;
        ArrayList<Point> partNumberCoordinates = new ArrayList<>();

        boolean isDigit = isDigit(currentChar);

        if (isDigit) {

          for (int i = 0; i < 5; i++) {
            partNumber.append(currentChar);
            partnumberLength++;
            currentChar = coordinateMap.get(
                new Point(currentPoint.x + partnumberLength, currentPoint.y));
            if (currentChar == '.' || !isDigit(currentChar)) {
              partNumberCoordinates.add(new Point(currentPoint.x + i, currentPoint.y));
              break;
            } else {
              partNumberCoordinates.add(new Point(currentPoint.x + i, currentPoint.y));
            }
          }

          partNumberLong = Long.parseLong(partNumber.toString());

          if (partNumberMap.containsKey(partNumberLong)) {
            partNumberMap.get(partNumberLong).add(partNumberCoordinates);
          } else {
            ArrayList<ArrayList<Point>> partNumberCoordinatesList = new ArrayList<>();
            partNumberCoordinatesList.add(partNumberCoordinates);
            partNumberMap.put(partNumberLong, partNumberCoordinatesList);
          }

          if (DEBUGGING) {
            System.out.println(STR."Part Number: \{partNumber}");
          }

          x += partnumberLength - 1;
        }
      }
    }

    return partNumberMap;
  }

  private static void printCoordinateMapToConsole(Map<Point, Character> coordinateMap,
      int char2dArraySize, int char2dArrayLineLength) {
    if (Boolean.FALSE.equals(DEBUGGING)) {
      return;
    }

    for (int y = -1; y <= char2dArraySize; y++) {
      for (int x = -1; x <= char2dArrayLineLength; x++) {
        Point currentPoint = new Point(x, y);
        Character currentCharacter = coordinateMap.get(currentPoint);
        System.out.print(currentCharacter);
      }

      System.out.println();
    }
  }

  private static void printCoordinateMapToConsoleVerbose(Map<Point, Character> coordinateMap,
      int char2dArraySize, int char2dArrayLineLength) {
    if (Boolean.FALSE.equals(VERBOSE)) {
      return;
    }

    for (int y = -1; y <= char2dArraySize; y++) {
      for (int x = -1; x <= char2dArrayLineLength; x++) {
        Point currentPoint = new Point(x, y);
        Character currentCharacter = coordinateMap.get(currentPoint);
        System.out.printf("Coordinate (%s,%s) = %s%n", x, y, currentCharacter);
      }
    }
  }

}

