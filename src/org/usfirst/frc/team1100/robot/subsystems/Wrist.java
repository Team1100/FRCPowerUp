package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.wrist.DefaultWrist;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
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
    
    DigitalInput topLimit, bottomLimit;
    
    boolean canGoUp = true;
    boolean canGoDown = true;
	
    /*
     * Initializes wrist hardware
     */
    private Wrist() {
    	leftWristMotor = new WPI_TalonSRX(RobotMap.W_WRIST_MOTOR_LEFT);
		rightWristMotor = new WPI_TalonSRX(RobotMap.W_WRIST_MOTOR_RIGHT);
		leftWristMotor.setInverted(true);
		
		topLimit = new DigitalInput(RobotMap.W_TOP_WRIST_LIMIT);
		bottomLimit = new DigitalInput(RobotMap.W_BOTTOM_WRIST_LIMIT);
    }
    
    /**
     * Gets the singular instance of the wrist subsystem
     * @return the wrist subsystem instance
     */
    public static Wrist getInstance() {
    	if (wrist == null) wrist = new Wrist();
    	return wrist;
    }
    
	/**
	 * Move wrist with the given speed, unless moving past limit switches.
	 * <p>
	 * The speed is limited here, because the robot will be smashed into bits otherwise.
	 * @param speed Speed of wrist motor
	 * @return true if move successful
	 */
	public boolean rotateWrist(double speed) {
		if ((getTop() && speed > 0) || (getBottom() && speed < 0) || (!getBottom() && !getTop())){
			if (-.6>speed) speed = -.6;
			if (.375<speed) speed = .375;
			leftWristMotor.set(speed);
			rightWristMotor.set(speed);
			return true;
		}
		speed = 0;
		leftWristMotor.set(speed);
		rightWristMotor.set(speed);
		return false;
	}
	
	public boolean getTop() {
		return topLimit.get();
	}
	
	public boolean getBottom() {
		return !bottomLimit.get();
	}
    
    public void initDefaultCommand() {
    	setDefaultCommand(new DefaultWrist());
    }
}

