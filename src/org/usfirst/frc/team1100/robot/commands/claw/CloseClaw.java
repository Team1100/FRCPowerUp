package org.usfirst.frc.team1100.robot.commands.claw;

import org.usfirst.frc.team1100.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command opens the claw
 */
public class CloseClaw extends Command {

    Claw claw;
    
    /**
     * Uses the claw
     */
    public CloseClaw() {
    	requires(Claw.getInstance());
        claw = Claw.getInstance();
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    }
    
    /**
     * Closes the claw
     */
    protected void execute() {
    	claw.close();
    }
    
    /**
     * Always true
     */
    protected boolean isFinished() {
        return true;
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
