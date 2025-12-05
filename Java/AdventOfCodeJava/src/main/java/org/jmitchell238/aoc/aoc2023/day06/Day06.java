package org.jmitchell238.aoc.aoc2023.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day06 {
  private static final Boolean DEBUGGING = false;
  private static final Boolean VERBOSE = false;
  private static final ArrayList<Long> times = new ArrayList<>();
  private static final ArrayList<Long> distances = new ArrayList<>();
  private static final Map<Long, Long> raceTimeDistanceRecords = new HashMap<>();
  private static Long p2Time = 0L;
  private static Long p2Distance = 0L;

  public void main(String[] args) throws FileNotFoundException {
    Day06Run();
  }

  public void Day06Run() throws FileNotFoundException {
    System.out.println("\n--- Day 6: Wait For It ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day06/input_test.txt";

    long partOneAnswer = part1(inputTest);
    System.out.println("Part 1: Answer: " + partOneAnswer);

    long partTwoAnswer = part2(inputTest);
    System.out.println("Part 2: Answer: " + partTwoAnswer);
  }

  public long part1(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (DEBUGGING) System.out.println(line);

      if (line.startsWith("Time:")) {
        String[] timesArray = line.split(" ");

        for (int i = 1; i < timesArray.length; i++) {
          long time = Long.parseLong(timesArray[i]);
          times.add(time);
          if (DEBUGGING) System.out.println(timesArray[i]);
        }
      }

      if (line.startsWith("Distance:")) {
        String[] distancesArray = line.split(" ");

        for (int i = 1; i < distancesArray.length; i++) {
          long distance = Long.parseLong(distancesArray[i]);
          distances.add(distance);
          if (DEBUGGING) System.out.println(distancesArray[i]);
        }
      }
    }

    for (int i = 0; i < times.size(); i++) {
      raceTimeDistanceRecords.put(times.get(i), distances.get(i));
    }

    if (DEBUGGING) System.out.println(raceTimeDistanceRecords);

    return numberOfWaysToWinMultiplied();
  }

  public long part2(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (DEBUGGING) System.out.println(line);

      if (line.startsWith("Time:")) {
        String[] timesArray = line.split(":");
        p2Time = Long.parseLong(timesArray[1].trim().replaceAll("\\s", ""));
      }

      if (line.startsWith("Distance:")) {
        String[] distancesArray = line.split(":");
        p2Distance = Long.parseLong(distancesArray[1].trim().replaceAll("\\s", ""));
      }
    }

    raceTimeDistanceRecords.put(p2Time, p2Distance);


    if (DEBUGGING) System.out.println(raceTimeDistanceRecords);

    return numberOfWaysToWinMultiplied();
  }

  private long numberOfWaysToWinMultiplied() {
    long numberOfWaysToWinMultiplied = 1;

    for(Map.Entry<Long, Long> entry : raceTimeDistanceRecords.entrySet()) {
      long numberOfWaysToWin = 0;
      long time = entry.getKey();
      long distance = entry.getValue();

      for (int i = 0; i <= time ; i++) {
        long buttonPushedTime = i;
        long distanceTraveledPerMillisecond = buttonPushedTime;
        long distanceTraveled = distanceTraveledPerMillisecond * (time - buttonPushedTime);
        if (distanceTraveled > distance) {
          numberOfWaysToWin++;
        }
      }

      numberOfWaysToWinMultiplied *= numberOfWaysToWin;
    }

    return numberOfWaysToWinMultiplied;
  }

  public static void reset() {
    times.clear();
    distances.clear();
    raceTimeDistanceRecords.clear();
  }
}
