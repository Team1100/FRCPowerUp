package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 * DriveStop sends a zero speed instruction to the drive and 
 * finishes.
 * 
 */
public class DriveStop extends Command {
	
	/**
	 * Uses drive subsystem
	 */
    public DriveStop() {
        requires(Drive.getInstance());
    }

    /**
     * Stops all driving
     */
    protected void initialize() {
        Drive.getInstance().tankDrive(0, 0);
    }
    
    /**
     * Unused
     */
    protected void execute() {
    }
    
    /**
     * Always true
     */
    protected boolean isFinished() {
        return true;
    }

    /**
     * Unsued
     */
    protected void end() {
    }

    /**
     * Unused
     */
    protected void interrupted() {
    }
}
