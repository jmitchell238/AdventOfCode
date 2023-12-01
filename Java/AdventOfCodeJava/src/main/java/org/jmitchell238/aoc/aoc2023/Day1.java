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
// Open the resources/aoc2023/day1.txt file and read it into a String
    // For each line in the file, strip all but numerical characters
    // Each line number should be 2 digits
    // If there are more than 2 digits, take the first and last
    // If there is only 1 digit, use it as the first and the last
// Print the total

    int total = 0;

    try (Scanner scanner = new Scanner(day1Part1Input);) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String number = line.replaceAll("[^0-9]", "");
        String firstNumber = number.substring(0, 1);
        String lastNumber = number.substring(number.length() - 1);
        number = firstNumber + lastNumber;
//      System.out.println(number); // Debug line.
        total += Integer.parseInt(number);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return total;
  }

  public static int Part2(File day1Part2Input) throws FileNotFoundException {
    int total = 0;

    try (Scanner scanner = new Scanner(day1Part2Input);) {
      while (scanner.hasNextLine()) {
        // This time I need a dictionary of words to numbers because I don't know how to do this in Java.
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
//        wordToNumber.put("zero", 0);

        String line = scanner.nextLine();

        StringBuilder newString = new StringBuilder();
        System.out.println(newString);

        // iterate over the line and add any words that are numbers or numbers to a new line, An example would be
        // "eightwothree" would become "83"
        // "two1nine" would become "29"

        // go left to right on the line and find each matching word in the dictionary and add to newString
        // each Key in the dictionary and see if it is in the line
        // if so, insert the value into the starting index of that word in the new string

        int index = 0;

        for (char c : line.toCharArray()) {
          if (Character.isDigit(c)) {
            newString.append(c);
            System.out.println("Appended Digit " + c + " to " + newString);
            index++;
            continue;
          }

          if (!Character.isDigit(c)) {
            System.out.println("Character " + c + " is at index " + index);
            for (String key : wordToNumber.keySet()) {
              // I want to check the line starting at the current character to see if the key is there, if so, add the value to the newString
              // if I get an index out of bounds exception, I want to catch it and continue
              try {
                int keyLength = key.length();
                // check to see if the key is at the current index,
                // if so, add the value to the newString and break out of the
                // for each key in keySet loop
                String lineSubstring = line.substring(index, index + keyLength);
                if (Objects.equals(key, lineSubstring)) {
                  newString.append(wordToNumber.get(key));
                  // break out of the for each key in keySet loop
                  System.out.println("Appended character " + wordToNumber.get(key) + " to " + newString);
                  break;
                }
              } catch (StringIndexOutOfBoundsException e) {
                // do nothing
              }
            }
          }
          System.out.println(newString);

          index++;
        }



//        newString = new StringBuilder(newString.toString().replaceAll("[^0-9]", ""));
        int firstNumber = Integer.parseInt(newString.substring(0, 1));
        int lastNumber = Integer.parseInt(newString.substring(newString.length() - 1));
        String number = String.valueOf(firstNumber + "" + lastNumber);
        System.out.println(number);
        total += Integer.parseInt(number);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return total;
  }
}

