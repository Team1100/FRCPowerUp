package org.usfirst.frc.team1100.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

/**
 *
 */
public class PathFollower extends Command {
	private EncoderFollower leftFollower;
    private EncoderFollower rightFollower;

    public PathFollower() {
    }

    protected void initialize() {
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
