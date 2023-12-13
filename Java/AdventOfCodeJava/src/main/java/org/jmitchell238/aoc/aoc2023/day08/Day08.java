package org.jmitchell238.aoc.aoc2023.day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import org.jmitchell238.aoc.aoc2023.utilities.Utilities;

public class Day08 {
  private static final Boolean DEBUGGING = false;
  private static final Boolean VERBOSE = false;
  @Getter @Setter private Boolean isPartTwo = false;
  private final Map<String, ArrayList<String>> nodeMap = new HashMap<>();
  private final ArrayList<Character> directionsArray = new ArrayList<>();
  private final Map<String, String> startingNodesCurrentNodes = new HashMap<>();

  public void main(String[] args) throws FileNotFoundException {
    Day08Run();
  }

  public void Day08Run() throws FileNotFoundException {
    System.out.println("\n--- Day 8: Camel Cards ---\n");

    String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input.txt";
    String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input_test.txt";
    String inputTest2 = "src/main/java/org/jmitchell238/aoc/aoc2023/day08/input_test_2.txt";

    long partOneAnswer = part1(input);
    System.out.println(STR."Part 1: Answer: \{partOneAnswer}");

    long partTwoAnswer = part2(input);
    System.out.println(STR."Part 2: Answer: \{partTwoAnswer}");
  }

  public long part1(String filePath) throws FileNotFoundException {
    processMaps(filePath);

    boolean isTraversingNodes = true;
    String currentNode = "AAA";
    int steps = 0;

    while (isTraversingNodes) {
      for (char direction : directionsArray) {
        if (direction == 'R') {
          currentNode = nodeMap.get(currentNode).get(1);
        } else if (direction == 'L') {
          currentNode = nodeMap.get(currentNode).getFirst();
        }

        steps++;

        String DESTINATION_NODE = "ZZZ";
        if (currentNode.equals(DESTINATION_NODE)) {
          isTraversingNodes = false;
          break;
        }
      }
    }

    return steps;
  }

  public long part2(String filePath) throws FileNotFoundException {
    processMaps(filePath);

    long steps = 0;
    List<Long> nodeLengthsToZ= new ArrayList<>();

    for (String node : startingNodesCurrentNodes.keySet()) {
      boolean isTraversingNodes = true;

      while (isTraversingNodes) {

        for (char direction : directionsArray) {
          if (direction == 'R') {
            startingNodesCurrentNodes.put(node, nodeMap.get(startingNodesCurrentNodes.get(node)).get(1));
          } else if (direction == 'L') {
            startingNodesCurrentNodes.put(node, nodeMap.get(startingNodesCurrentNodes.get(node)).getFirst());
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

    if (DEBUGGING) {
      System.out.println(STR."Node Lengths to Z: \{nodeLengthsToZ.toString()}");
    }

    long lcm;
    if (nodeLengthsToZ.size() == 2) {
      lcm = Utilities.lcm(nodeLengthsToZ.get(0), nodeLengthsToZ.get(1));
    } else {
      lcm = Utilities.lcmSix(nodeLengthsToZ.get(0), nodeLengthsToZ.get(1), nodeLengthsToZ.get(2), nodeLengthsToZ.get(3), nodeLengthsToZ.get(4), nodeLengthsToZ.get(5));
    }

    if (DEBUGGING) {
      System.out.println(STR."LCM: \{lcm}");
    }

    return lcm;
  }

  private void processMaps(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));
    boolean directions = true;

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      if (directions) {
        for (int i = 0; i < line.length(); i++) {
          directionsArray.add(line.charAt(i));
        }
        directions = false;
        continue;
      }

      addToMap(line);
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

    if (getIsPartTwo()) {
      if (node.toCharArray()[2] == 'A') {
        startingNodesCurrentNodes.put(node, node);
      }
    }

    nodeMap.put(node, nodes);
  }
}
