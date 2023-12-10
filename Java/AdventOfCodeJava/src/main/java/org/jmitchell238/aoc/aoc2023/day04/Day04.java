package org.jmitchell238.aoc.aoc2023.day04;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Day04 {
  private static final Boolean DEBUGGING = false;
  private static final Boolean VERBOSE = false;

  public static void main(String[] args) throws FileNotFoundException {
    Day04Run();
  }

  public static void Day04Run() {
    System.out.println("\n--- Day 4: Scratchcards ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input_test.txt";

    int partOneAnswer = part1(input);
    System.out.println(STR."Part 1: Answer: \{partOneAnswer}");

    int partTwoAnswer = part2(inputTest);
    System.out.println(STR."Part 2: Answer: \{partTwoAnswer}");
  }

  public static int part1(String inputString) {
    int totalPoints = 0;

    ArrayList<ArrayList<String>> inputSplit = new ArrayList<>();

    try {
      File input = new File(inputString);
      Scanner scanner = new Scanner(input);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        inputSplit.add(new ArrayList<>(Arrays.asList(line.split(": |\\|"))));

        if (DEBUGGING) System.out.println(line);
      }
      scanner.close();

      for (ArrayList<String> line : inputSplit) {
        if (DEBUGGING) System.out.println(line);
      }

      for (ArrayList<String> line : inputSplit) {
        ArrayList<Integer> winningNumbers = new ArrayList<>();
        ArrayList<Integer> scratchNumbers = new ArrayList<>();
        ArrayList<Integer> matchingNumbers = new ArrayList<>();

        winningNumbers = stream(line.get(1).split(" ")).filter(n -> !Objects.equals(n, "")).mapToInt(Integer::parseInt).boxed()
            .collect(toCollection(ArrayList::new));
        if (DEBUGGING) {
          System.out.print(STR."\n\{line.getFirst()}:\n");
          System.out.print("Winning Numbers: ");
          for (Integer number : winningNumbers) System.out.print(STR."\{number},");
        }

        scratchNumbers = stream(line.get(2).split(" ")).filter(n -> !Objects.equals(n, "")).mapToInt(Integer::parseInt).boxed()
            .collect(toCollection(ArrayList::new));
        if (DEBUGGING) {
          System.out.print("\nScratch Numbers:");
          for (Integer number : scratchNumbers) System.out.print(STR."\{number},");
        }

        for (Integer number : scratchNumbers) {
          if (winningNumbers.contains(number)) {
            matchingNumbers.add(number);
          }
        }

        if (DEBUGGING) {
          System.out.print("\nMatching Numbers:");
          for (Integer number : matchingNumbers) System.out.print(STR."\{number},");
        }

        int points = 0;
        for (Integer _ : matchingNumbers) {
          if (points == 0) {
            points++;
          } else {
            points = points * 2;
          }
        }

        if (DEBUGGING) System.out.println(STR."\nPoints: \{points}\n");

        totalPoints = totalPoints + points;
      }

      return totalPoints;
    } catch (FileNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }

  public static int part2(String inputString) {
    return -1;
  }
}
