package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleOverride extends Command {

    public ToggleOverride() {
    }

    protected void initialize() {
    	Robot.manualOverride = !Robot.manualOverride;
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }


    protected void interrupted() {
    }
}
