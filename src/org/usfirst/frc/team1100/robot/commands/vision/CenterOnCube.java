package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.commands.drive.ChangeHeading;
import org.usfirst.frc.team1100.robot.subsystems.vision.Limelight;


public class CenterOnCube {

	Limelight lime;
	
	private boolean breaker = false; 
	
	//Center X of Cube
	private double centerX = 0;
	
	public CenterOnCube() {
		centerX = lime.getX();
	}
	
	public void CubeCcenter() {
		while(breaker == false)
			new ChangeHeading(centerX*27);
			centerX = lime.getX();
			if(centerX <= .05 && centerX >= -.05) {
				breaker = true;
			}	
	}
}
