package org.jmitchell238.aoc.aoc2023.day11;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

class Day11Tests {

    @Test
    void Day11Part1_CalledWithTestInput_Expect374() throws FileNotFoundException {
        // Arrange
        String day11Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day11/input_test.txt";
        Day11 day11 = new Day11();

        // Assert
        long expected = 374L;
        long actual = day11.part1(day11Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day11Part1_CalledWithRealInput_Expect9647174() throws FileNotFoundException {
        // Arrange
        String day11Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day11/input.txt";
        Day11 day11 = new Day11();

        // Assert
        long expected = 9647174L;
        long actual = day11.part1(day11Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day11Part2_CalledWithTestInput10TimesExpansion_Expect1030() throws FileNotFoundException {
        // Arrange
        String day11Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day11/input_test.txt";

        Day11 day11 = new Day11();
        long expansionFactor = 10L;

        // Act
        long expected = 1030L;
        long actual = day11.part2(day11Part2Input, expansionFactor);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day11Part2_CalledWithRealInput1000000TimesExpansion_Expect377318892554() throws FileNotFoundException {
        // Arrange
        String day11Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day11/input.txt";

        Day11 day11 = new Day11();
        long expansionFactor = 1000000L;

        // Act
        long expected = 377318892554L;
        long actual = day11.part2(day11Part2Input, expansionFactor);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
