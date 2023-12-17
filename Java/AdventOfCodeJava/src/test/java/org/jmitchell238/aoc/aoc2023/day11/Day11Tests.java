package org.jmitchell238.aoc.aoc2023.day11;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.jmitchell238.aoc.aoc2023.day09.Day11;
import org.junit.jupiter.api.Test;

class Day11Tests {

  @Test
  void Day11Part1_CalledWithTestInput_Expect4() throws FileNotFoundException {
    // Arrange
    String day11Part1Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day11/input_test_1.txt";
    Day11 day11 = new Day11();

    // Assert
    long expected = 4L;
    long actual = day11.part1(day11Part1Input);

    // Act
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void Day11Part1_CalledWithTestInput2_Expect_() throws FileNotFoundException {
    // Arrange
    String day11Part1Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day11/input_test_2.txt";
    Day11 day11 = new Day11();

    // Assert
    long expected = 9L;
    long actual = day11.part1(day11Part1Input);

    // Act
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void Day11Part1_CalledWithRealInput_Expect_() throws FileNotFoundException {
    // Arrange
    String day11Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day11/input.txt";
    Day11 day11 = new Day11();

    // Assert
    long expected = 6773L;
    long actual = day11.part1(day11Part1Input);

    // Act
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void Day11Part2_CalledWithRealInput_Expect493() throws FileNotFoundException {
    // Arrange
    String day11Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day11/input.txt";
    Day11 day11 = new Day11();
    day11.setIsPartTwo(true);

    // Act
    long expected = 493L;
    long actual = day11.part2(day11Part2Input);

    // Assert
    assertThat(actual).isEqualTo(expected);
  }
}
