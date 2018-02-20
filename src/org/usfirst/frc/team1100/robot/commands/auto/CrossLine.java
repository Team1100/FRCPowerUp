package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLine extends CommandGroup {
    public CrossLine() {
    	addSequential(new DriveStraight(12, .6, 0));
    }
}
