package org.usfirst.frc.team1100.robot.commands.climber;

import org.usfirst.frc.team1100.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;
 
/**
 * Moves climber all the way to the top
 */
public class ClimbToTop extends Command {
	
	Climber climber;
	boolean finished = false;
	double speed;
	
	/**
	 * Requires Climber
	 */
    public ClimbToTop() {
        requires(Climber.getInstance());
        climber = Climber.getInstance();
        this.speed = .5;
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
    	finished = !climber.climb(-speed);
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
    	climber.climb(0);
    }
    
    /**
     * Unused
     */
    protected void interrupted() {
    	climber.climb(0);
    }
}
