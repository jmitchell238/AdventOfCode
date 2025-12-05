package org.jmitchell238.aoc.aoc2023.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import lombok.Getter;
import lombok.Setter;

public class Day11 {
    private static final Boolean DEBUGGING = true;
    private static final Boolean VERBOSE = false;

    @Getter
    @Setter
    private Boolean isPartTwo = false;

    ArrayList<ArrayList<Character>> galaxyGrid = new ArrayList<>();

    public void main(String[] args) throws FileNotFoundException {
        Day11Run();
    }

    public void Day11Run() throws FileNotFoundException {
        System.out.println("\n--- Day 9: Mirage Maintenance ---\n");

        String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day11/input.txt";
        String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day11/input_test.txt";

        long partOneAnswer = part1(inputTest);
        System.out.println("Part 1: Answer: " + partOneAnswer);

        //    long partTwoAnswer = part2(inputTest);
        //    System.out.println(STR."Part 2: Answer: \{partTwoAnswer}");
    }

    public long part1(String filePath) throws FileNotFoundException {
        processFile(filePath);

        for (ArrayList<Character> row : galaxyGrid) {
            for (Character c : row) {
                System.out.print(c);
            }
            System.out.println();
        }

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

            ArrayList<Character> charArrayList = new ArrayList<>();

            for (char c : line.toCharArray()) {
                charArrayList.add(c);
            }

            Set<Character> charSet = new HashSet<>();

            charSet.addAll(charArrayList);

            if (charSet.size() == 1) {
                System.out.println("All the same");
                galaxyGrid.add(new ArrayList<>(charArrayList));
                galaxyGrid.add(new ArrayList<>(charArrayList));
            } else {
                System.out.println("Not all the same");
                galaxyGrid.add(new ArrayList<>(charArrayList));
            }
        }
        scanner.close();

        // Check each COLUMN and if they are the same(all .'s) then insert another . at that position for each row in
        // the column
        // THis is to add another blank column.

        for (int i = 0; i < galaxyGrid.getFirst().size(); i++) {
            boolean allSame = true;
            for (int j = 0; j < galaxyGrid.size(); j++) {
                if (galaxyGrid.get(j).get(i) != '.') {
                    allSame = false;
                    break;
                }
            }
            if (allSame) {
                for (int j = 0; j < galaxyGrid.size(); j++) {
                    ArrayList<Character> row = galaxyGrid.get(j);
                    row.add(i + 1, '.');
                }
            }
        }
    }
}
