package org.usfirst.frc.team1100.robot.commands.drive;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.AttackThree;
import org.usfirst.frc.team1100.robot.input.XboxController;

/**
 * This command allows tank driving with two joysticks
 */
public class UserDrive extends Command {
	/**
	 * Left and right joystick values
	 */
	double left, right;
	
	/**
	 * Requires Drive subsystem
	 */
    public UserDrive() {
        requires(Drive.getInstance()); 
    }

    /**
     * unused
     */
    protected void initialize() {
    }

    /**
     * Reads joysticks and plugs the into super.tankDrive
     */
    protected void execute() {
    	left = OI.getInstance().getLeftStick().getAxis(AttackThree.AttackThreeAxis.kY);
    	right = OI.getInstance().getRightStick().getAxis(AttackThree.AttackThreeAxis.kY);
    	Drive.getInstance().tankDrive(left, right);
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
