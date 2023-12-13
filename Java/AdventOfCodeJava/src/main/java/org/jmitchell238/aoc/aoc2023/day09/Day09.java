package org.jmitchell238.aoc.aoc2023.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.jmitchell238.aoc.aoc2023.utilities.Utilities;

public class Day09 {
  private static final Boolean DEBUGGING = true;
  private static final Boolean VERBOSE = false;
  @Getter @Setter
  private Boolean isPartTwo = false;
  private final List<List<Integer>> sequences = new ArrayList<>();

  public void main(String[] args) throws FileNotFoundException {
    Day09Run();
  }

  public void Day09Run() throws FileNotFoundException {
    System.out.println("\n--- Day 9: Mirage Maintenance ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day09/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day09/input_test.txt";

    long partOneAnswer = part1(input);
    System.out.println(STR."Part 1: Answer: \{partOneAnswer}");

    long partTwoAnswer = part2(input);
    System.out.println(STR."Part 2: Answer: \{partTwoAnswer}");
  }

  public long part1(String filePath) throws FileNotFoundException {
    processFile(filePath);

    int sum = 0;

    List<List<Integer>> changedSequences = new ArrayList<>();

    for (List<Integer> sequence : sequences) {
      List<Integer> changedSequence = findNextNumberInSequenceReturnSequence(sequence);
      changedSequences.add(changedSequence);
    }

    for (List<Integer> changedSequence : changedSequences) {
      sum += changedSequence.getLast();
    }

    return sum;
  }

  public long part2(String filePath) throws FileNotFoundException {
    processFile(filePath);

    int sum = 0;

    List<List<Integer>> changedSequences = new ArrayList<>();

    for (List<Integer> sequence : sequences) {
      List<Integer> changedSequence = findPreviousNumberInSequenceReturnSequence(sequence);
      changedSequences.add(changedSequence);
    }

    for (List<Integer> changedSequence : changedSequences) {
      sum += changedSequence.getFirst();
    }

    return sum;
  }

  private void processFile(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      int[] sequence = Utilities.splitToIntArray(line, " ");
      if (DEBUGGING) System.out.println(STR."Sequence: \{Arrays.toString(sequence)}");

      sequences.add(Arrays.stream(sequence).boxed().collect(Collectors.toList()));
    }
  }

  private int findNextNumberInSequence(List<Integer> sequence) {
    List<Integer> differences = new ArrayList<>();

    for (int i = 0; i < sequence.size() - 1; i++) {
      differences.add(sequence.get(i + 1) - sequence.get(i));
    }

    if (!differences.stream().allMatch(difference -> difference == 0)) {
      int nextNumber = findNextNumberInSequence(differences);

      return sequence.getLast() + nextNumber;
    }

    return sequence.getLast();
  }

  private List<Integer> findNextNumberInSequenceReturnSequence(List<Integer> sequence) {
    List<Integer> differences = new ArrayList<>();

    for (int i = 0; i < sequence.size() - 1; i++) {
      differences.add(sequence.get(i + 1) - sequence.get(i));
    }

    // If the differences List doesn't contain all 0's, recursively call this function with the differences List
    if (!differences.stream().allMatch(difference -> difference == 0)) {
      int nextNumber = findNextNumberInSequence(differences);

      // Calculate the next number in the sequence
      sequence.add(sequence.getLast() + nextNumber);
      return sequence;
    }

    // Return the last number in the original sequence
    return sequence;
  }

  private int findPreviousNumberInSequence(List<Integer> sequence) {
    List<Integer> differences = new ArrayList<>();

    for (int i = 0; i < sequence.size() - 1; i++) {
      differences.add(sequence.get(i + 1) - sequence.get(i));
    }

    if (!differences.stream().allMatch(difference -> difference == 0)) {
      int previousNumber = findPreviousNumberInSequence(differences);

      return sequence.getFirst() - previousNumber;
    }

    return sequence.getFirst();
  }

  private List<Integer> findPreviousNumberInSequenceReturnSequence(List<Integer> sequence) {
    List<Integer> differences = new ArrayList<>();

    for (int i = 0; i < sequence.size() - 1; i++) {
      differences.add(sequence.get(i + 1) - sequence.get(i));
    }

    if (!differences.stream().allMatch(difference -> difference == 0)) {
      int previousNumber = findPreviousNumberInSequence(differences);

      sequence.addFirst(sequence.getFirst() - previousNumber);
      return sequence;
    }

    return sequence;
  }
}
