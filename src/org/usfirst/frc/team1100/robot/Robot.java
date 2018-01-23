package org.usfirst.frc.team1100.robot;

import org.opencv.core.Mat;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.vision.Vision;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.CvSink;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


//version -> most recent event/stage of development. no numbers please i'm lazy
/**
 * This is the main class for the robot. The VM calls every method in this class at the 
 * appropriate time.
 * 
 * @author Grant Perkins, Tejas Maraliga, Thor Smith, and Chris Perkins
 * @version Week 2
 * 
 */
public class Robot extends IterativeRobot {

	AHRS ahrs = OI.getInstance().getAHRS();
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	private Thread t;
	private final Object imgLock = new Object();
	Timer timer;
	/**
	 * Called when the robot is first started up.
	 * Initializes all subsystems by calling their respective getInstance() methods. Also,
	 * it sends a SendableChooser object for choosing auto modes to Smart Dashboard.
	 */
	@Override
	public void robotInit() {
		// PLEASE: remember to initialize all of the subsystems by calling their respective getInstance() method
		// If you fail to do this, it will not work and then it will be considered a software issue
		OI.getInstance();
		Drive.getInstance();
		timer = new Timer();
		timer.start();
		
		
        
		ahrs.zeroYaw();

		// chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		// SmartDashboard.putData("Auto mode", chooser);
		
		/*
		 * This thread is for vision, running on the RoboRio.
		 * It configures the USB camera, and prepares to plug images into GRIP
		 * 
		 * If a command calls Vision.getInstance().request(), the if statement code is run
		 */ 
		this.t = new Thread(() -> {
			
			//vision camera plugged into roborio
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("cam0",0);
	        camera.setExposureManual(40);
	        camera.setFPS(15);
	        
			CvSink cvSink = CameraServer.getInstance().getVideo();
			Mat source = new Mat();
			
			while(!Thread.interrupted()) {
				if (Vision.getInstance().isImageRequested()) {
					//saves image
	                cvSink.grabFrame(source);
	                Vision.getInstance().process(source);
	                Vision.getInstance().imgRequest = false;
				}
			}
	    });
	    t.start();
	}

	/**
	 * Called once each time the robot enters Disabled mode.
	 * Disabled mode is when the amount of joysticks connected are insufficient for the code.
	 */
	@Override
	public void disabledInit() {

	}
	
	/**
	 * Called when the robot is in Disabled mode.
	 * I don't think there's ever a need to actually use this function.
	 */
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * Called when the robot first enters auto.
	 * Finds the auto command that is selected in Smart Dashboard, and runs it.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * Called periodically during autonomous (~20ms).
	 * Continues the command started in autonomousInit().
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/**
	 * Called when the robot first enters teleop.
	 * Ends the autonomous command if it's still running.
	 * NOTE: One doesn't actually need to stop the command, it is simply done so
	 * that the drivers aren't confused and call it a software "issue".
	 */
	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		
	}

	/**
	 * Called periodically during teleop (~20ms).
	 * Continues to run commands either triggered in the OI class, or the default
	 * commands for any given subsystem.
	 */
	@Override
	public void teleopPeriodic() {

		SmartDashboard.putNumber("Yaw", ahrs.getYaw());
        
		Scheduler.getInstance().run();

	}

	/**
	 * Called periodically during test mode (~20ms). Allows testing to be done in Smart Dashboard.
	 */
	@Override
	public void testPeriodic() {
	}
}
