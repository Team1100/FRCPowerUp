package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StateMachineAuto extends CommandGroup {

	final int LEFT_SIDE = 1;
	final int CENTERED = 0;
	final int RIGHT_SIDE = -1;
	
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
            addSequential(new DriveStraight(1, 0.5, 0)); // drive forward 1 foot to clear the wall
        	currentSide = switchPosition; // If we are in the center, we just chase the switch
            if (currentSide == LEFT_SIDE) // The center robot cannot be centered on the field
            {
                addSequential(new ChangeHeading(-25)); // turn to switch
                addSequential(new DriveStraight(15, 1, currentSide * -20)); // drive near switch
            } else {
                addSequential(new ChangeHeading(20)); // turn to switch
                addSequential(new DriveStraight(13, 1, currentSide * -20)); // drive near switch
            }
            //addSequential(new PrepareCubeForSwitch());
        	addSequential(new DriveStraight(0.25, 0.2, 0)); // turn into switch
        	addSequential(new DriveStop()); // STOP!!!
        	//addSequential(new DropCubeInSwitch());
            hasCube = false;
            // Next, drive around to load a cube and target the scale
            addSequential(new DriveStraight(1, -0.25, 0)); // back up from switch
            addSequential(new ChangeHeading(currentSide * -90)); // prepare to drive around switch
            addSequential(new DriveStraight(6, 1, currentSide * -90)); // drive past switch
            addSequential(new DriveStraight(6, 1, 0)); // fast turn toward scale
            if (currentSide != scalePosition)
            {
                addSequential(new ChangeHeading(currentSide * 90)); // turn toward other side
                addSequential(new DriveStraight(16, 1, currentSide * 90)); // drive across field
                currentSide = -currentSide; // flip sides
            }
        }
        else {
            currentSide = initPosition;
            // We're driving for the scale, nevermind the switch
        	addSequential(new DriveStraight(20, 1, 0)); // drive to first way-point
            if (scalePosition == (currentSide*RIGHT_SIDE))
            {
                addSequential(new ChangeHeading(currentSide * 90)); // turn to other side
                addSequential(new DriveStraight(16, 1, currentSide * 90)); // drive to other side
                addSequential(new ChangeHeading(0)); // turn to farther scale
                currentSide = -currentSide; // flip sides
            }
            addSequential(new DriveStraight(7, 1, 0)); // drive to scale
            addSequential(new ChangeHeading(currentSide * 90)); // turn to scale
            addSequential(new DriveStraight(2, 0.5, currentSide * 90));
            addSequential(new DriveStop()); // STOP!!
        	//addSequential(new DropCubeInScale());
            addSequential(new ChangeHeading(180)); // turn back toward platform zone
            addSequential(new DriveStraight(7, 1, 180)); // drive back toward platform zone
        }
        addSequential(new ChangeHeading(currentSide * 120)); // turn in toward cubes
        // addSequential(new VisualHuntForCube()); // go get a cube
    }
}
