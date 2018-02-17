package org.usfirst.frc.team1100.robot.commands.intake;

import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class PullCubeIn extends Command {

    Intake intake;
    Timer t;
    double time;
    
    public PullCubeIn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Intake.getInstance());
        intake = Intake.getInstance();
        time = 10;
        t = new Timer();
        t.start();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intake.setPullSpeed(.5);
    	intake.pullIn();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intake.pullIn();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return t.get()>100;
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.pullStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	intake.pullStop();
    }
}
