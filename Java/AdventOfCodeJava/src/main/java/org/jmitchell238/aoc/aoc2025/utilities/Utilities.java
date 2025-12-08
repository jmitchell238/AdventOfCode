package org.jmitchell238.aoc.aoc2025.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Consumer;

public class Utilities {

    /**
     * Open a file and call the provided action for each line.
     */
    public static void readFileAndProcessEachLine(String filePath, Consumer<String> whatToDoWithEachLine) {
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                whatToDoWithEachLine.accept(currentLine);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + filePath);
        }
    }

    /**
     * Print a message if the corresponding log level is enabled.
     */
    public static void log(LogLevel level, Boolean shouldOutput, String message) {
        if (shouldOutput) {
            System.out.println(level.toString() + " " + message);
        }
    }
}
