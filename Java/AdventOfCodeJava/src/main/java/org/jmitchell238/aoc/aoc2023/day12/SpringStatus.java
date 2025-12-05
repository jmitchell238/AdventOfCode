package org.jmitchell238.aoc.aoc2023.day12;

public class SpringStatus {

    private char spring;

    public SpringStatus(char spring) {
        this.spring = spring;
    }

    public static SpringStatus fromChar(char c) {
        return new SpringStatus(c);
    }

    public char getSpring() {
        return spring;
    }

    @Override
    public String toString() {
        return String.valueOf(spring);
    }
}
