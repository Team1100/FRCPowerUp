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

	/*
	 * The two PWM motors driving the claw
	 */
	DoubleSolenoid pClimber;

	
	private PneumaticClimber( ) {
		pClimber = new DoubleSolenoid(RobotMap.P_CLIMBER_CAN, RobotMap.P_CLIMBER_0, RobotMap.P_CLIMBER_1);
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
	}
	
	/**
	 * Lowers the climber
	 */
	public void lower() {
		pClimber.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * Unused
	 */
    public void initDefaultCommand() {

    }
}

