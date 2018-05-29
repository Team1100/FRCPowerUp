package org.usfirst.frc.team1100.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.AttackThree;

/**
 * This command allows tank driving with two joysticks
 */
public class DefaultDrive extends Command {
	/**
	 * Left and right joystick values
	 */
	double left, right;
	
	/**
	 * Requires Drive subsystem
	 */
    public DefaultDrive() {
        requires(Drive.getInstance()); 
    }

    /**
     * Unused
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

    /**
     * Always false, this is a default command
     */
    protected boolean isFinished() {
        return false;
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
