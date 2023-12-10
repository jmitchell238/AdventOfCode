package org.jmitchell238.aoc.aoc2023.day04;

import java.io.FileNotFoundException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

class Day04Tests {

  @Test
  void Day04Part1_CalledWithTestInput_Expect13() throws FileNotFoundException {
    // Arrange
    String day04Part1Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input_test_part1.txt";

    // Assert
    int expected = 13;

    // Act
    AssertionsForClassTypes.assertThat(Day04.part1(day04Part1Input)).isEqualTo(expected);
  }

  @Test
  void Day04Part1_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day04Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input.txt";

    // Assert
    int expected = 0;

    // Act
    AssertionsForClassTypes.assertThat(Day04.part1(day04Part1Input)).isEqualTo(expected);
  }

  @Test
  void Day04Part2_CalledWithTestInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day04Part2Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input_test_part2.txt";

    // Assert
    int expected = 0;

    // Act
    AssertionsForClassTypes.assertThat(Day04.part2(day04Part2Input)).isEqualTo(expected);
  }

  @Test
  void Day04Part2_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day04Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day04/input.txt";

    // Assert
    int expected = 0;

    // Act
    AssertionsForClassTypes.assertThat(Day04.part2(day04Part2Input)).isEqualTo(expected);
  }
}
