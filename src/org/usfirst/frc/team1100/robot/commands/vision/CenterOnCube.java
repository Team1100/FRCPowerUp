package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * THis command turns robot such that cube is moved to center of limelight frame
 */
public class CenterOnCube extends PIDCommand {
	private PIDController pidController = getPIDController();
	int countOnTarget;
	Timer t;
	
	/**
	 * Uses the drive subsystem, sets up PID controller
	 */
    public CenterOnCube(double tolerance) {
    	super(.05, .01, 0);
    	t = new Timer();
    	requires(Drive.getInstance()); 
        setInputRange(-20, 20);
        pidController.setOutputRange(-1, 1);
        pidController.setPercentTolerance(tolerance);
        setSetpoint(0);
    }

    /**
     * Sets counter for being on target to zero
     */
    protected void initialize() {
    	countOnTarget = 0;
    	t.start();
    }

    /**
     * Unused, all of the content normally present is in returnPIDInput() and usePIDOutput
     */
    protected void execute() {
    }
    
    /**
     * Finished after 1.5 seconds
     */
    protected boolean isFinished() {
    	return t.get()>1.5;
    }

    /**
     * Unused
     */
    protected void end() {
    }

    /**
     * Unused
     */
    protected void interrupted() {
    }
    
    /**
     * Returns the Y coordinate of the center of the cube
     */
	@Override
	protected double returnPIDInput() {
		//If not detected, end
		if (Limelight.getInstance().getArea() == -1) {
			return 0;
		}
		return Limelight.getInstance().getY();
	}
	
	/**
	 * Tank drives based on the output of the PID controller
	 * @param output the output of the PID controller
	 */
	@Override
	protected void usePIDOutput(double output) {
		Drive.getInstance().tankDrive(output, -output);
	}
}
