package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.intake.DefaultIntake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	private static Intake intake;
	private double speed;
	
	/*
	 * The two PWM motors driving the claw to
	 * pull the cube in or shoot it out.
	 */
	WPI_TalonSRX leftPullMotor;
	WPI_TalonSRX rightPullMotor;

    /*
     * Initializes intake hardware
     */
	private Intake() {
		leftPullMotor = new WPI_TalonSRX(RobotMap.W_PULL_MOTOR_LEFT);
		rightPullMotor = new WPI_TalonSRX(RobotMap.W_PULL_MOTOR_RIGHT);
		rightPullMotor.setInverted(true);
		speed = 0;
	}
	
	/**
	 * Gets the singular instance of the intake subsystem
	 * @return the intake subsystem instance
	 */
	public static Intake getInstance() {
		if (intake == null) intake = new Intake();
		return intake;
	}
	
	/**
	 * This function sets the speed at which the cube is pulled in
	 * and shot out.
	 * @param speed the speed at which to pull/shoot the cube
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	/**
	 * This function sets claw motors to pull a cube into
	 * the claw.
	 */
	public void spinWheels() {
		leftPullMotor.set(speed);
		rightPullMotor.set(speed);
	}
	
	/**
	 * This function stops the claw motors from spinning.
	 */
	public void stop() {
		leftPullMotor.set(0);
		rightPullMotor.set(0);
	}
	
	/**
	 * Sets default command to Default intake
	 * @see org.usfirst.frc.team1100.robot.commands.intake.DefaultIntake
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultIntake());
	}
}

