package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns robot such that cube is moved to center of limelight frame
 */
public class CenterOnCube extends PIDCommand {
	private PIDController pidController = getPIDController();
	int countOnTarget;
	Timer t;
    public CenterOnCube() {
    	super(.06, .01, 0);
    	t = new Timer();
    	SmartDashboard.putNumber("P", .06);
    	SmartDashboard.putNumber("I", .01);
    	SmartDashboard.putNumber("D", 0);
    	requires(Limelight.getInstance());
    	requires(Drive.getInstance()); 
        setInputRange(-20, 20);
        pidController.setOutputRange(-.75, .75);
        pidController.setPercentTolerance(1);
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
    	pidController.setP(SmartDashboard.getNumber("P", .06));
    	pidController.setI(SmartDashboard.getNumber("I", .01));
    	pidController.setD(SmartDashboard.getNumber("D", 0));
    }

    // Make this return true when this Command no longer needs to run execute()
    
    protected boolean isFinished() {
    	return t.get()>1;
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
		return Limelight.getInstance().getY();
	}

	@Override
	protected void usePIDOutput(double output) {
		Drive.getInstance().tankDrive(-output, output);
	}
}
