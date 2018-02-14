package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
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
	
    Encoder encoder;
	double speed;
    double distance;
    final double DISTANCE_PER_PULSE = 1; // TODO - Calibrate this to the selected unit of measure

    public DriveStraight(double distance, double speed, double heading) {
    	super(.07,.05,.3);
        requires(Drive.getInstance());
        this.speed = speed;
        this.distance = distance;
        setSetpoint(heading);
        setInputRange(-180.0, 180.0); 
        pidController.setOutputRange(-1, 1);
        pidController.setContinuous();
        pidController.setPercentTolerance(0.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	encoder = new Encoder(0, 1);
        encoder.setDistancePerPulse(DISTANCE_PER_PULSE);
    	encoder.reset();
        Drive.getInstance().arcadeDrive(-speed, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        SmartDashboard.putNumber("Distance (steps)", encoder.getDistance());
        return encoder.getDistance() >= distance;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Drive.getInstance().arcadeDrive(0, 0);
    }

	@Override
	protected double returnPIDInput() {
		return Drive.getInstance().getNavX().getYaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Drive.getInstance().arcadeDrive(-speed, -output);
	}
}
