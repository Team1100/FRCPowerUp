package org.usfirst.frc.team1100.robot.commands.wrist;

import org.usfirst.frc.team1100.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Controls the wrist through a PID loop
 */
public class PIDWrist extends PIDCommand {
	
	PIDController pidController = getPIDController();
	Wrist wrist;
	int countOnTarget = 0;
	
	/**
	 * Sets up PID loop
	 * @param angle angle of the wrist that's desired
	 */
    public PIDWrist(double angle) {
        super(.1,.1,.1);
        requires(Wrist.getInstance());
        wrist = Wrist.getInstance();
        //TODO: setInputRange(climber.getBottom(), climber.getTop()); 
        setSetpoint(angle);
        pidController.setOutputRange(-1, 1);
        pidController.setPercentTolerance(0.1);
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    }
    
    /**
     * Unused
     */
    protected void execute() {
    }

    /**
     * True if on target 10 times in a row, ensures that it will stay in same place
     */
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

    /**
     * Sets speed of wrist to 0
     */
    protected void end() {
    	wrist.rotateWrist(0);
    }

    /**
     * Sets speed of wrist to 0
     */
    protected void interrupted() {
    	wrist.rotateWrist(0);
    }
    
    /**
     * Returns angle of wrist in voltage
     */
	@Override
	protected double returnPIDInput() {
		return wrist.getVoltage();
	}
	
	/**
	 * Rotates wrist based on output unless wrist is going down and it wants to go down
	 */
	@Override
	protected void usePIDOutput(double output) {
		if (wrist.getVoltage() < .5 && output < 0) { //TODO: Change .5
			output *= .1;
		}
		wrist.rotateWrist(output);
		
	}
}
