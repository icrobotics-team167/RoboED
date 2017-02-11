package org.iowacityrobotics.roboed.data;

/**
 * Thrown when a sink without a bound source tries to request data.
 * @author Evan Geng
 */
public final class UnboundException extends RuntimeException {

    /**
     * Instantiates an UnboundException.
     */
    public UnboundException() {
        super("No bound data source!");
    }

}
