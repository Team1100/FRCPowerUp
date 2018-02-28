package org.usfirst.frc.team1100.robot.commands.elevator;

import org.usfirst.frc.team1100.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
 
/**
 * Moves elevator all the way to the top
 */
public class ElevateToTop extends Command {
	
	Elevator elevator;
	boolean finished = false;
	double speed;
	
	/**
	 * Requires Elevator
	 */
    public ElevateToTop() {
        requires(Elevator.getInstance());
        elevator = Elevator.getInstance();
        this.speed = .9;
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
    	finished = !elevator.climb(-speed);
    }
    
    /**
     * Gets state of climbing down
     * @return Whether elevator can't go down anymore
     */
    protected boolean isFinished() {
        return finished;
    }

    /**
     * Stops climbing
     */
    protected void end() {
    	elevator.climb(0);
    }
    
    /**
     * Stops climbing
     */
    protected void interrupted() {
    	elevator.climb(0);
    }
}
