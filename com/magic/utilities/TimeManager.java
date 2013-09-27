package com.magic.utilities;

import java.util.concurrent.TimeUnit;

/**
 * @author Ramisme
 * @since Sept 21, 2013
 */

public class TimeManager {
	
	/**
     * The last variable is assigned to the last set time in nanoseconds.
     */
    private long last;

    /**
     * Reset the last time.
     */
    public final synchronized void resetTime() {
            this.last = System.nanoTime();
    }

    /**
     * Return whether the amount of specified time has elapsed in milliseconds.
     *
     * @param time
     * @return
     */
    public final synchronized boolean sleep(final long time) {
            return sleep(time, TimeUnit.MILLISECONDS);
    }

    /**
     * Return whether the amount of specified time has elapsed in the specified
     * time unit converted from nanoseconds.
     *
     * @param time
     * @param timeUnit
     * @return
     */
    public synchronized boolean sleep(final long time, final TimeUnit timeUnit) {
            return timeUnit.convert(System.nanoTime() - this.last,
                            TimeUnit.NANOSECONDS) >= time;
    }

    /**
     * Convert rate per second to milliseconds.
     *
     * @param rate
     */
    public final long convertToMillis(final double rate) {
            return (long) (1000 / rate);
    }

}
