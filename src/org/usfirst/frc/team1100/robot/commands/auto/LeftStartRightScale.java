package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.intake.ShootCubeOut;
import org.usfirst.frc.team1100.robot.commands.wrist.PIDWrist;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStop;
import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.commands.climber.ClimbToBottom;
import org.usfirst.frc.team1100.robot.commands.climber.ClimbToTop;
import org.usfirst.frc.team1100.robot.commands.climber.PIDClimber;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Auto sequence to run command steps when starting from the left or right position.
 */
public class LeftStartRightScale extends CommandGroup {

	/// TODO We need to figure out the wrist angles
	final double kLoadWristAngle = -20;
	final double kSwitchWristAngle = 0;
	final double kForwardScaleWristAngle = 45;
	final double kReverseScaleWristAngle = 120;
	
	private int currentSide;
	
	/**
	 * Start with center of robot 2 feet from corner, and backwards. This auto will drive to scale,
	 * 
	 * @param defaultSpeed
	 * @param initPosition
	 * @param switchPosition
	 * @param scalePosition
	 */
    public LeftStartRightScale(double defaultSpeed) {
    	int initPosition = 1;
    	int scalePosition = -1;
    	// Do stuff if we are in the left or right position
    	// We're driving for the scale, never-mind the switch
    	currentSide = initPosition;
    	// Start driving for the scale
    	addSequential(new DriveStraight(17, -defaultSpeed, 0));

		// Turn toward other side of field
		addSequential(new ChangeHeading(currentSide * 90, .9));
		// Drive across field
		addSequential(new DriveStraight(12, -defaultSpeed, currentSide * 90));
		addParallel(new PIDClimber(.5));
		addSequential(new DriveStraight(4, -.6, currentSide * 90));
		// Flip sides
		currentSide = -currentSide;
    	
    	// Turn to the scale
    	addParallel(new ChangeHeading(currentSide*35, .6));
    	addSequential(new ClimbToTop());
    	// Drive to scale
    	addSequential(new DriveStraight(3, -.5, currentSide*35));
    	// While driving set wrist angle
    //addParallel(new PIDWrist(kForwardScaleWristAngle));
    	// Shoot the cube
    	addSequential(new ShootCubeOut());
    	addSequential(new DriveStop());
    	addSequential(new DriveStraight(3, .5, currentSide*35));
    	// While driving, set the claw to load height
    	addParallel(new ClimbToBottom());
    	// Turn back toward platform zone
    	addSequential(new ChangeHeading(0, 1));
    }
}
