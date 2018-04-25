package org.usfirst.frc.team1100.robot.commands.elevator;

import org.usfirst.frc.team1100.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopElevator extends Command {

    public StopElevator() {
        requires(Elevator.getInstance());
    }

    protected void initialize() {
    }

    protected void execute() {
    	Elevator.getInstance().climb(0);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
