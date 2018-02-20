package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.commands.Wait;
import org.usfirst.frc.team1100.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team1100.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team1100.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team1100.robot.commands.intake.PullCubeIn;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrabCube extends CommandGroup {

    public GrabCube() {
        addSequential(new CenterOnCube());
        addSequential(new Wait(.5));
        addSequential(new OpenClaw());
        addParallel(new IntakeUntilIn());
        addSequential(new DriveUntilIn());
        addSequential(new DriveStraight(.6, .6, Drive.getInstance().getNavX().getYaw()));
        addSequential(new Wait(.5));
        addSequential(new CloseClaw());
    }
}
