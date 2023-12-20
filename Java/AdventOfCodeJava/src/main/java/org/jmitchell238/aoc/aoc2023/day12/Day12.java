package org.jmitchell238.aoc.aoc2023.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class Day12 {
  private static final Boolean DEBUGGING = false;
  @Getter @Setter
  private Boolean isPartTwo = false;
  private SpringConditionRecordsList springConditionRecordsList = new SpringConditionRecordsList();

  public void main(String[] args) throws FileNotFoundException {
    Day12Run();
  }

  public void Day12Run() throws FileNotFoundException {
    System.out.println("\n--- Day 9: Mirage Maintenance ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day12/input.txt";
    String input_test = "src/main/java/org/jmitchell238/aoc/aoc2023/day12/input_test.txt";

    long partOneAnswer = part1(input_test);
    System.out.println(STR."Part 1: Answer: \{partOneAnswer}");

//    long partTwoAnswer = part2(input_test);
//    System.out.println(STR."Part 2: Answer: \{partTwoAnswer}");
  }

  public long part1(String filePath) throws FileNotFoundException {
    processFile(filePath);

    return -1L;
  }

  public long part2(String filePath) throws FileNotFoundException {
    processFile(filePath);

    return -1L;
  }

  private void processFile(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      SpringConditionRecord springConditionRecord = new SpringConditionRecord(line);
      springConditionRecordsList.getSpringConditionRecordsList().add(springConditionRecord);
    }

    springConditionRecordsList.printAllRecordsConditions();
    scanner.close();
  }
}
