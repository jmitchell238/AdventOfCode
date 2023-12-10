package org.jmitchell238.aoc.aoc2023.day04;

import java.io.FileNotFoundException;

public class Day04 {
  private static final Boolean DEBUGGING = false;
  private static final Boolean VERBOSE = false;

  public static void main(String[] args) throws FileNotFoundException {
    Day04Run();
  }

  public static void Day04Run() throws FileNotFoundException {
    System.out.println("\n--- Day 4: Scratchcards ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input_test_part1.txt";

    int partOneAnswer = part1(inputTest);
    System.out.println(STR."Part 1: Answer: \{partOneAnswer}");

    int partTwoAnswer = part2(inputTest);
    System.out.println(STR."Part 2: Answer: \{partTwoAnswer}");
  }

  public static int part1(String inputString) {
    return -1;
  }

  public static int part2(String inputString) {
    return -1;
  }
}
