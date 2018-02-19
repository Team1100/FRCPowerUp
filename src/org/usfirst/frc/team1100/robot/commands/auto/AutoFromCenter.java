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
 * Auto sequence to run command steps when starting from the center position.
 */
public class AutoFromCenter extends CommandGroup {

	/// TODO We need to figure out the wrist angles
	final double kLoadWristAngle = -20;
	final double kSwitchWristAngle = 0;
	final double kForwardScaleWristAngle = 45;
	final double kReverseScaleWristAngle = 120;
	
	private int currentSide;
	
    public AutoFromCenter(double defaultSpeed, int switchPosition, int scalePosition) {
    	
    	// Do stuff if we are in the center position
    	// If we are in the center, we just chase the switch
    	currentSide = switchPosition;
    	// Drive forward 1 foot to clear the wall
    	addSequential(new DriveStraight(1, 0.5, 0));
    	// While driving, set the height of the climber
    	addParallel(new ClimbToTop());
    	// While driving, set the wrist angle for the switch
    	addParallel(new PIDWrist(kSwitchWristAngle));
    	if (currentSide == Robot.LEFT_SIDE) // The center robot cannot be centered on the field
    	{
    		// Turn toward switch
    		addSequential(new ChangeHeading(-25));
    		// Drive close to switch
    		addSequential(new DriveStraight(15, defaultSpeed, currentSide * -20));
    	} else {
    		// Turn toward switch
    		addSequential(new ChangeHeading(20));
    		// Drive close to switch
    		addSequential(new DriveStraight(13, defaultSpeed, currentSide * -20));
    	}
    	// Drive up to the switch
    	addSequential(new DriveStraight(0.25, 0.2, 0));
    	// STOP!!!
    	addSequential(new DriveStop());
    	// Shoot the cube
    	addSequential(new ShootCubeOut());
    	// Whilie driving, bring the claw down to load height
    	addParallel(new ClimbToBottom());
    	// Next, drive around to load a cube and target the scale
    	// Back up from switch
    	addSequential(new DriveStraight(1, -0.25, 0));
    	// Turn to drive around switch
    	addSequential(new ChangeHeading(currentSide * -90));
    	// Drive past switch
    	addSequential(new DriveStraight(6, defaultSpeed, currentSide * -90)); 
    	// Driving turn toward scale
    	addSequential(new DriveStraight(6, defaultSpeed, 0));
    	// Is this our scale?
    	if (currentSide != scalePosition)
    	{
    		// Turn toward other side of field
    		addSequential(new ChangeHeading(currentSide * 90));
    		// Drive across field
    		addSequential(new DriveStraight(16, defaultSpeed, currentSide * 90));
    		// Flip sides
    		currentSide = -currentSide;
    	}
    }
}
