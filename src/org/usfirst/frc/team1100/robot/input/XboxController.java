package org.usfirst.frc.team1100.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is for an XboxController. All information about any given button or
 * axis can be accessed through the functions written here.
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
	
	/**
	 * This enumeration is used for the 6 axes: the x and y of the two joysticks, as well
	 * as the two triggers.
	 *
	 */
	public enum XboxAxis {
		/**
		 * Left stick, x axis
		 */
		kXLeft (0),
		/**
		 * Left stick, y axis
		 */
		kYLeft (1),
		/**
		 * Left trigger
		 */
		kLeftTrigger (2),
		/**
		 * Right trigger
		 */
		kRightTrigger (3),
		/**
		 * Right stick, x axis
		 */
		kXRight (4),
		/**
		 * RIght stick, y axis
		 */
		kYRight (5);
		
		public final int key;
		/**
		 * This is the constructor of the enumeration. The keys provided to the constructor
		 * are used to access the value of each axis in getAxis().
		 * @param key the magical number assigned by the Driver Station
		 */
		private XboxAxis(int key){
			this.key = key;
		}
	}
	
	/**
	 * Initializes a XBOX Controller on a specific channel, mapping the buttons.
	 * The JoyStick will never return a value in between +/- the deadband value.
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
	 * Gets the X Button from the Controller
	 * 
	 * @return the X Button
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
	 * Gets position of specified axis, accounting for the deadband
	 *
	 * @param axis the XboxAxis (XboxController.XboxAxis.k___) to retrieve
	 * @return the value of the axis, with the deadband factored in
	 */
	public double getAxis(XboxAxis axis) {
		double val = getRawAxis(axis.key);
		if (Math.abs(val) <= deadband) {
			val = 0.0;
		}
		return val;
	}
}


