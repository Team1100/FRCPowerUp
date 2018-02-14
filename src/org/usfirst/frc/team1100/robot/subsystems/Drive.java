package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.drive.UserDrive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * This subsystem is the two sides of the robot
 */
public class Drive extends Subsystem {

	
	/**
	 * The singular instance of the Drive subsystem
	 */
	private static Drive drive;
	/**
	 * The drivetrain instance
	 */
	private static DifferentialDrive drivetrain;
	
	private VictorSP leftOne, leftTwo, leftThree, rightOne, rightTwo, rightThree;
	private AHRS ahrs;
	
	/**
	 * Sets up talons and drivetrain
	 */
	private Drive() {
		leftOne = new VictorSP(RobotMap.D_LEFT_ONE);
		leftTwo = new VictorSP(RobotMap.D_LEFT_TWO);
		leftThree = new VictorSP(RobotMap.D_LEFT_THREE);
		rightOne = new VictorSP(RobotMap.D_RIGHT_ONE);
		rightTwo = new VictorSP(RobotMap.D_RIGHT_TWO);
		rightThree = new VictorSP(RobotMap.D_RIGHT_THREE);
		ahrs = new AHRS(RobotMap.D_NAVX);
		SpeedControllerGroup left = new SpeedControllerGroup(leftOne, leftTwo, leftThree);
		SpeedControllerGroup right = new SpeedControllerGroup(rightOne, rightTwo, rightThree);
		left.setInverted(true);
		right.setInverted(true);
		
		//new updated driving class allows tank drive
		drivetrain = new DifferentialDrive(left, right);
		
	}
	
	/**
	 * Gets the singular instance of the Drive subsystem
	 * @return the singular instance of the Drive subsystem
	 */
	public static Drive getInstance() {
		if (drive == null) drive = new Drive();
		return drive;
	}
	
	/**
	 * Sets the default command to userDrive
	 */
    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        setDefaultCommand(new UserDrive());
    	

    }
    
    /**
     * Plugs joystick values into tankDrive
     * @param leftSpeed left joystick value
     * @param rightSpeed right joystick value
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	drivetrain.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void arcadeDrive(double speed, double angle) {
    	drivetrain.arcadeDrive(speed, angle);
    }
    
    /**
	 * Gets the singular instance of the AHRS class
	 * @return the NavX instance
	 */
    public AHRS getNavX() {
    	return ahrs;
    }
}

