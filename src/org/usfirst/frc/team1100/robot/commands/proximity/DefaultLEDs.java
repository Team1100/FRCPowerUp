package org.usfirst.frc.team1100.robot.commands.proximity;

import org.usfirst.frc.team1100.robot.subsystems.LEDs;
import org.usfirst.frc.team1100.robot.subsystems.Proximity;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultLEDs extends Command {
	
	Proximity proximity;
	LEDs leds;
	
	/**
	 * Because this command only reads from a sensor, it only requires the LEDs
	 */
    public DefaultLEDs() {
    	requires(LEDs.getInstance());
    	proximity = Proximity.getInstance();
    	leds = LEDs.getInstance();
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leds.set(proximity.getProximity());
    }

    /**
     * Always false, this is a default command
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Turns lights off
     */
    protected void end() {
    	leds.set(false);
    }

    /**
     * Turns lights off
     */
    protected void interrupted() {
    	leds.set(false);
    }
}
