package org.jmitchell238.aoc.aoc2023.day03;

import static java.lang.Character.isDigit;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Day03 {

  private static final Boolean DEBUGGING = true;
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
      partNumberSum = getPartNumberSum(char2dArray, partNumberSum);

      System.out.println("Part Number Sum: " + partNumberSum);
      return partNumberSum;
    } catch (FileNotFoundException e) {
      return -1L;
    }
  }

  private static Long getPartNumberSum(ArrayList<char[]> char2dArray, Long partNumberSum) {
    for (int y = 0; y < char2dArray.size(); y++) {
      for (int x = 0; x < char2dArray.get(0).length - 1; x++) {
        Point currentPoint = new Point(x, y);
        char currentChar = coordinateMap.get(currentPoint);
        boolean isDigit = isDigit(currentChar);

        StringBuilder partNumber = new StringBuilder();
        int partnumberLength = 0;
        Long partNumberLong = 0L;

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

          isDigit = false;

          if (DEBUGGING) {
            System.out.println("Part Number: " + partNumber);
          }

          String partNumberString = partNumber.toString();
          partNumberLong = Long.parseLong(partNumberString);

          x += partnumberLength - 1;

          partNumber = new StringBuilder();

          // Check the symbol to the left of part number.
          if (checkCharToLeftOfPartNumber(currentPoint, coordinateMap)) {
            partNumberSum += partNumberLong;

            partNumberLong = 0L;
            continue;
          }

          // Check the symbol to the right of part number.
          if (checkCharToRightOfPartNumber(currentPoint, partnumberLength, coordinateMap,
              char2dArray.get(x).length)) {
            partNumberSum += partNumberLong;
            partNumberLong = 0L;
            continue;
          }

          // Check the row above the part number.
          if (checkRowAbovePartNumber(currentPoint, partnumberLength, partNumberLong, partNumberSum,
              coordinateMap)) {
            partNumberSum += partNumberLong;
            partNumberLong = 0L;
            continue;
          }

          // Check the row below the part number.
          if (checkRowBelowPartNumber(currentPoint, partnumberLength, partNumberLong, partNumberSum,
              coordinateMap)) {
            partNumberSum += partNumberLong;
            partNumberLong = 0L;
            continue;
          }
        }
      }
    }
    return partNumberSum;
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

        Long currentPartNumber = 0L;
        Long partNumberLong = 0L;
        Long gearRatio = 0L;
        boolean breakLoop = false;
        boolean inPartNumber = false;

        for (int y = asterick.y - 1; y <= asterick.y + 1; y++) {
          for (int x = asterick.x - 1; x <= asterick.x + 1; x++) {

            Point currentPoint = new Point(x, y);

            Long coordinatePartNumber = getPartNumberForPoint(currentPoint);

            if (coordinatePartNumber == -2L) {
              inPartNumber = false;
              currentPartNumber = 0L;
              continue;
            } else if (inPartNumber) {
              continue;
            } else if (coordinatePartNumber > 0L) {
              inPartNumber = true;

              if (currentPartNumber == 0L) {
                currentPartNumber =  coordinatePartNumber;
                if (partNumberLong == 0L) {
                  partNumberLong = coordinatePartNumber;
                } else if (partNumberLong != 0L) {
                  gearRatio = partNumberLong * coordinatePartNumber;
                  gearRatioSum += gearRatio;
                  breakLoop = true;

                  if (DEBUGGING) {
                    System.out.println("Part Number 1: " + partNumberLong);
                    System.out.println("Part Number 2: " + coordinatePartNumber);
                    System.out.println("Gear Ratio: " + gearRatio);
                    System.out.println("Gear Ratio Sum: " + gearRatioSum);
                  }
                  break;
                }
              }
              if (breakLoop)
                break;
            }
            if (breakLoop)
              break;
          }
          if (breakLoop)
            break;
        }
      }

      System.out.println("Gear Ratio Sum: " + gearRatioSum);
      // Attempt 1:
        // 101,046,947 - Too High.....
      // Attempt 2:
        // 86,619,648 - Too Low......
      // Attempt 3:
        // 86,619,648 |
      // Attempt 4:
        // 81,106,820 - what the crap.

      // Someone elses solution:
      // 87,605,697
      return gearRatioSum;
    } catch (FileNotFoundException e) {
      return -1L;
    }
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
      System.out.println("Current Point: " + currentPoint);
      System.out.println("Part Number Length: " + partNumberLength);
      System.out.println("Char2dArrayLineLength: " + char2dArrayLineLength);
      return false;
    }
  }

  private static boolean checkRowAbovePartNumber(Point currentPoint, int partnumberLength,
      Long partNumberLong, Long partNumberSum, Map<Point, Character> coordinateMap) {
    for (int i = currentPoint.x - 1; i <= currentPoint.x + partnumberLength; i++) {
      Point abovePoint = new Point(i, currentPoint.y - 1);
      char aboveChar = coordinateMap.get(abovePoint);
      boolean isAboveCharSymbol = aboveChar != '.' && !isDigit(aboveChar);
      if (isAboveCharSymbol) {
        return true;
      }
    }

    return false;
  }

  private static boolean checkRowBelowPartNumber(Point currentPoint, int partnumberLength,
      Long partNumberLong, Long partNumberSum, Map<Point, Character> coordinateMap) {
    for (int i = currentPoint.x - 1; i <= currentPoint.x + partnumberLength; i++) {
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
    asterickList = new ArrayList<>();

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
    int char2dArrayLineLength = char2dArray.get(0).length;
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
          asterickList.add(new Point(x, y));

//          if (DEBUGGING) {
//            System.out.println("Asterick at: " + x + "," + y);
//          }
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

  private static Map<Long, ArrayList<Point>> createPartNumberMap(ArrayList<char[]> char2dArray,
      Map<Point, Character> coordinateMap) {
    partNumberMap = new HashMap<>();

    for (int y = 0; y < char2dArray.size(); y++) {
      for (int x = 0; x < char2dArray.get(0).length - 1; x++) {
        Point currentPoint = new Point(x, y);
        char currentChar = coordinateMap.get(currentPoint);
        boolean isDigit = isDigit(currentChar);

        StringBuilder partNumber = new StringBuilder();
        int partnumberLength = 0;
        Long partNumberLong = 0L;

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

          isDigit = false;

//          if (DEBUGGING) {
//            System.out.println("Part Number: " + partNumber);
//          }

          partNumberLong = Long.parseLong(partNumber.toString());

          addPointToPartNumberMap(partNumberLong, currentPoint);

          x += partnumberLength - 1;
        }
      }
    }

    return partNumberMap;
  }

  private static void addPartNumberToGearRatioMap(Long partNumberLong, Point currentPoint,
      int partnumberLength) {
    ArrayList<Point> partNumberCoordinates = new ArrayList<>();
    for (int i = 0; i < partnumberLength; i++) {
      partNumberCoordinates.add(new Point(currentPoint.x + i, currentPoint.y));
    }

    addPointToPartNumberMap(partNumberLong, currentPoint);
  }

  private static void addPointToPartNumberMap(Long partNumber, Point point) {
    long partNumberLength = partNumber.toString().length();

    for (int i = 0; i < partNumberLength; i++) {
      partNumberMap.computeIfAbsent(partNumber, k -> new ArrayList<>())
          .add(new Point(point.x + i, point.y));
    }

//    if (DEBUGGING) {
//      System.out.println("Part Number: " + partNumber);
//      System.out.println("Points: " + partNumberMap.get(partNumber));
//    }
  }

  private static ArrayList<Point> getPointsForPartNumber(
      Map<Integer, ArrayList<Point>> partNumberMap, int partNumber) {
    return partNumberMap.getOrDefault(partNumber, new ArrayList<>());
  }

  private static Long getPartNumberForPoint(Point point) {
    for (Map.Entry<Long, ArrayList<Point>> entry : partNumberMap.entrySet()) {
      if (entry.getValue().contains(point)) {
        return entry.getKey();
      }
    }

    return -2L;
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
      int char2dArraySize, int char2dArrayLinelength) {
    if (Boolean.FALSE.equals(VERBOSE)) {
      return;
    }

    for (int y = -1; y <= char2dArraySize; y++) {
      for (int x = -1; x <= char2dArrayLinelength; x++) {
        Point currentPoint = new Point(x, y);
        Character currentCharacter = coordinateMap.get(currentPoint);
        System.out.printf("Coordinate (%s,%s) = %s%n", x, y, currentCharacter);
      }
    }
  }







  // Adding this for testing purposes ONLY.
  private static int[][] directions=new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};
  public static void main(String[]args) {
    long sum = 0;
    Map<Point,Integer> map=new HashMap<>();
    Map<Point,Integer> counts=new HashMap<>();

    try {
      File inputfile = new File("src/main/java/org/jmitchell238/aoc/aoc2023/day03/input.txt");
      BufferedReader br = new BufferedReader(new FileReader(inputfile));

      List<String> list = br.lines().toList();
      char[][] css = new char[list.size()][];

      IntStream.range(0, list.size()).forEach(i -> css[i] = list.get(i).toCharArray());

      for (int i = 0 ; i < css.length ; i++) {
        int num = 0;
        boolean isGear = false;
        Point gearCoord = new Point(-1,-1);

        for (int j = 0 ; j < css[i].length ; j++) {
          int tmp = findNum(css[i][j]);

          if (tmp == -1) {
            if(isGear){
              System.out.println("row " + (i + 1) + ": " + num + "; " + gearCoord);

              map.put(gearCoord, map.getOrDefault(gearCoord, 1) * num);
              counts.put(gearCoord, counts.getOrDefault(gearCoord, 0) + 1);
              gearCoord = new Point(-1, -1);
              isGear = false;
            }

            num = 0;
          } else {
            num = num * 10 + tmp;

            for(int[] dir:directions) {
              if(i + dir[0] >= 0 && i + dir[0] < css.length && j + dir[1] >= 0 && j + dir[1] < css[i + dir[0]].length) {
                if(!isGear && css[i + dir[0]][j + dir[1]] == '*') {
                  gearCoord = new Point(i + dir[0], j + dir[1]);
                  isGear = true;
                }
              }
            }
          }
        }
        if(isGear) {
          map.put(gearCoord, map.getOrDefault(gearCoord,1) * num);
          counts.put(gearCoord, counts.getOrDefault(gearCoord,0) + 1);
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    for(Point p:map.keySet()) {
      sum += counts.get(p) == 2 ? map.get(p) : 0;
    }

    System.out.println(sum);
  }

  private static int findNum(char c) {
    if( c >= '0' && c <= '9' ) {
      return c - '0';
    }

    return -1;
  }

}

