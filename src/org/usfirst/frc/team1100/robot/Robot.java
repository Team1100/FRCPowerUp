package org.usfirst.frc.team1100.robot;

import org.usfirst.frc.team1100.robot.commands.auto.CenterStartLeftSwitch;
import org.usfirst.frc.team1100.robot.commands.auto.CenterStartRightSwitch;
import org.usfirst.frc.team1100.robot.commands.auto.CrossLine;
import org.usfirst.frc.team1100.robot.commands.auto.LeftStartLeftScale;
import org.usfirst.frc.team1100.robot.commands.auto.LeftStartRightScale;
import org.usfirst.frc.team1100.robot.commands.auto.RightStartLeftScale;
import org.usfirst.frc.team1100.robot.commands.auto.RightStartRightScale;
import org.usfirst.frc.team1100.robot.subsystems.Claw;
import org.usfirst.frc.team1100.robot.subsystems.Elevator;
import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.subsystems.Folder;
import org.usfirst.frc.team1100.robot.subsystems.Intake;
import org.usfirst.frc.team1100.robot.subsystems.Pi;
import org.usfirst.frc.team1100.robot.subsystems.PneumaticElevator;
import org.usfirst.frc.team1100.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
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
 * @version District Championship
 * 
 */
public class Robot extends IterativeRobot {

	/**
	 * The singular instance of the AHRS class. There's only one NavX on the robot.
	 */
	CameraServer cs;
	Command autonomousCommand;
	SendableChooser<Integer> initPositionChooser = new SendableChooser<>();
	public static Integer initPosition = 0;
	
	public static final int LEFT_SIDE = 1;
	public static final int CENTERED = 0;
	public static final int RIGHT_SIDE = -1;
	
	final double DEFAULT_SPEED = 1.0;
	//PowerDistributionPanel pdp = new PowerDistributionPanel();
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
		Pi.getInstance();
		Elevator.getInstance();
		Claw.getInstance();
		Intake.getInstance();
		Wrist.getInstance();
		PneumaticElevator.getInstance();
		Folder.getInstance();
		
		cs = CameraServer.getInstance();
		cs.startAutomaticCapture("Drive", 0);
		
		Drive.getInstance().getNavX().zeroYaw();

		initPositionChooser.addObject("Left", 1);
		initPositionChooser.addObject("Middle",0);
		initPositionChooser.addObject("Right",-1);
		initPositionChooser.addObject("Cross Line", -2);
		SmartDashboard.putData("Initial Position", initPositionChooser);
	}

	/**
	 * Called once each time the robot enters Disabled mode.
	 * Turns of the blinding light of the limelight
	 */
	@Override
	public void disabledInit() {
		
	}
	
	/**
	 * Called when the robot is in Disabled mode.
	 * Writes all of the sensor values to ShuffleBoard
	 */
	@Override
	public void disabledPeriodic() {
		//CameraServer.getInstance().startAutomaticCapture();
		SmartDashboard.putNumber("Yaw", Drive.getInstance().getNavX().getYaw());
		SmartDashboard.putBoolean("Top", Elevator.getInstance().getTopLimit());
		SmartDashboard.putBoolean("Near Bottom", Elevator.getInstance().getNearBottomLimit());
		SmartDashboard.putBoolean("Bottom", Elevator.getInstance().getBottomLimit());
		SmartDashboard.putNumber("Wrist Pot", Wrist.getInstance().getVoltage());
		SmartDashboard.putNumber("Elevator Voltage", (Elevator.getInstance().getVoltage()));
		Scheduler.getInstance().run();
	}

	/**
	 * Called when the robot first enters auto.
	 * Finds the auto command that is selected in SmartDashboard, and runs it.
	 */
	@Override
	public void autonomousInit() { 
		Drive.getInstance().getNavX().zeroYaw();
		initPosition = initPositionChooser.getSelected();
		String message = DriverStation.getInstance().getGameSpecificMessage();
		int switchPosition = message.charAt(0) == 'L' ? LEFT_SIDE : RIGHT_SIDE;
		int scalePosition = message.charAt(1) == 'L' ? LEFT_SIDE : RIGHT_SIDE;
		if (initPosition == CENTERED) {
			if (switchPosition == LEFT_SIDE) {
				autonomousCommand = new CenterStartLeftSwitch(DEFAULT_SPEED, switchPosition, scalePosition);
			} else {
				autonomousCommand = new CenterStartRightSwitch(DEFAULT_SPEED, switchPosition, scalePosition);
			}
       	} else if (initPosition == LEFT_SIDE){
        	if (scalePosition == LEFT_SIDE) {
        		autonomousCommand = new LeftStartLeftScale(DEFAULT_SPEED);
        	} else {
        		autonomousCommand = new LeftStartRightScale(DEFAULT_SPEED);
        	}
        } else if (initPosition == RIGHT_SIDE) {
        	if (scalePosition == LEFT_SIDE) {
        		autonomousCommand = new RightStartLeftScale(DEFAULT_SPEED);
        	} else {
        		autonomousCommand = new RightStartRightScale(DEFAULT_SPEED, switchPosition);
        	}
        } else {
        	autonomousCommand = new CrossLine();
        }
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
		//CameraServer.getInstance().startAutomaticCapture();
		SmartDashboard.putNumber("Yaw", Drive.getInstance().getNavX().getYaw());
		SmartDashboard.putBoolean("Top", Elevator.getInstance().getTopLimit());
		SmartDashboard.putBoolean("Near Bottom", Elevator.getInstance().getNearBottomLimit());
		SmartDashboard.putBoolean("Bottom", Elevator.getInstance().getBottomLimit());
		SmartDashboard.putNumber("Wrist Pot", Wrist.getInstance().getVoltage());
		SmartDashboard.putNumber("Elevator Voltage", (Elevator.getInstance().getVoltage()));
		SmartDashboard.putNumber("Encoder", Drive.getInstance().getEncoder().getDistance());
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
		Drive.getInstance().getNavX().zeroYaw();
	}

	/**
	 * Called periodically during teleop (~20ms).
	 * Continues to run commands either triggered in the OI class, or the default
	 * commands for any given subsystem. Also writes sensor values to ShuffleBoard
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("Encoder", Drive.getInstance().getEncoder().getDistance());
		SmartDashboard.putNumber("Yaw", Drive.getInstance().getNavX().getYaw());
		SmartDashboard.putBoolean("Top", Elevator.getInstance().getTopLimit());
		SmartDashboard.putBoolean("Near Bottom", Elevator.getInstance().getNearBottomLimit());
		SmartDashboard.putBoolean("Bottom", Elevator.getInstance().getBottomLimit());
		SmartDashboard.putNumber("Wrist Pot", Wrist.getInstance().getVoltage());
		SmartDashboard.putNumber("Elevator Voltage", (Elevator.getInstance().getVoltage()));
		Scheduler.getInstance().run();
	}

	/**
	 * Called periodically during test mode (~20ms). Allows testing to be done in Smart Dashboard.
	 */
	@Override
	public void testPeriodic() {
	}
}
