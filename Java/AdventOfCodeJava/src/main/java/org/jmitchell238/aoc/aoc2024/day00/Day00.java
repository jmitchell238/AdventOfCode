package org.jmitchell238.aoc.aoc2024.day00;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import org.jmitchell238.aoc.generalutilities.LogLevel;

@SuppressWarnings({"java:S106", "java:S1940", "java:S2589", "java:S1118"})
public class Day00 {
    // Configuration flags
    @SuppressWarnings({"ConstantConditions", "unused"})
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings({"ConstantConditions", "unused"})
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Day00Run();
    }

    @SuppressWarnings("java:S100")
    public static void Day00Run() {
        logOutput(LogLevel.INFO, true, "\n--- Day 00: Template ---\n");

        String input = "src/main/java/org/jmitchell238/aoc/aoc2024/day00/input.txt";
        String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2024/day00/input_test_part1.txt";

        int partOneAnswer = part1(input);
        logOutput(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        int partTwoAnswer = part2(inputTest);
        logOutput(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    @SuppressWarnings("unused")
    public static int part1(String input) {
        return -1;
    }

    @SuppressWarnings("unused")
    public static int part2(String input) {
        return -1;
    }
}
