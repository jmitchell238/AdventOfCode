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

  public void main(String[] args) throws FileNotFoundException {
    Day04Run();
  }

  public void Day04Run() {
    System.out.println("\n--- Day 4: Scratchcards ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input_test.txt";

    int partOneAnswer = part1(input);
    System.out.println("Part 1: Answer: " + partOneAnswer);


    int partTwoAnswer = this.part2(input);
    System.out.println("Part 2: Answer: " + partTwoAnswer);
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
        ArrayList<Integer> winningNumbers;
        ArrayList<Integer> scratchNumbers;
        ArrayList<Integer> matchingNumbers = new ArrayList<>();

        winningNumbers = stream(line.get(1).split(" ")).filter(n -> !Objects.equals(n, "")).mapToInt(Integer::parseInt).boxed()
            .collect(toCollection(ArrayList::new));
        if (DEBUGGING) {
          System.out.print("\n" + line.getFirst() + ":\n");
          System.out.print("Winning Numbers: ");
          for (Integer number : winningNumbers) System.out.print(number + ",");
        }

        scratchNumbers = stream(line.get(2).split(" ")).filter(n -> !Objects.equals(n, "")).mapToInt(Integer::parseInt).boxed()
            .collect(toCollection(ArrayList::new));
        if (DEBUGGING) {
          System.out.print("\nScratch Numbers:");
          for (Integer number : scratchNumbers) System.out.print(number + ",");
        }

        for (Integer number : scratchNumbers) {
          if (winningNumbers.contains(number)) {
            matchingNumbers.add(number);
          }
        }

        if (DEBUGGING) {
          System.out.print("\nMatching Numbers:");
          for (Integer number : matchingNumbers) System.out.print(number + ",");
        }

        int points = 0;
        for (Integer ignored : matchingNumbers) {
          if (points == 0) {
            points++;
          } else {
            points = points * 2;
          }
        }

        if (DEBUGGING) System.out.println("\nPoints: " + points + "\n");

        totalPoints = totalPoints + points;
      }

      return totalPoints;
    } catch (FileNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }

  public int part2(String inputString) {
    int totalCards = 0;

    ArrayList<CountedArrayList> inputSplitWithCount = new ArrayList<>();

    try {
      File input = new File(inputString);
      Scanner scanner = new Scanner(input);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        totalCards++;
        ArrayList<ArrayList<String>> card = new ArrayList<>();
        card.add(new ArrayList<>(Arrays.asList(line.split(": |\\|"))));
        inputSplitWithCount.add(new CountedArrayList(1 , card));

        if (DEBUGGING) System.out.println(line);
      }
      scanner.close();

      for (int i = 0; i < inputSplitWithCount.size(); i++) {
        if (DEBUGGING) System.out.println(inputSplitWithCount.get(i).getCount());
        if (DEBUGGING) System.out.println(inputSplitWithCount.get(i).getList());
      }

      for (int i = 0; i < inputSplitWithCount.size(); i++) {
        // I need to check how many matching numbers there are.
        ArrayList<Integer> winningNumbers;
        ArrayList<Integer> scratchNumbers;
        ArrayList<Integer> matchingNumbers = new ArrayList<>();

        winningNumbers = stream(inputSplitWithCount.get(i).getList().get(0).get(1).split(" ")).filter(n -> !Objects.equals(n, "")).mapToInt(Integer::parseInt).boxed()
            .collect(toCollection(ArrayList::new));
        if (DEBUGGING) {
          System.out.print("\n" + inputSplitWithCount.get(i).getList().get(0).getFirst() + ":\n");
          System.out.print("Winning Numbers: ");
          for (Integer number : winningNumbers) System.out.print(number + ",");
        }

        scratchNumbers = stream(inputSplitWithCount.get(i).getList().get(0).get(2).split(" ")).filter(n -> !Objects.equals(n, "")).mapToInt(Integer::parseInt).boxed()
            .collect(toCollection(ArrayList::new));

        if (DEBUGGING) {
          System.out.print("\nScratch Numbers:");
          for (Integer number : scratchNumbers) System.out.print(number + ",");
        }

        for (Integer number : scratchNumbers) {
          if (winningNumbers.contains(number)) {
            matchingNumbers.add(number);
          }
        }
        if (DEBUGGING) {
          System.out.print("\nMatching Numbers:");
          for (Integer number : matchingNumbers) System.out.print(number + ",");
        }

        int extraCards = matchingNumbers.size();

        for (int p = 0; p < inputSplitWithCount.size(); p++) {
          if (DEBUGGING) System.out.println("\nAmount of Cards: " + inputSplitWithCount.get(p).getCount());
          if (DEBUGGING) System.out.println(inputSplitWithCount.get(p).getList());
        }

        // Loop through each card and loop through it the amount of "count"/cards it has
        for (int cardCount = 0 ; cardCount < inputSplitWithCount.get(i).getCount() ; cardCount++) {
          for (int j = 0; j < extraCards; j++) {
            if (i + j + 1 < inputSplitWithCount.size()) {
              inputSplitWithCount.get(i + j + 1).count = inputSplitWithCount.get(i + j + 1).count + 1;
            }
          }
        }

      }

      if (DEBUGGING) System.out.println("\nAFTER WORKING ON ADDING CARDS");

      for (int i = 0; i < inputSplitWithCount.size(); i++) {
        if (DEBUGGING) System.out.println("\nAmount of Cards: " + inputSplitWithCount.get(i).getCount());
        if (DEBUGGING) System.out.println(inputSplitWithCount.get(i).getList());
      }

      totalCards = 0;

      for (int card = 0; card < inputSplitWithCount.size(); card++) {
        totalCards += inputSplitWithCount.get(card).getCount();
      }

      return totalCards;
    } catch (FileNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }

  class CountedArrayList {
    private int count;
    private ArrayList<ArrayList<String>> list;

    public CountedArrayList(int count, ArrayList<ArrayList<String>> list) {
      this.count = count;
      this.list = list;
    }

    public int getCount() {
      return count;
    }

    public ArrayList<ArrayList<String>> getList() {
      return list;
    }
  }
}
