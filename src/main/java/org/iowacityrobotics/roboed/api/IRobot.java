package org.iowacityrobotics.roboed.api;

import org.iowacityrobotics.roboed.api.actuator.IActuatorRegistry;
import org.iowacityrobotics.roboed.api.auto.IAutoManager;
import org.iowacityrobotics.roboed.api.event.IButtonManager;
import org.iowacityrobotics.roboed.api.event.IEventBus;
import org.iowacityrobotics.roboed.api.sensor.ISensorRegistry;

/**
 * Interface representing the robot instances. Exposes all the necessary API functions to program a functioning robot.
 * @author Evan Geng
 */
public interface IRobot {

    /**
     * Gets the event bus for this robot.
     * @return The robot's event bus.
     */
    IEventBus eventBus();

    /**
     * Gets the sensor (data input) registry for this robot.
     * @return The robot's sensor registry.
     */
    ISensorRegistry sensors();

    /**
     * Gets the actuator (data output) registry for this robot.
     * @return The robot's actuator registry.
     */
    IActuatorRegistry actuators();

    /**
     * Gets the autonomous routine manager for this robot.
     * @return The robot's autonomous manager.
     */
    IAutoManager autonomous();
    
    /**
     * Gets the button input manager for this robot.
     * @return The robot's button manager.
     */
    IButtonManager buttons();
    
    /**
     * Gets the operational mode of the robot.
     * @return The robot's operational mode.
     */
    RobotMode mode();
    
}
