package org.usfirst.frc.team1100.robot.commands.auto;
 
import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.intake.PullCubeIn;
import org.usfirst.frc.team1100.robot.commands.intake.ShootCubeOut;
import org.usfirst.frc.team1100.robot.commands.wrist.LowerWrist;
import org.usfirst.frc.team1100.robot.commands.wrist.RaiseWrist;
import org.usfirst.frc.team1100.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team1100.robot.commands.claw.OpenClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Auto sequence to run command steps when starting from the center position.
 */
public class CenterStartLeftSwitch extends CommandGroup {
	
	/**
	 * Start with center of robot 2 feet to the right from the exchange zone, and BACKWARDS.
	 * This auto puts cube in switch prepares to load next cube into robot.
	 * 
	 * @param defaultSpeed the default speed for most of the driving being done in this command
	 * @param switchPosition the position (1 or -1) of the switch 
	 * @param scalePosition (1 or -1) of the scale
	 */
    public CenterStartLeftSwitch(double defaultSpeed, int switchPosition, int scalePosition) {
    	
    	// Do stuff if we are in the center position
    	// If we are in the center, we just chase the switch
    	
    	// Drive forward 1 foot to clear the wall
    	addSequential(new DriveStraight(1, -0.75, 0));
    	// While driving, set the wrist angle for the switch
    	addParallel(new RaiseWrist());
		// Turn toward switch
		addSequential(new ChangeHeading(-60, .9));
		// Drive close to switch
		addSequential(new DriveStraight(6.5, -defaultSpeed, -60));
		// Turn toward switch
    	addSequential(new ChangeHeading(0, .9));
    	// Drive up to the switch
    	addSequential(new DriveStraight(2.5, -.75, 0));
    	// Shoot the cube
    	addSequential(new ShootCubeOut(.75, 1));
    	
    	/* SECOND CUBE */
    	
    	// Reset
    	// Lower wrist
    	addParallel(new LowerWrist());
    	// Back up from switch
    	addSequential(new DriveStraight(2.5, .75, 0));
    	// Turn away from switch
    	addSequential(new ChangeHeading(-60, .9));
    	// Drive further from switch
    	addSequential(new DriveStraight(6.5, defaultSpeed, -60));
    	
    	// Grab new cube
    	// Spin around
    	addSequential(new ChangeHeading(168.4, .9));
    	// Open claw
    	addParallel(new OpenClaw());
    	// Start driving to first cube in pyramid, because wrist is lowering
    	addSequential(new DriveStraight(3.5, .8, 168.4));
    	// Finish driving forward
    	addSequential(new DriveStraight(2, .8, 168.4));
    	// Close claw
    	addParallel(new CloseClaw());
    	// Roll intake wheels
    	addSequential(new PullCubeIn(.5));
    	// Raise wrist
    	addParallel(new RaiseWrist());
    	// Drive back to point of reset
    	addSequential(new DriveStraight(5.5, -.8, 168.4));
    	
    	// Spit out second cube
    	// Turn toward switch
		addSequential(new ChangeHeading(-60, .9));
		// Drive close to switch
		addSequential(new DriveStraight(6.5, -defaultSpeed, -60));
    	addSequential(new ChangeHeading(0, .9));
    	// Drive up to the switch
    	addSequential(new DriveStraight(2.5, -.75, 0));
    	// Shoot the cube
    	addSequential(new ShootCubeOut(.75, 1));

    }
}
