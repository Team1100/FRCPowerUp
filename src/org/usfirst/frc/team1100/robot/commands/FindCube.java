package org.usfirst.frc.team1100.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1100.robot.subsystems.vision.Vision;

/**
 * This command finds center X value of power cube and writes it to Smart Dashboard
 */
public class FindCube extends Command {
	/**
	 * State of command, true = finished, false = running
	 */
	private static boolean finished = false;
	
	/**
	 * Requires Vision subsystem
	 */
    public FindCube() {
       requires(Vision.getInstance());
    }

    /**
     * Requests vision be processed once to eliminate first time failure
     */
    protected void initialize() {
    	//This line is used because it never detects contours the first time
    	Vision.getInstance().request();
    }

    /**
     * Asks thread to process image, then puts center X to SmartDashboard
     */
    protected void execute() {
    	Vision.getInstance().request();
    	putCenterX();
    	finished = true;
    }

    
    /**
     * @return state of command
     */
    protected boolean isFinished() {
        return finished;
    }

    /**
     *  Called once after isFinished returns true(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#end()
     */
    protected void end() {
    }

    /**
     *  Called when another command which requires one or more of the same
     *
     * subsystems is scheduled to run
     */
    protected void interrupted() {
    }
    
    /**
     * If there's a contour/hull, it finds the center X
     */
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
