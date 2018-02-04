package org.usfirst.frc.team1100.robot.commands.vision;

import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team1100.robot.subsystems.Limelight;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Attempt at capturing images using OpenCV
 */
public class CaptureImage extends Command {
	boolean imageCaptured = false;
	Limelight lime;
	VideoCapture camera;
	Mat frame;
	
    public CaptureImage() {
        requires(Limelight.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	camera = new VideoCapture("http://10.11.00.11:5800");

    	frame = new Mat();
    	lime = Limelight.getInstance();
    }

    protected void execute() {
    	if (lime.getArea() != -1 && !imageCaptured) {
    		if (camera.read(frame)) {
	    		Imgcodecs.imwrite("/home/lvuser/Images/Output.jpg", frame);
	    		imageCaptured = true;
    		} else {
    			SmartDashboard.putBoolean("Open", camera.isOpened());
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putBoolean("Image Captured", imageCaptured);
    	if (imageCaptured) camera.release();
        return imageCaptured;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
