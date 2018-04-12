package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.commands.elevator.ElevateToBottom;
import org.usfirst.frc.team1100.robot.commands.pneumaticelevator.PneumaticLower;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command lowers the elevator and pneumatic elevator simultaneously
 */
public class Lower extends CommandGroup {
	
	/**
	 * Lowers both elevators
	 */
    public Lower() {
       addParallel(new PneumaticLower());
       addSequential(new ElevateToBottom());
    }
}
