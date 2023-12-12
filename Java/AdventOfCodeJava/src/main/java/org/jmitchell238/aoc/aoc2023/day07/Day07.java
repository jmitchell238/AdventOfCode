package org.jmitchell238.aoc.aoc2023.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day07 {
  private static final Boolean DEBUGGING = false;
  private static final Boolean VERBOSE = false;

  public void main(String[] args) throws FileNotFoundException {
    Day07Run();
  }

  public void Day07Run() throws FileNotFoundException {
    System.out.println("\n--- Day 7: Camel Cards ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input_test.txt";

    long partOneAnswer = part1(inputTest);
    System.out.println(STR."Part 1: Answer: \{partOneAnswer}");

    long partTwoAnswer = part2(inputTest);
    System.out.println(STR."Part 2: Answer: \{partTwoAnswer}");
  }

  public long part1(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (DEBUGGING)
        System.out.println(line);
    }

    return -1;
  }

  public long part2(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (DEBUGGING) System.out.println(line);
    }

    return -1;
  }
}
