package org.usfirst.frc.team1100.robot.commands.intake;

import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCubeOut extends Command {

    Intake intake;
    Timer t;
    
    public ShootCubeOut() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Intake.getInstance());
        intake = Intake.getInstance();
        t = new Timer();
        t.start();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intake.setShootSpeed(1);
    	intake.shootOut();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intake.shootOut();
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
