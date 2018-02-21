package org.usfirst.frc.team1100.robot.commands.climber;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * User control of the climber through Xbox controller left joystick. Used for
 * resetting the potentiometer while robot is in development
 */
public class DefaultClimber extends Command {
	
	double speed = 0;
	/**
	 * Requires Climber
	 */
    public DefaultClimber() {
        requires(Climber.getInstance());
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    }

    /**
     * Sets speed of climb to Xbox controller left stick
     */
    protected void execute() {
    	speed = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYLeft);
    	Climber.getInstance().climb(speed);
    }
    
    /**
     * Never true, just interrupted
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