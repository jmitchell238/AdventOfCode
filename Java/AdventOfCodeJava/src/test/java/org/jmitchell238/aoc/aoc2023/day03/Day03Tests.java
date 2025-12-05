package org.jmitchell238.aoc.aoc2023.day03;

import java.io.FileNotFoundException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

class Day03Tests {

    @Test
    void Day03Part1_CalledWithTestInput_Expect4361() throws FileNotFoundException {
        // Arrange
        String day03Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input_test_custom.txt";

        // Assert
        Long expected = 4361L;

        // Act
        AssertionsForClassTypes.assertThat(Day03.part1(day03Part1Input)).isEqualTo(expected);
    }

    @Test
    void Day03Part1_CalledWithRealInput_Expect540212() throws FileNotFoundException {
        // Arrange
        String day03Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input.txt";

        // Assert
        Long expected = 540212L;

        // Act
        AssertionsForClassTypes.assertThat(Day03.part1(day03Part1Input)).isEqualTo(expected);
    }

    @Test
    void Day03Part2_CalledWithTestInput_Expect467835() throws FileNotFoundException {
        // Arrange
        String day03Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input_test.txt";

        // Assert
        Long expected = 467835L;

        // Act
        AssertionsForClassTypes.assertThat(Day03.part2(day03Part2Input)).isEqualTo(expected);
    }

    @Test
    void Day03Part2_CalledWithRealInput_Expect87605697() throws FileNotFoundException {
        // Arrange
        String day03Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input.txt";

        // Assert
        Long expected = 87605697L;

        // Act
        AssertionsForClassTypes.assertThat(Day03.part2(day03Part2Input)).isEqualTo(expected);
    }
}
