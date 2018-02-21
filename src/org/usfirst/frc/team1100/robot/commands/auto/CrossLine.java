package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.Wait;
import org.usfirst.frc.team1100.robot.commands.drive.TimedForwards;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command will satisfy the ranking point
 */
public class CrossLine extends CommandGroup {
	/**
	 * Wait 3 seconds, then cross the line
	 */
    public CrossLine() {
    	addSequential(new Wait(3));
    	addSequential(new TimedForwards(4));
    }
}
