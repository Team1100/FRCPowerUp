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
public class RightStartRightScale extends CommandGroup {

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
    public RightStartRightScale(double defaultSpeed) {
    	addSequential(new DriveStraight(15, -defaultSpeed, 0));
    	addSequential(new DriveStraight(1, -.8, 0));
    	addSequential(new DriveStraight(1, -.7, 0));
    	
    	addParallel(new PIDClimber(.4));
    	addSequential(new DriveStraight(1, -.6, 0));
    	
    	addParallel(new ChangeHeading(-40, .6));
    	addSequential(new ClimbToTop());
    	
    	addSequential(new DriveStraight(2.75, -.5, -40));
    //addParallel(new PIDWrist(kForwardScaleWristAngle));
    	addSequential(new ShootCubeOut());
    	addSequential(new DriveStop());
    	addSequential(new DriveStraight(2.75, .5, -40));
    	addParallel(new ClimbToBottom());
    	addSequential(new ChangeHeading(0, 1));
    	
    }
}