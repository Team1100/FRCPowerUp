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
	private double pullSpeed, shootSpeed;
	
	/*
	 * The two PWM motors driving the claw to
	 * change the angle of how the cube is held.
	 * The motors act like a wrist for the claw.
	 */
	WPI_TalonSRX leftWristMotor;
	WPI_TalonSRX rightWristMotor;
	
	/*
	 * The two PWM motors driving the claw to
	 * pull the cube in or shoot it out.
	 */
	WPI_TalonSRX leftPullMotor;
	WPI_TalonSRX rightPullMotor;

	/*
	 * The two PWM motors driving the claw
	 */
	DoubleSolenoid pincherLeft;
	
	/*
	 * The two PWM motors driving the claw
	 */
	DoubleSolenoid pincherRight;
	/*
	 * Analog encoder
	 */
    AnalogInput pot; // potentiometer

	/**
	 * Sets up talons and drivetrain
	 */
	private Claw() {
		leftPullMotor = new WPI_TalonSRX(RobotMap.W_PULL_MOTOR_LEFT);
		rightPullMotor = new WPI_TalonSRX(RobotMap.W_PULL_MOTOR_RIGHT);
		leftWristMotor = new WPI_TalonSRX(RobotMap.W_WRIST_MOTOR_LEFT);
		rightWristMotor = new WPI_TalonSRX(RobotMap.W_WRIST_MOTOR_RIGHT);
		pincherLeft = new DoubleSolenoid(RobotMap.W_PINCHER_CAN, RobotMap.W_PINCHER_0, RobotMap.W_PINCHER_1);
		pincherRight = new DoubleSolenoid(RobotMap.W_PINCHER_CAN, RobotMap.W_PINCHER_2, RobotMap.W_PINCHER_3);
		pot = new AnalogInput(RobotMap.W_WRIST_POT);
		pullSpeed = 0.2;
		shootSpeed = 1;
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

    /**
     * Move wrist with the given speed.
     * @param speed Speed of wrist motor
     * @return true if move successful
     */
    public boolean rotateWrist(double speed) {
    	// rotate the wrist
    	leftWristMotor.set(speed);
    	rightWristMotor.set(speed);
    	return true;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
 
    /**
     * Gets angle of wrist
     * @return angle of wrist
     */
    public double getVoltage() {
    	return pot.getAverageVoltage();
    }

}