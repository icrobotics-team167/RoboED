package org.iowacityrobotics.roboed.api.auto;

/**
 * Manages autonomous routines.
 * @author Evan Geng
 */
public interface IAutoManager {

    /**
     * Creates a new autonomous routine with the given ID.
     * @param id The new routine's ID.
     * @return The newly created routine.
     */
    IAutoRoutine createRoutine(String id);

    /**
     * Sets the active autonomous routine.
     * @param id The routine ID to search for.
     * @throws IllegalArgumentException If an unknown routine ID is supplied.
     */
    void setRoutine(String id);
    
}
