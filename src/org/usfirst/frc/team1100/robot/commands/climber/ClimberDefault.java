package org.usfirst.frc.team1100.robot.commands.climber;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * User control of the climber through Xbox controller left joystick. Used for
 * resetting the potentiometer while robot is in developement
 */
public class ClimberDefault extends Command {
	
	double speed = 0;
	/**
	 * Requires Climber
	 */
    public ClimberDefault() {
        requires(Climber.getInstance());
    }
    
    /**
     * Unused
     */
    protected void initialize() {
    }

    /**
     * Sets speed of climb to Xbox controller left stick, puts height (0 to 2) to SmartDashboard
     */
    protected void execute() {
    	speed = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYLeft);
    	Climber.getInstance().climb(speed);
    	SmartDashboard.putNumber("Voltage", Climber.getInstance().getVoltage());
    }
    
    /**
     * Never true, just interrupted
     */
    protected boolean isFinished() {
        return false;
    }
    
    /**
     * Unused
     */
    protected void end() {
    }
    
    /**
     * Unused
     */
    protected void interrupted() {
    }
}