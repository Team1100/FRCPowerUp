package org.usfirst.frc.team1100.robot.commands.pneumaticelevator;

import org.usfirst.frc.team1100.robot.subsystems.PneumaticElevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command makes the Pneumatic Elevator lower
 */
public class PneumaticLower extends Command {
	PneumaticElevator climber;
	
	/**
	 * Uses the pneumatic elevator subsystem
	 */
    public PneumaticLower() {
        requires(PneumaticElevator.getInstance());
        climber = PneumaticElevator.getInstance();
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    }

    /**
     * Makes elevator lower
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
