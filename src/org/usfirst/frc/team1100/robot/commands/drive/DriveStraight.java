package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends PIDCommand {
	private PIDController pidController = getPIDController();
    
	private AHRS ahrs = Robot.getAHRS();
	
	double target;
	double maxSpeed = .6;
    public DriveStraight() {
    	super(.07,.05,.3);
        requires(Drive.getInstance());
        target = Robot.getAHRS().getYaw();
        setSetpoint(0);
        setInputRange(-180.0, 180.0); 
        pidController.setOutputRange(-1, 1);
        pidController.setContinuous();
        pidController.setPercentTolerance(0.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected double returnPIDInput() {
		return Robot.getAHRS().getYaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		System.err.println(output);
		Drive.getInstance().arcadeDrive(maxSpeed, output);
		
	}
}
