package org.jmitchell238.aoc;

import java.io.FileNotFoundException;
import org.jmitchell238.aoc.aoc2023.day01.Day01;
import org.jmitchell238.aoc.aoc2023.day02.Day02;
import org.jmitchell238.aoc.aoc2023.day03.Day03;
import org.jmitchell238.aoc.aoc2023.day04.Day04;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Hello Advent of Code 2023!");

    // Day 1
    Day01.Day01();

    // Day 2
    Day02.Day02();

    // Day 3
    Day03.main(args);

    // Day 4
    Day04 day04 = new Day04();
    day04.main(args);
  }
}
