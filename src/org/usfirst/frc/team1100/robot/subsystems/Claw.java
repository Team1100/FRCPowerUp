package org.usfirst.frc.team1100.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import org.usfirst.frc.team1100.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * This subsystem represents the piece of the
 * robot that grabs a cube, pulls it in,
 * holds onto it, and shoots it out. The claw
 * can also rotate while holding the
 * cube.
 */
public class Claw extends Subsystem {

	/**
	 * The singular instance of the Claw subsystem
	 */
	private static Claw claw;

	/*
	 * The two PWM motors driving the claw
	 */
	DoubleSolenoid pincherLeft;
	
	/*
	 * The two PWM motors driving the claw
	 */
	DoubleSolenoid pincherRight;
	

	/**
	 * Sets up talons and drivetrain
	 */
	private Claw() {
		pincherLeft = new DoubleSolenoid(RobotMap.W_PINCHER_CAN, RobotMap.W_PINCHER_0, RobotMap.W_PINCHER_1);
		pincherRight = new DoubleSolenoid(RobotMap.W_PINCHER_CAN, RobotMap.W_PINCHER_2, RobotMap.W_PINCHER_3);
	}

	/**
	 * Gets the singular instance of the Claw subsystem
	 * @return the singular instance of the Claw subsystem
	 */
	public static Claw getInstance() {
		if (claw == null) claw = new Claw();
		return claw;
	}

	// PINCHER FUNCTIONS
	
	/**
	 * This function triggers the PCM module to open the claw.
	 */
	public void open() {
		// close the claw
		pincherLeft.set(DoubleSolenoid.Value.kForward);
		pincherRight.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * This function triggers the PCM module to close the claw.
	 */
	public void close() {
		// close the claw
		pincherLeft.set(DoubleSolenoid.Value.kReverse);
		pincherRight.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}



}