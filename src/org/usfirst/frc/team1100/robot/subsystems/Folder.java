package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Folder extends Subsystem {

    private Folder folder;
    DoubleSolenoid pFolder;
    
    public Folder() {
    	pFolder = new DoubleSolenoid(RobotMap.F_FOLDER_CAN, RobotMap.F_FOLDER_0, RobotMap.F_FOLDER_1);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

