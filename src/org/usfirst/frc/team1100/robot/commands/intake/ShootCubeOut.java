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
    	requires(Intake.getInstance());
        intake = Intake.getInstance();
        t = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intake.setSpeed(1);
    	intake.spinWheels();
    	t.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intake.spinWheels();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return t.get()>1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	intake.stop();
    }
}
