package org.jmitchell238.aoc.aoc2023.day02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Day02Tests {

  @Test
  void DayO2Part1_CalledWithTestInput_Expect8() {
    // Arrange
    String day02Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/Day02/input_test.txt";

    // Assert
    int expected = 8;

    // Act
    assertThat(Day02.Part1(day02Part1Input)).isEqualTo(expected);
  }

  @Test
  void DayO2Part1_CalledWithRealInput_Expect2006() {
    // Arrange
    String day02Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/Day02/input.txt";

    // Assert
    int expected = 2006;

    // Act
    assertThat(Day02.Part1(day02Part1Input)).isEqualTo(expected);
  }

  @Test
  void DayO2Part2_CalledWithTestInput_Expect2286() {
    // Arrange
    String day02Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/Day02/input_test.txt";

    // Assert
    int expected = 2286;

    // Act
    assertThat(Day02.Part2(day02Part2Input)).isEqualTo(expected);
  }

  @Test
  void DayO2Part2_CalledWithRealInput_Expect84911() {
    // Arrange
    String day02Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/Day02/input.txt";

    // Assert
    int expected = 84911;

    // Act
    assertThat(Day02.Part2(day02Part2Input)).isEqualTo(expected);
  }
}
