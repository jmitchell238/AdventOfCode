package org.jmitchell238.aoc.aoc2023.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day02 {

    public static void Day02() {
        System.out.println("\n--- Day 2: Cube Conundrum ---\n");

        String input = "C:\\Users\\jmitc\\workspace\\AdventOfCode\\Java\\AdventOfCodeJava\\src\\main\\java\\org\\jmitchell238\\aoc\\aoc2023\\Day02\\input.txt";
        String input_test = "C:\\Users\\jmitc\\workspace\\AdventOfCode\\Java\\AdventOfCodeJava\\src\\main\\java\\org\\jmitchell238\\aoc\\aoc2023\\Day02\\input_test.txt";

        int partOneAnswer = Part1(input);
        System.out.println("Part 1: Answer: Possible set ID's sum = " + partOneAnswer);

        int partTwoAnswer = Part2(input_test);
        System.out.println("Part 2: Answer: Power of each set minimum cubes sum = " + partTwoAnswer);
    }

    public static int Part1(String inputString) {
        File input = new File(inputString);

        // Total Number of cubes possible for each color In a SET (not a full round)
        Map<String, Integer> total_number_of_cubes_possible_by_color = new HashMap<>();
        total_number_of_cubes_possible_by_color.put("red", 12);
        total_number_of_cubes_possible_by_color.put("green", 13);
        total_number_of_cubes_possible_by_color.put("blue", 14);

        List<Integer> possible_games = new ArrayList<>();
        possible_games.add(0);

        try {
            Scanner scanner = new Scanner(input);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] line_split = line.split(":");
                Integer game_id = Integer.parseInt(line_split[0].split(" ")[1]);
                String[] sets = line_split[1].split(";");
                boolean not_possible = false;

                for (String set : sets) {
                    String[] cubes_by_color = set.split(",");
                    Map<String, Integer> cubes_in_set = new HashMap<>();
                    not_possible = false;

                    for (String cube_color : cubes_by_color) {
                        cube_color = cube_color.trim();
                        String cubeColor = cube_color.split(" ")[1];
                        Integer cubeAmount = Integer.parseInt(cube_color.split(" ")[0]);
                        cubes_in_set.put(cubeColor, cubeAmount);

                        int current_color_amount_in_set = cubes_in_set.get(cubeColor);
                        int possible_cubes_by_current_color = total_number_of_cubes_possible_by_color.get(cubeColor);
                        if (cubes_in_set.get(cubeColor) > total_number_of_cubes_possible_by_color.get(cubeColor)) {
                            not_possible = true;
                            break;
                        }
                    }

                    if (not_possible) {
                        break;
                    }
                }

                if (not_possible) {
                    continue;
                }

                possible_games.add(game_id);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return possible_games.stream().mapToInt(Integer::intValue).sum();
    }

    public static int Part2(String inputString) {
        File input = new File(inputString);

        try {
            Scanner scanner = new Scanner(input);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
