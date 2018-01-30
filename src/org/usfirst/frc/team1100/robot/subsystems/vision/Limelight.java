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
	double x, y, area;
	
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
		
		//Get important values from the Limelight NetworkTables.
		
		//Get Horiz. Cursor Offset
		NetworkTableEntry tx = table.getEntry("tx");
		
		//Get Vert. Cursor Offset
		NetworkTableEntry ty = table.getEntry("ty");
		
		//Get total Bounding Box Area
		NetworkTableEntry ta = table.getEntry("ta");
		
		
		//Assign NetworkTableEntries to doubles
		x = tx.getDouble(0);
		y = ty.getDouble(0);
		area = ta.getDouble(0);
		
		//Publish Limelight values to ShuffleBoard
		SmartDashboard.putNumber("Horizontal Cursor Offset", x);
		SmartDashboard.putNumber("Vertical Cursor Offset", y);
		SmartDashboard.putNumber("Target Area", area);
    }
    
    public double getX() {
    	return x;
    }
    
    public double getY() {
    	return y;
    }
    
    public double getArea() {
    	return area;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DefaultVision());
    }
}

