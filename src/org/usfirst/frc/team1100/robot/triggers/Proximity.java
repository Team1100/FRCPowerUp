package org.usfirst.frc.team1100.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import org.usfirst.frc.team1100.robot.subsystems.Claw;

/**
 * This trigger is used to close the claw when this is triggered 'insert meme here'
 */
public class Proximity extends Trigger {
	
	private static Proximity proximity;
	
	/*
	 * Unused
	 */
	private Proximity() {
	}
	
	/**
	 * Gets the singular proximity instance
	 * @return the proximity instance
	 */
	public static Proximity getInstance() {
		if (proximity == null) proximity = new Proximity();
		return proximity;
	}
	
	/**
	 * Gets the state of the sensor
	 */
    public boolean get() {
        return Claw.getInstance().getProximity();
    }
}