package org.usfirst.frc.team1100.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command makes the robot wait for a specified number of seconds. This is 
 * used in auto for ensuring we don't hit another robot in CrossLine.java
 * @see org.usfirst.frc.team1100.robot.commands.auto.Wait
 */
public class Wait extends Command {
	Timer t;
	double time;
    public Wait(double time) {
    	t = new Timer();
    	this.time = time;
    }

    /**
     * Starts timer
     */
    protected void initialize() {
    	t.start();
    }

    /**
     * Unused
     */
    protected void execute() {
    }

    /**
     * True when timer has gone over specified time
     */
    protected boolean isFinished() {
        return t.get()>time;
    }

    /**
     * Unused
     */
    protected void end() {
    }

    /**
     * Unused
     */
    protected void interrupted() {
    }
}
