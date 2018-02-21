package org.usfirst.frc.team1100.robot.commands.vision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs GrabCubeRaw, but ensures that it doesn't run too long.
 * @see org.usfirst.frc.team1100.robot.commands.vision.GrabCubeRaw
 */
public class GrabCube extends Command {
	
	Timer t;
	GrabCubeRaw command;
	
	/**
	 * Initializes timer and GrabCubeRaw
	 */
    public GrabCube() {
        t = new Timer();
        command = new GrabCubeRaw();
    }
    
    /**
     * Starts timer and command
     */
    protected void initialize() {
    	t.start();
    	command.start();
    }
    
    /**
     * Unused
     */
    protected void execute() {
    }
    
    /**
     * True when timer is over desired time
     */
    protected boolean isFinished() {
        return t.get() > 1.5;
    }

    /**
     * Stops GrabCubeRaw
     */
    protected void end() {
    	command.cancel();
    }

    /**
     * Stops GrabCubeRaw
     */
    protected void interrupted() {
    	command.cancel();
    }
}
