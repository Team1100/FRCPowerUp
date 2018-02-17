package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	private static Intake intake;
	private double pullSpeed, shootSpeed;
	
	/*
	 * The two PWM motors driving the claw to
	 * pull the cube in or shoot it out.
	 */
	WPI_TalonSRX leftPullMotor;
	WPI_TalonSRX rightPullMotor;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Intake() {
		leftPullMotor = new WPI_TalonSRX(RobotMap.W_PULL_MOTOR_LEFT);
		rightPullMotor = new WPI_TalonSRX(RobotMap.W_PULL_MOTOR_RIGHT);
		rightPullMotor.setInverted(true);
		pullSpeed = 0.2;
		shootSpeed = 1;
	}
	
	public static Intake getInstance() {
		if (intake == null) intake = new Intake();
		return intake;
	}
	
	/**
	 * This function sets the speed at which the cube is pulled in
	 * and shot out.
	 * @param speed the speed at which to pull/shoot the cube
	 */
	public void setPullSpeed(double speed) {
		pullSpeed = speed;
	}

	public void setShootSpeed(double speed) {
		shootSpeed = speed;
	}
	
	/**
	 * This function sets claw motors to pull a cube into
	 * the claw.
	 */
	public void pullIn() {
		// pull cube in
		leftPullMotor.set(-pullSpeed);
		rightPullMotor.set(-pullSpeed);
	}
	
	/**
	 * This function sets the claw motors to shoot a cube
	 * out of the claw.
	 */
	public void shootOut() {
		// shoot cube out
		leftPullMotor.set(shootSpeed);
		rightPullMotor.set(shootSpeed);
	}
	
	/**
	 * This function stops the claw motors from spinning.
	 */
	public void pullStop() {
		leftPullMotor.set(0);
		rightPullMotor.set(0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

