package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeadingWhileUp;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.intake.ShootCubeOut;
import org.usfirst.frc.team1100.robot.commands.pneumaticclimber.PneumaticClimb;
import org.usfirst.frc.team1100.robot.commands.wrist.RaiseWrist;
import org.usfirst.frc.team1100.robot.commands.climber.ClimbToBottom;
import org.usfirst.frc.team1100.robot.commands.climber.ClimbToTop;

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
    	addSequential(new DriveStraight(17, -defaultSpeed, 0));
		addSequential(new ChangeHeading(90, .9));
		addParallel(new ClimbToTop());
	//TODO: addParallel(new PneumaticClimb());
		addSequential(new DriveStraight(5, -defaultSpeed, 90));
		addSequential(new DriveStraight(2, -.75, 90));
		addSequential(new DriveStraight(2, -.6, 90)); //There's a significant bump around here, so we slow down.
		addSequential(new DriveStraight(5, -.8, 90));
		addSequential(new DriveStraight(2, -.7, 90));
    	
    	//Turn and drive to the scale
    	addSequential(new ChangeHeadingWhileUp(-35, .8));
    	addSequential(new DriveStraight(3, -.65, -35));

    	// Shoot the cube into scale, back up
    	addSequential(new ShootCubeOut(1));
    	addParallel(new ClimbToBottom());
    	addSequential(new DriveStraight(3, .65, -35));
    	
    	// Turn back toward platform zone
    	addSequential(new ChangeHeading(0, 1));
    }
}
