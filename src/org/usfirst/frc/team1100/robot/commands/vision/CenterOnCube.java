package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.vision.Limelight;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class CenterOnCube extends PIDCommand {
	private PIDController pidController = getPIDController();
    public CenterOnCube() {
    	super(.005, .0025, .04);
    	requires(Limelight.getInstance());
    	requires(Drive.getInstance()); 
        setInputRange(-27, 27);
        pidController.setOutputRange(-1, 1);
        pidController.setContinuous();
        pidController.setPercentTolerance(0.1);
        setSetpoint(0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    int countOnTarget = 0;
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

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
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
