package org.jmitchell238.aoc.aoc2023.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import org.jmitchell238.aoc.aoc2023.utilities.Utilities;

public class Day09 {
  private static final Boolean DEBUGGING = false;
  private static final Boolean VERBOSE = false;
  @Getter @Setter
  private Boolean isPartTwo = false;

  public void main(String[] args) throws FileNotFoundException {
    Day09Run();
  }

  public void Day09Run() throws FileNotFoundException {
    System.out.println("\n--- Day 9: Mirage Maintenance ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day09/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day09/input_test.txt";

    long partOneAnswer = part1(inputTest);
    System.out.println(STR."Part 1: Answer: \{partOneAnswer}");

    long partTwoAnswer = part2(inputTest);
    System.out.println(STR."Part 2: Answer: \{partTwoAnswer}");
  }

  public long part1(String filePath) throws FileNotFoundException {
    processFile(filePath);
    return -1;
  }

  public long part2(String filePath) throws FileNotFoundException {
    processFile(filePath);
    return -1;
  }

  private void processFile(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
    }
  }
}
