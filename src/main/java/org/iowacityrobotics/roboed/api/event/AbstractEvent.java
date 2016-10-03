package org.iowacityrobotics.roboed.api.event;

/**
 * A basic, asbtract implementation of {@link IEvent}.
 * @author Evan Geng
 */
public abstract class AbstractEvent implements IEvent {

    /**
     * The time this event was created.
     */
	private final long timestamp;
	
	/**
	 * Creates the abstract event, setting the timestamp to the current system time.
	 */
	public AbstractEvent() {
		this(System.currentTimeMillis());
	}
	
	/**
	 * Creates the abstract event, setting the timestamp to the provided epoch millisecond time.
	 */
	public AbstractEvent(long timestamp) {
	    this.timestamp = timestamp;
	}
	
	@Override
	public long timestamp() {
		return timestamp;
	}

}
