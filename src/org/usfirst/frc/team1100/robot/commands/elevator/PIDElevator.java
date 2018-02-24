package org.usfirst.frc.team1100.robot.commands.elevator;

import org.usfirst.frc.team1100.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * PIDElevator uses the Elevator subsystem and is based on the 
 * PIDCommand class. A potentiometer is used to sense the 
 * height. 
 */
public class PIDElevator extends PIDCommand {
	private PIDController pidController = getPIDController();
    
	double height;
    Elevator elevator;
    int countOnTarget = 0;
    
    /**
     * Sets up PID controller, setpoint, and PID values
     * @param height the desired height of the elevator, as a percent (0.0 to 1.0). Please stay away from the endpoints
     */
    public PIDElevator(double height) {
    	super(2.5,.4,1);
        requires(Elevator.getInstance());
        height = 1-height;
        height *= 3.6;
        elevator = Elevator.getInstance();
        setInputRange(elevator.getTop(), elevator.getBottom()); 
        this.height = height;
        setSetpoint(height+elevator.getTop());
        pidController.setOutputRange(-1, 1);
        pidController.setPercentTolerance(0.2);
    }
    
    /**
     * Sets speed of elevator to 0
     */
    protected void initialize() {
        elevator.climb(0);
        setSetpoint(height+elevator.getTop());
    }
    
    /**
     * Gets state of PID loop
     * @return whether elevator is "close enough" to setpoint
     */
    protected boolean isFinished() {
    	if (pidController.onTarget()) {
    		if (countOnTarget == 3) {
    			return true;
    		}
    		countOnTarget++;
    		
    	} else {
    		countOnTarget = 0;
    	}
    	return false;
    }
    
    /**
     * Sets speed of elevator to 0
     */
    protected void end() {
        elevator.climb(0);
    }
    
    /**
     * Sets speed of elevator to 0
     */
    protected void interrupted() {
        elevator.climb(0);
    }
    
    
    /**
     * Puts height of elevator to SmartDashboard
     * @return height of robot/2, therefore the height as a percent
     */
	@Override
	protected double returnPIDInput() {
		return elevator.getVoltage();
	}
	
	/**
	 * Sets speed of elevator to output of PID loop
	 */
	@Override
	protected void usePIDOutput(double output) {
		elevator.climb(output);
	}
}
