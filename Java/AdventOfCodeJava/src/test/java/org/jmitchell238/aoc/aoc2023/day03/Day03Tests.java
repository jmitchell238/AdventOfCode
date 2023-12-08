package org.jmitchell238.aoc.aoc2023.day03;

import java.io.FileNotFoundException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

class Day03Tests {

  @Test
  void Day03Part1_CalledWithTestInput_Expect4361() throws FileNotFoundException {
    // Arrange
    String day03Part1Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input_test_custom.txt";

    // Assert
    int expected = 4361;

    // Act
    AssertionsForClassTypes.assertThat(Day03.Part1(day03Part1Input)).isEqualTo(expected);
  }

  @Test
  void Day03Part1_CalledWithRealInput_Expect540212() throws FileNotFoundException {
    // Arrange
    String day03Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input.txt";

    // Assert
    int expected = 540212;

    // Act
    AssertionsForClassTypes.assertThat(Day03.Part1(day03Part1Input)).isEqualTo(expected);
  }

  @Test
  void Day03Part2_CalledWithTestInput_Expect467835() throws FileNotFoundException {
    // Arrange
    String day03Part2Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input_test.txt";

    // Assert
    int expected = 467835;

    // Act
    AssertionsForClassTypes.assertThat(Day03.Part2(day03Part2Input)).isEqualTo(expected);
  }

  @Test
  void Day03Part2_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day03Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day03/input.txt";

    // Assert
    int expected = 0;

    // Act
    AssertionsForClassTypes.assertThat(Day03.Part2(day03Part2Input)).isEqualTo(expected);
  }
}
