package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.Claw;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrabCube extends Command {
	
	Timer t;
	GrabCubeRaw command;
    public GrabCube() {
        requires(Drive.getInstance());
        requires(Intake.getInstance());
        requires(Claw.getInstance());
        t = new Timer();
        command = new GrabCubeRaw();
    }

    protected void initialize() {
    	t.start();
    	command.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return t.get() > 1.5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	command.cancel();
    }

    protected void interrupted() {
    	command.cancel();
    }
}
