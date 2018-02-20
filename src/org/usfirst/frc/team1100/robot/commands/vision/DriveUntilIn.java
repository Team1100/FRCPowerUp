package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveUntilIn extends Command {

    public DriveUntilIn() {
        requires(Drive.getInstance());
        requires(Limelight.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drive.getInstance().tankDrive(-.6, -.6);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("Area", Limelight.getInstance().getArea());
        return Limelight.getInstance().getArea()==-1;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
