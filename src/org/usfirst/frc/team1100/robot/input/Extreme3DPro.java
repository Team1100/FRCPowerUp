package org.usfirst.frc.team1100.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Extreme3DPro extends Joystick{
	/**
	 * Total amount of buttons on the Attack Three
	 */
	private final short totalButtons = 11;
	/**
	 * Array of every button on the Attack Three
	 */
	private JoystickButton buttons[];
	/**
	 * Amount of deadband on X and Y axes
	 */
	private double deadbandXY;
	/**
	 * Amount of deadband on Z axis
	 */
	private double deadbandZ;

	/**
	 * Initializes a Joystick on a specific channel, mapping the buttons. The
	 * Joystick will never return a value in between +/- the deadband value.
	 * 
	 * @param channel the channel the Joystick is plugged into
	 * @param deadbandXY the value of the deadband on x and y axes, from 0 to 1
	 * @param deadbandZ the value of the deadband on z axis, from 0 to 1
	 */
	public Extreme3DPro(int channel, double deadbandXY, double deadbandZ) {
		super(channel);
		
		buttons = new JoystickButton[totalButtons];
		
		//Maps each button key to a location in the buttons array
		for (int i = 0; i < totalButtons; i++) {
			buttons[i] = new JoystickButton(this,i+1);
		}

		this.deadbandXY = deadbandXY;
		this.deadbandZ = deadbandZ;
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
		double deadband = deadbandXY;
		if(axis.equals(AxisType.kZ)){
			deadband=deadbandZ;
		}
		if (Math.abs(val) <= deadband) {
			val = 0.0;
		}
		return val;
	}
}
