package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.drive.UserDrive;

import edu.wpi.first.wpilibj.SpeedControllerGroup;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Talon;

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
	
	private Talon leftOne, leftTwo, rightOne, rightTwo;
	
	/**
	 * Sets up talons and drivetrain
	 */
	private Drive() {
		leftOne = new Talon(RobotMap.D_LEFT_ONE);
		leftTwo = new Talon(RobotMap.D_LEFT_TWO);
		rightOne = new Talon(RobotMap.D_RIGHT_ONE);
		rightTwo = new Talon(RobotMap.D_RIGHT_TWO);
		
		//new updated driving class allows tank drive
		drivetrain = new DifferentialDrive(new SpeedControllerGroup(leftOne, leftTwo), new SpeedControllerGroup(rightOne, rightTwo));
		
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
}

