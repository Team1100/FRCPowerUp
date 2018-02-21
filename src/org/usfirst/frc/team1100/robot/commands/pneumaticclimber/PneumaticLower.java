package org.usfirst.frc.team1100.robot.commands.pneumaticclimber;

import org.usfirst.frc.team1100.robot.subsystems.PneumaticClimber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command makes the Pneumatic Climber lower
 */
public class PneumaticLower extends Command {
	PneumaticClimber climber;
	
	/**
	 * Uses the pneumatic climber subsystem
	 */
    public PneumaticLower() {
        requires(PneumaticClimber.getInstance());
        climber = PneumaticClimber.getInstance();
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    }

    /**
     * Makes climber lower
     */
    protected void execute() {
    	climber.lower();
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
