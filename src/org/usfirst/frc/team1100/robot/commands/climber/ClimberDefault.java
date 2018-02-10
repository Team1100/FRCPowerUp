package org.usfirst.frc.team1100.robot.commands.climber;

import org.usfirst.frc.team1100.robot.OI;
import org.usfirst.frc.team1100.robot.input.XboxController;
import org.usfirst.frc.team1100.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ClimberDefault extends Command {
	
	double speed = 0;
	
    public ClimberDefault() {
        requires(Climber.getInstance());
    }

    protected void initialize() {
    }


    protected void execute() {
    	speed = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYLeft);
    	Climber.getInstance().climb(speed);
    	SmartDashboard.putNumber("Voltage", Climber.getInstance().getVoltage());
    }

    protected boolean isFinished() {
        return false;
    }


    protected void end() {
    }

    protected void interrupted() {
    }
}
