package org.jmitchell238.aoc;

import java.io.FileNotFoundException;
// 2023 imports
import org.jmitchell238.aoc.aoc2023.day01.Day01;
import org.jmitchell238.aoc.aoc2023.day02.Day02;
import org.jmitchell238.aoc.aoc2023.day03.Day03;
import org.jmitchell238.aoc.aoc2023.day04.Day04;
import org.jmitchell238.aoc.aoc2023.day05.Day05;
import org.jmitchell238.aoc.aoc2023.day06.Day06;
import org.jmitchell238.aoc.aoc2023.day07.Day07;
import org.jmitchell238.aoc.aoc2023.day08.Day08;
import org.jmitchell238.aoc.aoc2023.day09.Day09;
import org.jmitchell238.aoc.aoc2023.day10.Day10;
import org.jmitchell238.aoc.aoc2023.day11.Day11;
import org.jmitchell238.aoc.aoc2023.day12.Day12;

/**
 * Main entry point for running Advent of Code solutions.
 * <p>
 * By default, runs the current year (2025).
 * Uncomment other year methods to run previous years' solutions.
 * </p>
 */
@SuppressWarnings({"java:S106", "java:S1118"})
public class Main {

    @SuppressWarnings("unused")
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("=== Advent of Code Solutions ===\n");

        // Run current year (2025) by default
        runCurrentYear();

        // Uncomment below to run previous years:
        // runYear2024();
        // runYear2023();

        System.out.println("=== ALL SOLUTIONS COMPLETED ===");
    }

    /**
     * Runs the current year's Advent of Code solutions (2025).
     */
    private static void runCurrentYear() {
        runYear2025();
    }

    /**
     * Runs all available 2025 Advent of Code solutions.
     */
    private static void runYear2025() {
        System.out.println("🎄 ADVENT OF CODE 2025 🎄");
        System.out.println("Current Year - Latest Solutions");
        System.out.println("================================\n");

        // Day 0 (Template)
        System.out.println("Running Day 0 (Template)...");
        org.jmitchell238.aoc.aoc2025.day00.Day00.main(new String[0]);

        // Day 1 - Secret Entrance
        System.out.println("Running Day 1 - Secret Entrance...");
        org.jmitchell238.aoc.aoc2025.day01.Day01.run();

        // Day 2 - Gift Shop
        System.out.println("Running Day 2 - Gift Shop...");
        org.jmitchell238.aoc.aoc2025.day02.Day02.run();

        System.out.println("\n✅ 2025 COMPLETED\n");
    }

    /**
     * Runs all available 2024 Advent of Code solutions.
     * Uncomment the call in main() to run these solutions.
     */
    private static void runYear2024() throws FileNotFoundException {
        System.out.println("🎄 ADVENT OF CODE 2024 🎄");
        System.out.println("Previous Year Solutions");
        System.out.println("=======================\n");

        // Day 0 (Template)
        System.out.println("Running Day 0 (Template)...");
        org.jmitchell238.aoc.aoc2024.day00.Day00.main(new String[0]);

        System.out.println("\n✅ 2024 COMPLETED\n");
    }

    /**
     * Runs all available 2023 Advent of Code solutions.
     * Uncomment the call in main() to run these solutions.
     */
    private static void runYear2023() throws FileNotFoundException {
        System.out.println("🎄 ADVENT OF CODE 2023 🎄");
        System.out.println("Archive Solutions");
        System.out.println("=================\n");

        // Day 1 - Trebuchet?!
        System.out.println("Running Day 1 - Trebuchet?!...");
        Day01.runDay01();

        // Day 2 - Cube Conundrum
        System.out.println("Running Day 2 - Cube Conundrum...");
        Day02.runDay02();

        // Day 3 - Gear Ratios
        System.out.println("Running Day 3 - Gear Ratios...");
        Day03.runDay03();

        // Day 4 - Scratchcards
        System.out.println("Running Day 4 - Scratchcards...");
        Day04 day04 = new Day04();
        day04.runDay04();

        // Day 5 - If You Give A Seed A Fertilizer
        System.out.println("Running Day 5 - If You Give A Seed A Fertilizer...");
        Day05 day05 = new Day05();
        day05.runDay05();

        // Day 6 - Wait For It
        System.out.println("Running Day 6 - Wait For It...");
        Day06 day06 = new Day06();
        day06.runDay06();

        // Day 7
        System.out.println("Running Day 7...");
        Day07 day07 = new Day07();
        day07.main(new String[0]);

        // Day 8
        System.out.println("Running Day 8...");
        Day08 day08 = new Day08();
        day08.main(new String[0]);

        // Day 9 - Mirage Maintenance
        System.out.println("Running Day 9 - Mirage Maintenance...");
        Day09 day09 = new Day09();
        day09.runDay09();

        // Day 10
        System.out.println("Running Day 10...");
        Day10 day10 = new Day10();
        day10.main(new String[0]);

        // Day 11
        System.out.println("Running Day 11...");
        Day11 day11 = new Day11();
        day11.main(new String[0]);

        // Day 12
        System.out.println("Running Day 12...");
        Day12 day12 = new Day12();
        day12.main(new String[0]);

        System.out.println("\n✅ 2023 COMPLETED\n");
    }
}
