package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.XboxController;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Meme for the operator. When the driver uses either trigger, it rumbles joysticks.
 */
public class RumbleMeme extends Command {
	XboxController xbox;
    public RumbleMeme() {
        //Doesn't require anything
    }
    
    /**
     * Sets xbox controller
     */
    protected void initialize() {
    	xbox = OI.getInstance().getXbox();
    }
    
    /**
     * Rumbles xbox controller
     */
    protected void execute() {
    	xbox.setRumble(RumbleType.kLeftRumble, 1);
    	xbox.setRumble(RumbleType.kRightRumble, 1);
    }
    
    /**
     * Returns false
     */
    protected boolean isFinished() {
        return false;
    }
    
    /**
     * Stops rumbling xbox controller
     */
    protected void end() {
    	xbox.setRumble(RumbleType.kLeftRumble, 0);
    	xbox.setRumble(RumbleType.kRightRumble, 0);
    }
    
    /**
     * Stops rumbling xbox controller
     */
    protected void interrupted() {
    	xbox.setRumble(RumbleType.kLeftRumble, 0);
    	xbox.setRumble(RumbleType.kRightRumble, 0);
    }
}
