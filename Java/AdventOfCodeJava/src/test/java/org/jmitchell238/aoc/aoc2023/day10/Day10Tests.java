package org.jmitchell238.aoc.aoc2023.day10;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

class Day10Tests {

    @Test
    void Day10Part1_CalledWithTestInput_Expect4() throws FileNotFoundException {
        // Arrange
        String day10Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day10/input_test_1.txt";
        Day10 day10 = new Day10();

        // Assert
        long expected = 4L;
        long actual = day10.part1(day10Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day10Part1_CalledWithTestInput2_Expect8() throws FileNotFoundException {
        // Arrange
        String day10Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day10/input_test_2.txt";
        Day10 day10 = new Day10();

        // Assert
        long expected = 8L;
        long actual = day10.part1(day10Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day10Part1_CalledWithRealInput_Expect6773() throws FileNotFoundException {
        // Arrange
        String day10Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day10/input.txt";
        Day10 day10 = new Day10();

        // Assert
        long expected = 6773L;
        long actual = day10.part1(day10Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day10Part2_CalledWithRealInput_Expect493() throws FileNotFoundException {
        // Arrange
        String day10Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day10/input.txt";
        Day10 day10 = new Day10();
        day10.setIsPartTwo(true);

        // Act
        long expected = 493L;
        long actual = day10.part2(day10Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
