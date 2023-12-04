package org.jmitchell238.aoc.aoc2023.day00;

import java.io.FileNotFoundException;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

class Day00Tests {

  @Test
  void Day00Part1_CalledWithTestInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day00Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day00/input_test_part1.txt";

    // Assert
    int expected = 00;

    // Act
    AssertionsForClassTypes.assertThat(Day00.Part1(day00Part1Input)).isEqualTo(expected);
  }

  @Test
  void Day00Part1_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day00Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day00/input.txt";

    // Assert
    int expected = 00;

    // Act
    AssertionsForClassTypes.assertThat(Day00.Part1(day00Part1Input)).isEqualTo(expected);
  }

  @Test
  void Day00Part2_CalledWithTestInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day00Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day00/input_test_part2.txt";

    // Assert
    int expected = 00;

    // Act
    AssertionsForClassTypes.assertThat(Day00.Part2(day00Part2Input)).isEqualTo(expected);
  }

  @Test
  void Day00Part2_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day00Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day00/input.txt";

    // Assert
    int expected = 00;

    // Act
    AssertionsForClassTypes.assertThat(Day00.Part2(day00Part2Input)).isEqualTo(expected);
  }
}
