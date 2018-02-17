package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Puts values of contours onto SmartDashboard periodically
 */
public class DefaultVision extends Command {

	Limelight lime;

	/**
	 * Uses Limelight subsystem
	 */
	public DefaultVision() {
		requires(Limelight.getInstance());
	}

	/**
	 * Gets Limelight instance
	 */
	protected void initialize() {
		lime = Limelight.getInstance();
	}

	/**
	 * Writes center x, center y, and area to SmartDashboard
	 */
	protected void execute() {
		lime.readNetworkTable();
	}

	/**
	 * Doesn't need to end
	 */
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Nothing to close, unused.
	 */
	protected void end() {
	}

	/**
	 * Welcomely interrupted.
	 */
	protected void interrupted() {
	}
}
