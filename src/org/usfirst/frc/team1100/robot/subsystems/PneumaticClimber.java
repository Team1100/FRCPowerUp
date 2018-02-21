package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticClimber extends Subsystem {
	
	/*
	 * The singular instance of the PneumaticClimber subsystem
	 */
	private static PneumaticClimber climber;
	
	private boolean isDown;

	DoubleSolenoid pClimber;

	
	private PneumaticClimber( ) {
		pClimber = new DoubleSolenoid(RobotMap.P_CLIMBER_CAN, RobotMap.P_CLIMBER_0, RobotMap.P_CLIMBER_1);
		isDown = pClimber.get()==DoubleSolenoid.Value.kReverse;
	}
	
	/**
	 * Gets the singular instance of the PneumaticClimber subsystem
	 * @return the singular instance of the PneumaticClimber subsystem
	 */
	public static PneumaticClimber getInstance() {
		if (climber == null) climber = new PneumaticClimber();
		return climber;
	}
	
	/**
	 * Extends the climber
	 */
	public void climb() {
		pClimber.set(DoubleSolenoid.Value.kForward);
		isDown = false;
	}
	
	/**
	 * Lowers the climber
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

