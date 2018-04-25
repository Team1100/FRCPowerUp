package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticElevator extends Subsystem {
	
	/*
	 * The singular instance of the PneumaticElevator subsystem
	 */
	private static PneumaticElevator climber;
	
	private boolean isDown;

	DoubleSolenoid pClimber;

	private PneumaticElevator( ) {
		pClimber = new DoubleSolenoid(RobotMap.P_CLIMBER_CAN, RobotMap.P_CLIMBER_0, RobotMap.P_CLIMBER_1);
		isDown = pClimber.get()==DoubleSolenoid.Value.kReverse;
	}
	
	/**
	 * Gets the singular instance of the PneumaticElevator subsystem
	 * @return the singular instance of the PneumaticElevator subsystem
	 */
	public static PneumaticElevator getInstance() {
		if (climber == null) climber = new PneumaticElevator();
		return climber;
	}
	
	/**
	 * Extends the elevator
	 */
	public void climb() {
		pClimber.set(DoubleSolenoid.Value.kForward);
		isDown = false;
	}
	
	/**
	 * Lowers the elevator
	 */
	public void lower() {
		pClimber.set(DoubleSolenoid.Value.kReverse);
		isDown = true;
	}
	
	public boolean isDown() {
		return isDown;
	}
	
	/**
	 * Unused
	 */
    public void initDefaultCommand() {
    }
}

