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
	int countOnTarget = 0;
	
    public PIDWrist(double angle) {
        super(.1,.1,.1);
        requires(Wrist.getInstance());
        wrist = Wrist.getInstance();
        //TODO: setInputRange(climber.getBottom(), climber.getTop()); 
        setSetpoint(angle);
        pidController.setOutputRange(-1, 1);
        pidController.setPercentTolerance(0.1);
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (pidController.onTarget()) {
    		if (countOnTarget == 10) {
    			return true;
    		}
    		countOnTarget++;
    		
    	} else {
    		countOnTarget = 0;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	wrist.rotateWrist(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	wrist.rotateWrist(0);
    }

	@Override
	protected double returnPIDInput() {
		return wrist.getVoltage();
	}

	@Override
	protected void usePIDOutput(double output) {
		if (wrist.getVoltage() < .5 && output < 0) { //TODO: Change .5
			output *= .1;
		}
		wrist.rotateWrist(output);
		
	}
}
