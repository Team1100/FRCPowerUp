package org.usfirst.frc.team1100.robot.commands.claw;

import org.usfirst.frc.team1100.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCubeOut extends Command {

    Claw claw;
    Timer t;
    double time;
    
    public ShootCubeOut() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Claw.getInstance());
        claw = Claw.getInstance();
        time = 10;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	claw.setShootSpeed(1);
    	t = new Timer();
    	t.start();
    	claw.shootOut();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// nothing to do but wait for time to expire
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return t.get() >= time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	claw.pullStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
