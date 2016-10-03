package org.iowacityrobotics.roboed.frcimpl;

import org.iowacityrobotics.roboed.api.IRobot;
import org.iowacityrobotics.roboed.api.RobotMode;
import org.iowacityrobotics.roboed.api.actuator.IActuatorRegistry;
import org.iowacityrobotics.roboed.api.auto.IAutoManager;
import org.iowacityrobotics.roboed.api.event.IButtonManager;
import org.iowacityrobotics.roboed.api.event.IEventBus;
import org.iowacityrobotics.roboed.api.event.mode.AutoInitEvent;
import org.iowacityrobotics.roboed.api.event.mode.DisabledEvent;
import org.iowacityrobotics.roboed.api.event.mode.ModeChangeEvent;
import org.iowacityrobotics.roboed.api.event.mode.RobotInitEvent;
import org.iowacityrobotics.roboed.api.event.mode.TeleopInitEvent;
import org.iowacityrobotics.roboed.api.event.mode.TestInitEvent;
import org.iowacityrobotics.roboed.api.event.mode.TestTickEvent;
import org.iowacityrobotics.roboed.api.sensor.ISensorRegistry;
import org.iowacityrobotics.roboed.frcimpl.actuator.FRCActuatorRegistry;
import org.iowacityrobotics.roboed.frcimpl.auto.FRCAutoManager;
import org.iowacityrobotics.roboed.frcimpl.event.FRCEventBus;
import org.iowacityrobotics.roboed.frcimpl.sensor.FRCSensorRegistry;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The default RoboED implementation, designed for the 2016 WPILib release.
 * @author Evan Geng
 */
public class FRCRobot extends IterativeRobot implements IRobot { // TODO Finish implementation
    
    private FRCEventBus eventBus;
    private FRCSensorRegistry sensorRegistry;
    private FRCActuatorRegistry actuatorRegistry;
    private FRCAutoManager autoManager;
    private FRCButtonManager btnManager;
    private RobotMode mode = RobotMode.UNINITIALIZED;
    
    public FRCRobot() {
        this.eventBus = new FRCEventBus();
        this.sensorRegistry = new FRCSensorRegistry();
        this.actuatorRegistry = new FRCActuatorRegistry();
        this.btnManager = new FRCButtonManager();
    }

    @Override
    public IEventBus eventBus() {
        return eventBus;
    }

    @Override
    public ISensorRegistry sensors() {
        return sensorRegistry;
    }

    @Override
    public IActuatorRegistry actuators() {
        return actuatorRegistry;
    }

    @Override
    public IAutoManager autonomous() {
        return autoManager;
    }
    
    public IButtonManager buttons() {
        return btnManager;
    }
    
    @Override
    public RobotMode mode() {
        return mode;
    }
    
    @Override
	public void robotInit() {
        modeChange(RobotMode.DISABLED);
		eventBus.post(new RobotInitEvent(this));
	}

	@Override
	public void autonomousInit() {
	    modeChange(RobotMode.AUTO);
		eventBus.post(new AutoInitEvent(autoManager));
		autoManager.autonomousStart();
	}

	@Override
	public void autonomousPeriodic() {
		autoManager.tick();
	}

	@Override
	public void disabledInit() {
	    modeChange(RobotMode.DISABLED);
	    eventBus.post(new DisabledEvent());
	}

	@Override
	public void disabledPeriodic() {
		// NO-OP
	}

	@Override
	public void teleopInit() {
	    modeChange(RobotMode.TELEOP);
	    eventBus.post(new TeleopInitEvent());
	}

	@Override
	public void teleopPeriodic() {
		//eventBus.post(new TeleopTickEvent()); Is this event actually necessary?
		sensorRegistry.tick();
		btnManager.tick();
	}

	@Override
	public void testInit() {
	    modeChange(RobotMode.TEST);
	    eventBus.post(new TestInitEvent());
	}

	@Override
	public void testPeriodic() {
		eventBus.post(new TestTickEvent()); // TODO Use some kind of text fixture system instead
	}
	
	private void modeChange(RobotMode newMode) {
	    if (mode != newMode) {
            ModeChangeEvent event = new ModeChangeEvent(mode, newMode);
            mode = newMode;
            eventBus.post(event);
	    }
	}

}
