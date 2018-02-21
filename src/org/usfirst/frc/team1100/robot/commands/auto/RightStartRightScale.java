package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeadingWhileUp;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.intake.ShootCubeOut;
import org.usfirst.frc.team1100.robot.commands.pneumaticclimber.PneumaticClimb;
import org.usfirst.frc.team1100.robot.commands.vision.GrabCube;
import org.usfirst.frc.team1100.robot.commands.wrist.LowerWrist;
import org.usfirst.frc.team1100.robot.commands.wrist.RaiseWrist;
import org.usfirst.frc.team1100.robot.commands.climber.ClimbToBottom;
import org.usfirst.frc.team1100.robot.commands.climber.ClimbToTop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Auto sequence to run command steps when starting from the left or right position.
 */
public class RightStartRightScale extends CommandGroup {

	/**
	 * Start with center of robot 2 feet from corner, and backwards.
	 * <p>
	 * This auto will start on the right, and drive to the right scale,
	 * deposit a cube into that scale, then turn around and grab the first cube it sees.
	 * 
	 * @param defaultSpeed the speed which the robot will mostly move
	 */
    public RightStartRightScale(double defaultSpeed) {
    	//Drive to scale, prep for depositing cube
    	addParallel(new RaiseWrist());
    	addParallel(new ClimbToTop());
    	addSequential(new DriveStraight(17, -defaultSpeed, 0));
    //addParallel(new PneumaticClimb());
    	addSequential(new DriveStraight(1, -.8, 0));
    	addSequential(new DriveStraight(1, -.7, 0));
    	addSequential(new DriveStraight(1, -.6, 0));
    	
    	//Turn to scale, drive up to it
    	addSequential(new ChangeHeadingWhileUp(-40, 1));
    	addSequential(new DriveStraight(1.5, -.5, -40));
    	
    	//Shoot cube into scale, back up, lower elevator/climber
    	addSequential(new ShootCubeOut(1));
    	addParallel(new ClimbToBottom());
    	addSequential(new DriveStraight(.75, .6, -40));
    	
    	//Turn to approximate location of a cube, get that cube
    	addSequential(new ChangeHeadingWhileUp(35, 1));
    	addSequential(new LowerWrist());
    	addSequential(new GrabCube());
    }
}
