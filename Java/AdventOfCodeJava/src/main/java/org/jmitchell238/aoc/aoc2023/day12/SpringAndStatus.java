package org.jmitchell238.aoc.aoc2023.day12;

public class SpringAndStatus {

    private char spring;
    private SpringStatus springStatus;

    public SpringAndStatus(char spring, SpringStatus springStatus) {
        this.spring = spring;
        this.springStatus = springStatus;
    }

    public char getSpring() {
        return spring;
    }

    public SpringStatus getSpringStatus() {
        return springStatus;
    }
}
