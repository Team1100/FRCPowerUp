package org.usfirst.frc.team1100.robot.commands.drive;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.OI;

import com.kauailabs.navx.frc.AHRS;

/**
 * This command allows tank driving with two joysticks
 */
public class ChangeHeading extends PIDCommand {
	/**
	 * Left and right speed values
	 */
	double left, right;
	double speed;
	
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
	 * Provide P,I,D parameters
	 */
    public ChangeHeading(double p, double i, double d) {
    	super(p,i,d);
        requires(Drive.getInstance()); 
        setSetpoint(0); // We are targeting 0 heading error
    }

    /**
     * Caclulate the heading error and use it as the PID input
     */
    protected double returnPIDInput() {
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
        return headingError;
    }

    protected void usePIDOutput(double output) {
    	speed = output;
    	left = -speed;
    	right = speed;
    	Drive.getInstance().tankDrive(left, right);
    	SmartDashboard.putNumber("PIDSpeed", speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (headingError > -headingTolerance && headingError < headingTolerance)
        {
            return true;
        }
        return false;
    }

    public void setTargetHeading(double heading) {
        headingTarget = heading;
    }
    
    public void setHeadingTolerance(double tolerance) {
    	headingTolerance = tolerance;
    }
}
