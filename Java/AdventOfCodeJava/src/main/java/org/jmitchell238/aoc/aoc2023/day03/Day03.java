package org.jmitchell238.aoc.aoc2023.day03;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day03 {

  private static final Boolean DEBUGGING = false;
  private static final Boolean VERBOSE = false;
  private static Map<Point, Character> coordinateMap;
  private static Map<Long, ArrayList<Point>> partNumberMap;
  private static ArrayList<Point> asterickList;

  public static void Day03() throws FileNotFoundException {
    System.out.println("%nDay 03: Gear Ratios");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input_test.txt";

    Long partOneAnswer = Part1(inputTest);

    Long partTwoAnswer = Part2(inputTest);

  }

  public static Long Part1(String inputString) throws FileNotFoundException {
    Long partNumberSum = 0L;

    try {
      File input = new File(inputString);
      Scanner scanner = new Scanner(input);

      ArrayList<char[]> char2dArray = new ArrayList<>();
      coordinateMap = createCoordinateMap(scanner, char2dArray);

      // Get Part Numbers and check around them for symbols.
      for (int y = 0 ; y < char2dArray.size() ; y++) {
        for (int x = 0; x < char2dArray.get(0).length -1 ; x++) {
          Point currentPoint = new Point(x, y);
          char currentChar = coordinateMap.get(currentPoint);
          boolean isDigit = Character.isDigit(currentChar);

          StringBuilder partNumber = new StringBuilder();
          int partnumberLength = 0;
          Long partNumberLong = 0L;

          if (isDigit) {

            for (int i = 0 ; i < 5 ; i++) {
              partNumber.append(currentChar);
              partnumberLength++;
              currentChar = coordinateMap.get(new Point(currentPoint.x + partnumberLength, currentPoint.y));
              if (currentChar == '.' || !Character.isDigit(currentChar)) {
                break;
              }
            }

            isDigit = false;
//            if (!isDigit) {
//              System.out.println("Part Number: " + partNumber);
//            }

            String partNumberString = partNumber.toString();
            partNumberLong = Long.parseLong(partNumberString);

            x += partnumberLength - 1 ; // Suspicious of this.

            partNumber = new StringBuilder();


          if (!isDigit) {
            // Check the symbol to the left of part number.
            if (checkCharToLeftOfPartNumber(currentPoint, coordinateMap)) {
              partNumberSum += partNumberLong;

              partNumberLong = 0L;
              continue;
            }

            // Check the symbol to the right of part number.
            if (checkCharToRightOfPartNumber(currentPoint, partnumberLength, coordinateMap, char2dArray.get(x).length)) {
              partNumberSum += partNumberLong;
              partNumberLong = 0L;
              continue;
            }

            // Check the row above the part number.
            if (checkRowAbovePartNumber(currentPoint, partnumberLength, partNumberLong, partNumberSum, coordinateMap)) {
              partNumberSum += partNumberLong;
              partNumberLong = 0L;
              continue;
            }

            // Check the row below the part number.
            if (checkRowBelowPartNumber(currentPoint, partnumberLength, partNumberLong, partNumberSum, coordinateMap)) {
              partNumberSum += partNumberLong;
              partNumberLong = 0L;
              continue;
            }
          }
          }
        }
      }

      System.out.println("Part Number Sum: " + partNumberSum);
      return partNumberSum;
    } catch(FileNotFoundException e) {
      return -1L;
    }
  }

  public static Long Part2(String inputString) {
    try {
      File input = new File(inputString);
      Scanner scanner = new Scanner(input);
      ArrayList<char[]> char2dArray = new ArrayList<>();
      coordinateMap = createCoordinateMap(scanner, char2dArray);
      partNumberMap = createPartNumberMap(char2dArray, coordinateMap);

      Long gearRatioSum = 0L;

      for (Point asterick : asterickList) {

        Long partNumberLong = 0L;

        for (int x = asterick.x - 1 ; x <= asterick.x + 1 ; x++) {
          for (int y = asterick.y - 1; y <= asterick.y + 1; y++) {
            Point currentPoint = new Point(x, y);

            Long coordinatePartNumber = getPartNumberForPoint(currentPoint);

            if (coordinatePartNumber != -2L) {
              if (partNumberLong == 0L) {
                partNumberLong = coordinatePartNumber;
              } else {
                gearRatioSum += partNumberLong * coordinatePartNumber;
              }
            }
          }
        }
      }

        System.out.println("Gear Ratio Sum: " + gearRatioSum);
      return gearRatioSum;
    } catch (FileNotFoundException e) {
      return -1L;
    }
  }

  private static boolean checkCharToLeftOfPartNumber(Point currentPoint, Map<Point, Character> coordinateMap) {
    Point leftPoint = new Point(currentPoint.x - 1, currentPoint.y);
    char leftChar = coordinateMap.get(leftPoint);
    return leftChar != '.' && !Character.isDigit(leftChar);
  }

  private static boolean checkCharToRightOfPartNumber(Point currentPoint, int partNumberLength, Map<Point, Character> coordinateMap, int char2dArrayLineLength) {
    try {
      Point rightPoint = new Point(currentPoint.x + partNumberLength, currentPoint.y);
      char rightChar = coordinateMap.get(rightPoint);
      return rightChar != '.' && !Character.isDigit(rightChar);

    } catch (IndexOutOfBoundsException e) {
      System.out.println("Part Number is at the end of the line.");
      System.out.println("Current Point: " + currentPoint);
      System.out.println("Part Number Length: " + partNumberLength);
      System.out.println("Char2dArrayLineLength: " + char2dArrayLineLength);
      return false;
    }
  }

  private static boolean checkRowAbovePartNumber(Point currentPoint, int partnumberLength, Long partNumberLong, Long partNumberSum, Map<Point, Character> coordinateMap) {
    for (int i = currentPoint.x - 1 ; i <= currentPoint.x + partnumberLength ; i++) {
      Point abovePoint = new Point(i, currentPoint.y - 1);
      char aboveChar = coordinateMap.get(abovePoint);
      boolean isAboveCharSymbol = aboveChar != '.' && !Character.isDigit(aboveChar);
      if (isAboveCharSymbol) {
        return true;
      }
    }

    return false;
  }

  private static boolean checkRowBelowPartNumber(Point currentPoint, int partnumberLength, Long partNumberLong, Long partNumberSum, Map<Point, Character> coordinateMap) {
    for (int i = currentPoint.x - 1 ; i <= currentPoint.x + partnumberLength ; i++) {
      Point belowPoint = new Point(i, currentPoint.y + 1);
      char belowChar = coordinateMap.get(belowPoint);
      boolean isBelowCharSymbol = belowChar != '.' && !Character.isDigit(belowChar);
      if (isBelowCharSymbol) {
        return true;
      }
    }

    return false;
  }

  private static Map<Point, Character> createCoordinateMap(Scanner scanner, ArrayList<char[]> char2dArray) {
    coordinateMap = new HashMap<>();
    asterickList = new ArrayList<>();

    while (scanner.hasNextLine()) {
      char2dArray.add(scanner.nextLine().toCharArray());
    }

    // Add row of '.'s to the top and bottom of the grid
    int char2dArraySize = char2dArray.size();
    for (int i = -1 ; i <= char2dArraySize ; i++) {
      Point topRowPoint = new Point(i, -1);
      Point bottomRowPoint = new Point(i, char2dArraySize);

      coordinateMap.put(topRowPoint, '.');
      coordinateMap.put(bottomRowPoint, '.');
    }

    // Add column of '.'s to the left and right of the grid
    int char2dArrayLineLength = char2dArray.get(0).length;
    for (int i = -1 ; i <= char2dArrayLineLength + 1 ; i++) {
      Point leftColumnPoint = new Point(-1, i);
      Point rightColumnPoint = new Point(char2dArrayLineLength, i);
      Point rightColumnPoint2 = new Point(char2dArrayLineLength + 1, i);

      coordinateMap.put(leftColumnPoint, '.');
      coordinateMap.put(rightColumnPoint, '.');
      coordinateMap.put(rightColumnPoint2, '.');
    }

    for (int y = 0 ; y < char2dArray.size() ; y++) {
      char[] currentCharArray = char2dArray.get(y);

      for (int x = 0 ; x < currentCharArray.length ; x++) {
        coordinateMap.put(new Point(x,y), currentCharArray[x]);

        if (currentCharArray[x] == '*') {
          asterickList.add(new Point(x,y));

          if (DEBUGGING) {
            System.out.println("Asterick at: " + x + "," + y);
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

  private static Map<Long, ArrayList<Point>> createPartNumberMap(ArrayList<char[]> char2dArray, Map<Point, Character> coordinateMap) {
    partNumberMap = new HashMap<>();

    for (int y = 0 ; y < char2dArray.size() ; y++) {
      for (int x = 0 ; x < char2dArray.get(0).length - 1 ; x++) {
        Point currentPoint = new Point(x, y);
        char currentChar = coordinateMap.get(currentPoint);
        boolean isDigit = Character.isDigit(currentChar);

        StringBuilder partNumber = new StringBuilder();
        int partnumberLength = 0;
        Long partNumberLong = 0L;

        if (isDigit) {

          for (int i = 0; i < 5; i++) {
            partNumber.append(currentChar);
            partnumberLength++;
            currentChar = coordinateMap.get(new Point(currentPoint.x + partnumberLength, currentPoint.y));
            if (currentChar == '.' || !Character.isDigit(currentChar)) {
              break;
            }
          }

          partNumberLong = Long.parseLong(partNumber.toString());

          addPointToPartNumberMap(partNumberLong, currentPoint);
          partNumberLong = Long.parseLong(partNumber.toString());
          isDigit = false;
        }
      }
    }

    return partNumberMap;
  }

  private static void addPartNumberToGearRatioMap(Long partNumberLong, Point currentPoint, int partnumberLength) {
    ArrayList<Point> partNumberCoordinates = new ArrayList<>();
    for (int i = 0 ; i < partnumberLength ; i++) {
      partNumberCoordinates.add(new Point(currentPoint.x + i, currentPoint.y));
    }

    addPointToPartNumberMap(partNumberLong, currentPoint);
  }

  private static void addPointToPartNumberMap(Long partNumber, Point point) {
    partNumberMap.computeIfAbsent(partNumber, k -> new ArrayList<>()).add(point);

    if (DEBUGGING) {
      System.out.println("Part Number: " + partNumber);
      System.out.println("Point: " + point);
    }
  }

  private static ArrayList<Point> getPointsForPartNumber(Map<Integer, ArrayList<Point>> partNumberMap, int partNumber) {
    return partNumberMap.getOrDefault(partNumber, new ArrayList<>());
  }

  private static Long getPartNumberForPoint(Point point) {
    for (Map.Entry<Long, ArrayList<Point>> entry : partNumberMap.entrySet()) {
      if (entry.getValue().contains(point)) {
        return entry.getKey();
      }
    }
    // Return a default value (you may want to handle this case differently based on your requirements)
    return -2L;
  }


  private static void printCoordinateMapToConsole(Map<Point, Character> coordinateMap, int char2dArraySize, int char2dArrayLineLength) {
    if (Boolean.FALSE.equals(DEBUGGING)) {
      return;
    }

    for (int y = -1 ; y <= char2dArraySize ; y++) {
      for (int x = -1 ; x <= char2dArrayLineLength ; x++) {
        Point currentPoint = new Point(x,y);
        Character currentCharacter = coordinateMap.get(currentPoint);
        System.out.print(currentCharacter);
      }

      System.out.println();
    }
  }

  private static void printCoordinateMapToConsoleVerbose(Map<Point, Character> coordinateMap, int char2dArraySize, int char2dArrayLinelength) {
    if (Boolean.FALSE.equals(VERBOSE)) {
      return;
    }

    for (int y = -1 ; y <= char2dArraySize ; y++) {
      for (int x = -1 ; x <= char2dArrayLinelength ; x++) {
        Point currentPoint = new Point(x,y);
        Character currentCharacter = coordinateMap.get(currentPoint);
        System.out.printf("Coordinate (%s,%s) = %s%n", x, y, currentCharacter);
      }
    }
  }

  private static void printIsCoordinateSymbol() {

  }
}