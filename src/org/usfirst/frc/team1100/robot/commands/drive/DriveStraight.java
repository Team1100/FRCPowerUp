package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;

/**
 * DriveStraight drives the robot a specified distance at a 
 * specified speed at a specified heading. THe robot does not 
 * stop when the command ends, so follow up with another drive 
 * command (like DriveStop). 
 */
public class DriveStraight extends PIDCommand {
	private PIDController pidController = getPIDController();
    
	private AHRS ahrs = Drive.getInstance().getNavX();
	
    Drive drive;
    Encoder encoder;
	double speed, distance;
	
	/**
	 * 
	 * @param distance in feet
	 * @param speed
	 * @param heading
	 */
    public DriveStraight(double distance, double speed, double heading) {
    	super(.07,.05,.3);
        requires(Drive.getInstance());
        drive = Drive.getInstance();
        encoder = drive.getEncoder();
        this.speed = speed;
        this.distance = distance;
        setSetpoint(heading);
        setInputRange(-180.0, 180.0); 
        pidController.setOutputRange(-1, 1);
        pidController.setContinuous();
        pidController.setPercentTolerance(0.5);
    }

    protected void initialize() {
    	encoder.reset();
        Drive.getInstance().arcadeDrive(-speed, 0);
    }

    protected boolean isFinished() {
        SmartDashboard.putNumber("Distance (steps)", encoder.getDistance());
        return Math.abs(encoder.getDistance()) >= Math.abs(distance);
    }

    protected void end() {
    }

    protected void interrupted() {
        Drive.getInstance().arcadeDrive(0, 0);
    }

	@Override
	protected double returnPIDInput() {
		return Drive.getInstance().getNavX().getYaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		Drive.getInstance().arcadeDrive(-speed, -output);
	}
}
