package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Square extends CommandGroup {

    public Square() {
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
    	
    	addSequential(new DriveStraight(2, 0.5, 0));
    	addSequential(new ChangeHeading(90));
    	addSequential(new DriveStraight(2, 0.5, 90));
    	addSequential(new ChangeHeading(180));
    	addSequential(new DriveStraight(2, 0.5, 180));
    	addSequential(new ChangeHeading(-90));
    	addSequential(new DriveStraight(2, 0.5, -90));
    	addSequential(new ChangeHeading(0));
    }
}
