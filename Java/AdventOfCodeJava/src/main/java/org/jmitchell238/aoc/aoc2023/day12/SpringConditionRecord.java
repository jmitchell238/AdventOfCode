package org.jmitchell238.aoc.aoc2023.day12;

import java.util.ArrayList;
import lombok.Data;

@Data
public class SpringConditionRecord {
  private ArrayList<Character> springConditionRecord = new ArrayList<>();

  // Need an enum of the possible conditions

  public SpringConditionRecord(String line) {
    for (int i = 0; i < line.length(); i++) {
      springConditionRecord.add(line.charAt(i));
    }
  }

  public void printRecordConditions() {
    for (Character springCondition : springConditionRecord) {
      System.out.print(springCondition);
    }
    System.out.println();
  }

  public enum SpringCondition {
    DAMAGED_SPRING('#'),

  }
}
