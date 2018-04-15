package org.usfirst.frc.team1100.robot.commands.wrist;

import org.usfirst.frc.team1100.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command raises the wrist until it hits the limit switch
 */
public class RaiseWrist extends Command {
	
	boolean done = false;
	
	/**
	 * Uses the wrist subsystem
	 */
    public RaiseWrist() {
        requires(Wrist.getInstance());
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    }
    
    /**
     * Raises the wrist
     */
    protected void execute() {
    	done = !Wrist.getInstance().rotateWrist(-.5);
    }
    
    /**
     * True when the upper limit switch is hit
     */
    protected boolean isFinished() {
        return done;
    }
    
    /**
     * Stops wrist
     */
    protected void end() {
    	Wrist.getInstance().rotateWrist(0);
    }
    
    /**
     * Stops wrist
     */
    protected void interrupted() {
    	Wrist.getInstance().rotateWrist(0);
    }
}
