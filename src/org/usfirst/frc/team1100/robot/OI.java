package org.usfirst.frc.team1100.robot;

import org.usfirst.frc.team1100.robot.input.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * Triggers for commands through buttons are done in here.
 */
public class OI {
	private static OI oi;
	private XboxController xbox;
	
	
	/**
	 * 
	 * @return the singular instance of the OI class
	 */
	public static OI getInstance() {
		if (oi == null) {
			oi = new OI();
		}
		return oi;
	}
	
	////TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	/**
	 * This function is used to initialize all input devices. It also
	 * is used to establish button triggers.
	 */
	private OI() {
		xbox = new XboxController(RobotMap.U_XBOX, 0.1);
	}
	
	/**
	 * 
	 * @return the singular instance of the Xbox Controller
	 */
	public XboxController getXbox() {
		return xbox;
	}
}
