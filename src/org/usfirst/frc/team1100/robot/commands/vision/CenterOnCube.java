package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.subsystems.vision.Limelight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CenterOnCube extends CommandGroup {

    public CenterOnCube() {
    	Limelight lime = Limelight.getInstance();
    	if (lime.getArea() != -1) {
    		System.err.println("Running");
	    	double centerX = lime.getX();
	    	double heading = 27/centerX;
	    	SmartDashboard.putNumber("Center Heading", Robot.getAHRS().getYaw()+heading);
			addSequential(new ChangeHeading(Robot.getAHRS().getYaw()+heading));
    	}
    }
}
