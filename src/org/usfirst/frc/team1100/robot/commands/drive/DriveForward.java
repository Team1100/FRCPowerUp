package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives the robot forward, without using the NavX
 */
public class DriveForward extends Command {
	Encoder encoder;
	Drive drive;
	double distance, speed;
	
	/**
	 * Uses the drive subsystem and an encoder
	 * @param distance the distance in feet in which the robot will move
	 * @param speed the speed in which the robot will move
	 */
    public DriveForward(double distance, double speed) {
        requires(Drive.getInstance());
        drive = Drive.getInstance();
        encoder = drive.getEncoder();
    }

    /**
     * Unused
     */
    protected void initialize() {
    }

    /**
     * Drives forward at given speed
     */
    protected void execute() {
    	drive.tankDrive(speed, speed);
    }

    /**
     * True when robot is equal or has passed desired distance
     */
    protected boolean isFinished() {
    	return Math.abs(encoder.getDistance()) >= Math.abs(distance);
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
