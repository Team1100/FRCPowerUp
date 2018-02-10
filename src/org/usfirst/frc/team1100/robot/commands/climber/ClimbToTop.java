package org.usfirst.frc.team1100.robot.commands.climber;

import org.usfirst.frc.team1100.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves climber all the way to the top
 */
public class ClimbToTop extends Command {
	
	Climber climber;
	boolean finished = false;
	
	/**
	 * Requires Climber
	 */
    public ClimbToTop() {
        requires(Climber.getInstance());
        climber = Climber.getInstance();
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    }
    
    /**
     * Climbs up until limited
     */
    protected void execute() {
    	finished = !climber.climb(-1);
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
    
    /**
     * Unused
     */
    protected void interrupted() {
    }
}
