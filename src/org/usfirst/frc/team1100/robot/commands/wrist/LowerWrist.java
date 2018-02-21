package org.usfirst.frc.team1100.robot.commands.wrist;

import org.usfirst.frc.team1100.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.command.Command;

/**
 * THis command lowers wrist until it hits the lower limit switch
 */
public class LowerWrist extends Command {
	
	boolean done = false;
	
	/**
	 * Uses the wrist subsystem
	 */
    public LowerWrist() {
        requires(Wrist.getInstance());
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    }
    
    /**
     * Lowers wrist
     */
    protected void execute() {
    	done = !Wrist.getInstance().rotateWrist(1);
    }
    
    /**
     * True when lower limit switch is hit
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
