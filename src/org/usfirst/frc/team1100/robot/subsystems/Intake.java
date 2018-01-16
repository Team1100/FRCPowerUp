package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.RunIntake;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * This subsystem controls the Intake that is currently being prototyped.
 *
 */
public class Intake extends Subsystem {

	private static Intake intake;
	
	private Talon intakeMotor;
	
	/**
	 * Called by getInstance().
	 */
	private Intake() {
		intakeMotor = new Talon(RobotMap.M_INTAKE);
	}
	
	/**
	 * Gets the singular instance of the Intake subsystem.
	 * @return The singular instance of the Intake subsystem.
	 */
	public static Intake getInstance() {
		if(intake == null) {
			intake = new Intake();
			
		}
		return intake;
	}
	
	/**
	 * runs the intake mechanism.
	 * @param n The value from the Xbox Controller Joystick.
	 */
	public void runIntake(double n) {
		intakeMotor.set(n);
	}
	
	/**
	 * Sets default command to RunIntake().
	 */
    public void initDefaultCommand() {
        setDefaultCommand(new RunIntake());
    }

    
}

