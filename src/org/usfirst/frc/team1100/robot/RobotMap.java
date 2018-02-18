package org.usfirst.frc.team1100.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;

/**
 * The RobotMap is a mapping from the port's sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * MXP PWM and DIO pins are multiplexed. If you are using one, you can't use the other.
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
	// see https://www.pdocs.kauailabs.com/navx-mxp/installation/io-expansion/
	private final static int PWM_MXP_0 = 10;
	private final static int PWM_MXP_1 = 11;
	private final static int PWM_MXP_2 = 12;
	private final static int PWM_MXP_3 = 13;
	private final static int PWM_MXP_4 = 14;
	private final static int PWM_MXP_5 = 15;
	private final static int PWM_MXP_6 = 16;
	private final static int PWM_MXP_7 = 17;
	private final static int PWM_MXP_8 = 18;
	private final static int PWM_MXP_9 = 19;
	
	// List of PCM CAN IDs
	private final static int PCM_CAN_0 = 0;
	
	// List of Talon SRX CAN IDs
	private final static int SRX_CAN_0 = 0;
	private final static int SRX_CAN_1 = 1;
	private final static int SRX_CAN_2 = 2;
	private final static int SRX_CAN_3 = 3;
	
	//List of all analog ports
	private final static int ANALOG_0 = 0;
	private final static int ANALOG_1 = 1;
	private final static int ANALOG_2 = 2;
	private final static int ANALOG_3 = 3;
	// see https://www.pdocs.kauailabs.com/navx-mxp/installation/io-expansion/
	private final static int ANALOG_MXP_0 = 4;
	private final static int ANALOG_MXP_1 = 5;
	private final static int ANALOG_MXP_2 = 6;
	private final static int ANALOG_MXP_3 = 7;
	
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
	// see https://www.pdocs.kauailabs.com/navx-mxp/installation/io-expansion/
	private final static int DIO_MXP_0 = 10;
	private final static int DIO_MXP_1 = 11;
	private final static int DIO_MXP_2 = 12;
	private final static int DIO_MXP_3 = 13;
	private final static int DIO_MXP_4 = 18;
	private final static int DIO_MXP_5 = 19;
	private final static int DIO_MXP_6 = 20;
	private final static int DIO_MXP_7 = 21;
	private final static int DIO_MXP_8 = 22;
	private final static int DIO_MXP_9 = 23;
	
	//List of all USB ports
	private static final int USB_0 = 0;
	private static final int USB_1 = 1;
	private static final int USB_2 = 2;
	private static final int USB_3 = 3;
	
	// Pneumatic Control Module (PCM) ports
	private static final int PCM_0 = 0;
	private static final int PCM_1 = 1;
	private static final int PCM_2 = 2;
	private static final int PCM_3 = 3;
	private static final int PCM_4 = 4;
	private static final int PCM_5 = 5;
	private static final int PCM_6 = 6;
	private static final int PCM_7 = 7;
	
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
	public static final int D_PEACH = PWM_0;
	public static final int D_LUIGI = PWM_1;
	public static final int D_TOAD = PWM_4;
	public static final int D_MARIO = PWM_5;
	public static final int D_ENCODER_MARIOTOAD_A = DIO_0;
	public static final int D_ENCODER_MARIOTOAD_B = DIO_1;
	public static final int D_ENCODER_PEACHLUIGI_A = DIO_2;
	public static final int D_ENCODER_PEACHLUIGI_B = DIO_3;
	public static final Port D_NAVX = SPI.Port.kMXP;
	//they are named "one" and "two" because it doesn't matter which is first
	
	//[C]limber
	public static final int C_CLIMB_ONE = PWM_2;
	public static final int C_CLIMB_TWO = PWM_3;
	public static final int C_CLIMB_POT = ANALOG_0;
	public static final int C_BOTTOM_SWITCH = DIO_4;
	public static final int C_TOP_SWITCH = DIO_5;
	public static final int C_NEAR_BOTTOM_SWITCH = DIO_6;
	//they are named "one" and "two" because it doesn't matter what side they're on
	
	// Cla[W]
	public static final int W_PULL_MOTOR_LEFT = SRX_CAN_0;
	public static final int W_PULL_MOTOR_RIGHT = SRX_CAN_1;
	public static final int W_WRIST_MOTOR_LEFT = SRX_CAN_2;
	public static final int W_WRIST_MOTOR_RIGHT = SRX_CAN_3;
	public static final int W_PINCHER_CAN = PCM_CAN_0;
	public static final int W_PINCHER_0 = PCM_0; //left
	public static final int W_PINCHER_1 = PCM_1; //left
	public static final int W_PINCHER_2 = PCM_2; //right
	public static final int W_PINCHER_3 = PCM_3; //right
	public static final int W_WRIST_POT = ANALOG_1;
}