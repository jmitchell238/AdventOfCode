package org.jmitchell238.aoc.aoc2023.day05;

import static org.jmitchell238.aoc.aoc2025.utilities.Utilities2025.log;

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
import org.jmitchell238.aoc.generalutilities.LogLevel;

/**
 * Advent of Code 2023 - Day 5: If You Give A Seed A Fertilizer
 * <p>
 * This class contains the solution logic for Day 5 of Advent of Code 2023.
 * Part 1 maps individual seeds through transformation maps to find locations.
 * Part 2 handles seed ranges and processes them in batches for efficiency.
 * </p>
 */
@SuppressWarnings({"java:S106", "java:S1118", "java:S1940", "java:S2589", "java:S100", "java:S3776", "java:S127"})
public class Day05 {

    // Configuration flags
    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    // Constants for map headers
    private static final String SEED_TO_SOIL_MAP_HEADER = "seed-to-soil map:";
    private static final String SOIL_TO_FERTILIZER_MAP_HEADER = "soil-to-fertilizer map:";
    private static final String FERTILIZER_TO_WATER_MAP_HEADER = "fertilizer-to-water map:";
    private static final String WATER_TO_LIGHT_MAP_HEADER = "water-to-light map:";
    private static final String LIGHT_TO_TEMPERATURE_MAP_HEADER = "light-to-temperature map:";
    private static final String TEMPERATURE_TO_HUMIDITY_MAP_HEADER = "temperature-to-humidity map:";
    private static final String HUMIDITY_TO_LOCATION_MAP_HEADER = "humidity-to-location map:";
    private static final String SEEDS_HEADER = "seeds";
    private static final String SEEDS_PREFIX = "seeds:";

    // Processing constants
    private static final int SEED_PROCESSING_BATCH_SIZE = 1000;

    // Map instances
    private static final SourceToDestinationMap seedToSoilMap = new SeedToSoilMap();
    private static final SourceToDestinationMap soilToFertilizerMap = new SoilToFertilizerMap();
    private static final SourceToDestinationMap fertilizerToWaterMap = new FertilizerToWaterMap();
    private static final SourceToDestinationMap waterToLightMap = new WaterToLightMap();
    private static final SourceToDestinationMap lightToTemperatureMap = new LightToTemperatureMap();
    private static final SourceToDestinationMap temperatureToHumidityMap = new TemperatureToHumidityMap();
    private static final SourceToDestinationMap humidityToLocationMap = new HumidityToLocationMap();

    // State variables
    private static final List<Long> seedsForPart1 = new ArrayList<>();
    private String seedsLineForPart2 = null;

    @SuppressWarnings("unused")
    public void main(String[] args) throws FileNotFoundException {
        runDay05();
    }

    /**
     * Entry point for Day 5 solution.
     */
    public void runDay05() throws FileNotFoundException {
        System.out.println("\n--- Day 5: If You Give A Seed A Fertilizer ---\n");

        String actualInputFilePath = "src/main/java/org/jmitchell238/aoc/aoc2023/day05/input.txt";

        long partOneAnswer = solvePart1(actualInputFilePath);
        log(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        long partTwoAnswer = solvePart2(actualInputFilePath);
        log(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    /**
     * Solves Part 1 by processing individual seeds through transformation maps.
     */
    public long solvePart1(String inputFilePath) throws FileNotFoundException {
        return processInputFile(inputFilePath, 1);
    }

    /**
     * Solves Part 2 by processing seed ranges in batches through transformation maps.
     */
    public long solvePart2(String inputFilePath) throws FileNotFoundException {
        return processInputFile(inputFilePath, 2);
    }

    /**
     * Processes the input file and builds transformation maps based on the specified part.
     */
    public long processInputFile(String inputFilePath, int problemPart) throws FileNotFoundException {
        String currentSectionHeader = null;
        seedsLineForPart2 = null;

        try (Scanner inputFileScanner = new Scanner(new File(inputFilePath))) {
            while (inputFileScanner.hasNextLine()) {
                String currentLine = inputFileScanner.nextLine().trim();

                currentSectionHeader = determineCurrentSectionHeader(currentLine, currentSectionHeader);

                if (currentSectionHeader == null) {
                    continue;
                }

                processLineBasedOnSection(currentSectionHeader, currentLine, problemPart);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            String errorMessage = "Input file not found: " + inputFilePath;
            System.err.println(errorMessage);
            log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "FileNotFoundException: " + fileNotFoundException.getMessage());
            throw fileNotFoundException;
        }

        long lowestLocationNumber = calculateLowestLocationForPart(problemPart);

        log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Lowest Location number for provided seeds: " + lowestLocationNumber);

        return lowestLocationNumber;
    }

    /**
     * Determines the current section header based on the input line.
     */
    private String determineCurrentSectionHeader(String currentLine, String previousSectionHeader) {
        if (currentLine.startsWith(SEEDS_PREFIX)) {
            return SEEDS_HEADER;
        } else if (currentLine.isBlank()) {
            return null;
        } else if (currentLine.startsWith(SEED_TO_SOIL_MAP_HEADER)) {
            return SEED_TO_SOIL_MAP_HEADER;
        } else if (currentLine.startsWith(SOIL_TO_FERTILIZER_MAP_HEADER)) {
            return SOIL_TO_FERTILIZER_MAP_HEADER;
        } else if (currentLine.startsWith(FERTILIZER_TO_WATER_MAP_HEADER)) {
            return FERTILIZER_TO_WATER_MAP_HEADER;
        } else if (currentLine.startsWith(WATER_TO_LIGHT_MAP_HEADER)) {
            return WATER_TO_LIGHT_MAP_HEADER;
        } else if (currentLine.startsWith(LIGHT_TO_TEMPERATURE_MAP_HEADER)) {
            return LIGHT_TO_TEMPERATURE_MAP_HEADER;
        } else if (currentLine.startsWith(TEMPERATURE_TO_HUMIDITY_MAP_HEADER)) {
            return TEMPERATURE_TO_HUMIDITY_MAP_HEADER;
        } else if (currentLine.startsWith(HUMIDITY_TO_LOCATION_MAP_HEADER)) {
            return HUMIDITY_TO_LOCATION_MAP_HEADER;
        } else {
            return previousSectionHeader; // Keep the current header for data lines
        }
    }

    /**
     * Processes a line based on the current section header and problem part.
     */
    private void processLineBasedOnSection(String currentSectionHeader, String currentLine, int problemPart) {
        if (SEEDS_HEADER.equals(currentSectionHeader)) {
            processSeedsLine(currentLine, problemPart);
        } else {
            processTransformationMapLine(currentSectionHeader, currentLine);
        }
    }

    /**
     * Processes the seeds line differently based on the problem part.
     */
    private void processSeedsLine(String currentLine, int problemPart) {
        if (problemPart == 1) {
            parseIndividualSeeds(currentLine);
        } else {
            this.seedsLineForPart2 = currentLine;
        }
    }

    /**
     * Parses individual seeds for Part 1.
     */
    private void parseIndividualSeeds(String seedsLine) {
        Arrays.stream(seedsLine.split(" "))
                .skip(1) // Skip "seeds:" prefix
                .map(Long::parseLong)
                .forEach(seedsForPart1::add);

        log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Parsed individual seeds: " + seedsForPart1);
    }

    /**
     * Processes transformation map lines and adds them to the appropriate map.
     */
    private void processTransformationMapLine(String currentSectionHeader, String currentLine) {
        if (currentSectionHeader != null && !currentLine.contains("map:")) {
            switch (currentSectionHeader) {
                case SEED_TO_SOIL_MAP_HEADER:
                    addMappingDataToMap(currentLine, seedToSoilMap);
                    break;
                case SOIL_TO_FERTILIZER_MAP_HEADER:
                    addMappingDataToMap(currentLine, soilToFertilizerMap);
                    break;
                case FERTILIZER_TO_WATER_MAP_HEADER:
                    addMappingDataToMap(currentLine, fertilizerToWaterMap);
                    break;
                case WATER_TO_LIGHT_MAP_HEADER:
                    addMappingDataToMap(currentLine, waterToLightMap);
                    break;
                case LIGHT_TO_TEMPERATURE_MAP_HEADER:
                    addMappingDataToMap(currentLine, lightToTemperatureMap);
                    break;
                case TEMPERATURE_TO_HUMIDITY_MAP_HEADER:
                    addMappingDataToMap(currentLine, temperatureToHumidityMap);
                    break;
                case HUMIDITY_TO_LOCATION_MAP_HEADER:
                    addMappingDataToMap(currentLine, humidityToLocationMap);
                    break;
                default:
                    log(LogLevel.VERBOSE, ENABLE_VERBOSE_LOGGING, "Unknown section header: " + currentSectionHeader);
            }
        }
    }

    /**
     * Calculates the lowest location number based on the problem part.
     */
    private long calculateLowestLocationForPart(int problemPart) {
        if (problemPart == 1) {
            return calculateLowestLocationForIndividualSeeds();
        } else {
            return calculateLowestLocationForSeedRanges();
        }
    }

    /**
     * Calculates the lowest location for individual seeds in Part 1.
     */
    private long calculateLowestLocationForIndividualSeeds() {
        long lowestLocationNumber = Long.MAX_VALUE;

        for (Long seedNumber : seedsForPart1) {
            long seedLocationNumber = calculateLocationForSeed(seedNumber);
            if (seedLocationNumber < lowestLocationNumber) {
                lowestLocationNumber = seedLocationNumber;
            }

            log(
                    LogLevel.VERBOSE,
                    ENABLE_VERBOSE_LOGGING,
                    "Seed " + seedNumber + " maps to location " + seedLocationNumber);
        }

        return lowestLocationNumber;
    }

    /**
     * Calculates the lowest location for seed ranges in Part 2.
     */
    private long calculateLowestLocationForSeedRanges() {
        String seedRangeData = extractSeedRangeData();
        String[] rangeComponents = seedRangeData.split("\\s+");
        long overallLowestLocationNumber = Long.MAX_VALUE;

        log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Processing seed ranges for Part 2");

        for (int rangeIndex = 0; rangeIndex < rangeComponents.length; rangeIndex += 2) {
            long rangeStartSeed = Long.parseLong(rangeComponents[rangeIndex]);
            long rangeLength = Long.parseLong(rangeComponents[rangeIndex + 1]);

            log(
                    LogLevel.DEBUG,
                    ENABLE_DEBUG_LOGGING,
                    "Processing seed range: start=" + rangeStartSeed + ", length=" + rangeLength);

            long rangeLowestLocation = processEntireSeedRange(rangeStartSeed, rangeLength);
            overallLowestLocationNumber = Math.min(overallLowestLocationNumber, rangeLowestLocation);
        }

        return overallLowestLocationNumber;
    }

    /**
     * Extracts seed range data from the seeds line for Part 2.
     */
    private String extractSeedRangeData() {
        return this.seedsLineForPart2
                .substring(this.seedsLineForPart2.indexOf(SEEDS_PREFIX) + SEEDS_PREFIX.length())
                .trim();
    }

    /**
     * Processes an entire seed range and returns the lowest location found.
     */
    private long processEntireSeedRange(long rangeStartSeed, long rangeLength) {
        long rangeLowestLocation = Long.MAX_VALUE;
        List<Long> currentBatchSeeds = new ArrayList<>();

        for (long currentSeedOffset = 0; currentSeedOffset < rangeLength; currentSeedOffset++) {
            long currentSeedNumber = rangeStartSeed + currentSeedOffset;
            currentBatchSeeds.add(currentSeedNumber);

            if (currentBatchSeeds.size() % SEED_PROCESSING_BATCH_SIZE == 0) {
                long batchLowestLocation = processSeedBatch(new ArrayList<>(currentBatchSeeds));
                rangeLowestLocation = Math.min(rangeLowestLocation, batchLowestLocation);
                currentBatchSeeds.clear();
            }
        }

        // Process any remaining seeds in the final batch
        if (!currentBatchSeeds.isEmpty()) {
            long finalBatchLowestLocation = processSeedBatch(currentBatchSeeds);
            rangeLowestLocation = Math.min(rangeLowestLocation, finalBatchLowestLocation);
        }

        return rangeLowestLocation;
    }

    /**
     * Processes a batch of seeds and returns the lowest location found.
     */
    private long processSeedBatch(List<Long> seedBatch) {
        long batchLowestLocation = Long.MAX_VALUE;

        for (Long seedNumber : seedBatch) {
            long seedLocationNumber = calculateLocationForSeed(seedNumber);

            if (seedLocationNumber < batchLowestLocation) {
                batchLowestLocation = seedLocationNumber;
            }
        }

        log(
                LogLevel.VERBOSE,
                ENABLE_VERBOSE_LOGGING,
                "Processed batch of " + seedBatch.size() + " seeds, lowest location: " + batchLowestLocation);

        return batchLowestLocation;
    }

    /**
     * Calculates the final location for a given seed through all transformation maps.
     */
    private long calculateLocationForSeed(long seedNumber) {
        long soilNumber = seedToSoilMap.getDestinationForSource(seedNumber);
        long fertilizerNumber = soilToFertilizerMap.getDestinationForSource(soilNumber);
        long waterNumber = fertilizerToWaterMap.getDestinationForSource(fertilizerNumber);
        long lightNumber = waterToLightMap.getDestinationForSource(waterNumber);
        long temperatureNumber = lightToTemperatureMap.getDestinationForSource(lightNumber);
        long humidityNumber = temperatureToHumidityMap.getDestinationForSource(temperatureNumber);
        long locationNumber = humidityToLocationMap.getDestinationForSource(humidityNumber);

        log(
                LogLevel.VERBOSE,
                ENABLE_VERBOSE_LOGGING,
                "Seed " + seedNumber + " → " + soilNumber + " → " + fertilizerNumber + " → " + waterNumber
                        + " → " + lightNumber + " → " + temperatureNumber + " → " + humidityNumber
                        + " → " + locationNumber);

        return locationNumber;
    }

    /**
     * Adds mapping data to the specified transformation map.
     */
    private void addMappingDataToMap(String mappingDataLine, SourceToDestinationMap transformationMap) {
        List<String> mappingComponents = Arrays.asList(mappingDataLine.split(" "));
        long destinationRangeStart = Long.parseLong(mappingComponents.get(0));
        long sourceRangeStart = Long.parseLong(mappingComponents.get(1));
        long rangeLength = Long.parseLong(mappingComponents.get(2));

        transformationMap.addMapping(destinationRangeStart, sourceRangeStart, rangeLength);

        log(
                LogLevel.VERBOSE,
                ENABLE_VERBOSE_LOGGING,
                "Added mapping: dest=" + destinationRangeStart + ", src=" + sourceRangeStart + ", len=" + rangeLength);
    }

    /**
     * Resets all state for fresh execution (used in testing).
     */
    public static void resetAllMapsAndSeeds() {
        seedsForPart1.clear();
        seedToSoilMap.getRangeMappings().clear();
        soilToFertilizerMap.getRangeMappings().clear();
        fertilizerToWaterMap.getRangeMappings().clear();
        waterToLightMap.getRangeMappings().clear();
        lightToTemperatureMap.getRangeMappings().clear();
        temperatureToHumidityMap.getRangeMappings().clear();
        humidityToLocationMap.getRangeMappings().clear();

        log(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "All maps and seeds have been reset");
    }
}
