package org.jmitchell238.aoc.aoc2023.day12;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

class Day12Tests {

    @Test
    void Day12Part1_CalledWithTestInput_Expect21() throws FileNotFoundException {
        // Arrange
        String day12Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day12/input_test.txt";
        Day12 day12 = new Day12();

        // Assert
        long expected = 21L;
        long actual = day12.part1(day12Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day12Part1_CalledWithRealInput_Expect_() throws FileNotFoundException {
        // Arrange
        String day12Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day12/input.txt";
        Day12 day12 = new Day12();

        // Assert
        long expected = 0L;
        long actual = day12.part1(day12Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day12Part2_CalledWithTestInput10TimesExpansion_Expect_() throws FileNotFoundException {
        // Arrange
        String day12Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day12/input_test.txt";

        Day12 day12 = new Day12();

        // Act
        long expected = 0L;
        long actual = day12.part2(day12Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day12Part2_CalledWithRealInput_Expect_() throws FileNotFoundException {
        // Arrange
        String day12Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day12/input.txt";

        Day12 day12 = new Day12();

        // Act
        long expected = 0L;
        long actual = day12.part2(day12Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
