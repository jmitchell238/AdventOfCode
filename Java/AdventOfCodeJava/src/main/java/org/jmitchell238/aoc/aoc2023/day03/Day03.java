package org.jmitchell238.aoc.aoc2023.day03;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day03 {

  private static final Boolean DEBUGGING = true;
  private static final Boolean VERBOSE = false;

  public static void Day03() throws FileNotFoundException {
    System.out.println("%nDay 03: Gear Ratios");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input_test_part1.txt";

    int partOneAnswer = Part1(inputTest);

  }

  public static int Part1(String inputString) throws FileNotFoundException {

    try {
      File input = new File(inputString);
      Scanner scanner = new Scanner(input);

      ArrayList<char[]> char2dArray = new ArrayList<>();
      Map<Point, Character> coordinateMap = new HashMap<>();

      while (scanner.hasNextLine()) {
        char2dArray.add(scanner.nextLine().toCharArray());
      }

      for (int y = 0 ; y < char2dArray.size() ; y++) {
        char[] currentCharArray = char2dArray.get(y);

        for (int x = 0 ; x < currentCharArray.length ; x++) {
          coordinateMap.put(new Point(x,y), currentCharArray[x]);
          debugMappingAndCoordinatesToConsole(x,y, currentCharArray.length -1, currentCharArray[x],coordinateMap);
        }
      }

      long partNumberSum = 0;

      for (int y = 0 ; y < char2dArray.size() ; y++) {
        for (int x = 0 ; x < char2dArray.get(x).length - 1 ; x++) {
          Point currentPoint = new Point(x,y);
          char currentChar = coordinateMap.get(currentPoint);
          boolean isDigit = Character.isDigit(currentChar);
          StringBuilder partNumber = new StringBuilder();
          int partnumberLength = 0;

          if (isDigit) {
            partnumberLength++;
            partNumber.append(currentChar);

            while (isDigit) {
//              partnumberLength++;

              partNumber.append(coordinateMap.get(new Point(currentPoint.x + partnumberLength, currentPoint.y)));

              partnumberLength++;

              isDigit = Character.isDigit(coordinateMap.get(new Point(currentPoint.x + partnumberLength, currentPoint.y)));
            }

            if (true) {
              System.out.println("Part Number: " + partNumber);
            }


//            if (partnumberLength > 1) {
//              if (currentPoint.x > 0) {
//                Point previousPoint = new Point(currentPoint.x - 1, currentPoint.y);
//                char previousChar = coordinateMap.get(previousPoint);
//                boolean isPreviousCharDigit = Character.isDigit(previousChar);
//                if (isPreviousCharDigit) {
//                  continue;
//                }
//              }
//              if (currentPoint.y > 0) {
//                Point previousPoint = new Point(currentPoint.x, currentPoint.y - 1);
//                char previousChar = coordinateMap.get(previousPoint);
//                boolean isPreviousCharDigit = Character.isDigit(previousChar);
//                if (isPreviousCharDigit) {
//                  continue;
//                }
//              }
//              if (currentPoint.x > 0 && currentPoint.y > 0) {
//                Point previousPoint = new Point(currentPoint.x - 1, currentPoint.y - 1);
//                char previousChar = coordinateMap.get(previousPoint);
//                boolean isPreviousCharDigit = Character.isDigit(previousChar);
//                if (isPreviousCharDigit) {
//                  continue;
//                }
//              }
//              if (currentPoint.x > 0 && currentPoint.y < char2dArray.size() - 1) {
//                Point previousPoint = new Point(currentPoint.x - 1, currentPoint.y + 1);
//                char previousChar = coordinateMap.get(previousPoint);
//                boolean isPreviousCharDigit = Character.isDigit(previousChar);
//                if (isPreviousCharDigit) {
//                  continue;
//                }
//              }
//                if (currentPoint.y < char2dArray.size() - 1) {
//                    Point previousPoint = new Point(currentPoint.x, currentPoint.y + 1);
//                    char previousChar = coordinateMap.get(previousPoint);
//                    boolean isPreviousCharDigit = Character.isDigit(previousChar);
//                    if (isPreviousCharDigit) {
//                    continue;
//                    }
//                }
//                if (currentPoint.x < char2dArray.get(x).length - 1) {
//                    Point previousPoint = new Point(currentPoint.x + 1, currentPoint.y);
//                    char previousChar = coordinateMap.get(previousPoint);
//                    boolean isPreviousCharDigit = Character.isDigit(previousChar);
//                    if (isPreviousCharDigit) {
//                    continue;
//                    }
//                }
//                if (currentPoint.x < char2dArray.get(x).length - 1 && currentPoint.y < char2dArray.size() - 1) {
//                    Point previousPoint = new Point(currentPoint.x + 1, currentPoint.y + 1);
//                    char previousChar = coordinateMap.get(previousPoint);
//                    boolean isPreviousCharDigit = Character.isDigit(previousChar);
//                    if (isPreviousCharDigit) {
//                    continue;
//                    }
//                }
//                if (currentPoint.x < char2dArray.get(x).length - 1 && currentPoint.y > 0) {
//                    Point previousPoint = new Point(currentPoint.x + 1, currentPoint.y - 1);
//                    char previousChar = coordinateMap.get(previousPoint);
//                    boolean isPreviousCharDigit = Character.isDigit(previousChar);
//                    if (isPreviousCharDigit) {
//                    continue;
//                    }
//                }
//            }

            String partNumberString = partNumber.toString();
            Long partNumberLong = Long.parseLong(partNumberString);
            partNumberSum += partNumberLong;
            x += partnumberLength - 1;
            partNumber = new StringBuilder();
            }
          }
        }

      // Create an iterator - PartNumLength = 0
      // iterate left to right, find "DIGIT/NUMBER"
      // Set number length to start at 1

      System.out.println("Part Number Sum: " + partNumberSum);
    } catch(FileNotFoundException e) {
      // do nothing
    }

    return 0;
  }

  public static int Part2(String input) {
    return 0;
  }

  private static void debugMappingAndCoordinatesToConsole(int x, int y, int length, Character character, Map<Point, Character> coordinateMap) {
    if (Boolean.FALSE.equals(DEBUGGING)) {
      return;
    }

    if (x < length) {
      if (Boolean.TRUE.equals(VERBOSE)) {
        System.out.printf("Coordinate (%s,%s) = %s  | ", x, y, character);
      } else {
        System.out.print(character);
      }
    } else {
      if (Boolean.TRUE.equals(VERBOSE)) {
        System.out.printf("Coordinate (%s,%s) = %s%n", x, y, character);
      } else {
        System.out.println(character);
      }
    }
//    Point coordinate = new Point(7,2);
//    System.out.println("Character at : (7,2) = " + coordinateMap.get(coordinate));

  }
}
