package org.usfirst.frc.team1100.robot.commands.auto;
 
import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeadingWhileUp;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.intake.ShootCubeOut;
import org.usfirst.frc.team1100.robot.commands.vision.GrabCube;
import org.usfirst.frc.team1100.robot.commands.wrist.LowerWithTime;
import org.usfirst.frc.team1100.robot.commands.wrist.PIDWrist;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStop;
import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team1100.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team1100.robot.commands.climber.ClimbToBottom;
import org.usfirst.frc.team1100.robot.commands.climber.ClimbToTop;
import org.usfirst.frc.team1100.robot.commands.climber.PIDClimber;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Auto sequence to run command steps when starting from the center position.
 */
public class AutoFromCenter extends CommandGroup {

	/// TODO We need to figure out the wrist angles
	final double kLoadWristAngle = -20;
	final double kSwitchWristAngle = 0;
	final double kForwardScaleWristAngle = 45;
	final double kReverseScaleWristAngle = 120;
	
	private int currentSide;
	/**
	 * Start with center of robot 2 feet to the right from the exchange zone, and BACKWARDS.
	 * This auto puts cube in switch prepares to load next cube into robot.
	 * 
	 * @param defaultSpeed
	 * @param switchPosition
	 * @param scalePosition
	 */
    public AutoFromCenter(double defaultSpeed, int switchPosition, int scalePosition) {
    	
    	// Do stuff if we are in the center position
    	// If we are in the center, we just chase the switch
    	currentSide = switchPosition;
    	// Drive forward 1 foot to clear the wall
    	addSequential(new DriveStraight(1, -0.75, 0));
    	// While driving, set the wrist angle for the switch
    //addParallel(new PIDWrist(kSwitchWristAngle));
    	if (currentSide == Robot.LEFT_SIDE) // The center robot cannot be centered on the field
    	{
    		// Turn toward switch
    		addSequential(new ChangeHeading(-62.5, .9));
    		// Drive close to switch
    		addSequential(new DriveStraight(7.2, -defaultSpeed, -62.5));
    	} else {
    		// Turn toward switch
    		addSequential(new ChangeHeading(45, .9));
    		// Drive close to switch
    		addSequential(new DriveStraight(4, -defaultSpeed, 45));
    	}
    	addSequential(new ChangeHeading(0, .9));
    	// Drive up to the switch
    	addSequential(new DriveStraight(2.9, -.75, 0));
    	// STOP!!!
    	addSequential(new DriveStop());
    	// Shoot the cube
    	addSequential(new ShootCubeOut());
    	// Next, drive around to load a cube and target the scale
    	// Back up from switch
    	addSequential(new DriveStraight(1, 0.5, 0));
    	// Turn to drive around switch
    	addSequential(new ChangeHeading(currentSide * -90, .9));
    	// Drive past switch
    	addSequential(new DriveStraight(4, -defaultSpeed, currentSide * -90)); 
    	// Turn toward scale
    	addSequential(new ChangeHeading(0, .9));
    	// Drive toward scale
    	addSequential(new DriveStraight(10, -defaultSpeed, 0));
    	// Is this our scale?
    	if (currentSide != scalePosition)
    	{
    		// Turn toward other side of field
    		addSequential(new ChangeHeading(currentSide * 90, .9));
    		// Drive across field
    		addSequential(new DriveStraight(18, -defaultSpeed, currentSide * 90));
    		// Flip sides
    		currentSide = -currentSide;
    	}
    	addParallel(new CloseClaw());
    	addSequential(new ChangeHeadingWhileUp(35*-currentSide, 1));
    	addSequential(new LowerWithTime());
    	addSequential(new GrabCube());
    }
}
