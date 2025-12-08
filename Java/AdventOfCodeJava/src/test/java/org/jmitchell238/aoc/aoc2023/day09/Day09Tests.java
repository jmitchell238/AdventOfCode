package org.jmitchell238.aoc.aoc2023.day09;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class Day09Tests {

    @AfterEach
    void tearDown() {
        // Reset is handled per instance, so no static reset needed
    }

    @Test
    void Day09Part1_CalledWithTestInput_Expect114() throws FileNotFoundException {
        // Arrange
        String day09Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day09/input_test.txt";
        Day09 day09 = new Day09();
        long expected = 114L;

        // Act
        long actual = day09.solvePart1(day09Part1Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day09Part1_CalledWithRealInput_Expect1684566095() throws FileNotFoundException {
        // Arrange
        String day09Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day09/input.txt";
        Day09 day09 = new Day09();
        long expected = 1684566095L;

        // Act
        long actual = day09.solvePart1(day09Part1Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day09Part2_CalledWithTestInput_Expect2() throws FileNotFoundException {
        // Arrange
        String day09Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day09/input_test.txt";
        Day09 day09 = new Day09();
        long expected = 2L;

        // Act
        long actual = day09.solvePart2(day09Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day09Part2_CalledWithRealInput_Expect1136() throws FileNotFoundException {
        // Arrange
        String day09Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day09/input.txt";
        Day09 day09 = new Day09();
        long expected = 1136L;

        // Act
        long actual = day09.solvePart2(day09Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
