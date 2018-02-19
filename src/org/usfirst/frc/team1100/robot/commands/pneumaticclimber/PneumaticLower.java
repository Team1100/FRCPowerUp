package org.usfirst.frc.team1100.robot.commands.pneumaticclimber;

import org.usfirst.frc.team1100.robot.subsystems.PneumaticClimber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Makes the Pneumatic Climber lower
 */
public class PneumaticLower extends Command {
	PneumaticClimber climber;
	
    public PneumaticLower() {
        requires(PneumaticClimber.getInstance());
        climber = PneumaticClimber.getInstance();
    }

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

    protected void end() {
    }

    protected void interrupted() {
    }
}
