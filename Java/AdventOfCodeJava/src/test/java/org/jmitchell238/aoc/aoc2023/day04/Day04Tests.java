package org.jmitchell238.aoc.aoc2023.day04;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class Day04Tests {

    @Test
    void Day04Part1_CalledWithTestInput_Expect13() {
        // Arrange
        String day04Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input_test.txt";
        Day04 day04 = new Day04();
        int expected = 13;

        // Act
        int actual = day04.solvePart1(day04Part1Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day04Part1_CalledWithRealInput_Expect15268() {
        // Arrange
        String day04Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input.txt";
        Day04 day04 = new Day04();
        int expected = 15268;

        // Act
        int actual = day04.solvePart1(day04Part1Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day04Part2_CalledWithTestInput_Expect30() {
        // Arrange
        String day04Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input_test.txt";
        Day04 day04 = new Day04();
        int expected = 30;

        // Act
        int actual = day04.solvePart2(day04Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day04Part2_CalledWithRealInput_Expect6283755() {
        // Arrange
        String day04Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input.txt";
        Day04 day04 = new Day04();
        int expected = 6283755;

        // Act
        int actual = day04.solvePart2(day04Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
