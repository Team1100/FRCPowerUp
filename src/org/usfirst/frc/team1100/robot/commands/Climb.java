package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.commands.elevator.ElevateToTop;
import org.usfirst.frc.team1100.robot.commands.pneumaticelevator.PneumaticElevate;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command allows the robot to climb. Robot should be on the bar already.
 */
public class Climb extends CommandGroup {
	
	/**
	 * Begins climbing with main climbing, waits, begins folding
	 */
    public Climb() {
        addParallel(new ElevateToTop());
        addSequential(new Wait(1));
        addSequential(new PneumaticElevate());
    }
}
