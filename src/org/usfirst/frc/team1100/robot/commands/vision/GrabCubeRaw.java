package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.commands.Wait;
import org.usfirst.frc.team1100.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team1100.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team1100.robot.commands.drive.DriveForward;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.intake.PullCubeIn;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrabCubeRaw extends CommandGroup {

    public GrabCubeRaw() {
    	addSequential(new OpenClaw());
        addSequential(new CenterOnCube());
        addParallel(new IntakeUntilIn());
        addSequential(new DriveUntilIn());
        addSequential(new DriveForward(.5, .6));
        addSequential(new CloseClaw());
        addSequential(new PullCubeIn(1));
    }
}
