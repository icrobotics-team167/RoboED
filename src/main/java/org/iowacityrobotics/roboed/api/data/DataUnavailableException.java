package org.iowacityrobotics.roboed.api.data;

/**
 * Thrown if data is requested from a data stream, but none is available.
 * @author Evan Geng
 */
@SuppressWarnings("serial")
public class DataUnavailableException extends RuntimeException {

    /**
     * Creates a generic exception. Thrown if a reason is unknown.
     */
    public DataUnavailableException() {
        this("No data available!");
    }
    
    /**
     * Creates an exception with a reason.
     * @param reason The reason no data was available.
     */
    public DataUnavailableException(String reason) {
        super(reason);
    }
    
    /**
     * Creates an exception with a {@link Throwable} that caused the exception.
     * @param cause The cause of the unavailability.
     */
    public DataUnavailableException(Throwable cause) {
        super(cause);
    }
    
}
