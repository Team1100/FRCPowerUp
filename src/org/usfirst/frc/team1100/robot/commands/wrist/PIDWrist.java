package org.usfirst.frc.team1100.robot.commands.wrist;

import org.usfirst.frc.team1100.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class PIDWrist extends PIDCommand {
	
	PIDController pidController = getPIDController();
	Wrist wrist;
	
    public PIDWrist(double angle) {
        super(0,0,0);
        requires(Wrist.getInstance());
        wrist = Wrist.getInstance();
        //TODO: setInputRange(climber.getBottom(), climber.getTop()); 
        setSetpoint(angle);
        pidController.setOutputRange(-1, 1);
        pidController.setPercentTolerance(0.1);
    }

    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
}
