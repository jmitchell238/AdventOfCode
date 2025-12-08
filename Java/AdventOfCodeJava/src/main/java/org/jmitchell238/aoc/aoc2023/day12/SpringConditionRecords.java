package org.jmitchell238.aoc.aoc2023.day12;

import java.util.ArrayList;
import lombok.Data;

@Data
public class SpringConditionRecords {
    private ArrayList<SpringConditionRecord> springConditionRecordsList = new ArrayList<>();

    public void printAllRecordsConditions() {
        for (SpringConditionRecord springConditionRecord : springConditionRecordsList) {
            springConditionRecord.printRecordConditions();
        }
    }
}
