package org.usfirst.frc.team1100.robot.commands.folder;

import org.usfirst.frc.team1100.robot.subsystems.Folder;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command folds the robot
 */
public class Fold extends Command {
	
	Folder folder;
    public Fold() {
        requires(Folder.getInstance());
        folder = Folder.getInstance();
    }
    
    /**
     * Activates folder
     */
    protected void initialize() {
    	folder.fold();
    }
    
    /**
     * Unused
     */
    protected void execute() {
    }
    
    /**
     * Always is true, because folder is activated in initialize()
     */
    protected boolean isFinished() {
        return true;
    }
    
    /**
     * Unused
     */
    protected void end() {
    }

    /**
     * Unused
     */
    protected void interrupted() {
    }
}
