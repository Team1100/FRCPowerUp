package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Proximity extends Subsystem {
	private static Proximity proximity;
    DigitalInput prox; 
    
    /*
     * Creates new Digital Input, which in this case is a proximity sensor
     */
    private Proximity() {
    	prox = new DigitalInput(RobotMap.W_PROXIMITY);
    }
    
    /**
     * Gets the singular instance of the proximity sensor subsystem
     * @return the proximity sensor
     */
    public static Proximity getInstance() {
    	if (proximity == null) proximity = new Proximity();
    	return proximity;
    }
    
    /**
	 * Gets the state of the proximity sensor that detects the cube
	 * @return the state of the proximity sensor
	 */
    public boolean getProximity() {
    	return prox.get();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

