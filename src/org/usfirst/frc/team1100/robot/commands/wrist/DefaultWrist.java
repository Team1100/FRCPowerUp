package org.usfirst.frc.team1100.robot.commands.wrist;

import org.usfirst.frc.team1100.robot.subsystems.Wrist;
import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.XboxController;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultWrist extends Command {
	private Wrist wrist;
    public DefaultWrist() {
        requires(Wrist.getInstance());
        wrist = Wrist.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	wrist.rotateWrist(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	wrist.rotateWrist(-OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYRight));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	wrist.rotateWrist(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
