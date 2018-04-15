package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team1100.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team1100.robot.commands.drive.DriveForward;
import org.usfirst.frc.team1100.robot.commands.drive.TimedForwards;
import org.usfirst.frc.team1100.robot.commands.intake.PullCubeIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command looks for a cube and goes and gets it
 */
public class GrabCubeRaw extends CommandGroup {
	
	/**
	 * Drives to cube, grabs it
	 */
    public GrabCubeRaw() {
    	
    	addSequential(new OpenClaw());
        addSequential(new CenterOnCube(3));
        addParallel(new IntakeUntilIn());
        addSequential(new DriveUntilIn());
        addSequential(new DriveForward(.5, .6));
        addSequential(new CloseClaw());
        addSequential(new PullCubeIn(1));
        
    }
}
