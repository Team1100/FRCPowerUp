package org.usfirst.frc.team1100.robot.commands.pneumaticclimber;

import org.usfirst.frc.team1100.robot.subsystems.PneumaticClimber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command makes the Pneumatic Climber climb
 */
public class PneumaticClimb extends Command {
	PneumaticClimber climber;
	
	/**
	 * Uses the pneumatic climber subsystem
	 */
    public PneumaticClimb() {
        requires(PneumaticClimber.getInstance());
        climber = PneumaticClimber.getInstance();
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    }

    /**
     * Makes climber climb
     */
    protected void execute() {
    	climber.climb();
    }

    /**
     * Always true
     */
    protected boolean isFinished() {
        return true;
    }
    
    /**
     * Unused
     */
    protected void end() {
    }
    
    /**
     * Unused
     */
    protected void interrupted() {
    }
}
