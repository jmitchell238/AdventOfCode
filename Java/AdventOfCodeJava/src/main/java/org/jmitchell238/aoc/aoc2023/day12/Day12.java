package org.jmitchell238.aoc.aoc2023.day12;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import org.jmitchell238.aoc.generalutilities.LogLevel;

@SuppressWarnings({"java:S106", "java:S1940", "java:S2589", "java:S1118"})
public class Day12 {
    // Configuration flags
    @SuppressWarnings({"ConstantConditions", "unused"})
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings({"ConstantConditions", "unused"})
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    @Getter
    @Setter
    private Boolean isPartTwo = false;

    private final SpringConditionRecords springConditionRecords = new SpringConditionRecords();

    @SuppressWarnings("unused")
    public static void main(String[] args) throws FileNotFoundException {
        day12Run();
    }

    @SuppressWarnings("java:S100")
    public static void day12Run() throws FileNotFoundException {
        logOutput(LogLevel.INFO, true, "\n--- Day 12: Hot Springs ---\n");

        String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day12/input_test.txt";

        Day12 day12 = new Day12();
        long partOneAnswer = day12.part1(inputTest);
        logOutput(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        long partTwoAnswer = day12.part2(inputTest);
        logOutput(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    @SuppressWarnings("unused")
    public long part1(String filePath) throws FileNotFoundException {
        processFile(filePath);
        return findPossibleSpringConditions();
    }

    @SuppressWarnings("unused")
    public long part2(String filePath) throws FileNotFoundException {
        processFile(filePath);

        return -1L;
    }

    private void processFile(String filePath) throws FileNotFoundException {
        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Processing file: " + filePath);

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                SpringConditionRecord springConditionRecord = new SpringConditionRecord(line);
                springConditionRecords.getSpringConditionRecordsList().add(springConditionRecord);
            }
        }

        springConditionRecords.printAllRecordsConditions();
        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "File processing completed");
    }

    private int findPossibleSpringConditions() {
        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Finding possible spring conditions");

        int possibleSpringConditions = 0;
        for (SpringConditionRecord springConditionRecord : springConditionRecords.getSpringConditionRecordsList()) {
            for (SpringAndStatus springAndStatus : springConditionRecord.getSpringsAndStatuses()) {
                if (springAndStatus.getSpringStatus() == SpringStatus.OPERATIONAL_SPRING) {
                    possibleSpringConditions++;
                }
            }
        }

        logOutput(
                LogLevel.DEBUG,
                ENABLE_DEBUG_LOGGING,
                "Total possible spring conditions found: " + possibleSpringConditions);
        return possibleSpringConditions;
    }
}
