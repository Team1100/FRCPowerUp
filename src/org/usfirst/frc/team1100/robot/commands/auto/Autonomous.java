package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStop;
import org.usfirst.frc.team1100.robot.commands.auto.AutoFromCenter;
import org.usfirst.frc.team1100.robot.commands.auto.AutoFromSide;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Top level autonomous entry point
 */
public class Autonomous extends CommandGroup {

	final int LEFT_SIDE = 1;
	final int CENTERED = 0;
	final int RIGHT_SIDE = -1;
	
	final double DEFAULT_SPEED = 0.75;

	private int currentSide;
	
    public Autonomous(int initPosition, int switchPosition, int scalePosition) {
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
        	addSequential(new AutoFromCenter(DEFAULT_SPEED, switchPosition, scalePosition));
        }
        else {
       		// Do stuff if we are in the left or right position
        	addSequential(new AutoFromSide(DEFAULT_SPEED, initPosition, switchPosition, scalePosition));
        }
        // Turn in toward cubes
        addSequential(new ChangeHeading(currentSide * 120));
        // Go get a cube
        // addSequential(new VisionHuntForCube());
    }
}
