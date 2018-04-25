package org.usfirst.frc.team1100.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * This subsystem interacts with the data being sent from the Pi 3.
 */
public class Pi extends Subsystem {

    private static Pi pi;
    private static NetworkTable table;
    /*
     * Sets up the NetworkTable server side
     */
    private Pi() {
    	table = NetworkTableInstance.getDefault().getTable("Pi");
    }
    
    /**
     * Gets the singular instance of the Raspberry Pi subsystem
     * @return the Pi instance
     */
    public static Pi getInstance() {
    	if (pi == null) pi = new Pi();
    	return pi;
    }
    
    /**
     * Gets the center x coordinate of the cube, if a cube is visible
     * @return the center x coordinate (0 to 640)
     */
    public double getCX() {
    	return table.getEntry("centerx").getDouble(-1);
    }
    
    /**
     * Gets the center y coordinate of the cube, if a cube is visible
     * @return the center y coordinate (0 to 480)
     */
    public double getCY() {
    	return table.getEntry("centery").getDouble(-1);
    }
    
    /**
     * Gets the area of the cube, if a cube is visible
     * @return the area (0 to 480*640)
     */
    public double getArea() {
    	return table.getEntry("Area").getDouble(-1);
    }
    
    /**
     * Unused
     */
    public void initDefaultCommand() {
    	
    }
}

