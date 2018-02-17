package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStop;
import org.usfirst.frc.team1100.robot.commands.climber.PIDClimber;
import org.usfirst.frc.team1100.robot.commands.claw.ShootCubeOut;
import org.usfirst.frc.team1100.robot.commands.claw.RotateWrist;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StateMachineAuto extends CommandGroup {

	final int LEFT_SIDE = 1;
	final int CENTERED = 0;
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
    private boolean hasCube = true;
	
    public StateMachineAuto(int initPosition, int switchPosition, int scalePosition) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
        if (initPosition == CENTERED) {
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
        else {
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
                addSequential(new DriveStraight(16, 1, currentSide * 90));
                // Turn to the farther scale
                addSequential(new ChangeHeading(0));
                // Flip sides
                currentSide = -currentSide;
            }
            // Drive to scale
            addSequential(new DriveStraight(7, 1, 0));
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
            addSequential(new DriveStraight(7, 1, 180));
        }
        // Turn in toward cubes
        addSequential(new ChangeHeading(currentSide * 120));
        // Go get a cube
        // addSequential(new VisionHuntForCube());
    }
}
