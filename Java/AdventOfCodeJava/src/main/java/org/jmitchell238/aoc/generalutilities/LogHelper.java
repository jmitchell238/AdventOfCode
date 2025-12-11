package org.jmitchell238.aoc.generalutilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHelper {

    private static final Logger log = LoggerFactory.getLogger(LogHelper.class);
    private static final String LOG_FORMAT = "{}: {}";

    /**
     * Private constructor to prevent instantiation
     */
    private LogHelper() {}

    /**
     * Print a message if the corresponding log level is enabled.
     */
    public static void logOutput(LogLevel level, boolean shouldOutput, String message) {
        if (shouldOutput) {
            switch (level) {
                case LogLevel.DEBUG, LogLevel.VERBOSE -> log.debug(LOG_FORMAT, level, message);
                case LogLevel.ERROR -> log.error(LOG_FORMAT, level, message);
                default -> log.info(LOG_FORMAT, level, message);
            }
        }
    }

    /**
     * Print the stack trace of an exception to standard error output.
     */
    public static void logException(Exception e) {
        log.error("{} Exception occurred: ", LogLevel.ERROR, e);
    }
}
