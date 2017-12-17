package org.usfirst.frc.team1100.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Peasant Scum.
 */
public class XboxController extends Joystick {

	private JoystickButton buttonA;
	private JoystickButton buttonB;
	private JoystickButton buttonX;
	private JoystickButton buttonY;
	private JoystickButton buttonLeftBumper;
	private JoystickButton buttonRightBumper;
	private JoystickButton buttonBack;
	private JoystickButton buttonStart;
	private JoystickButton buttonLeftStick;
	private JoystickButton buttonRightStick;
	private double deadband;

	public enum XboxAxis {
		kXLeft (0),
		kYLeft (1),
		kLeftTrigger (2), 
		kRightTrigger (3),
		kXRight (4),
		kYRight (5);
		
		public final int value;
		private XboxAxis(int value){
			this.value = value;
		}
	}
	
	/**
	 * Initializes a XBOX Controller on a specific channel, mapping the buttons.
	 * The JoyStick will never return a value in between +/- the beadband value.
	 * 
	 * @param channel the channel the Controller is plugged into
	 * @param deadband the value of the deadband, from 0 to 1
	 */
	public XboxController(int channel, double deadband) {
		super(channel);

		buttonA = new JoystickButton(this, 1);
		buttonB = new JoystickButton(this, 2);
		buttonX = new JoystickButton(this, 3);
		buttonY = new JoystickButton(this, 4);
		buttonLeftBumper = new JoystickButton(this, 5);
		buttonRightBumper = new JoystickButton(this, 6);
		buttonBack = new JoystickButton(this, 7);
		buttonStart = new JoystickButton(this, 8);
		buttonLeftStick = new JoystickButton(this, 9);
		buttonRightStick = new JoystickButton(this, 10);

		this.deadband = deadband;
	}

	/**
	 * Gets the A Button from the Controller
	 * 
	 * @return the A Button
	 */
	public JoystickButton getButtonA() {
		return buttonA;
	}

	/**
	 * Gets the B Button from the Controller
	 * 
	 * @return the B Button
	 */
	public JoystickButton getButtonB() {
		return buttonB;
	}

	/**
	 * Gets the B Button from the Controller
	 * 
	 * @return the B Button
	 */
	public JoystickButton getButtonX() {
		return buttonX;
	}

	/**
	 * Gets the Y Button from the Controller
	 * 
	 * @return the Y Button
	 */
	public JoystickButton getButtonY() {
		return buttonY;
	}

	/**
	 * Gets the Left Bumper from the Controller
	 * 
	 * @return the Left Bumper
	 */
	public JoystickButton getButtonLeftBumper() {
		return buttonLeftBumper;
	}

	/**
	 * Gets the Right Bumper from the Controller
	 * 
	 * @return the Right Bumper
	 */
	public JoystickButton getButtonRightBumper() {
		return buttonRightBumper;
	}

	/**
	 * Gets the Back Button from the Controller
	 * 
	 * @return the Back Button
	 */
	public JoystickButton getButtonBack() {
		return buttonBack;
	}

	/**
	 * Gets the Start Button from the Controller
	 * 
	 * @return the Start Button
	 */
	public JoystickButton getButtonStart() {
		return buttonStart;
	}

	/**
	 * Gets the Left Stick Button from the Controller
	 * 
	 * @return Left Stick Button
	 */
	public JoystickButton getButtonLeftStick() {
		return buttonLeftStick;
	}

	/**
	 * Gets the Right Stick Button from the Controller
	 * 
	 * @return Right Stick Button
	 */
	public JoystickButton getButtonRightStick() {
		return buttonRightStick;
	}

	/**
	 * Gets position of a specific axis, accounting for the deadband
	 *
	 * @param axis the XboxAxis to retrieve
	 * @return the value of the axis, with the deadband factored in
	 */
	public double getAxis(XboxAxis axis) {
		double val = getRawAxis(axis.value);
		if (Math.abs(val) <= deadband) {
			val = 0.0;
		}
		return val;
	}
}


