package org.jmitchell238.aoc.aoc2023.day06;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class Day06Tests {

    @AfterEach
    void tearDown() {
        Day06.resetAllRaceData();
    }

    @Test
    void Day06Part1_CalledWithTestInput_Expect288() throws FileNotFoundException {
        // Arrange
        String day06Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input_test.txt";
        Day06 day06 = new Day06();
        long expected = 288L;

        // Act
        long actual = day06.solvePart1(day06Part1Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day06Part1_CalledWithRealInput_Expect6209190() throws FileNotFoundException {
        // Arrange
        String day06Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input.txt";
        Day06 day06 = new Day06();
        long expected = 6209190L;

        // Act
        long actual = day06.solvePart1(day06Part1Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day06Part2_CalledWithTestInput_Expect71503() throws FileNotFoundException {
        // Arrange
        String day06Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input_test.txt";
        Day06 day06 = new Day06();
        long expected = 71503L;

        // Act
        long actual = day06.solvePart2(day06Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day06Part2_CalledWithRealInput_Expect28545089() throws FileNotFoundException {
        // Arrange
        String day06Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input.txt";
        Day06 day06 = new Day06();
        long expected = 28545089L;

        // Act
        long actual = day06.solvePart2(day06Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
