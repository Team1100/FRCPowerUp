package org.usfirst.frc.team1100.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * This subsystem interacts with the data being sent from the Pi 3.
 */
public class Pi extends Subsystem {

    private static Pi pi;
    private static NetworkTable table;
    
    private Pi() {
    	table = NetworkTableInstance.getDefault().getTable("SmartDashboard");
    }
    
    public static Pi getInstance() {
    	if (pi == null) pi = new Pi();
    	return pi;
    }
    
    public double getCX() {
    	return table.getEntry("centerx").getDouble(-1);
    }
    
    public double getCY() {
    	return table.getEntry("centery").getDouble(-1);
    }
    
    public void initDefaultCommand() {
    	
    }
}

