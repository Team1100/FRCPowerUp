
package org.usfirst.frc.team1100.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//version -> most recent event/stage of development. no numbers please i'm lazy
/**
 * 
 * @author Grant Perkins, Thor Smith, and Chris Perkins
 * @version original
 * This is the main class for the robot. The VM calls every method in this class at the 
 * appropriate time.
 */
public class Robot extends IterativeRobot {


	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This is called when the robot is first started up.
	 * All subsystem initialization is done here, by calling their respected getInstance()
	 * methods. Also, a SendableChooser object for choosing auto modes is sent to Smart Dashboard.
	 */
	@Override
	public void robotInit() {
		// PLEASE: remember to initialize all of the subsystems by calling their respective getInstance() method
		// If you fail to do this, it will not work and then it will be considered a software issue
		OI.getInstance();
		// chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		// SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * Disabled mode is when the amount of joysticks connected are insufficient for the code.
	 */
	@Override
	public void disabledInit() {

	}
	
	/**
	 * This function is called when the robot is in Disabled mode.
	 * I don't think there's every a need to actually use this function.
	 */
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called when the robot first enters auto.
	 * This function finds the auto command that is selected in Smart Dashboard, and
	 * runs it.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous (~20ms).
	 * This function just continues the command started in autonomousInit().
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/**
	 * This function is run when the robot first enters teleop.
	 * All this function does is end the autonomous command if it's still running.
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
	 * This function is called periodically during teleop (~20ms).
	 * This function just continues to run commands either triggered in the OI
	 * class, or the default commands for any given subsystem.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode (~20ms).
	 * It allows testing to be done is Smart Dashboard.
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
