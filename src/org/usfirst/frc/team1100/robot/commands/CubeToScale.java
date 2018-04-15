package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.commands.elevator.ElevateToTop;
import org.usfirst.frc.team1100.robot.commands.intake.PullCubeIn;
import org.usfirst.frc.team1100.robot.commands.wrist.WristTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Lift elevator, pull in cube a little, move wrist to top
 */
public class CubeToScale extends CommandGroup {
	
	/**
	 * Move wrist to top, pull cube in, elevate to top
	 */
    public CubeToScale() {
        addParallel(new WristTime(3));
        addParallel(new PullCubeIn(1));
        addSequential(new ElevateToTop());
    }
}
