package org.usfirst.frc.team1100.robot.commands.navx;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1100.robot.OI;

import com.kauailabs.navx.frc.AHRS;

/**
 * This command moves the robot from its current position to a specified point
 * as fast as it can.
 */
public class TankAToB extends Command {
	AHRS ahrs;
    public TankAToB() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ahrs = OI.getInstance().getAHRS();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
