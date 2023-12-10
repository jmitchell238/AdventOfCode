package org.jmitchell238.aoc.aoc2023.day05;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.jmitchell238.aoc.aoc2023.day00.Day00;
import org.junit.jupiter.api.Test;

class Day05Tests {

  @Test
  void Day05Part1_CalledWithTestInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day05Part1Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input_test_part1.txt";

    // Assert
    int expected = 00;

    // Act
    assertThat(Day05.part1(day05Part1Input)).isEqualTo(expected);
  }

  @Test
  void Day05Part1_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day05Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input.txt";

    // Assert
    int expected = 00;

    // Act
    assertThat(org.jmitchell238.aoc.aoc2023.day05.Day05.part1(day05Part1Input)).isEqualTo(expected);
  }

  @Test
  void Day05Part2_CalledWithTestInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day05Part2Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input_test_part2.txt";

    // Assert
    int expected = 00;

    // Act
    assertThat(org.jmitchell238.aoc.aoc2023.day05.Day05.part2(day05Part2Input)).isEqualTo(expected);
  }

  @Test
  void Day05Part2_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day05Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input.txt";

    // Assert
    int expected = 00;

    // Act
    assertThat(Day05.part2(day05Part2Input)).isEqualTo(expected);
  }
}
