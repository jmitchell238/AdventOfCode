package org.jmitchell238.aoc.aoc2023.day05;

import java.io.FileNotFoundException;

public class Day05 {
  private static final Boolean DEBUGGING = false;
  private static final Boolean VERBOSE = false;

  public static void main(String[] args) throws FileNotFoundException {
    Day05Run();
  }

  public static void Day05Run() throws FileNotFoundException {
    System.out.println("\n--- Day 5: If You Give A Seed A Fertilizer ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input_test_part1.txt";

    int partOneAnswer = part1(inputTest);
    System.out.println(STR."Part 1: Answer: \{partOneAnswer}");

    int partTwoAnswer = part2(inputTest);
    System.out.println(STR."Part 2: Answer: \{partTwoAnswer}");
  }

  public static int part1(String input) {
    return -1;
  }

  public static int part2(String input) {
    return -1;
  }
}
