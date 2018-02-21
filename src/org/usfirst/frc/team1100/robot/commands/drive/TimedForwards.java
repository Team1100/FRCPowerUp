package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * THis command drives forward for a specified amount of seconds. It is
 * used as an emergency command when nearly EVERYTHING breaks
 */
public class TimedForwards extends Command {
	Timer t;
	Drive drive;
	double time;
	
	/**
	 * Uses drive subsystem
	 * @param time amount of time which the robot will drive
	 */
    public TimedForwards(double time) {
    	this.time = time;
        requires(Drive.getInstance());
        drive = Drive.getInstance();
        t = new Timer();
    }
    
    /**
     * Starts timer
     */
    protected void initialize() {
    	t.start();
    }
    
    /**
     * Drives away from wall
     */
    protected void execute() {
    	drive.tankDrive(-.75, -.75);
    }
    
    /**
     * True when timer is over specified time
     */
    protected boolean isFinished() {
        return t.get() > time;
    }
    
    /**
     * Stops all driving
     */
    protected void end() {
    	drive.tankDrive(0, 0);
    }
    
    /**
     * Stops all driving
     */
    protected void interrupted() {
    	drive.tankDrive(0, 0);
    }
}
