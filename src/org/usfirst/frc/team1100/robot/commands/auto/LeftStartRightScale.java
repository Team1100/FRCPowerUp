package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeadingWhileUp;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.elevator.ElevateToBottom;
import org.usfirst.frc.team1100.robot.commands.elevator.ElevateToTop;
import org.usfirst.frc.team1100.robot.commands.intake.ShootCubeOut;
import org.usfirst.frc.team1100.robot.commands.pneumaticelevator.PneumaticElevate;
import org.usfirst.frc.team1100.robot.commands.pneumaticelevator.PneumaticLower;
import org.usfirst.frc.team1100.robot.commands.wrist.RaiseWrist;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Auto sequence to run command steps when starting from the left or right position.
 */
public class LeftStartRightScale extends CommandGroup {

	/**
	 * Start with center of robot 2 feet from corner, and backwards.
	 * <p>
	 * This auto will start on the left, and drive to the right scale,
	 * deposit a cube into that scale.
	 * 
	 * @param defaultSpeed the speed which the robot will mostly move
	 */
    public LeftStartRightScale(double defaultSpeed) {
    	//Drive down, then across field to scale
    	addParallel(new RaiseWrist());
    	addSequential(new DriveStraight(16.5, -defaultSpeed, 0));
		addSequential(new ChangeHeading(90, .9));
		addParallel(new ElevateToTop());
	    addParallel(new PneumaticElevate());
		addSequential(new DriveStraight(5, -defaultSpeed, 90));
		addSequential(new DriveStraight(2, -.75, 90));
		addSequential(new DriveStraight(2, -.6, 90)); //There's a significant bump around here, so we slow down.
		addSequential(new DriveStraight(5, -.8, 90));
		addSequential(new DriveStraight(3.5, -.7, 90));
    	
    	//Turn and drive to the scale
    	addSequential(new ChangeHeadingWhileUp(-22.5, .8));
    	addSequential(new DriveStraight(5, -.65, -22.5));

    	// Shoot the cube into scale, back up
    	addSequential(new ShootCubeOut(2, .6));
    	addParallel(new ElevateToBottom());
    	addParallel(new PneumaticLower());
    	addSequential(new DriveStraight(3.5, .65, -22.5));
    	
    	// Turn back toward platform zone
    	addSequential(new ChangeHeading(0, 1));
    }
}
