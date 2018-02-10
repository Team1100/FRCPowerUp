package org.usfirst.frc.team1100.robot.commands.climber;

import org.usfirst.frc.team1100.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbToBottom extends Command {
	
	Climber climber;
	boolean finished = false;

    public ClimbToBottom() {
        requires(Climber.getInstance());
        climber = Climber.getInstance();
    }

    protected void initialize() {
    }

    protected void execute() {
    	finished = !climber.climb(1);
    }

    protected boolean isFinished() {
        return finished;
    }


    protected void end() {
    }

    protected void interrupted() {
    }
}
