package org.usfirst.frc.team1100.robot.commands.drive;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 * DriveStop sends a zero speed instruction to the drive and 
 * finishes.. 
 * 
 * @author cp18587 (2/5/2018)
 */
public class DriveStop extends Command {

    public DriveStop() {
        requires(Drive.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Drive.getInstance().arcadeDrive(0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
