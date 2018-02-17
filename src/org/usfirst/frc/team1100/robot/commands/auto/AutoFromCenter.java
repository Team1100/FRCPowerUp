package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.intake.ShootCubeOut;
import org.usfirst.frc.team1100.robot.commands.wrist.RotateWrist;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStop;
import org.usfirst.frc.team1100.robot.commands.climber.PIDClimber;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoFromCenter extends CommandGroup {

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
	
    public AutoFromCenter(int switchPosition, int scalePosition) {
    	
    	// Do stuff if we are in the center position
    	// Drive forward 1 foot to clear the wall
    	// If we are in the center, we just chase the switch
    	currentSide = switchPosition;
    	addSequential(new DriveStraight(1, 0.5, 0));
    	// While driving, set the height of the climber
    	addParallel(new PIDClimber(kSwitchHeight));
    	// While driving, set the wrist angle for the switch
    	addParallel(new RotateWrist(kSwitchWristAngle));
    	if (currentSide == LEFT_SIDE) // The center robot cannot be centered on the field
    	{
    		// Turn toward switch
    		addSequential(new ChangeHeading(-25));
    		// Drive close to switch
    		addSequential(new DriveStraight(15, 1, currentSide * -20));
    	} else {
    		// Turn toward switch
    		addSequential(new ChangeHeading(20));
    		// Drive close to switch
    		addSequential(new DriveStraight(13, 1, currentSide * -20));
    	}
    	// Drive up to the switch
    	addSequential(new DriveStraight(0.25, 0.2, 0));
    	// STOP!!!
    	addSequential(new DriveStop());
    	// Shoot the cube
    	addSequential(new ShootCubeOut());
    	// Whilie driving, bring the claw down to load height
    	addParallel(new PIDClimber(kLoadHeight));
    	// Next, drive around to load a cube and target the scale
    	// Back up from switch
    	addSequential(new DriveStraight(1, -0.25, 0));
    	// Turn to drive around switch
    	addSequential(new ChangeHeading(currentSide * -90));
    	// Drive past switch
    	addSequential(new DriveStraight(6, 1, currentSide * -90)); 
    	// Driving turn toward scale
    	addSequential(new DriveStraight(6, 1, 0));
    	// Is this our scale?
    	if (currentSide != scalePosition)
    	{
    		// Turn toward other side of field
    		addSequential(new ChangeHeading(currentSide * 90));
    		// Drive across field
    		addSequential(new DriveStraight(16, 1, currentSide * 90));
    		// Flip sides
    		currentSide = -currentSide;
    	}
    }
}
