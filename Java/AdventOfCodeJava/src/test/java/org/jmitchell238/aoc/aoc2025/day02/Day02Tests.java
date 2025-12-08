package org.jmitchell238.aoc.aoc2025.day02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Day02Tests {

    @Test
    void Day02Part1_CalledWithTestInput_ExpectCorrectAnswer() {
        // Arrange
        String day02Part1Input = "src/test/java/org/jmitchell238/aoc/aoc2025/day02/input_test_part1.txt";

        // Assert
        int expected = 1227775554;

        // Act
        assertThat(Day02.part1(day02Part1Input)).isEqualTo(expected);
    }

    @Test
    void Day02Part1_CalledWithRealInput_ExpectCorrectAnswer() {
        // Arrange
        String day02Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2025/day02/input.txt";

        // Assert
        int expected = -1;

        // Act
        assertThat(Day02.part1(day02Part1Input)).isEqualTo(expected);
    }

    @Test
    void Day02Part2_CalledWithTestInput_ExpectCorrectAnswer() {
        // Arrange
        String day02Part2Input = "src/test/java/org/jmitchell238/aoc/aoc2025/day02/input_test_part2.txt";

        // Assert
        int expected = -1;

        // Act
        assertThat(Day02.part2(day02Part2Input)).isEqualTo(expected);
    }

    @Test
    void Day02Part2_CalledWithRealInput_ExpectCorrectAnswer() {
        // Arrange
        String day02Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2025/day02/input.txt";

        // Assert
        int expected = -1;

        // Act
        assertThat(Day02.part2(day02Part2Input)).isEqualTo(expected);
    }
}
