package org.jmitchell238.aoc.aoc2024.day01;

import static org.jmitchell238.aoc.aoc2025.utilities.Utilities2025.log;

import java.io.FileNotFoundException;
import org.jmitchell238.aoc.generalutilities.LogLevel;

public class Day01 {
    private static final boolean DEBUGGING = false;
    private static final boolean VERBOSE = false;

    static void main(String[] args) throws FileNotFoundException {
        Day01Run();
    }

    public static void Day01Run() throws FileNotFoundException {
      String day4 = "\n--- Day 4: Scratchcards ---\n";
      log(LogLevel.INFO, true, day4);

        String input = "src/main/java/org/jmitchell238/aoc/aoc2024/day00/input.txt";
        String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2024/day00/input_test_part1.txt";

        int partOneAnswer = part1(inputTest);
        log(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        int partTwoAnswer = part2(inputTest);
        log(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    public static int part1(String input) {
        return -1;
    }

    public static int part2(String input) {
        return -1;
    }
}
