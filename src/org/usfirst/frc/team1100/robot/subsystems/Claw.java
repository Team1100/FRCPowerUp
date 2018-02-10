package org.usfirst.frc.team1100.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1100.robot.RobotMap;

/**
 * This subsystem represents the piece of the
 * robot that grabs a milk crate, pulls it in,
 * holds onto it, and shoots it out. The claw
 * can also rotate while holding the
 * milk crate.
 */
public class Claw extends Subsystem {

	/**
	 * The singular instance of the Claw subsystem
	 */
	private static Claw claw;
	
	/**
	 * The two PWM motors driving the claw
	 */
	private static Talon leftMotor = null;
	private static Talon rightMotor = null;
	
	/**
	 * Sets up talons and drivetrain
	 */
	private Claw() {
		leftMotor = new Talon(RobotMap.W_MOTOR_LEFT);
		rightMotor = new Talon(RobotMap.W_MOTOR_RIGHT);
	}
	
	/**
	 * Gets the singular instance of the Claw subsystem
	 * @return the singular instance of the Claw subsystem
	 */
	public static Claw getInstance() {
		if (claw == null) claw = new Claw();
		return claw;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}