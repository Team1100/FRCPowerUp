package org.usfirst.frc.team1100.robot.commands.climber;

import org.usfirst.frc.team1100.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * PIDClimber uses the Climber subsystem and is based on the 
 * PIDCommand class. A potentiometer is used to sense the 
 * height. 
 */
public class PIDClimber extends PIDCommand {
	private PIDController pidController = getPIDController();
    
	double height;
    Climber climber;
    int countOnTarget = 0;
    
    /**
     * Sets up PID controller, setpoint, and PID values
     * @param height the desired height of the climber, as a percent (0.0 to 1.0). Please stay away from the endpoints
     */
    public PIDClimber(double height) {
    	super(2.5,.4,1);
        requires(Climber.getInstance());
        height = 1-height;
        height *= 3.6;
        climber = Climber.getInstance();
        setInputRange(climber.getTop(), climber.getBottom()); 
        this.height = height;
        setSetpoint(height+climber.getTop());
        pidController.setOutputRange(-1, 1);
        pidController.setPercentTolerance(0.2);
    }
    
    /**
     * Sets speed of climber to 0
     */
    protected void initialize() {
        climber.climb(0);
        setSetpoint(height+climber.getTop());
    }
    
    /**
     * Gets state of PID loop
     * @return whether climber is "close enough" to setpoint
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
     * Sets speed of climber to 0
     */
    protected void end() {
        climber.climb(0);
    }
    
    /**
     * Sets speed of climber to 0
     */
    protected void interrupted() {
        climber.climb(0);
    }
    
    
    /**
     * Puts height of climber to SmartDashboard
     * @return height of robot/2, therefore the height as a percent
     */
	@Override
	protected double returnPIDInput() {
		return climber.getVoltage();
	}
	
	/**
	 * Sets speed of climber to output of PID loop
	 */
	@Override
	protected void usePIDOutput(double output) {
		climber.climb(output);
	}
}
