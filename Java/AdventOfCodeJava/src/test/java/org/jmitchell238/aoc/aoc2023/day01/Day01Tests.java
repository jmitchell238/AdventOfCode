package org.jmitchell238.aoc.aoc2023.day01;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

class Day01Tests {

    @Test
    void Day01Part1_CalledWithTestInput_Expect142() throws FileNotFoundException {
        // Arrange
        File day1Part1Input = new File("src/main/java/org/jmitchell238/aoc/aoc2023/Day01/day1_part1_test.txt");

        // Assert
        int expected = 142;

        // Act
        AssertionsForClassTypes.assertThat(Day01.Part1(day1Part1Input)).isEqualTo(expected);
    }

    @Test
    void Day01Part1_CalledWithRealInput_Expect54940() throws FileNotFoundException {
        // Arrange
        File day1Part1Input = new File("src/main/java/org/jmitchell238/aoc/aoc2023/Day01/day1.txt");

        // Assert
        int expected = 54940;

        // Act
        assertThat(Day01.Part1(day1Part1Input)).isEqualTo(expected);
    }

    @Test
    void Day01Part2_CalledWithTestInput_Expect281() throws FileNotFoundException {
        // Arrange
        File day1Part2Input = new File("src/main/java/org/jmitchell238/aoc/aoc2023/Day01/day1_part2_test.txt");

        // Assert
        int expected = 281;

        // Act
        assertThat(Day01.Part2(day1Part2Input)).isEqualTo(expected);
    }

    @Test
    void Day01Part2_CalledWithRealInput_Expect54208() throws FileNotFoundException {
        // Arrange
        File day1Part2Input = new File("src/main/java/org/jmitchell238/aoc/aoc2023/Day01/day1.txt");

        // Assert
        int expected = 54208;

        // Act
        assertThat(Day01.Part2(day1Part2Input)).isEqualTo(expected);
    }
}
