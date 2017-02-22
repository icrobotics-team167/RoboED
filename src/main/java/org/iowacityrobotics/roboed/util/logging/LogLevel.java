package org.iowacityrobotics.roboed.util.logging;

/**
 * The levels of logging.
 * @author Evan Geng
 */
public enum LogLevel {

    /**
     * Finer debug logging.
     */
    DEBUG,

    /**
     * General information logging.
     */
    INFO,

    /**
     * Warning logging.
     */
    WARN,

    /**
     * Error logging.
     */
    ERROR;

    public boolean hasLevel(LogLevel level) {
        return ordinal() <= level.ordinal();
    }

}
