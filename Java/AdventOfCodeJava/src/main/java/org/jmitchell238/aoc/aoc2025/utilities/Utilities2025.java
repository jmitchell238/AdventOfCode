package org.jmitchell238.aoc.aoc2025.utilities;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Consumer;
import org.jmitchell238.aoc.generalutilities.LogLevel;

public class Utilities2025 {

    private Utilities2025() {}

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
            logOutput(LogLevel.ERROR, true, "Input file not found: " + filePath);
        }
    }
}
