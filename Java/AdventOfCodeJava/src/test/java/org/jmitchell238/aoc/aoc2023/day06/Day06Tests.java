package org.jmitchell238.aoc.aoc2023.day06;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.jmitchell238.aoc.aoc2023.day06.Day06;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class Day06Tests {

//  @AfterEach
//  void tearDown() {
//    Day06.reset();
//  }

  @Test
  void Day06Part1_CalledWithTestInput_Expect288() throws FileNotFoundException {
    // Arrange
    String day06Part1Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input_test.txt";
    Day06 day06 = new Day06();

    // Assert
    long expected = 288L;
    long actual = day06.part1(day06Part1Input);

    // Act
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void Day06Part1_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day06Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input.txt";
    Day06 day06 = new Day06();

    // Assert
    long expected = 00L;
    long actual = day06.part1(day06Part1Input);

    // Act
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void Day06Part2_CalledWithTestInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day06Part2Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input_test.txt";
    Day06 day06 = new Day06();

    // Act
    long expected = 00L;
    long actual = day06.part2(day06Part2Input);

    // Assert
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void Day06Part2_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day06Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input.txt";
    Day06 day06 = new Day06();

    // Act
    long expected = 00L;
    long actual = day06.part2(day06Part2Input);

    // Assert
    assertThat(actual).isEqualTo(expected);
  }
}
