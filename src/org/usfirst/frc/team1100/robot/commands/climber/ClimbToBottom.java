package org.usfirst.frc.team1100.robot.commands.climber;

import org.usfirst.frc.team1100.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves climber all the way to the bottom
 */
public class ClimbToBottom extends Command {
	
	Climber climber;
	boolean finished = false;
	
	/**
	 * Requires Climber
	 */
    public ClimbToBottom() {
        requires(Climber.getInstance());
        climber = Climber.getInstance();
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    }
    
    /**
     * Climbs down until limited
     */
    protected void execute() {
    	finished = !climber.climb(.7);
    }
    
    /**
     * Gets state of climbing down
     * @return Whether climber can't go down anymore
     */
    protected boolean isFinished() {
        return finished;
    }

    /**
     * Unused
     */
    protected void end() {
    }
    

    protected void interrupted() {
    	climber.climb(0);
    }
}
