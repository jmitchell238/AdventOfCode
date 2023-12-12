package org.jmitchell238.aoc.aoc2023.day07;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.jmitchell238.aoc.aoc2023.day07.Day07;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class Day07Tests {

//  @AfterEach
//  void tearDown() {
//    Day07.reset();
//  }

  @Test
  void Day07Part1_CalledWithTestInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day07Part1Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input_test.txt";
    Day07 day07 = new Day07();

    // Assert
    long expected = 0L;
    long actual = day07.part1(day07Part1Input);

    // Act
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void Day07Part1_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day07Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input.txt";
    Day07 day07 = new Day07();

    // Assert
    long expected = 0L;
    long actual = day07.part1(day07Part1Input);

    // Act
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void Day07Part2_CalledWithTestInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day07Part2Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input_test.txt";
    Day07 day07 = new Day07();

    // Act
    long expected = 0L;
    long actual = day07.part2(day07Part2Input);

    // Assert
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void Day07Part2_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day07Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input.txt";
    Day07 day07 = new Day07();

    // Act
    long expected = 0L;
    long actual = day07.part2(day07Part2Input);

    // Assert
    assertThat(actual).isEqualTo(expected);
  }
}
