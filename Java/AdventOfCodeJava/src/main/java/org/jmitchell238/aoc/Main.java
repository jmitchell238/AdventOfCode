package org.jmitchell238.aoc;

import java.io.FileNotFoundException;
import org.jmitchell238.aoc.aoc2023.day01.Day01;
import org.jmitchell238.aoc.aoc2023.day02.Day02;
import org.jmitchell238.aoc.aoc2023.day03.Day03;
import org.jmitchell238.aoc.aoc2023.day04.Day04;
import org.jmitchell238.aoc.aoc2023.day05.Day05;
import org.jmitchell238.aoc.aoc2023.day06.Day06;
import org.jmitchell238.aoc.aoc2023.day07.Day07;
import org.jmitchell238.aoc.aoc2023.day08.Day08;

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

        // Day 5
        Day05 day05 = new Day05();
        day05.main(args);

        // Day 6
        Day06 day06 = new Day06();
        day06.main(args);

        // Day 7
        Day07 day07 = new Day07();
        day07.main(args);

        // Day 8
        Day08 day08 = new Day08();
        day08.main(args);
    }
}
