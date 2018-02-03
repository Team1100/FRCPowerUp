package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.vision.Limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

/**
 *
 */
public class ImageCapture extends Command {
	
	NetworkTable table;
	ArrayList<String> keys = new ArrayList<String>();
	Limelight lime;
	
    public ImageCapture() {
        requires(Limelight.getInstance());
        lime = Limelight.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lime.readNetworkTable();
    	for (String key :  SmartDashboard.getKeys()) {
    		if (!keys.contains(key)) {
    			System.err.println(key);
    			keys.add(key);
    		}
    		
    	}
    	
    	//SmartDashboard.putString("MPJG2", table.getInstance().getEntry("description").getString("Broken"));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
