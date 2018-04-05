package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Pi;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command has the robot drive until the cube is in the claw
 */
public class DriveUntilIn extends Command { 
	
	/**
	 * Uses the drive subsystem
	 */
    public DriveUntilIn() {
        requires(Drive.getInstance());
    }

    /**
     * Unused
     */
    protected void initialize() {
    }

    /**
     * Drives with the limelight being the 'front'
     */
    protected void execute() {
    	Drive.getInstance().tankDrive(-.6, -.6);
    }

    /**
     * True when the cube is in the claw
     */
    protected boolean isFinished() {
    	//My logic is that if the cube is so close to the camera that everything is too dark to
    	//detect the cube, or if 65% of the image is the cube, the robot has the cube
        return Pi.getInstance().getArea() == -1 || Pi.getInstance().getArea() > 65;
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
