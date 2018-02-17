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
	//WPI_TalonSRX pincher;
	DoubleSolenoid pincher;

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
		pincher = new DoubleSolenoid(RobotMap.W_PINCHER_CAN, RobotMap.W_PINCHER_0, RobotMap.W_PINCHER_1);
		pot = new AnalogInput(RobotMap.W_WRIST_POT);
	}

	/**
	 * Gets the singular instance of the Claw subsystem
	 * @return the singular instance of the Claw subsystem
	 */
	public static Claw getInstance() {
		if (claw == null) claw = new Claw();
		return claw;
	}

	public void open() {
		// close the claw
		pincher.set(DoubleSolenoid.Value.kForward);
	}

	public void close() {
		// close the claw
		pincher.set(DoubleSolenoid.Value.kReverse);
	}

	public void pullIn(double speed) {
		// pull cube in
		leftPullMotor.set(-speed);
		rightPullMotor.set(-speed);
	}

	public void shootOut(double speed) {
		// shoot cube out
		leftPullMotor.set(speed);
		rightPullMotor.set(speed);
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