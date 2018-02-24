package org.usfirst.frc.team1100.robot.commands.elevator;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * User control of the elevator through Xbox controller left joystick. Used for
 * resetting the potentiometer while robot is in development
 */
public class DefaultElevator extends Command {
	
	double speed = 0;
	/**
	 * Requires Elevator
	 */
    public DefaultElevator() {
        requires(Elevator.getInstance());
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
    	if (Robot.manualOverride) {
	    	speed = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYRight);
	    	Elevator.getInstance().climb(speed);
    	}
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