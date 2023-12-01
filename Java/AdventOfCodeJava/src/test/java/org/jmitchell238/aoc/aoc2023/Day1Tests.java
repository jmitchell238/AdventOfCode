package org.jmitchell238.aoc.aoc2023;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

class Day1Tests {

  @Test
  void Day1Part1_CalledWithTestInput_Expect142() throws FileNotFoundException {
    // Arrange
    File day1Part1Input = new File(
        "/Users/jamesm.mitchell/james-workspace/AdventOfCode/Java/AdventOfCodeJava/src/main/resources/aoc2023/day1_part1_test.txt");

    // Assert
    int expected = 142;

    // Act
    assertThat(Day1.Part1(day1Part1Input)).isEqualTo(expected);
  }

  @Test
  void Day1Part1_CalledWithRealInput_Expect54940() throws FileNotFoundException {
    // Arrange
    File day1Part1Input = new File("/Users/jamesm.mitchell/james-workspace/AdventOfCode/Java/AdventOfCodeJava/src/main/resources/aoc2023/day1_part1.txt");

    // Assert
    int expected = 54940;

    // Act
    assertThat(Day1.Part1(day1Part1Input)).isEqualTo(expected);
  }

  @Test
  void Day1Part2_CalledWithTestInput_Expect281() throws FileNotFoundException {
    // Arrange
    File day1Part2Input = new File(
        "/Users/jamesm.mitchell/james-workspace/AdventOfCode/Java/AdventOfCodeJava/src/main/resources/aoc2023/day1_part2_test.txt");

    // Assert
    int expected = 281;

    // Act
    assertThat(Day1.Part2(day1Part2Input)).isEqualTo(expected);
  }

  @Test
  void Day1Part2_CalledWithRealInput_Expect54208() throws FileNotFoundException {
    // Arrange
    File day1Part2Input = new File("/Users/jamesm.mitchell/james-workspace/AdventOfCode/Java/AdventOfCodeJava/src/main/resources/aoc2023/day1_part2.txt");

    // Assert
    int expected = 54208;

    // Act
    assertThat(Day1.Part2(day1Part2Input)).isEqualTo(expected);
  }
}

