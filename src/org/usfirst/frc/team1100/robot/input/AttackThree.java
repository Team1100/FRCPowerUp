package org.usfirst.frc.team1100.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class AttackThree extends Joystick {

	private JoystickButton buttons[];
	private double deadband;

	/**
	 * Initializes a Joystick on a specific channel, mapping the buttons. The
	 * Joystick will never return a value in between +/- the deadband value.
	 * 
	 * @param channel the channel the Joystick is plugged into
	 * @param deadband the value of the deadband, from 0 to 1
	 */
	public AttackThree(int channel, double deadband) {
		super(channel);
		
		buttons = new JoystickButton[11];
		
		for (int i = 0; i < 11; i++) {
			buttons[i] = new JoystickButton(this, i + 1);
		}

		this.deadband = deadband;
	}

	/**
	 * Gets the specified button on this controller
	 *
	 * @param number the number of the button on the Joystick
	 * @return the Button corresponding the the number, starting at 1
	 */
	public JoystickButton getButton(int number) {
		return buttons[number - 1];
	}

	/**
	 * Gets position of a specific axis, accounting for the deadband
	 *
	 * @param axis the AxisType to retrieve
	 * @return the value of the axis, with the deadband factored in
	 */
	public double getAxis(AxisType axis) {
		double val = -super.getAxis(axis);
		if (Math.abs(val) <= deadband) {
			val = 0.0;
		}
		return val;
	}
}
