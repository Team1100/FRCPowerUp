package org.usfirst.frc.team1100.robot.commands.claw;

import org.usfirst.frc.team1100.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * PIDClimber uses the Climber subsystem and is based on the 
 * PIDCommand class. A potentiometer is used to sense the 
 * height. 
 */
public class RotateWrist extends PIDCommand {
	private PIDController pidController = getPIDController();
    
	double angle;
    Claw claw;
    
    /**
     * Sets up PID controller, setpoint, and PID values
     * @param height the desired height of the climber, as a percent (0.0 to 1.0). Please stay away from the endpoints
     */
    public RotateWrist(double angle) {
    	super(2.5,.4,1);
        requires(Claw.getInstance());
        claw = Claw.getInstance();
        setInputRange(-45, 45); 
        this.angle = angle;
        setSetpoint(0);
        pidController.setOutputRange(-0.5, 0.5);
        pidController.setPercentTolerance(0.1);
    }
    
    /**
     * Sets speed of climber to 0
     */
    protected void initialize() {
        claw.rotateWrist(0);
    }
    
    /**
     * Gets state of PID loop
     * @return whether climber is "close enough" to setpoint
     */
    protected boolean isFinished() {
        return false;
    }
    
    /**
     * Sets speed of climber to 0
     */
    protected void end() {
    }
    
    /**
     * Sets speed of climber to 0
     */
    protected void interrupted() {
        claw.rotateWrist(0);
    }
    
    
    /**
     * Puts height of climber to SmartDashboard
     * @return height of robot/2, therefore the height as a percent
     */
	@Override
	protected double returnPIDInput() {
//        SmartDashboard.putNumber("Wrist angle", climber.getVoltage());
		return claw.getVoltage();
	}
	
	/**
	 * Sets speed of wrist to output of PID loop
	 */
	@Override
	protected void usePIDOutput(double output) {
		claw.rotateWrist(-output);
	}
}
