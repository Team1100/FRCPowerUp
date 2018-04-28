package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.commands.elevator.PIDElevator;
import org.usfirst.frc.team1100.robot.commands.pneumaticelevator.PneumaticElevate;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDRaise extends CommandGroup {

    public PIDRaise() {
        addParallel(new PneumaticElevate());
        addParallel(new PIDElevator(.65));
    }
}
