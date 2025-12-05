package org.jmitchell238.aoc.aoc2025.day01;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

class Day01Tests {

    @Test
    void Day01Part1_CalledWithTestInput_ExpectCorrectAnswer() {
        // Arrange
        String day01Part1Input = "src/test/java/org/jmitchell238/aoc/aoc2025/day01/input_test_part1.txt";

        // Assert
        int expected = 3;

        // Act
        AssertionsForClassTypes.assertThat(Day01.part1(day01Part1Input)).isEqualTo(expected);
    }

    @Test
    void Day01Part1_CalledWithRealInput_ExpectCorrectAnswer() {
        // Arrange
        String day01Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2025/day01/input.txt";

        // Assert
        int expected = 1118;

        // Act
        AssertionsForClassTypes.assertThat(Day01.part1(day01Part1Input)).isEqualTo(expected);
    }

    @Test
    void Day01Part2_CalledWithTestInput_ExpectCorrectAnswer() {
        // Arrange
        String day01Part2Input = "src/test/java/org/jmitchell238/aoc/aoc2025/day01/input_test_part2.txt";

        // Assert
        int expected = 6;

        // Act
        AssertionsForClassTypes.assertThat(Day01.part2(day01Part2Input)).isEqualTo(expected);
    }

    @Test
    void Day01Part2_CalledWithRealInput_ExpectCorrectAnswer() {
        // Arrange
        String day01Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2025/day01/input.txt";

        // Assert
        int expected = 6289;

        // Act
        AssertionsForClassTypes.assertThat(Day01.part2(day01Part2Input)).isEqualTo(expected);
    }
}
