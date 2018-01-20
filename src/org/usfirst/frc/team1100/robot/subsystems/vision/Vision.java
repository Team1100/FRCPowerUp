package org.usfirst.frc.team1100.robot.subsystems.vision;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;


/**
 *
 */
public class Vision extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static Vision v;
	public boolean imgRequest = false;
	private GripPipeline grip;
	double centerX;
	
	public static Vision getInstance() {
		if (v == null) v = new Vision();
		return v;
	}
	
	private Vision() {
		grip = new GripPipeline();
	}
	
	public void process(Mat image) {
		if(grip == null) {
			System.err.println("Vision getInstance() not called, no pipeline created");
		}
		if(image == null) {
			System.err.println("Null image provided to Vision.process");
		}
		imgRequest = false;
		grip.process(image);
	}
	
	public synchronized boolean isImageRequested() {
		return imgRequest;
	}
	
	
    public void initDefaultCommand() {
        
    }

	public synchronized void request() {
		imgRequest = true;
	} 
	
	public synchronized boolean noHulls() {
		return grip.convexHullsOutput().isEmpty();
	}
	public synchronized double getCenterX() {
		return grip.getCenteroid(grip.convexHullsOutput().get(0)).x;
	}
}

