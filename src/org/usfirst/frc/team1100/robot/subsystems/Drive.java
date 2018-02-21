package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.drive.DefaultDrive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * This subsystem is the two sides of the robot
 */
public class Drive extends Subsystem {

	final double PERCENT_SPEED = .8;
	private static Drive drive;
	private static DifferentialDrive drivetrain;
	Encoder encoder;
	final double PULSE_PER_FOOT = 4090;
	private VictorSP toad, mario, peach, luigi;
	private AHRS ahrs;
	
	/*
	 * Sets up talons and drivetrain
	 */
	private Drive() {
		//Speed controllers
		toad = new VictorSP(RobotMap.D_TOAD);
		mario = new VictorSP(RobotMap.D_MARIO);
		peach = new VictorSP(RobotMap.D_PEACH);
		luigi = new VictorSP(RobotMap.D_LUIGI);
		SpeedControllerGroup left = new SpeedControllerGroup(toad, mario);
		SpeedControllerGroup right = new SpeedControllerGroup(peach, luigi);
		left.setInverted(true);
		right.setInverted(true);
		
		//Sensors
		ahrs = new AHRS(RobotMap.D_NAVX);
		encoder = new Encoder(RobotMap.D_ENCODER_MARIOTOAD_A, RobotMap.D_ENCODER_MARIOTOAD_B);
        encoder.setDistancePerPulse(1/PULSE_PER_FOOT);
        
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
    
    public Encoder getEncoder() {
    	return encoder;
    }
    
    /**
	 * Sets the default command to userDrive
	 */
    public void initDefaultCommand() {
        setDefaultCommand(new DefaultDrive());
    }
}

