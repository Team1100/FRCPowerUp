package org.usfirst.frc.team1100.robot.commands.pneumaticclimber;

import org.usfirst.frc.team1100.robot.subsystems.PneumaticClimber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Makes the Pneumatic Climber climb
 */
public class PneumaticClimb extends Command {
	PneumaticClimber climber;
	
    public PneumaticClimb() {
        requires(PneumaticClimber.getInstance());
        climber = PneumaticClimber.getInstance();
    }

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

    protected void end() {
    }

    protected void interrupted() {
    }
}
