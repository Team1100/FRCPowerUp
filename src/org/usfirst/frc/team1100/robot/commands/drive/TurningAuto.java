package org.usfirst.frc.team1100.robot.commands.drive;
import edu.wpi.first.wpilibj.command.PIDCommand;
import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.drive.*;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class TurningAuto extends Command {
	
	//Instance fields
	private AHRS ahrs = OI.getInstance().getAHRS();
	private double turningDegrees;
	
	//Constructor taking in turning amount in degrees
	public TurningAuto(double turningInput) {
		turningDegrees = turningInput;
	}
	
	/**
	 * Turns a given amount of degrees, using PID
	 */
	@Override
	public void execute() {
		//Tested working values
		ChangeHeading test = new ChangeHeading(turningDegrees,.07,.01,.3);
		ahrs.zeroYaw();
		test.usePIDOutput(test.returnPIDInput());
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
