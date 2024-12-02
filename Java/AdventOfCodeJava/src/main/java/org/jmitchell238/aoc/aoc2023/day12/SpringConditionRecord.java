package org.jmitchell238.aoc.aoc2023.day12;

import java.util.ArrayList;
import lombok.Data;

@Data
public class SpringConditionRecord {
  private String unknownSection;
  private String numberedSection;

  private ArrayList<SpringAndStatus> springsAndStatuses = new ArrayList<>();
  private ArrayList<Integer> brokenSpringRecords = new ArrayList<>();

  public SpringConditionRecord(String line) {
    String[] lineParts = line.split(" ");
    unknownSection = lineParts[0];
    numberedSection = lineParts[1];

    for (char c : unknownSection.toCharArray()) {
      SpringStatus springStatus = SpringStatus.fromChar(c);
      SpringAndStatus springAndStatus = new SpringAndStatus(c, springStatus);
      springsAndStatuses.add(springAndStatus);
    }

    for (char c : numberedSection.toCharArray()) {
      Integer brokenSpringAmount = Character.getNumericValue(c);
      brokenSpringRecords.add(brokenSpringAmount);
    }
  }

  public void printRecordConditions() {
    System.out.print(STR."Unknown Section: \{unknownSection}  -  ");
    System.out.println(STR."Numbered Section: \{numberedSection}");
    for (SpringAndStatus springAndStatus : springsAndStatuses) {
      System.out.println(STR."Spring: \{springAndStatus.getSpring()}  -  Status: \{springAndStatus.getSpringStatus()}");
    }
  }

  public int getNumberOfPossibleArrangements() {
    int numberOfPossibleArrangements = 0;

    for(Integer brokenSpringInARowAmount : brokenSpringRecords) {
      if (brokenSpringInARowAmount == 0) {
        numberOfPossibleArrangements++;
      }
    }

    return numberOfPossibleArrangements;
  }
}
