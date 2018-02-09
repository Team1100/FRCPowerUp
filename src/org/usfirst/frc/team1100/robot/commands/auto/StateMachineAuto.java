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
        	currentSide = switchPosition; // If we are in the center, we just chase the switch
        	addSequential(new ChangeHeading(currentSide * -20)); // turn to switch
        	addSequential(new DriveStraight(3, 1, currentSide * -20)); // drive near switch
            //addSequential(new PrepareCubeForSwitch());
        	addSequential(new DriveStraight(0.25, 0.2, 0)); // turn into switch
        	addSequential(new DriveStop()); // STOP!!!
        	//addSequential(new DropCubeInSwitch());
            hasCube = false;
            // Next, drive around to load a cube and target the scale
            addSequential(new DriveStraight(0.25, -0.2, 0)); // back up from switch
            addSequential(new ChangeHeading(currentSide * -90)); // prepare to drive around switch
            addSequential(new DriveStraight(2, 1, currentSide * -90)); // back up from switch

        }
        else {
            currentSide = initPosition;
            // We're driving for the scale, nevermind the switch
            addSequential(new ChangeHeading(currentSide * -10)); // start driving to scale
        	addSequential(new DriveStraight(4, 1, currentSide * -20)); // drive to first way-point
            addSequential(new DriveStraight(2, 1, 0)); // drive to near closer scale
            if (scalePosition == (currentSide*LEFT_SIDE))
            {
                addSequential(new DriveStraight(2, 0.2, 0)); // drive to near scale
            }
            else {
                addSequential(new ChangeHeading(currentSide * 90)); // turn to farther scale
                addSequential(new DriveStraight(4, 1, 0)); // drive to farther scale
                addSequential(new ChangeHeading(0)); // turn to farther scale
                addSequential(new DriveStraight(2, 0.2, 0)); // drive to farther scale
                currentSide = currentSide * -1; // flip sides
            }
        	//addSequential(new DropCubeInScale());
        }
    }
}