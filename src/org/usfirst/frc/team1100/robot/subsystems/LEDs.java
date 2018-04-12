package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.proximity.DefaultLEDs;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDs extends Subsystem {

    private static LEDs leds;
    
    private boolean isOn = false;
    
    Solenoid lights;
    
    private LEDs() {
    	lights = new Solenoid(RobotMap.L_PCM_CAN, RobotMap.L_LED);
    }
    
    public static LEDs getInstance() {
    	 if (leds == null) leds = new LEDs();
    	 return leds;
    }
    
    public void set(boolean state) {
    	lights.set(state);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DefaultLEDs());
    }
}

