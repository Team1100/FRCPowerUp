package org.usfirst.frc.team1100.robot.commands.intake;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command allows manual control of the intake wheels via the Xbox controller
 * triggers
 */
public class DefaultIntake extends Command {

    Intake intake;
    double in, out;
    
    /**
     * Uses the intake subsystem
     */
    public DefaultIntake() {
    	requires(Intake.getInstance());
        intake = Intake.getInstance();
    }

    /**
     * Stops all intake movement
     */
    protected void initialize() {
    	intake.stop();
    }

    /**
     * Uses Xbox triggers to control intake wheels
     */
    protected void execute() {
    	out = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kRightTrigger);
    	in = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kLeftTrigger);
    	intake.setSpeed(out-in);
    	intake.spinWheels();
    }

    /**
     * Always false, this is a default command
     */
    protected boolean isFinished() {
    	return false;
    }

    /**
     * Stops all intake movement
     */
    protected void end() {
    	intake.stop();
    }
    
    /**
     * Stops all intake movement
     */
    protected void interrupted() {
    	intake.stop();
    }
}
