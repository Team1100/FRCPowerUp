package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns robot such that cube is moved to center of limelight frame
 */
public class CenterOnCube extends PIDCommand {
	private PIDController pidController = getPIDController();
	int countOnTarget;
    public CenterOnCube() {
    	super(.06, .01, 0); //TODO: Tune these values
    	SmartDashboard.putNumber("P", .06);
    	SmartDashboard.putNumber("I", .01);
    	SmartDashboard.putNumber("D", 0);
    	requires(Limelight.getInstance());
    	requires(Drive.getInstance()); 
        setInputRange(-27, 27);
        pidController.setOutputRange(-.25, .25);
        pidController.setPercentTolerance(.5);
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
    	pidController.setP(SmartDashboard.getNumber("P", .06));
    	pidController.setI(SmartDashboard.getNumber("I", .01));
    	pidController.setD(SmartDashboard.getNumber("D", 0));
    }

    // Make this return true when this Command no longer needs to run execute()
    
    protected boolean isFinished() { //TODO: Limit switch
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
		return Limelight.getInstance().getX();
	}

	@Override
	protected void usePIDOutput(double output) {
		Drive.getInstance().tankDrive(output, -output);
	}
}
