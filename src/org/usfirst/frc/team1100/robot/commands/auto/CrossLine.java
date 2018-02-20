package org.usfirst.frc.team1100.robot.commands.auto;

import org.usfirst.frc.team1100.robot.commands.Wait;
import org.usfirst.frc.team1100.robot.commands.drive.DriveForward;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.drive.TimedForwards;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossLine extends CommandGroup {
    public CrossLine() {
    	addSequential(new Wait(3));
    	addSequential(new TimedForwards(4));
    }
}
