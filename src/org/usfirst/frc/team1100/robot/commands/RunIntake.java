package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command controls the Intake subsystem.
 */
public class RunIntake extends Command {

	/**
	 * This command uses Intake
	 */
    public RunIntake() {
    	requires(Intake.getInstance());
        
    }
    
    /**
     * Not currently being used.
     */
    protected void initialize() {
    }
    
    /**
     * Plug the value of the Xbox LeftX Joystick to the Intake motor.
     */
    protected void execute() {
    	Intake.getInstance().runIntake(OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kXLeft));
    }
    
    /**
     * Not currently being used.
     */
    protected boolean isFinished() {
        return false;
    }
    
    /**
     * Not currently being used.
     */
    protected void end() {
    }
    
    /**
     * Not currently being used.
     */
    protected void interrupted() {
    }
}
