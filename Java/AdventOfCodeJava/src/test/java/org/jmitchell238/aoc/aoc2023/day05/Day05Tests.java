package org.jmitchell238.aoc.aoc2023.day05;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class Day05Tests {

    @AfterEach
    void tearDown() {
        Day05.reset();
    }

    @Test
    void Day05Part1_CalledWithTestInput_Expect35() throws FileNotFoundException {
        // Arrange
        String day05Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input_test.txt";
        Day05 day05 = new Day05();

        // Assert
        long expected = 35L;
        long actual = day05.part1(day05Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day05Part1_CalledWithRealInput_Expect31599214() throws FileNotFoundException {
        // Arrange
        String day05Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input.txt";
        Day05 day05 = new Day05();

        // Assert
        long expected = 31599214L;
        long actual = day05.part1(day05Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day05Part2_CalledWithTestInput_Expect46() throws FileNotFoundException {
        // Arrange
        String day05Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input_test.txt";
        Day05 day05 = new Day05();

        // Act
        long expected = 46L;
        long actual = day05.part2(day05Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day05Part2_CalledWithRealInput_Expect20358599() throws FileNotFoundException {
        // Arrange
        String day05Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input.txt";
        Day05 day05 = new Day05();

        // Act
        long expected = 20358599L;
        long actual = day05.part2(day05Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
