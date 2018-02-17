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

	final double PERCENT_SPEED = .8;
	/**
	 * The singular instance of the Drive subsystem
	 */
	private static Drive drive;
	/**
	 * The drivetrain instance
	 */
	private static DifferentialDrive drivetrain;
	
	private VictorSP wario, toad, mario, peach, luigi, waluigi;
	private AHRS ahrs;
	
	/**
	 * Sets up talons and drivetrain
	 */
	private Drive() {
		toad = new VictorSP(RobotMap.D_TOAD);
		mario = new VictorSP(RobotMap.D_MARIO);
		peach = new VictorSP(RobotMap.D_PEACH);
		luigi = new VictorSP(RobotMap.D_LUIGI);
		ahrs = new AHRS(RobotMap.D_NAVX);
		SpeedControllerGroup left = new SpeedControllerGroup(toad, mario);
		SpeedControllerGroup right = new SpeedControllerGroup(peach, luigi);
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
    	drivetrain.tankDrive(leftSpeed*PERCENT_SPEED, rightSpeed*PERCENT_SPEED);
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

