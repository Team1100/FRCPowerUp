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
	
	private static Drive drive;
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
	
	public static Drive getInstance() {
		if (drive == null) drive = new Drive();
		return drive;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new UserDrive());
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	drivetrain.tankDrive(leftSpeed, rightSpeed);
    }
}

