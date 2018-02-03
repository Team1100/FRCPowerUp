package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends PIDCommand {
	private PIDController pidController = getPIDController();
    
	private AHRS ahrs = Robot.getAHRS();
	
    Timer t;
	double speed;
    double time;

    public DriveStraight(double time, double speed, double heading) {
    	super(.07,.05,.3);
        requires(Drive.getInstance());
        this.speed = speed;
        this.time = time;
        setSetpoint(heading);
        setInputRange(-180.0, 180.0); 
        pidController.setOutputRange(-1, 1);
        pidController.setContinuous();
        pidController.setPercentTolerance(0.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	t = new Timer();
    	t.start();
        Drive.getInstance().arcadeDrive(-speed, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return t.get() >= time;
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
		return Robot.getAHRS().getYaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Drive.getInstance().arcadeDrive(-speed, -output);
		
	}
}
