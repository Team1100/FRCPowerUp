package org.usfirst.frc.team1100.robot.commands.wrist;

import org.usfirst.frc.team1100.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseWithTime extends Command {
	Timer t;
    public RaiseWithTime() {
        requires(Wrist.getInstance());
        t = new Timer();
    }

    protected void initialize() {
    	t.start();
    }

    protected void execute() {
    	Wrist.getInstance().rotateWrist(-.7);
    }

    protected boolean isFinished() {
        return t.get() > 2;
    }

    protected void end() {
    	Wrist.getInstance().rotateWrist(0);
    }

    protected void interrupted() {
    	Wrist.getInstance().rotateWrist(0);
    }
}
