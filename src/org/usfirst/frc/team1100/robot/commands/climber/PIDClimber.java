package org.usfirst.frc.team1100.robot.commands.climber;

import org.usfirst.frc.team1100.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * PIDClimber uses the Climber subsystem and is based on the 
 * PIDCommand class. An analog sensor is used to sense the 
 * height. 
 */
public class PIDClimber extends PIDCommand {
	private PIDController pidController = getPIDController();
    
	double height;
    Climber climber;

    public PIDClimber(double height) {
    	super(2.5,.4,1);
        requires(Climber.getInstance());
        climber = Climber.getInstance();
        setInputRange(0, 1); 
        this.height = height;
        setSetpoint(height);
        pidController.setOutputRange(-1, 1);
        pidController.setPercentTolerance(0.05);
    }

    protected void initialize() {
        climber.climb(0);
    }

    protected boolean isFinished() {
        SmartDashboard.putNumber("Voltage", climber.getVoltage()/2);
        return pidController.onTarget();
    }

    protected void end() {
        climber.climb(0);
    }

    protected void interrupted() {
        climber.climb(0);
    }
    
    
	@Override
	protected double returnPIDInput() {
        SmartDashboard.putNumber("Voltage", climber.getVoltage());
		return climber.getVoltage()/2;
	}

	@Override
	protected void usePIDOutput(double output) {
		Climber.getInstance().climb(-output);
	}
}
