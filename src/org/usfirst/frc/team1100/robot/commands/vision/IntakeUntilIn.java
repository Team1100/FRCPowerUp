package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.Intake;
import org.usfirst.frc.team1100.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class IntakeUntilIn extends Command {

    Intake intake;
    Timer t;
    double time;
    
    public IntakeUntilIn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Intake.getInstance());
    	
        intake = Intake.getInstance();
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	intake.setSpeed(-.5);
    	intake.spinWheels();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Area", Limelight.getInstance().getArea());
    	intake.spinWheels();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Limelight.getInstance().getArea()==-1;
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
