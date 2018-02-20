package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeadingWhileUp;
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
public class RightStartLeftScale extends CommandGroup {

	/// TODO We need to figure out the wrist angles
	final double kLoadWristAngle = -20;
	final double kSwitchWristAngle = 0;
	final double kForwardScaleWristAngle = 45;
	final double kReverseScaleWristAngle = 120;
	
	
	/**
	 * Start with center of robot 2 feet from corner, and backwards. This auto will drive to scale,
	 * 
	 * @param defaultSpeed
	 */
    public RightStartLeftScale(double defaultSpeed) {
    	// Do stuff if we are in the left or right position
    	// We're driving for the scale, never-mind the switch
    	// Start driving for the scale
    	addSequential(new DriveStraight(17, -defaultSpeed, 0));

		// Turn toward other side of field
		addSequential(new ChangeHeading(90, .9));
		// Drive across field
		addParallel(new ClimbToTop());
		addSequential(new DriveStraight(5, -defaultSpeed, -90));
		addSequential(new DriveStraight(2, -.75, -90));
		addSequential(new DriveStraight(2, -.6, -90));
		addSequential(new DriveStraight(5, -.8, -90));
		addSequential(new DriveStraight(2, -.7, -90));
    	
    	// Turn to the scale
    	addSequential(new ChangeHeadingWhileUp(35, .8));
    	
    	// Drive to scale
    	addSequential(new DriveStraight(3, -.65, 35));
    	// While driving set wrist angle
    //addParallel(new PIDWrist(kForwardScaleWristAngle));
    	// Shoot the cube
    	addSequential(new ShootCubeOut());
    	addSequential(new DriveStop());
    	addSequential(new DriveStraight(3, .65, 35));
    	// While driving, set the claw to load height
    	addParallel(new ClimbToBottom());
    	// Turn back toward platform zone
    	addSequential(new ChangeHeading(0, 1));
    }
}
