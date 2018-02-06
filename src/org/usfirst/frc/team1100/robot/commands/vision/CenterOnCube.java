package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Turns robot such that cube is moved to center of limelight frame
 */
public class CenterOnCube extends PIDCommand {
	private PIDController pidController = getPIDController();
	int countOnTarget;
    public CenterOnCube() {
    	super(.005, .0025, .04); //TODO: Tune these values
    	requires(Limelight.getInstance());
    	requires(Drive.getInstance()); 
        setInputRange(-27, 27);
        pidController.setOutputRange(-1, 1);
        pidController.setContinuous();
        pidController.setPercentTolerance(0.1);
        setSetpoint(0);
    }

    /**
     * Sets counter for being on target to zero
     */
    protected void initialize() {
    	countOnTarget = 0;
    }

    /**
     * Unused, all of the content normally present is in returnPIDInput() and usePIDOutput
     */
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    
    protected boolean isFinished() {
    	if (pidController.onTarget()) {
    		if (countOnTarget == 5) {
    			return true;
    		}
    		countOnTarget++;
    		
    	} else {
    		countOnTarget = 0;
    	}
    	return false;
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

	@Override
	protected double returnPIDInput() {
		//If not detected, end
		if (Limelight.getInstance().getArea() == -1) {
			return 0;
		}
		System.err.println(Limelight.getInstance().getX());
		return Limelight.getInstance().getX();
	}

	@Override
	protected void usePIDOutput(double output) {
		Drive.getInstance().tankDrive(output, -output);
	}
}
