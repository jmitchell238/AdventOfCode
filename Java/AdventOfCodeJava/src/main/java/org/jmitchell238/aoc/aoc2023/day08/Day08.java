package org.jmitchell238.aoc.aoc2023.day08;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import org.jmitchell238.aoc.generalutilities.LogLevel;
import org.jmitchell238.aoc.generalutilities.Utilities;

@SuppressWarnings({"java:S106", "java:S1118", "java:S1940", "java:S2589", "java:S100", "java:S3776", "java:S127"})
public class Day08 {
    // Configuration flags
    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings({"ConstantConditions", "unused"})
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    @Getter
    @Setter
    private Boolean isPartTwo = false;

    private final Map<String, ArrayList<String>> nodeMap = new HashMap<>();
    private final ArrayList<Character> directionsArray = new ArrayList<>();
    private final Map<String, String> startingNodesCurrentNodes = new HashMap<>();

    // Java 25-friendly main; suppress unused args
    @SuppressWarnings({"unused", "java:S1172"})
    public static void main(String[] args) {
        new Day08().runDay08();
    }

    public void runDay08() {
        logOutput(LogLevel.INFO, true, "\n--- Day 8: Haunted Wasteland ---\n");

        String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input.txt";

        long partOneAnswer = part1(input);
        logOutput(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        long partTwoAnswer = part2(input);
        logOutput(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    public long part1(String filePath) {
        processMaps(filePath);

        boolean isTraversingNodes = true;
        String currentNode = "AAA";
        long steps = 0;

        while (isTraversingNodes) {
            for (char direction : directionsArray) {
                if (direction == 'R') {
                    currentNode = nodeMap.get(currentNode).get(1);
                } else if (direction == 'L') {
                    currentNode = nodeMap.get(currentNode).getFirst();
                }

                steps++;

                String destinationNode = "ZZZ";
                if (currentNode.equals(destinationNode)) {
                    isTraversingNodes = false;
                    break;
                }
            }
        }

        return steps;
    }

    public long part2(String filePath) {
        processMaps(filePath);

        long steps = 0;
        List<Long> nodeLengthsToZ = new ArrayList<>();

        for (Map.Entry<String, String> entry : startingNodesCurrentNodes.entrySet()) {
            String node = entry.getKey();
            boolean isTraversingNodes = true;

            while (isTraversingNodes) {

                for (char direction : directionsArray) {
                    if (direction == 'R') {
                        startingNodesCurrentNodes.put(
                                node,
                                nodeMap.get(startingNodesCurrentNodes.get(node)).get(1));
                    } else if (direction == 'L') {
                        startingNodesCurrentNodes.put(
                                node,
                                nodeMap.get(startingNodesCurrentNodes.get(node)).getFirst());
                    }

                    steps++;

                    if (startingNodesCurrentNodes.get(node).charAt(2) == 'Z') {
                        nodeLengthsToZ.add(steps);
                        steps = 0;
                        isTraversingNodes = false;
                        break;
                    }
                }
            }
        }

        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "Node Lengths to Z: " + nodeLengthsToZ);

        long lcm;
        if (nodeLengthsToZ.size() == 2) {
            lcm = Utilities.lcm(nodeLengthsToZ.get(0), nodeLengthsToZ.get(1));
        } else {
            lcm = Utilities.lcmSix(
                    nodeLengthsToZ.get(0),
                    nodeLengthsToZ.get(1),
                    nodeLengthsToZ.get(2),
                    nodeLengthsToZ.get(3),
                    nodeLengthsToZ.get(4),
                    nodeLengthsToZ.get(5));
        }

        logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "LCM: " + lcm);

        return lcm;
    }

    @SuppressWarnings("java:S112")
    private void processMaps(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            boolean readingDirections = true;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (readingDirections) {
                    for (int i = 0; i < line.length(); i++) {
                        directionsArray.add(line.charAt(i));
                    }
                    readingDirections = false;
                    continue;
                }

                addToMap(line);
            }
        } catch (FileNotFoundException fileNotFound) {
            String errorMessage = "Input file not found: " + filePath;
            logOutput(LogLevel.ERROR, true, errorMessage);
            logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, "FileNotFoundException: " + fileNotFound.getMessage());
            throw new RuntimeException(fileNotFound);
        }
    }

    private void addToMap(String line) {
        String[] splitLine = line.split(" ");
        String node = splitLine[0];
        String leftNode = splitLine[2].replace("(", "").replace(",", "");
        String rightNode = splitLine[3].replace(")", "");

        ArrayList<String> nodes = new ArrayList<>();
        nodes.add(leftNode);
        nodes.add(rightNode);

        if (getIsPartTwo() && node.toCharArray()[2] == 'A') {
            startingNodesCurrentNodes.put(node, node);
        }

        nodeMap.put(node, nodes);
    }
}
