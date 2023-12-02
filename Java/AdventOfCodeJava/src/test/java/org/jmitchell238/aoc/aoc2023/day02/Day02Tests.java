package org.jmitchell238.aoc.aoc2023.day02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day02Tests {

  @Test
  void DayO2Part1_CalledWithTestInput_Expect8() {
    // Arrange
    String day02Part1Input = "C:\\Users\\jmitc\\workspace\\AdventOfCode\\Java\\AdventOfCodeJava\\src\\main\\java\\org\\jmitchell238\\aoc\\aoc2023\\Day02\\input_test.txt";

    // Assert
    int expected = 8;

    // Act
    assertThat(Day02.Part1(day02Part1Input)).isEqualTo(expected);
  }

  @Test
  void DayO2Part1_CalledWithRealInput_Expect2006() {
    // Arrange
    String day02Part1Input = "C:\\Users\\jmitc\\workspace\\AdventOfCode\\Java\\AdventOfCodeJava\\src\\main\\java\\org\\jmitchell238\\aoc\\aoc2023\\Day02\\input.txt";

    // Assert
    int expected = 2006;

    // Act
    assertThat(Day02.Part1(day02Part1Input)).isEqualTo(expected);
  }

  @Test
  void DayO2Part2_CalledWithTestInput_Expect2286() {
    // Arrange
    String day02Part2Input = "C:\\Users\\jmitc\\workspace\\AdventOfCode\\Java\\AdventOfCodeJava\\src\\main\\java\\org\\jmitchell238\\aoc\\aoc2023\\Day02\\input_test.txt";

    // Assert
    int expected = 8;

    // Act
    assertThat(Day02.Part2(day02Part2Input)).isEqualTo(expected);
  }

  @Test
  void DayO2Part2_CalledWithRealInput_Expect2286() {
    // Arrange
    String day02Part2Input = "C:\\Users\\jmitc\\workspace\\AdventOfCode\\Java\\AdventOfCodeJava\\src\\main\\java\\org\\jmitchell238\\aoc\\aoc2023\\Day02\\input.txt";

    // Assert
    int expected = 2006;

    // Act
    assertThat(Day02.Part2(day02Part2Input)).isEqualTo(expected);
  }

}
