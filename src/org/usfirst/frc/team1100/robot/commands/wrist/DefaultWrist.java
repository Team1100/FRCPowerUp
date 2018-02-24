package org.usfirst.frc.team1100.robot.commands.wrist;

import org.usfirst.frc.team1100.robot.subsystems.Wrist;
import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.input.XboxController;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command controls the angle of the wrist using the right joystick of the xbox controller
 */
public class DefaultWrist extends Command {
	private Wrist wrist;
    public DefaultWrist() {
        requires(Wrist.getInstance());
        wrist = Wrist.getInstance();
    }


    /**
     * Sets wrist speed to 0
     */
    protected void initialize() {
    	wrist.rotateWrist(0);
    }

    /**
     * Sets speed of wrist to xbox controller right joystick
     */
    protected void execute() {
    	if (Robot.manualOverride) {
    		wrist.rotateWrist(OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYRight));
    	}
    }

    /**
     * Returns false because this is a default command
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Sets speed of wrist to 0
     */
    protected void end() {
    	wrist.rotateWrist(0);
    }

    /**
     * Sets speed of wrist to 0
     */
    protected void interrupted() {
    	wrist.rotateWrist(0);
    }
}
