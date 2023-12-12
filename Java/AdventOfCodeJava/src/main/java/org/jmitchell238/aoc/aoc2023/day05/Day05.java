package org.jmitchell238.aoc.aoc2023.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.jmitchell238.aoc.aoc2023.day05.maps.FertilizerToWaterMap;
import org.jmitchell238.aoc.aoc2023.day05.maps.HumidityToLocationMap;
import org.jmitchell238.aoc.aoc2023.day05.maps.LightToTemperatureMap;
import org.jmitchell238.aoc.aoc2023.day05.maps.SeedToSoilMap;
import org.jmitchell238.aoc.aoc2023.day05.maps.SoilToFertilizerMap;
import org.jmitchell238.aoc.aoc2023.day05.maps.SourceToDestinationMap;
import org.jmitchell238.aoc.aoc2023.day05.maps.TemperatureToHumidityMap;
import org.jmitchell238.aoc.aoc2023.day05.maps.WaterToLightMap;

public class Day05 {
  private static final Boolean DEBUGGING = false;
  private static final Boolean VERBOSE = false;
  private static final String seedToSoilString = "seed-to-soil map:";
  private static final String soilToFertilizerString = "soil-to-fertilizer map:";
  private static final String fertilizerToWaterString = "fertilizer-to-water map:";
  private static final String waterToLightString = "water-to-light map:";
  private static final String lightToTemperatureString = "light-to-temperature map:";
  private static final String temperatureToHumidityString = "temperature-to-humidity map:";
  private static final String humidityToLocationString = "humidity-to-location map:";
  private static final SourceToDestinationMap seedToSoilMap = new SeedToSoilMap();
  private static final SourceToDestinationMap soilToFertilizerMap = new SoilToFertilizerMap();
  private static final SourceToDestinationMap fertilizerToWaterMap = new FertilizerToWaterMap();
  private static final SourceToDestinationMap waterToLightMap = new WaterToLightMap();
  private static final SourceToDestinationMap lightToTemperatureMap = new LightToTemperatureMap();
  private static final SourceToDestinationMap temperatureToHumidityMap = new TemperatureToHumidityMap();
  private static final SourceToDestinationMap humidityToLocationMap = new HumidityToLocationMap();
  private static final List<Long> seeds = new ArrayList<>();
  private String seedsLine = null;
  private static final int BATCH_SIZE = 1000;

  public void main(String[] args) throws FileNotFoundException {
    Day05Run();
  }

  public void Day05Run() throws FileNotFoundException {
    System.out.println("\n--- Day 5: If You Give A Seed A Fertilizer ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input_test.txt";

    long partOneAnswer = part1(input);
    System.out.println(STR."Part 1: Answer: \{partOneAnswer}");

    long partTwoAnswer = part2(input);
    System.out.println(STR."Part 2: Answer: \{partTwoAnswer}");
  }

  public long part1(String inputString) throws FileNotFoundException {
    return processFile(inputString, 1);
  }

  public long part2(String inputString) throws FileNotFoundException {
    return processFile(inputString, 2);
  }

  public long processFile(String filePath, int part) throws FileNotFoundException {
    String currentHeader = null;
    seedsLine = null;

    Scanner scanner = new Scanner(new File(filePath));
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();

      if (line.startsWith("seeds:")) {
        currentHeader = "seeds";
      } else if (line.isBlank()) {
        currentHeader = null;
        continue;
      } else if (line.startsWith(seedToSoilString)) {
        currentHeader = seedToSoilString;
        continue;
      } else if (line.startsWith(soilToFertilizerString)) {
        currentHeader = soilToFertilizerString;
        continue;
      } else if (line.startsWith(fertilizerToWaterString)) {
        currentHeader = fertilizerToWaterString;
        continue;
      } else if (line.startsWith(waterToLightString)) {
        currentHeader = waterToLightString;
        continue;
      } else if (line.startsWith(lightToTemperatureString)) {
        currentHeader = lightToTemperatureString;
        continue;
      } else if (line.startsWith(temperatureToHumidityString)) {
        currentHeader = temperatureToHumidityString;
        continue;
      } else if (line.startsWith(humidityToLocationString)) {
        currentHeader = humidityToLocationString;
        continue;
      }

      if (currentHeader.equals("seeds")) {
        if (part == 1) {
          processSeedsLine(line);
        } else {
          this.seedsLine = line;}
      } else {
        processLineBasedOnCurrentHeader(currentHeader, line);
      }
    }

    scanner.close();

    long answer;

    if (part == 1) {
      answer = processSeedsForPart1();
    } else {
      answer = processSeedsLinePart2();
    }

    System.out.println(STR."Lowest Location number for provided seeds: \{answer}");

    return answer;
  }

  private Long processSeedsForPart1() {
    long answer = Long.MAX_VALUE;

    for (Long seed : seeds) {
      long location = calculateLocation(seed);
      if (location < answer) {
        answer = location;
      }
    }

    return answer;
  }

  private static void processLineBasedOnCurrentHeader(String currentHeader, String line) {
    // Process lines based on the current header
    if (currentHeader != null) {
      switch (currentHeader) {
        case seedToSoilString:
          getMapDataStrings(line, seedToSoilMap);
          break;
        case soilToFertilizerString:
          getMapDataStrings(line, soilToFertilizerMap);
          break;
        case fertilizerToWaterString:
          getMapDataStrings(line, fertilizerToWaterMap);
          break;
        case waterToLightString:
          getMapDataStrings(line, waterToLightMap);
          break;
        case lightToTemperatureString:
          getMapDataStrings(line, lightToTemperatureMap);
          break;
        case temperatureToHumidityString:
          getMapDataStrings(line, temperatureToHumidityMap);
          break;
        case humidityToLocationString:
          getMapDataStrings(line, humidityToLocationMap);
          break;
      }
    }
  }

  private static void processSeedsLine(String line) {
    Arrays.stream(line.split(" ")).skip(1) // Skip "seeds:"
        .map(Long::parseLong)
        .forEach(seeds::add);
    if (DEBUGGING) System.out.println("seeds: " + seeds);
  }

  private long processSeedsLinePart2() {
    String seedsLine = this.seedsLine.substring(this.seedsLine.indexOf("seeds:") + "seeds:".length()).trim();

    String[] parts = seedsLine.split("\\s+");
    long overallLowestLocation = Long.MAX_VALUE;

    for (int i = 0; i < parts.length; i += 2) {
      long seedNumber = Long.parseLong(parts[i]);
      long range = Long.parseLong(parts[i + 1]);

      overallLowestLocation = Math.min(overallLowestLocation, processSeedRange(seedNumber, range));
    }

    // Process any remaining seeds
    overallLowestLocation = processRemainingSeeds(overallLowestLocation);

    return overallLowestLocation;
  }

  private static long processSeedRange(long seedNumber, long range) {
    long overallLowestLocation = Long.MAX_VALUE;

    for (long i = seedNumber; i < seedNumber + range; i++) {
      seeds.add(i);

      if (seeds.size() % BATCH_SIZE == 0) {
        List<Long> seedBatch = new ArrayList<>(seeds.subList(0, BATCH_SIZE));
        long lowestLocation = processBatch(seedBatch);
        overallLowestLocation = Math.min(overallLowestLocation, lowestLocation);
      }
    }

    // Process any remaining seeds
    if (!seeds.isEmpty()) {
      long lowestLocation = processBatches();
      overallLowestLocation = Math.min(overallLowestLocation, lowestLocation);
    }

    return overallLowestLocation;
  }

  private static long processBatch(List<Long> seedBatch) {
    long lowestLocation = getLowestLocationForSeeds(seedBatch);
    seeds.clear();
    return lowestLocation;
  }

  private static long processBatches() {
    long overallLowestLocation = Long.MAX_VALUE;

    for (int i = 0; i < seeds.size(); i += BATCH_SIZE) {
      List<Long> batch = seeds.subList(i, Math.min(i + BATCH_SIZE, seeds.size()));

      for (Long seed : batch) {
        long location = calculateLocation(seed);

        if (location < overallLowestLocation) {
          overallLowestLocation = location;
        }
      }
    }

    seeds.clear();
    return overallLowestLocation;
  }

  private static long calculateLocation(long seed) {
    long soil = seedToSoilMap.getDestinationForSource(seed);
    long fertilizer = soilToFertilizerMap.getDestinationForSource(soil);
    long water = fertilizerToWaterMap.getDestinationForSource(fertilizer);
    long light = waterToLightMap.getDestinationForSource(water);
    long temperature = lightToTemperatureMap.getDestinationForSource(light);
    long humidity = temperatureToHumidityMap.getDestinationForSource(temperature);
    return humidityToLocationMap.getDestinationForSource(humidity);
  }

  private long processRemainingSeeds(long overallLowestLocation) {
    if (!seeds.isEmpty()) {
      List<Long> seedBatch = new ArrayList<>(seeds);
      return Math.min(overallLowestLocation, processBatch(seedBatch));
    }
    return overallLowestLocation;
  }

  private static void getMapDataStrings(String line, SourceToDestinationMap sourceToDestinationMap) {
    List<String> sourceToDestinationMapDataStrings = Arrays.asList(line.split(" "));
    Long destination = Long.parseLong(sourceToDestinationMapDataStrings.get(0));
    Long source = Long.parseLong(sourceToDestinationMapDataStrings.get(1));
    Long range = Long.parseLong(sourceToDestinationMapDataStrings.get(2));

    sourceToDestinationMap.addMapping(destination, source, range);
  }

  private static long getLowestLocationForSeeds(List<Long> seedBatch) {
    long lowestLocation = Long.MAX_VALUE;

    for (Long seed : seedBatch) {
      long location = calculateLocation(seed);

      if (location < lowestLocation) {
        lowestLocation = location;
      }
    }

    return lowestLocation;
  }

  public static void reset() {
    seeds.clear();
    seedToSoilMap.getRangeMappings().clear();
    soilToFertilizerMap.getRangeMappings().clear();
    fertilizerToWaterMap.getRangeMappings().clear();
    waterToLightMap.getRangeMappings().clear();
    lightToTemperatureMap.getRangeMappings().clear();
    temperatureToHumidityMap.getRangeMappings().clear();
    humidityToLocationMap.getRangeMappings().clear();
  }
}
