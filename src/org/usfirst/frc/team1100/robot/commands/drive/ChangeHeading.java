package org.usfirst.frc.team1100.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.AttackThree;

import com.kauailabs.navx.frc.AHRS;

/**
 * This command allows tank driving with two joysticks
 */
public class ChangeHeading extends Command {
	/**
	 * Left and right speed values
	 */
	double left, right;
	
	/**
	 * Heading values
	 */
	private double headingNow;
    private double headingTarget;
    private double headingTolerance;
    private double headingError;
    
	private AHRS ahrs = OI.getInstance().getAHRS();
	
	/**
	 * Requires Drive subsystem
	 */
    public ChangeHeading() {
        requires(Drive.getInstance()); 
    }

    /**
     * unused
     */
    protected void initialize() {
    }

    /**
     * Read yaw angle and turn toward target using super.tankDrive 
     * TODO: Currently the algorithm is overly simplistic, just 
     * stopping when we get close. 
     */
    protected void execute() {
        double headingError;
        int speed;
        headingNow = ahrs.getYaw();
        headingError = headingNow - headingTarget;
        while (headingError > 180.0)
        {
            headingError = headingError - 360.0;
        }
        while (headingError < -180.0)
        {
            headingError = headingError + 360.0;
        }
        if (headingError > -headingTolerance && headingError < headingTolerance)
        {
            speed = 0;
        }
        else
        {
            speed = (int)(10.0 * headingError);  // TODO The factor of 10.0 is arbitrary and may need to be tuned
        }
        if (speed > 255)
        {
            speed = 255;
        }
        else if (speed < -255)
        {
            speed = -255;
        }
        left = speed;
        right = -speed;
    	Drive.getInstance().tankDrive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (headingError > -headingTolerance && headingError < headingTolerance)
        {
            return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    public void setTargetHeading(double heading) {
        headingTarget = heading;
    }
}
