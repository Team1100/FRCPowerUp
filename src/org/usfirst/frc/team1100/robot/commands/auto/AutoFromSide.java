package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.intake.ShootCubeOut;
import org.usfirst.frc.team1100.robot.commands.wrist.RotateWrist;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStop;
import org.usfirst.frc.team1100.robot.commands.climber.PIDClimber;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Auto sequence to run command steps when starting from the left or right position.
 */
public class AutoFromSide extends CommandGroup {

	final int LEFT_SIDE = 1;
	final int RIGHT_SIDE = -1;
		
	/// TODO We need to figure out the heights
	final double kLoadHeight = 0.0;
	final double kSwitchHeight = 0.25;
	final double kScaleHeight = 1.0;
	
	/// TODO We need to figure out the wrist angles
	final double kLoadWristAngle = -20;
	final double kSwitchWristAngle = 0;
	final double kForwardScaleWristAngle = 45;
	final double kReverseScaleWristAngle = 120;
	
	private int currentSide;
	
    public AutoFromSide(double defaultSpeed, int initPosition, int switchPosition, int scalePosition) {
    	// Do stuff if we are in the left or right position
    	// We're driving for the scale, never-mind the switch
    	currentSide = initPosition;
    	// Start driving for the scale
    	addSequential(new DriveStraight(20, 1, 0));
    	// Is this our scale?
    	if (scalePosition == (currentSide*RIGHT_SIDE))
    	{
    		// Turn toward other side of field
    		addSequential(new ChangeHeading(currentSide * 90));
    		// Drive across field
    		addSequential(new DriveStraight(16, defaultSpeed, currentSide * 90));
    		// Turn to the farther scale
    		addSequential(new ChangeHeading(0));
    		// Flip sides
    		currentSide = -currentSide;
    	}
    	// Drive to scale
    	addSequential(new DriveStraight(7, defaultSpeed, 0));
    	// Turn to scale
    	addSequential(new ChangeHeading(currentSide * 90));
    	// While driving set claw height for scale
    	addParallel(new PIDClimber(kScaleHeight));
    	// While driving set wrist angle
    	addParallel(new RotateWrist(kForwardScaleWristAngle));
    	// Drive up to scale
    	addSequential(new DriveStraight(2, 0.5, currentSide * 90));
    	// STOP!!
    	addSequential(new DriveStop());
    	// Shoot the cube
    	addSequential(new ShootCubeOut());
    	// While driving, set the claw to load height
    	addParallel(new PIDClimber(kLoadHeight));
    	// Turn back toward platform zone
    	addSequential(new ChangeHeading(180));
    	// Drive back toward platform zone
    	addSequential(new DriveStraight(7, defaultSpeed, 180));
    }
}
