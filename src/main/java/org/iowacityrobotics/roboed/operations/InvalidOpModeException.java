package org.iowacityrobotics.roboed.operations;

/**
 * Exception thrown upon trying to switch to an unknown opmode.
 * @author Evan Geng
 */
@SuppressWarnings("serial")
public class InvalidOpModeException extends RuntimeException {

    /**
     * The invalid opmode ID.
     */
    private final String mode;
    
    /**
     * Constructs a new InvalidOpModeException with the given opmode ID.
     * @param mode The invalid opmode ID.
     */
    public InvalidOpModeException(String mode) {
        this.mode = mode;
    }
    
    /**
     * Gets the invalid opmode ID that triggered this exception.
     * @return The invalid opmode ID.
     */
    public String getMode() {
        return mode;
    }
    
}
