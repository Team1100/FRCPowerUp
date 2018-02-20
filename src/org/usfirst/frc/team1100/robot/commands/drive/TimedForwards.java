package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimedForwards extends Command {
	Timer t;
	Drive drive;
	double time;
    public TimedForwards(double time) {
    	this.time = time;
        requires(Drive.getInstance());
        drive = Drive.getInstance();
        t = new Timer();
    }

    protected void initialize() {
    	t.start();
    }

    protected void execute() {
    	drive.tankDrive(-.75, -.75);
    }

    protected boolean isFinished() {
        return t.get() > time;
    }

    protected void end() {
    	drive.tankDrive(0, 0);
    }

    protected void interrupted() {
    	drive.tankDrive(0, 0);
    }
}
