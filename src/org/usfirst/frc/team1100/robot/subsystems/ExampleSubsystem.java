package org.usfirst.frc.team1100.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Example Subsystem in case one forgets how this stuff is written.
 */
public class ExampleSubsystem extends Subsystem {
	
	//add methods for command's use
	
	private static ExampleSubsystem subsystem; //ONLY ONE instance of a subsystem at a time.
	
	public static ExampleSubsystem getInstance() { //Used in requires(x) in command constructors.
		if (subsystem == null) {
			subsystem = new ExampleSubsystem();
		}
		return subsystem;
	}
	
	private ExampleSubsystem() {
		//initialize any hardware used in subsystem here.
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
}
