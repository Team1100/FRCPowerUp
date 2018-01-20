package org.usfirst.frc.team1100.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1100.robot.subsystems.vision.Vision;

/**
 *
 */
public class FindCube extends Command {
	private static boolean finished = false;
    public FindCube() {
       requires(Vision.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Vision.getInstance().request();
    	putCenterX();
    	finished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public synchronized void putCenterX() {
    	if (!Vision.getInstance().noHulls()) {
            //find center x of contours of convexHullsOutput
            double centerX = Vision.getInstance().getCenterX();
            SmartDashboard.putNumber("Center X", centerX);
        } else {
        	System.err.println("No hulls detected");
        }
    }
}
