package org.jmitchell238.aoc.aoc2023.day12;

import lombok.Getter;

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
    System.out.println("Char '" + inputChar + "' maps to: " + springStatus);
  }
}
