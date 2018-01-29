package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToX extends PIDCommand {
	/**
	 * Left and right speed values
	 */
	double left, right;
	
	private double headingNow;
	private double headingError;
	private double headingTarget;
	private double headingTolerance;
	private double speed;
	
	private AHRS ahrs = OI.getInstance().getAHRS();

    public TurnToX(double p, double i, double d) {
    	super(p,i,d);
    	requires(Drive.getInstance());
    	setSetpoint(0);
    }
    
    protected double returnPIDInput() {
    	double headingError;
        
        headingNow = ahrs.getYaw();
        headingError = headingNow - headingTarget;
        if (headingError > 180.0)
        {
            headingError = headingError - 360.0;
        }
        if (headingError < -180.0)
        {
            headingError = headingError + 360.0;
        }
        
    	return headingError;
    }
    
    protected void usePIDOutput(double output) {
    	//out of 1
    	//TODO: turn output into speed
    	//if output is negative, turn right
    	//if output is positive, turn left
    	/*
    	left = speed;
        right = -speed;
    	Drive.getInstance().tankDrive(left, right);
    	*/
    	SmartDashboard.putNumber("PIDOutput", output);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
}
