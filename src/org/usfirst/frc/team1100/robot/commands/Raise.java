package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.commands.elevator.ElevateToTop;
import org.usfirst.frc.team1100.robot.commands.pneumaticelevator.PneumaticElevate;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Raise extends CommandGroup {

    public Raise() {
        addParallel(new PneumaticElevate());
        addParallel(new ElevateToTop());
    }
}
