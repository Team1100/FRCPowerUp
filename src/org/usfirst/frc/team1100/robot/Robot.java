package org.usfirst.frc.team1100.robot;

import org.usfirst.frc.team1100.robot.commands.auto.Square;
import org.usfirst.frc.team1100.robot.commands.vision.SaveCubePNG;
import org.usfirst.frc.team1100.robot.subsystems.Claw;
import org.usfirst.frc.team1100.robot.subsystems.Climber;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Intake;
import org.usfirst.frc.team1100.robot.subsystems.Limelight;
import org.usfirst.frc.team1100.robot.subsystems.Wrist;

import com.kauailabs.navx.frc.AHRS;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.hal.MatchInfoData;


//version -> most recent event/stage of development. no numbers please i'm lazy
/**
 * This is the main class for the robot. The VM calls every method in this class at the 
 * appropriate time.
 * 
 * @author Grant Perkins, Tejas Maraliga, Matt Lefebvre Thor Smith, and Chris Perkins
 * @version Week 5
 * 
 */
public class Robot extends IterativeRobot {

	/**
	 * The singular instance of the AHRS class. There's only one NavX on the robot.
	 */
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	SendableChooser<Integer> initPositionChooser = new SendableChooser<>();
	Integer initPosition = 0;
	private SaveCubePNG saveCubeThread;
	public static SendableChooser<Double> angles = new SendableChooser<>();
	
	/**
	 * Whether an image is captured
	 */
	public static boolean imageCaptured = false;
	
	/**
	 * Called when the robot is first started up.
	 * Initializes all subsystems by calling their respective getInstance() methods. Also,
	 * it sends a SendableChooser object for choosing auto modes to Smart Dashboard.
	 */
	@Override
	public void robotInit() {
		// PLEASE: remember to initialize all of the subsystems by calling their respective getInstance() method
		// If you fail to do this, the robot will not work and then it will be considered a software issue
		OI.getInstance();
		Drive.getInstance();
		Limelight.getInstance();
		Climber.getInstance();
		Claw.getInstance();
		Intake.getInstance();
		Wrist.getInstance();
		
		Drive.getInstance().getNavX().zeroYaw();
		saveCubeThread = new SaveCubePNG();
		
		//Default code for auto selection, so I don't forget
		
		// chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		// SmartDashboard.putData("Auto mode", chooser);
		
		// Choose initial field position
		/*
		initPositionChooser.addObject("Left", 1);
		initPositionChooser.addObject("Middle",0);
		initPositionChooser.addObject("Right",-1);
		SmartDashboard.putData("Initial Position", initPositionChooser);
		*/
		angles.addObject("0", 0.0);
        angles.addObject("90", 90.0);
        angles.addObject("-90", -90.0);
        angles.addObject("180", 180.0);
        
        SmartDashboard.putData("Angle", angles);
	}

	/**
	 * Called once each time the robot enters Disabled mode.
	 * Disabled mode is when the amount of joysticks connected are insufficient for the code.
	 */

	@Override
	public void disabledInit() {
		SmartDashboard.putBoolean("Image Captured", Robot.imageCaptured);
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
	 * Finds the auto command that is selected in SmartDashboard, and runs it.
	 */
	@Override
	public void autonomousInit() { 
		//autonomousCommand = chooser.getSelected();
		initPosition = initPositionChooser.getSelected();
		String message = DriverStation.getInstance().getGameSpecificMessage();
		autonomousCommand = new Square();
		Drive.getInstance().getNavX().zeroYaw();
		Limelight.getInstance().readNetworkTable();
		if (autonomousCommand != null)
			//This is how one would use a command in another file. However, I like command groups.
			//Command groups allow for clarity about when/where commands are run.
			autonomousCommand.start();
	}

	/**
	 * Called periodically during autonomous (~20ms).
	 * Continues the command started in autonomousInit().
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Yaw", Drive.getInstance().getNavX().getYaw());
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
		Drive.getInstance().getNavX().zeroYaw();
		saveCubeThread = new SaveCubePNG();
		saveCubeThread.start();
		imageCaptured = false;
	}

	/**
	 * Called periodically during teleop (~20ms).
	 * Continues to run commands either triggered in the OI class, or the default
	 * commands for any given subsystem.
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("Yaw", Drive.getInstance().getNavX().getYaw());
		Scheduler.getInstance().run();
		
	}

	/**
	 * Called periodically during test mode (~20ms). Allows testing to be done in Smart Dashboard.
	 */
	@Override
	public void testPeriodic() {
	}
}
