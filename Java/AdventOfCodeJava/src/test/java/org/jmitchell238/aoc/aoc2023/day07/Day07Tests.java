package org.jmitchell238.aoc.aoc2023.day07;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class Day07Tests {

    @AfterEach
    void tearDown() {
        Day07.reset();
    }

    @Test
    void Day07Part1_CalledWithTestInput_Expect6440() throws FileNotFoundException {
        // Arrange
        String day07Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input_test.txt";
        Day07 day07 = new Day07();
        day07.setIsPart1(true);

        // Assert
        long expected = 6440L;
        long actual = day07.part1(day07Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day07Part1_CalledWithRealInput_Expect246912307() throws FileNotFoundException {
        // Arrange
        String day07Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input.txt";
        Day07 day07 = new Day07();
        day07.setIsPart1(true);

        // Assert
        long expected = 246912307L;
        long actual = day07.part1(day07Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day07Part2_CalledWithTestInput_Expect5905() throws FileNotFoundException {
        // Arrange
        String day07Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input_test.txt";
        Day07 day07 = new Day07();
        day07.setIsPart1(false);

        // Act
        long expected = 5905L;
        long actual = day07.part2(day07Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day07Part2_CalledWithRealInput_Expect246894760() throws FileNotFoundException {
        // Arrange
        String day07Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input.txt";
        Day07 day07 = new Day07();
        day07.setIsPart1(false);

        // Act
        long expected = 246894760L;
        long actual = day07.part2(day07Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
