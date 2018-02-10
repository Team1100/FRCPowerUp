package org.usfirst.frc.team1100.robot.commands.manipulator;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Elevator uses the Climber subsystem and is based on the 
 * PIDCommand class. An analog sensor is used to sense the 
 * height. 
 *  
 * @author cp18587 (2/5/2018)
 */
public class DriveStraight extends PIDCommand {
	private PIDController pidController = getPIDController();
    
	double height;
    final double INVERSE_VOLTAGE_AT_ZERO = 0.0;
    final double INCHES_PER_VOLT = 3.0;

    public Elevator(double height) {
    	super(.07,.05,.3);
        requires(Climber.getInstance());
        this.height = height;
        setSetpoint(height);
        setInputRange(0, 38.25); 
        pidController.setOutputRange(-1, 1);
        pidController.setPercentTolerance(0.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Climber.getInstance().climb(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        SmartDashboard.putNumber("Voltage", climb.getVoltage());
        return pidController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        Climber.getInstance().climb(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Climber.getInstance().climb(0);
    }

	@Override
	protected double returnPIDInput() {
		return Climber.getVoltage() * INCHES_PER_VOLT + INVERSE_VOLTAGE_AT_ZERO;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		//Drive.getInstance().climb(-output);
	}
}
