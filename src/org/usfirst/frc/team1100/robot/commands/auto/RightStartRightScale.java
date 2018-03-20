package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.commands.Wait;
import org.usfirst.frc.team1100.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team1100.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeadingWhileUp;
import org.usfirst.frc.team1100.robot.commands.drive.DriveForward;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.drive.TimedForwards;
import org.usfirst.frc.team1100.robot.commands.elevator.ElevateToBottom;
import org.usfirst.frc.team1100.robot.commands.elevator.ElevateToTop;
import org.usfirst.frc.team1100.robot.commands.intake.PullCubeIn;
import org.usfirst.frc.team1100.robot.commands.intake.ShootCubeOut;
import org.usfirst.frc.team1100.robot.commands.pneumaticelevator.PneumaticElevate;
import org.usfirst.frc.team1100.robot.commands.pneumaticelevator.PneumaticLower;
import org.usfirst.frc.team1100.robot.commands.vision.CenterOnCube;
import org.usfirst.frc.team1100.robot.commands.vision.GrabCube;
import org.usfirst.frc.team1100.robot.commands.vision.GrabCubeRaw;
import org.usfirst.frc.team1100.robot.commands.wrist.LowerWrist;
import org.usfirst.frc.team1100.robot.commands.wrist.RaiseWrist;
import org.usfirst.frc.team1100.robot.commands.wrist.WristTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Auto sequence to run command steps when starting from the left or right position.
 */

public class RightStartRightScale extends CommandGroup {
	private int currentSide;
	/**
	 * Start with center of robot 2 feet from corner, and backwards.
	 * <p>
	 * This auto will start on the right, and drive to the right scale,
	 * deposit a cube into that scale, then turn around and grab the first cube it sees.
	 * 
	 * @param defaultSpeed the speed which the robot will mostly move
	 */
    public RightStartRightScale(double defaultSpeed, int switchPosition) {
    	currentSide = switchPosition;
    	//Drive to scale, prep for depositing cube
    	addParallel(new RaiseWrist());
    	addParallel(new ElevateToTop());
    	addSequential(new DriveStraight(17, -defaultSpeed, 0));
    	addParallel(new PneumaticElevate());
    	addSequential(new DriveStraight(1, -.8, 0));
    	addSequential(new DriveStraight(1, -.7, 0));
    	addSequential(new DriveStraight(1, -.6, 0));
    	
    	//Turn to scale, drive up to it
    	addSequential(new ChangeHeadingWhileUp(-40, 1));
    	addSequential(new DriveStraight(1.5, -.5, -40));
    	
    	//Shoot cube into scale, back up, lower elevator/elevator
    	addSequential(new ShootCubeOut(.9, .9));
    	addParallel(new ElevateToBottom());
    	addParallel(new PneumaticLower());
    	addSequential(new DriveStraight(1, .6, -40));
    	
    	//Turn to approximate location of a cube, get that cube
    	addParallel(new ChangeHeading(14, 1));
    	addSequential(new LowerWrist());
    	addSequential(new OpenClaw());
        addSequential(new CenterOnCube(3));
    	// only use time no vision!
    	addParallel(new DriveStraight(5.3, .8, 17));
        addSequential(new PullCubeIn(1));
        addSequential(new CloseClaw());
        addSequential(new PullCubeIn(1));
        addSequential(new DriveStraight(.5, -.8, 17));
        addSequential(new WristTime(1.3));
        addSequential(new DriveStraight(.5, 1, 17));
        addSequential(new ShootCubeOut(1, .4));
        addSequential(new DriveStraight(1, -1, 17));
        
        /*
    	if (currentSide == Robot.RIGHT_SIDE) {
        	addSequential(new WristTime(1.5));
        	addSequential(new ShootCubeOut(.9, .9));
    	}
    	*/
    }
}
