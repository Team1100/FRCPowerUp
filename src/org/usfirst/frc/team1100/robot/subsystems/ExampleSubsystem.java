package org.usfirst.frc.team1100.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Example Subsystem in case one forgets how this stuff is written.
 */
public class ExampleSubsystem extends Subsystem {
	
	//add methods for command use
	
	private static ExampleSubsystem subsystem; //ONLY ONE instance of a subsystem at a time.
	
	/**
	 * Gets the only instance of the subsystem. One robot means one subsystem.
	 * @return the singular instance of the subsystem
	 */
	public static ExampleSubsystem getInstance() { //Used in requires(x) in command constructors.
		if (subsystem == null) {
			subsystem = new ExampleSubsystem();
		}
		return subsystem;
	}
	
	/**
	 * Initializes all hardware that is part of the subsystem.
	 */
	private ExampleSubsystem() {
		
	}
	
	/**
	 * Sets default command for subsystem here. A default command is a command that is run
	 * when no other command is using this subsystem.
	 */
	public void initDefaultCommand() {
		
		// setDefaultCommand(new MySpecialCommand());
	}
	
}
