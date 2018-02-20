package org.usfirst.frc.team1100.robot.commands.wrist;

import org.usfirst.frc.team1100.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls the wrist through a PID loop
 */
public class PIDWrist extends PIDCommand {
	
	PIDController pidController = getPIDController();
	Wrist wrist;
	Timer t;
	int countOnTarget = 0;
	double angle;
	
	/**
	 * Sets up PID loop
	 * @param angle angle of the wrist that's desired
	 */
    public PIDWrist(double angle) {
        super(.1,.01,.1);
        SmartDashboard.putNumber("P", .06);
    	SmartDashboard.putNumber("I", .01);
    	SmartDashboard.putNumber("D", 0);
        requires(Wrist.getInstance());
        this.angle = angle;
        wrist = Wrist.getInstance();
        setSetpoint(angle);
        setInputRange(1.3,5);//Backwards, bottom=3, top=.3
        pidController.setOutputRange(-.75, .25);
        pidController.setPercentTolerance(0.1);
        t = new Timer();
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    	t.start();
    }
    
    /**
     * Unused
     */
    protected void execute() {
    	System.err.println("Running PIDWrist, angle:  "+angle);
    	pidController.setP(SmartDashboard.getNumber("P", .06));
    	pidController.setI(SmartDashboard.getNumber("I", .01));
    	pidController.setD(SmartDashboard.getNumber("D", 0));
    }

    /**
     * True if on target 10 times in a row, ensures that it will stay in same place
     */
    protected boolean isFinished() {
    	/*
    	if (pidController.onTarget()) {
    		if (countOnTarget == 5) {
    			return true;
    		}
    		countOnTarget++;
    		
    	} else {
    		countOnTarget = 0;
    	}
    	return false;
    	*/
    	
    	return t.get() > 5;
    }

    /**
     * Sets speed of wrist to 0
     */
    protected void end() {
    	//if (t.get() > 5) {
    	System.err.println("DONE");
    	
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
		if (wrist.getVoltage() < 2 && output < 0) { //TODO: Change .5
			output *= .1;
		}
		wrist.rotateWrist(output);
		
	}
}
