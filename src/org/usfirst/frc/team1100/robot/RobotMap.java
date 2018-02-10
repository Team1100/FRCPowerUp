package org.usfirst.frc.team1100.robot;

/**
 * The RobotMap is a mapping from the port's sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */ 
public class RobotMap {
	
	//List of all PWM ports
	private final static int PWM_0 = 0;
	private final static int PWM_1 = 1;
	private final static int PWM_2 = 2;
	private final static int PWM_3 = 3;
	private final static int PWM_4 = 4;
	private final static int PWM_5 = 5;
	private final static int PWM_6 = 6;
	private final static int PWM_7 = 7;
	private final static int PWM_8 = 8;
	private final static int PWM_9 = 9;
	
	//List of all CAN ports
	private final static int CAN_0 = 0;
	private final static int CAN_1 = 1;
	private final static int CAN_2 = 2;
	private final static int CAN_3 = 3;
	private final static int CAN_4 = 4;
	private final static int CAN_5 = 5;
	private final static int CAN_6 = 6;
	private final static int CAN_7 = 7;
	
	//List of all analog ports
	private final static int ANALOG_0 = 0;
	private final static int ANALOG_1 = 1;
	private final static int ANALOG_2 = 2;
	private final static int ANALOG_3 = 3;
	
	//List of all relays
	private final static int RELAY_0 = 0;
	private final static int RELAY_1 = 1;
	private final static int RELAY_2 = 2;
	private final static int RELAY_3 = 3;
	
	//List of all DIO ports
	private final static int DIO_0 = 0;
	private final static int DIO_1 = 1;
	private final static int DIO_2 = 2;
	private final static int DIO_3 = 3;
	private final static int DIO_4 = 4;
	private final static int DIO_5 = 5;
	private final static int DIO_6 = 6;
	private final static int DIO_7 = 7;
	private final static int DIO_8 = 8;
	private final static int DIO_9 = 9;
	
	//List of all USB ports
	private static final int USB_0 = 0;
	private static final int USB_1 = 1;
	private static final int USB_2 = 2;
	private static final int USB_3 = 3;
	
	
	private static final int P_0 = 0;
	private static final int P_1 = 1;
	private static final int P_2 = 2;
	private static final int P_3 = 3;
	private static final int P_4 = 4;
	private static final int P_5 = 5;
	private static final int P_6 = 6;
	private static final int P_7 = 7;
	
	//List of all PDP ports
	private static final int PDP_0 = 0;
	private static final int PDP_1 = 1;
	private static final int PDP_2 = 2;
	private static final int PDP_3 = 3;
	private static final int PDP_4 = 4;
	private static final int PDP_5 = 5;
	private static final int PDP_6 = 6;
	private static final int PDP_7 = 7;
	private static final int PDP_8 = 8;
	private static final int PDP_9 = 9;
	private static final int PDP_10 = 10;
	private static final int PDP_11 = 11;
	private static final int PDP_12 = 12;
	private static final int PDP_13 = 13;
	private static final int PDP_14 = 14;
	private static final int PDP_15 = 15;
	private static final int PDP_16 = 16;
	
	//[U]ser Input
	public static final int U_LEFT = 1;
	public static final int U_RIGHT = 0;
	public static final int U_XBOX = 2;
	
	//[D]rive
	public static final int D_LEFT_ONE = PWM_0;
	public static final int D_LEFT_TWO = PWM_1;
	public static final int D_RIGHT_ONE = PWM_2;
	public static final int D_RIGHT_TWO = PWM_3;
	//they are named "one" and "two" because it doesn't matter which is first
	
	//[C]limber
	public static final int C_CLIMB_ONE = PWM_4;
	public static final int C_CLIMB_TWO = PWM_5;
	public static final int C_POT = P_0;
	//they are named "one" and "two" because it doesn't matter what side they're on
	
	// Cla[W]
	public static final int W_PULL_MOTOR_LEFT = PWM_6;
	public static final int W_PULL_MOTOR_RIGHT = PWM_7;
	public static final int W_WRIST_MOTOR_LEFT = PWM_8;
	public static final int W_WRIST_MOTOR_RIGHT = PWM_9;
	public static final int W_PINCHER = CAN_0;
}