package org.usfirst.frc.team1100.robot.commands.intake;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultIntake extends Command {

    Intake intake;
    double in, out;
    
    public DefaultIntake() {
    	requires(Intake.getInstance());
        intake = Intake.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intake.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	out = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kRightTrigger);
    	in = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kLeftTrigger);
    	intake.setSpeed(out-in);
    	intake.spinWheels();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
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
