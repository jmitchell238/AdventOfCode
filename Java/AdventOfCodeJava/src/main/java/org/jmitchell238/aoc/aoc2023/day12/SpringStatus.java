package org.jmitchell238.aoc.aoc2023.day12;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import lombok.Getter;
import org.jmitchell238.aoc.generalutilities.LogLevel;

@Getter
public enum SpringStatus {
    DAMAGED_SPRING('#'),
    OPERATIONAL_SPRING('.'),
    UNKNOWN('?');

    private final char statusChar;

    SpringStatus(char statusChar) {
        this.statusChar = statusChar;
    }

    public static SpringStatus fromChar(char statusChar) {
        for (SpringStatus status : SpringStatus.values()) {
            if (status.getStatusChar() == statusChar) {
                return status;
            }
        }
        // Default to UNKNOWN if no match is found
        return UNKNOWN;
    }

    public static void main(String[] args) {
        char inputChar = '#';

        SpringStatus springStatus = SpringStatus.fromChar(inputChar);
        String whatToOutput = "Char '" + inputChar + "' maps to: " + springStatus;
        logOutput(LogLevel.INFO, true, "Part 2: Answer: " + whatToOutput);
    }
}
