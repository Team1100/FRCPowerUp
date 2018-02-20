package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.wrist.DefaultWrist;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wrist extends Subsystem {

    private static Wrist wrist;
    
    
    /*
	 * The two PWM motors driving the claw to
	 * change the angle of how the cube is held.
	 * The motors act like a wrist for the claw.
	 */
	WPI_TalonSRX leftWristMotor;
	WPI_TalonSRX rightWristMotor;
	
	/*
	 * Analog encoder
	 */
    AnalogInput pot; // potentiometer
	
    private Wrist() {
    	leftWristMotor = new WPI_TalonSRX(RobotMap.W_WRIST_MOTOR_LEFT);
		rightWristMotor = new WPI_TalonSRX(RobotMap.W_WRIST_MOTOR_RIGHT);
		leftWristMotor.setInverted(true);
		pot = new AnalogInput(RobotMap.W_WRIST_POT);
    }
    
    public static Wrist getInstance() {
    	if (wrist == null) wrist = new Wrist();
    	return wrist;
    }
    
	/**
	 * Move wrist with the given speed.
	 * @param speed Speed of wrist motor
	 * @return true if move successful
	 */
	public boolean rotateWrist(double speed) {
		if (!PneumaticClimber.getInstance().isDown() && !Claw.getInstance().isOpen()) {
			if (-.5>speed) speed = -.5;
			if (.5<speed) speed = .5;
			// rotate the wrist
			leftWristMotor.set(speed);
			rightWristMotor.set(speed);
			return true;
		} 
		speed = 0;
		leftWristMotor.set(speed);
		rightWristMotor.set(speed);
		return false;
	}
	
	/**
	 * Gets angle of wrist
	 * @return angle of wrist
	 */
	public double getVoltage() {
		return pot.getAverageVoltage();
	}
	
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DefaultWrist());
    }
}

