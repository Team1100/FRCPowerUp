package org.usfirst.frc.team1100.robot;

import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.commands.vision.CenterOnCube;
import org.usfirst.frc.team1100.robot.input.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * Triggers for commands through buttons are done in here.
 */
public class OI {
	/**
	 * The singular instance of the OI class
	 */
	private static OI oi;
	/**
	 * The singular instance of the XboxController class (although there may
	 * be more).
	 */
	private XboxController xbox;
	
	private AttackThree rightStick;
	private AttackThree leftStick;
	
	/**
	 * Gets the only instance of the OI class, used to access all input device data.
	 * @return the singular instance of the OI class
	 */
	public static OI getInstance() {
		if (oi == null) {
			oi = new OI();
		}
		return oi;
	}
	
	/**
	 * Initializes all input devices. It also establishes button triggers.
	 */
	private OI() {
		xbox = new XboxController(RobotMap.U_XBOX, 0.1);
		
		leftStick = new AttackThree(RobotMap.U_LEFT, 0.1);
		rightStick = new AttackThree(RobotMap.U_RIGHT, 0.1);
		xbox.getButtonA().whenPressed(new ChangeHeading(90));
		xbox.getButtonB().whenPressed(new CenterOnCube());
		//xbox.getButtonX().whenPressed(new CaptureImage());
		
	}
	
	/**
	 * Gets the only XboxController instance, used to access all XboxController data.
	 * @return the singular instance of the Xbox Controller
	 */
	public XboxController getXbox() {
		return xbox;
	}
	

	public AttackThree getLeftStick() {
		return leftStick;
	}
	
	public AttackThree getRightStick() {
		return rightStick; 
	}
}
