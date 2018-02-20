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
    
    public PullCubeIn(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Intake.getInstance());
        intake = Intake.getInstance();
        this.time = time;
        t = new Timer();
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	t.start();
    	intake.setSpeed(-.5);
    	intake.spinWheels();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intake.spinWheels();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return t.get()>this.time;
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
