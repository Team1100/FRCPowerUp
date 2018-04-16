package org.usfirst.frc.team1100.robot;

import org.usfirst.frc.team1100.robot.commands.Lower;
import org.usfirst.frc.team1100.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team1100.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team1100.robot.commands.elevator.ElevateToTop;
import org.usfirst.frc.team1100.robot.commands.elevator.PIDElevator;
import org.usfirst.frc.team1100.robot.commands.elevator.StopElevator;
import org.usfirst.frc.team1100.robot.commands.folder.Fold;
import org.usfirst.frc.team1100.robot.commands.folder.Unfold;
import org.usfirst.frc.team1100.robot.commands.pneumaticelevator.PneumaticElevate;
import org.usfirst.frc.team1100.robot.commands.pneumaticelevator.PneumaticLower;
import org.usfirst.frc.team1100.robot.commands.vision.CenterOnCube;
import org.usfirst.frc.team1100.robot.commands.vision.GrabCubeRaw;
import org.usfirst.frc.team1100.robot.commands.wrist.LowerWrist;
import org.usfirst.frc.team1100.robot.commands.wrist.RaiseWrist;
import org.usfirst.frc.team1100.robot.input.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * Triggers for commands through buttons are done in here.
 */
public class OI {
	/**
	 * The singular instance of the OI class
	 */
	private static OI oi;
	/**
	 * The singular instance of the XboxController class (although there may
	 * be more).
	 */
	private XboxController xbox;
	
	private AttackThree rightStick;
	private AttackThree leftStick;
	
	/**
	 * Gets the only instance of the OI class, used to access all input device data.
	 * @return the singular instance of the OI class
	 */
	public static OI getInstance() {
		if (oi == null) {
			oi = new OI();
		}
		return oi;
	}
	
	/**
	 * Initializes all input devices. It also establishes button triggers.
	 */
	private OI() {
		xbox = new XboxController(RobotMap.U_XBOX, 0.1);
		leftStick = new AttackThree(RobotMap.U_LEFT, 0.1);
		rightStick = new AttackThree(RobotMap.U_RIGHT, 0.1);

		//Elevator
		xbox.getButtonY().whenPressed(new ElevateToTop()); //Scale
		xbox.getButtonX().whenPressed(new PIDElevator(.55)); //Switch
		xbox.getButtonB().whenPressed(new StopElevator()); //Stop
		xbox.getButtonA().whenPressed(new Lower()); //Ground
		
		//Claw
		xbox.getButtonLeftBumper().whenPressed(new OpenClaw());
		xbox.getButtonRightBumper().whenPressed(new CloseClaw());
		
		//Wrist
		xbox.getDPad().getDown().whenPressed(new LowerWrist());
		xbox.getDPad().getUp().whenPressed(new RaiseWrist());
		
		//Pneumatic Elevator
		xbox.getButtonBack().whenPressed(new PneumaticElevate());
		xbox.getButtonStart().whenPressed(new PneumaticLower());
		
		//Vision
		leftStick.getButton(3).whenPressed(new CenterOnCube(3));
		rightStick.getButton(11).whenPressed(new GrabCubeRaw());
		
		//Folding
		leftStick.getButton(8).whenPressed(new Fold());
		leftStick.getButton(9).whenPressed(new Unfold());
	}
	
	/**
	 * Gets the only XboxController instance, used to access all XboxController data.
	 * @return the singular instance of the Xbox Controller
	 */
	public XboxController getXbox() {
		return xbox;
	}
	
	/**
	 * Gets the AttackThree instance of the left stick
	 * @return the left joystick
	 */
	public AttackThree getLeftStick() {
		return leftStick;
	}
	
	/**
	 * Gets the AttackThree instance of the right stick
	 * @return the right joystick
	 */
	public AttackThree getRightStick() {
		return rightStick; 
	}
}
