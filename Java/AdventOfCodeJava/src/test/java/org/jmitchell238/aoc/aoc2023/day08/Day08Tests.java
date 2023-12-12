package org.jmitchell238.aoc.aoc2023.day08;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.FileNotFoundException;
import org.jmitchell238.aoc.aoc2023.day08.Day08;
import org.junit.jupiter.api.Test;

class Day08Tests {

//  @AfterEach
//  void tearDown() {
//    Day08.reset();
//  }

  @Test
  void Day08Part1_CalledWithTestInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day08Part1Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input_test.txt";
    Day08 day08 = new Day08();

    // Assert
    long expected = 0L;
    long actual = day08.part1(day08Part1Input);

    // Act
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void Day08Part1_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day08Part1Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input.txt";
    Day08 day08 = new Day08();

    // Assert
    long expected = 0L;
    long actual = day08.part1(day08Part1Input);

    // Act
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void Day08Part2_CalledWithTestInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day08Part2Input =
        "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input_test.txt";
    Day08 day08 = new Day08();

    // Act
    long expected = 0L;
    long actual = day08.part2(day08Part2Input);

    // Assert
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void Day08Part2_CalledWithRealInput_ExpectCorrectAnswer() throws FileNotFoundException {
    // Arrange
    String day08Part2Input = "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input.txt";
    Day08 day08 = new Day08();

    // Act
    long expected = 0L;
    long actual = day08.part2(day08Part2Input);

    // Assert
    assertThat(actual).isEqualTo(expected);
  }
}
