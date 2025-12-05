package org.jmitchell238.aoc.aoc2023.day00;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

class Day00Tests {

    @Test
    void Day00Part1_CalledWithTestInput_ExpectCorrectAnswer() {
        // Arrange
        String day00Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day00/input_test_part1.txt";

        // Assert
        int expected = -1;

        // Act
        AssertionsForClassTypes.assertThat(Day00.part1(day00Part1Input)).isEqualTo(expected);
    }

    @Test
    void Day00Part1_CalledWithRealInput_ExpectCorrectAnswer() {
        // Arrange
        String day00Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day00/input.txt";

        // Assert
        int expected = -1;

        // Act
        AssertionsForClassTypes.assertThat(Day00.part1(day00Part1Input)).isEqualTo(expected);
    }

    @Test
    void Day00Part2_CalledWithTestInput_ExpectCorrectAnswer() {
        // Arrange
        String day00Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day00/input_test_part2.txt";

        // Assert
        int expected = -1;

        // Act
        AssertionsForClassTypes.assertThat(Day00.part2(day00Part2Input)).isEqualTo(expected);
    }

    @Test
    void Day00Part2_CalledWithRealInput_ExpectCorrectAnswer() {
        // Arrange
        String day00Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day00/input.txt";

        // Assert
        int expected = -1;

        // Act
        AssertionsForClassTypes.assertThat(Day00.part2(day00Part2Input)).isEqualTo(expected);
    }
}
