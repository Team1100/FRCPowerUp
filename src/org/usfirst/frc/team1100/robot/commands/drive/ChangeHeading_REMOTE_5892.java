package org.usfirst.frc.team1100.robot.commands.drive;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

/**
 * This command allows tank driving with two joysticks
 */
public class ChangeHeading extends PIDCommand {
	
	/**
	 * Heading values
	 */
	private double headingNow;
    private double headingTarget;
    private double headingTolerance;
    private double headingError;

    private PIDController pidController = getPIDController();
    
	private AHRS ahrs = OI.getInstance().getAHRS();
	
	/**
	 * Requires Drive subsystem
	 * Provide P,I,D parameters
	 */
    public ChangeHeading(double target, double p, double i, double d) {
    	super(p,i,d);
        requires(Drive.getInstance()); 
        setTargetHeading(target);
        setInputRange(-180.0, 180.0);
        pidController.setContinuous();
        pidController.setPercentTolerance(1.0);
    }

    /**
     * Calculate the heading error and use it as the PID input
     */
    protected double returnPIDInput() {
    	setTargetHeading(Robot.angle.getSelected());
        headingNow = ahrs.getYaw();
        return headingNow;
    }

    protected void usePIDOutput(double output) {
    	Drive.getInstance().tankDrive(-output, output); // TODO: Are the signs still correct?
    	SmartDashboard.putNumber("PIDSpeed", output);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pidController.onTarget();
    }
    /**
     * 
     * @param heading Heading is relevant to starting direction
     */
    public void setTargetHeading(double heading) {
        if (heading != headingTarget)
        {
            headingTarget = heading;
            setSetpoint(heading);
        }
    }
    /**
     * 
     * @param tolerance tolerance in degrees
     */
    public void setHeadingPercentTolerance(double tolerance) {
    	pidController.setPercentTolerance(tolerance);
    }
}
