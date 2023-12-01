/**
 * --- Day 1: Trebuchet?! ---
 *
 * Something is wrong with global snow production, and you've been selected to take a look. The Elves have even given you a map;
 * on it, they've used stars to mark the top fifty locations that are likely to be having problems.
 *
 * You've been doing this long enough to know that to restore snow operations, you need to check all fifty stars by December 25th.
 *
 * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when
 * you complete the first. Each puzzle grants one star. Good luck!
 *
 * You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even sending you ("the sky")
 * and why your map looks mostly blank ("you sure ask a lot of questions") and hang on did you just say the sky
 * ("of course, where do you think snow comes from") when you realize that the Elves are already loading you into a trebuchet
 * ("please hold still, we need to strap you in").
 *
 * As they're making the final adjustments, they discover that their calibration document (your puzzle input)
 * has been amended by a very young Elf who was apparently just excited to show off her art skills.
 * Consequently, the Elves are having trouble reading the values on the document.
 *
 * The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves now
 * need to recover. On each line, the calibration value can be found by combining the first digit and the last digit (in that order)
 * to form a single two-digit number.
 *
 * For example:
 *
 * 1abc2
 * pqr3stu8vwx
 * a1b2c3d4e5f
 * treb7uchet
 *
 * In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.
 *
 * Consider your entire calibration document. What is the sum of all of the calibration values?
 */

/*
  * --- Part Two ---
  *
  * Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters:
  * one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".
  *
  * Equipped with this new information, you now need to find the real first and last digit on each line. For example:
  *
  * two1nine
  * eightwothree
  * abcone2threexyz
  * xtwone3four
  * 4nineeightseven2
  * zoneight234
  * 7pqrstsixteen
  *
  * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.
  *
  * What is the sum of all of the calibration values?
 */

package org.jmitchell238.aoc.aoc2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


public class Day1 {

  public static void Day1() throws FileNotFoundException {
    System.out.println("Day 1: Trebuchet?!");

    File day1Part1Input = new File("/Users/jamesm.mitchell/james-workspace/AdventOfCode/Java/AdventOfCodeJava/src/main/resources/aoc2023/day1_part1.txt");
    int partOneTotal = Part1(day1Part1Input);

    System.out.println("Part 1: Answer: " + partOneTotal);

    File day1Part2Input = new File("/Users/jamesm.mitchell/james-workspace/AdventOfCode/Java/AdventOfCodeJava/src/main/resources/aoc2023/day1_part2.txt");

    int partTwoTotal = Part2(day1Part2Input);
    System.out.println("Part 2: Answer:" + partTwoTotal);
  }


  public static int Part1(File day1Part1Input) throws FileNotFoundException {
    int total = 0;

    try (Scanner scanner = new Scanner(day1Part1Input);) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        StringBuilder numberString = new StringBuilder(line.replaceAll("[^0-9]", ""));


        total = getTotal(numberString, total);
      }
    } catch (FileNotFoundException ignored) {
      // ignored
    }

    return total;
  }

  public static int Part2(File day1Part2Input) throws FileNotFoundException {
    int total = 0;

    try (Scanner scanner = new Scanner(day1Part2Input);) {
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

