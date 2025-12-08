package org.jmitchell238.aoc.aoc2023.day12;

import java.util.ArrayList;
import lombok.Data;

@Data
public class SpringConditionRecord {
    private String unknownSection;
    private String numberedSection;

    private ArrayList<SpringAndStatus> springsAndStatuses = new ArrayList<>();

    public SpringConditionRecord(String line) {
        String[] lineParts = line.split(" ");
        unknownSection = lineParts[0];
        numberedSection = lineParts[1];

        for (char c : unknownSection.toCharArray()) {
            SpringStatus springStatus = SpringStatus.fromChar(c);
            SpringAndStatus springAndStatus = new SpringAndStatus(c, springStatus);
            springsAndStatuses.add(springAndStatus);
        }
    }

    public void printRecordConditions() {
        System.out.print("Unknown Section: " + unknownSection + "  -  ");
        System.out.println("Numbered Section: " + numberedSection);
        for (SpringAndStatus springAndStatus : springsAndStatuses) {
            System.out.println(
                    "Spring: " + springAndStatus.getSpring() + "  -  Status: " + springAndStatus.getSpringStatus());
        }
    }
}
