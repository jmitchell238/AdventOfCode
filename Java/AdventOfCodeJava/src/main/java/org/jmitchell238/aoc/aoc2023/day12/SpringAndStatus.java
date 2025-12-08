package org.jmitchell238.aoc.aoc2023.day12;

import lombok.Data;

@Data
public class SpringAndStatus {
    private Character spring;
    private SpringStatus springStatus;

    public SpringAndStatus(Character spring, SpringStatus springStatus) {
        this.spring = spring;
        this.springStatus = springStatus;
    }
}
