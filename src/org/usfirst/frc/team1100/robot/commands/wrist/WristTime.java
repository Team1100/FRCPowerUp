package org.usfirst.frc.team1100.robot.commands.wrist;

import org.usfirst.frc.team1100.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WristTime extends Command {
	Timer t;
	double time;
    public WristTime(double time) {
        requires(Wrist.getInstance());
        this.time = time;
        t = new Timer();
    }

    protected void initialize() {
    	t.start();
    }
    
    protected void execute() {
    	Wrist.getInstance().rotateWrist(-1);
    }

    protected boolean isFinished() {
        return t.get() > time;
    }

    protected void end() {
    	Wrist.getInstance().rotateWrist(0);
    }

    protected void interrupted() {
    }
}
