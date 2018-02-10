package org.usfirst.frc.team1100.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1100.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
	 * The two PWM motors driving the claw to
	 * change the angle of how the cube is held.
	 * The motors act like a wrist for the claw.
	 */
	private static Talon leftWristMotor = null;
	private static Talon rightWristMotor = null;
	
	/**
	 * The two PWM motors driving the claw to
	 * pull the cube in or shoot it out.
	 */
	private static Talon leftPullMotor = null;
	private static Talon rightPullMotor = null;

	/**
	 * The two PWM motors driving the claw
	 */
	private static WPI_TalonSRX pincher = null;
	
	/**
	 * Sets up talons and drivetrain
	 */
	private Claw() {
		leftPullMotor = new Talon(RobotMap.W_PULL_MOTOR_LEFT);
		rightPullMotor = new Talon(RobotMap.W_PULL_MOTOR_RIGHT);
		leftWristMotor = new Talon(RobotMap.W_WRIST_MOTOR_LEFT);
		rightWristMotor = new Talon(RobotMap.W_WRIST_MOTOR_RIGHT);
		pincher = new WPI_TalonSRX(RobotMap.W_PINCHER);
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