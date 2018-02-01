package org.usfirst.frc.team1100.robot.subsystems.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1100.robot.commands.vision.DefaultVision;

/**
 * Controls Limelight camera
 */
public class Limelight extends Subsystem {
	
	private static Limelight lime;
	NetworkTable table;
	private double x, y, area;
	boolean cubeDetected;
	
	/**
	 * Gets table, turns off Limelight LED
	 */
    private Limelight() {
    	//Assign Limelight table to variable table
		table = NetworkTableInstance.getDefault().getTable("limelight");
		
		//Turn green LEDs on Limelight off.
		table.getEntry("ledMode").forceSetNumber(1);
    }
    
    public static Limelight getInstance() {
    	if (lime == null) lime = new Limelight();
    	return lime;
    }
    
    /**
     * Reads values from NetworkTable, puts them to SmartDashboard
     */
    public void readNetworkTable(){
    	//Turn green LEDs on Limelight off.
    	table.getEntry("ledMode").forceSetNumber(1);
		
		//Get important values from the Limelight NetworkTables.
		
		//Get Horiz. Cursor Offset
		NetworkTableEntry tx = table.getEntry("tx");
		
		//Get Vert. Cursor Offset
		NetworkTableEntry ty = table.getEntry("ty");
		
		//Get total Bounding Box Area
		NetworkTableEntry ta = table.getEntry("ta");
		
		//Get total Bounding Box Area
		NetworkTableEntry tv = table.getEntry("tv");
		
		
		//Assign NetworkTableEntries to doubles
		x = tx.getDouble(-1);
		y = ty.getDouble(-1);
		area = ta.getDouble(-1);
		
		cubeDetected = tv.getDouble(0) == 1;
		
		//Publish Limelight values to ShuffleBoard
		SmartDashboard.putNumber("Horizontal Cursor Offset", x);
		SmartDashboard.putNumber("Vertical Cursor Offset", y);
		SmartDashboard.putNumber("Target Area", area);
    }
    
    /**
     * Gets the center X value of the cube's contours, or -1 if there are no contours
     * @return center X of cube or -1
     */
    public double getX() {
    	readNetworkTable();
    	if (cubeDetected) return x;
    	return -1;
    }
    
    /**
     * Gets the center Y value of the cube's contours, or -1 if there are no contours
     * @return center Y of cube or -1
     */
    public double getY() {
    	readNetworkTable();
    	if (cubeDetected) return y;
    	return -1;
    }
    
    /**
     * Gets the area of the cube's contours, or -1 if there are no contours
     * @return area of cube or -1
     */
    public double getArea() {
    	readNetworkTable();
    	if (cubeDetected) return area;
    	return -1;
    }

    /**
     * Sets default command to {@link org.usfirst.frc.team1100.robot.commands.vision.DefaultVision DefaultVision}
     */
    public void initDefaultCommand() {
        setDefaultCommand(new DefaultVision());
    }
}

