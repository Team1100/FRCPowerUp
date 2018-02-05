package org.usfirst.frc.team1100.robot.commands.vision;

import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team1100.robot.subsystems.Limelight;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Attempt at capturing images using OpenCV. This entire class will become unnecessary in the next Limelight update.
 * @see <a href="https://github.com/LimelightVision/LimelightDocs/issues/2">For my discussion about this class with the Limelight devs</a>
 */
public class CaptureImage extends Command {
	/**
	 * State of captured image
	 */
	protected boolean imageCaptured = false;
	
	Limelight lime;
	VideoCapture camera;
	Mat frame;
	
	/**
	 * Requires Limelight subsystem
	 */
    public CaptureImage() {
        requires(Limelight.getInstance());
    }

    /**
     * Initializes VideoCapture object with MJPG stream. 90% sure this is the element
     * in the code that doesn't work.
     */
    protected void initialize() {
    	//TODO: Add OpenCV Library as User Library on local machine
    	// http://opencv-java-tutorials.readthedocs.io/en/latest/02-first-java-application-with-opencv.html#create-a-simple-application
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	//Creates VideoCapture object with the Limelight's ip address and video stream's port
    	camera = new VideoCapture("http://10.11.00.11:5800");
    	frame = new Mat();
    	lime = Limelight.getInstance();
    }
    
    /**
     * Captures image if contours are detected, saves frame as .jpg to Roborio at /home/lvuser/Images/Output.jpg .
     * This method works.
     */
    protected void execute() {
    	if (lime.readNetworkTable() && !imageCaptured) {
    		if (camera.read(frame)) {
	    		Imgcodecs.imwrite("/home/lvuser/Images/Output.jpg", frame);
	    		imageCaptured = true;
    		} else {
    			SmartDashboard.putBoolean("Open", camera.isOpened());
    		}
    	}
    }

    /**
     * Puts state of image capture to Shuffle Board.
     * @return State of captured image
     */
    protected boolean isFinished() {
    	SmartDashboard.putBoolean("Image Captured", imageCaptured);
        return imageCaptured;
    }

    /**
     * Releases VideoCapture object
     */
    protected void end() {
    	camera.release();
    }

    /**
     * Releases VideoCapture object, but should never be interrupted.
     */
    protected void interrupted() {
    	camera.release();
    }
}
