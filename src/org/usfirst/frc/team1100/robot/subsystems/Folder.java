package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem controls the 2 pneumatic pistons the fold the elevator/elevator
 */
public class Folder extends Subsystem {

    private static Folder folder;
    DoubleSolenoid pFolder;
    
    /**
     * Gets the singular instance of the Folder subsystem
     * @return the singular instance of the folder subsystem
     */
    public static Folder getInstance() {
    	if (folder == null) {
    		folder = new Folder();
    	}
    	return folder;
    }
    
    private Folder() {
    	pFolder = new DoubleSolenoid(RobotMap.F_FOLDER_CAN, RobotMap.F_FOLDER_0, RobotMap.F_FOLDER_1);
    }
    
    /**
     * Activates folder
     */
    public void fold() {
    	pFolder.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * Deactivates folder
     */
    public void unfold() {
    	pFolder.set(DoubleSolenoid.Value.kReverse);
    }
    
    /**
     * Unused
     */
    public void initDefaultCommand() {
    	
    }
}

