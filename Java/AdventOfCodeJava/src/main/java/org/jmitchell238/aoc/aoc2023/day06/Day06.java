package org.jmitchell238.aoc.aoc2023.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day06 {
  private static final Boolean DEBUGGING = false;
  private static final Boolean VERBOSE = false;

  public void main(String[] args) throws FileNotFoundException {
    Day06Run();
  }

  public void Day06Run() throws FileNotFoundException {
    System.out.println("\n--- Day 6: Wait For It ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input_test.txt";

    int partOneAnswer = part1(inputTest);
    System.out.println(STR."Part 1: Answer: \{partOneAnswer}");

    int partTwoAnswer = part2(inputTest);
    System.out.println(STR."Part 2: Answer: \{partTwoAnswer}");
  }

  public int part1(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      System.out.println(line);
    }

    return -1;
  }

  public int part2(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));

    return -1;
  }
}
