package org.jmitchell238.aoc.aoc2023.day08;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

class Day08Tests {

    //  @AfterEach
    //  void tearDown() {
    //    Day08.reset();
    //  }

    @Test
    void Day08Part1_CalledWithTestInput_Expect2() throws FileNotFoundException {
        // Arrange
        String day08Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input_test.txt";
        Day08 day08 = new Day08();

        // Assert
        long expected = 2L;
        long actual = day08.part1(day08Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day08Part1_CalledWithTestInput2_Expect6() throws FileNotFoundException {
        // Arrange
        String day08Part1Input2 = "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input_test_2.txt";
        Day08 day08 = new Day08();

        // Assert
        long expected = 6L;
        long actual = day08.part1(day08Part1Input2);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day08Part1_CalledWithRealInput_Expect16343() throws FileNotFoundException {
        // Arrange
        String day08Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input.txt";
        Day08 day08 = new Day08();

        // Assert
        long expected = 16343L;
        long actual = day08.part1(day08Part1Input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day08Part2_CalledWithTestInput_Expect6() throws FileNotFoundException {
        // Arrange
        String day08Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input_test_3.txt";
        Day08 day08 = new Day08();
        day08.setIsPartTwo(true);

        // Act
        long expected = 6L;
        long actual = day08.part2(day08Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Day08Part2_CalledWithRealInput_Expect15299095336639() throws FileNotFoundException {
        // Arrange
        String day08Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input.txt";
        Day08 day08 = new Day08();
        day08.setIsPartTwo(true);

        // Act
        long expected = 15299095336639L;
        long actual = day08.part2(day08Part2Input);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
