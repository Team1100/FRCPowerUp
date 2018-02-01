package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {
	Timer t;
	boolean done = false;
    public DriveStraight() {
        requires(Drive.getInstance());
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	t = new Timer();
    	t.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (t.get() < 2) {
    		Drive.getInstance().tankDrive(-.5, -.5);
    	} else {
    		Drive.getInstance().tankDrive(0, 0);
    		done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
