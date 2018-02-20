package org.usfirst.frc.team1100.robot.commands.wrist;

import org.usfirst.frc.team1100.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseWrist extends Command {
	boolean done = false;
    public RaiseWrist() {
        requires(Wrist.getInstance());
    }

    protected void initialize() {
    }

    protected void execute() {
    	done = !Wrist.getInstance().rotateWrist(-1);
    }

    protected boolean isFinished() {
        return done;
    }

    protected void end() {
    	Wrist.getInstance().rotateWrist(0);
    }

    protected void interrupted() {
    	Wrist.getInstance().rotateWrist(0);
    }
}
