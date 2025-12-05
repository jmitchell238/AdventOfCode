package org.jmitchell238.aoc.aoc2023.day04;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class Day04Tests {

    @Test
    void Day04Part1_CalledWithTestInput_Expect13() {
        // Arrange
        String day04Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input_test.txt";

        // Assert
        int expected = 13;

        // Act
        assertThat(Day04.part1(day04Part1Input)).isEqualTo(expected);
    }

    @Test
    void Day04Part1_CalledWithRealInput_Expect15268() {
        // Arrange
        String day04Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input.txt";

        // Assert
        int expected = 15268;

        // Act
        assertThat(Day04.part1(day04Part1Input)).isEqualTo(expected);
    }

    @Test
    void Day04Part2_CalledWithTestInput_Expect30() {
        // Arrange
        String day04Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input_test.txt";

        // Assert
        int expected = 30;
        Day04 day04 = new Day04();

        // Act
        int actual = day04.part2(day04Part2Input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day04Part2_CalledWithRealInput_Expect6283755() {
        // Arrange
        String day04Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input.txt";

        // Assert
        int expected = 6283755;
        Day04 day04 = new Day04();

        // Act
        int actual = day04.part2(day04Part2Input);
        assertThat(actual).isEqualTo(expected);
    }
}
