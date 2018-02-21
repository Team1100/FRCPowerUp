package org.usfirst.frc.team1100.robot.commands.claw;

import org.usfirst.frc.team1100.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

/**
 * THis command opens the claw
 */
public class OpenClaw extends Command {

    Claw claw;
    
    /**
     * Uses the claw
     */
    public OpenClaw() {
    	requires(Claw.getInstance());
        claw = Claw.getInstance();
    }

    /**
     * Unused
     */
    protected void initialize() {
    }
    
    /**
     * Opens the claw
     */
    protected void execute() {
    	claw.open();
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
