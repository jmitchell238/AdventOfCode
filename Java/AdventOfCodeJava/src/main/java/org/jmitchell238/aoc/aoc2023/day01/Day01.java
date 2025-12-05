package org.jmitchell238.aoc.aoc2023.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Day01 {

    public static void Day01() throws FileNotFoundException {
        String dayOneInput = "src/main/java/org/jmitchell238/aoc/aoc2023/day01/day1.txt";

        System.out.println("\n--- Day 1: Trebuchet?! ---\n");

        File day1Part1Input = new File(dayOneInput);
        int partOneTotal = Part1(day1Part1Input);

        System.out.println("Part 1: Answer: " + partOneTotal);

        File day1Part2Input = new File(dayOneInput);

        int partTwoTotal = Part2(day1Part2Input);
        System.out.println("Part 2: Answer: " + partTwoTotal);
    }

    public static int Part1(File day1Part1Input) throws FileNotFoundException {
        int total = 0;

        try (Scanner scanner = new Scanner(day1Part1Input); ) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                StringBuilder numberString =
                        new StringBuilder(line.replaceAll("\\D", "")); // "\\D" is a regex for non-digits same as [^0-9]

                total = getTotal(numberString, total);
            }
        } catch (FileNotFoundException ignored) {
            // ignored
        }

        return total;
    }

    public static int Part2(File day1Part2Input) throws FileNotFoundException {
        int total = 0;

        try (Scanner scanner = new Scanner(day1Part2Input); ) {
            while (scanner.hasNextLine()) {
                Map<String, Integer> wordToNumber = getWordToNumber();
                String line = scanner.nextLine();
                StringBuilder newString = new StringBuilder();
                int index = 0;

                for (char c : line.toCharArray()) {
                    if (Character.isDigit(c)) {
                        newString.append(c);
                        index++;
                        continue;
                    }

                    if (!Character.isDigit(c)) {
                        for (String key : wordToNumber.keySet()) {
                            try {
                                int keyLength = key.length();
                                String lineSubstring = line.substring(index, index + keyLength);
                                if (Objects.equals(key, lineSubstring)) {
                                    newString.append(wordToNumber.get(key));
                                    break;
                                }
                            } catch (StringIndexOutOfBoundsException ignored) {
                                // ignored
                            }
                        }
                    }

                    index++;
                }

                total = getTotal(newString, total);
            }
        } catch (FileNotFoundException ignored) {
            // ignored
        }

        return total;
    }

    private static int getTotal(StringBuilder newString, int total) {
        int firstNumber = Integer.parseInt(newString.substring(0, 1));
        int lastNumber = Integer.parseInt(newString.substring(newString.length() - 1));
        String number = String.valueOf(firstNumber + "" + lastNumber);
        total += Integer.parseInt(number);
        return total;
    }

    private static Map<String, Integer> getWordToNumber() {
        Map<String, Integer> wordToNumber = new HashMap<>();
        wordToNumber.put("one", 1);
        wordToNumber.put("1", 1);
        wordToNumber.put("two", 2);
        wordToNumber.put("2", 2);
        wordToNumber.put("three", 3);
        wordToNumber.put("3", 3);
        wordToNumber.put("four", 4);
        wordToNumber.put("4", 4);
        wordToNumber.put("five", 5);
        wordToNumber.put("5", 5);
        wordToNumber.put("six", 6);
        wordToNumber.put("6", 6);
        wordToNumber.put("seven", 7);
        wordToNumber.put("7", 7);
        wordToNumber.put("eight", 8);
        wordToNumber.put("8", 8);
        wordToNumber.put("nine", 9);
        wordToNumber.put("9", 9);
        return wordToNumber;
    }
}
