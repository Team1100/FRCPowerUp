package org.usfirst.frc.team1100.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import org.usfirst.frc.team1100.robot.RobotMap;

/**
 * This subsystem represents the piece of the
 * robot that grabs a cube, pulls it in,
 * holds onto it, and shoots it out. The claw
 * can also rotate while holding the
 * cube.
 */
public class Claw extends Subsystem {

	/*
	 * The singular instance of the Claw subsystem
	 */
	private static Claw claw;

	DoubleSolenoid pincher;
	
	DigitalInput proximity;
	
	private boolean isOpen = false;;
	
	/*
	 * Sets up double solenoid and proximity sensor
	 */
	private Claw() {
		pincher = new DoubleSolenoid(RobotMap.W_PINCHER_CAN, RobotMap.W_PINCHER_0, RobotMap.W_PINCHER_1);
		proximity = new DigitalInput(RobotMap.W_PROXIMITY);
	}

	/**
	 * Gets the singular instance of the Claw subsystem
	 * @return the singular instance of the Claw subsystem
	 */
	public static Claw getInstance() {
		if (claw == null) claw = new Claw();
		return claw;
	}
	
	/**
	 * This function triggers the PCM module to open the claw.
	 */
	public void open() {
		isOpen = true;
		// close the claw
		pincher.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * This function triggers the PCM module to close the claw.
	 */
	public void close() {
		isOpen = false;
		// close the claw
		pincher.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * Gets the state of the claw
	 * @return state of the claw
	 */
	public boolean isOpen() {
		return isOpen;
	}
	
	/**
	 * Gets the state of the proximity sensor that detects the cube
	 * @return the state of the proximity sensor
	 */
	public boolean getProximity() {
		return proximity.get();
	}
	
	/**
	 * Unused, no default command
	 */
	@Override
	protected void initDefaultCommand() {
	}
}