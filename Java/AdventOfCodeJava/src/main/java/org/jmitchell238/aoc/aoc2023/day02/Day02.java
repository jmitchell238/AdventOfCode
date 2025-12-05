package org.jmitchell238.aoc.aoc2023.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day02 {

    public static void Day02() {
        System.out.println("\n--- Day 2: Cube Conundrum ---\n");

        String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day02/input.txt";
        String input_test = "src/main/java/org/jmitchell238/aoc/aoc2023/day02/input_test.txt";

        int partOneAnswer = Part1(input);
        System.out.println("Part 1: Answer: Possible set ID's sum = " + partOneAnswer);

        int partTwoAnswer = Part2(input_test);
        System.out.println("Part 2: Answer: Power of each set minimum cubes sum = " + partTwoAnswer);
    }

    public static int Part1(String inputString) {
        File input = new File(inputString);
        Map<String, Integer> totalNumberOfCubesPossibleByColorForSet = getPossibleCubesByColor();

        List<Integer> possibleGames = new ArrayList<>();
        possibleGames.add(0);

        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] lineSplit = line.split(":");
                Integer gameId = Integer.parseInt(lineSplit[0].split(" ")[1]);

                String[] sets = lineSplit[1].split(";");
                boolean notPossible = false;

                for (String set : sets) {
                    String[] cubesByColorWithAmount = set.split(",");
                    Map<String, Integer> cubesInSet = new HashMap<>();

                    for (String cubeColorWithAmount : cubesByColorWithAmount) {
                        cubeColorWithAmount = cubeColorWithAmount.trim();
                        String currentCubeColor = cubeColorWithAmount.split(" ")[1];
                        Integer cubeAmount =
                                Integer.parseInt(cubeColorWithAmount.split(" ")[0]);
                        cubesInSet.put(currentCubeColor, cubeAmount);

                        int currentColorAmountInSet = cubesInSet.get(currentCubeColor);
                        int possibleCubesByCurrentColor = totalNumberOfCubesPossibleByColorForSet.get(currentCubeColor);
                        if (currentColorAmountInSet > possibleCubesByCurrentColor) {
                            notPossible = true;
                            break;
                        }
                    }

                    if (notPossible) {
                        break;
                    }
                }

                if (notPossible) {
                    continue;
                }

                possibleGames.add(gameId);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return possibleGames.stream().mapToInt(Integer::intValue).sum();
    }

    public static int Part2(String inputString) {
        File input = new File(inputString);
        List<Integer> powersOfNecessaryCubeAmounts = new ArrayList<>();

        try (Scanner scanner = new Scanner(input); ) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] sets = line.split(":")[1].split(";");
                int round = Integer.parseInt(line.split(":")[0].split(" ")[1]);

                // DEBUGGING - Printing each round to console
                // System.out.println("\nRound " + round + ": ");

                Map<String, Integer> highestAmountByColorInRound = new HashMap<>();
                highestAmountByColorInRound.put("green", 0);
                highestAmountByColorInRound.put("red", 0);
                highestAmountByColorInRound.put("blue", 0);

                for (String set : sets) {
                    String[] cubesByColorWithAmount = set.split(",");
                    Map<String, Integer> cubesInSet = new HashMap<>();
                    cubesInSet.put("green", 0);
                    cubesInSet.put("red", 0);
                    cubesInSet.put("blue", 0);

                    for (String cubeColorWithAmount : cubesByColorWithAmount) {
                        cubeColorWithAmount = cubeColorWithAmount.trim();
                        String currentCubeColor = cubeColorWithAmount.split(" ")[1];
                        Integer currentCubeAmount =
                                Integer.parseInt(cubeColorWithAmount.split(" ")[0]);

                        int totalCubeColorAmount = cubesInSet.get(currentCubeColor) + currentCubeAmount;
                        cubesInSet.put(currentCubeColor, totalCubeColorAmount);
                    }

                    // Update cubeAmount in the original map if the new value is higher
                    highestAmountByColorInRound.replaceAll(
                            (key, oldValue) -> Math.max(oldValue, cubesInSet.getOrDefault(key, oldValue)));
                }

                // DEBUGGING - print the highest number of cubes by color in the found
                // System.out.println("Highest amount of cubes by color in this round:");
                // highestAmountByColorInRound.forEach((key, value) -> System.out.println(key + ": " +
                // value));

                // Multiply all values together
                int power = highestAmountByColorInRound.values().stream().reduce(1, (a, b) -> a * b);

                // Add this power to the powersOfNecessaryCubeAmounts List
                powersOfNecessaryCubeAmounts.add(power);

                // DEBUGGING - Print the Power of the total cubes needed. DEBUGGING
                // System.out.println("Power of needed cubes: " + power);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //        return -1;
        return powersOfNecessaryCubeAmounts.stream().mapToInt(Integer::intValue).sum();
    }

    private static Map<String, Integer> getPossibleCubesByColor() {
        Map<String, Integer> totalNumberOfCubesPossibleByColor = new HashMap<>();
        totalNumberOfCubesPossibleByColor.put("red", 12);
        totalNumberOfCubesPossibleByColor.put("green", 13);
        totalNumberOfCubesPossibleByColor.put("blue", 14);

        return totalNumberOfCubesPossibleByColor;
    }
}
