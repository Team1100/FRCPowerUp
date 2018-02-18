package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.Robot;
import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.climber.ClimberDefault;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * This subsystem controls the climber/elevator
 */
public class Climber extends Subsystem {

    private static Climber climber;
    VictorSP climberOne;
	VictorSP climberTwo;
    AnalogInput pot; // potentiometer
    DigitalInput bottomLimit, nearBottomLimit, topLimit; // Limit switches
    double bottom = 3.6;
    double top = 0;
    final double CLIMB_RANGE = 3.6;
    boolean canGoUp = true;
    boolean canGoDown = true;
    private static double rmCurrent1, rmCurrent2;
    private final double RM_CURRENT_LIMIT = 25.0 * 25.0;
    private final double tcGain = 0.01;    
    
    //TODO: Cant go down when hit speed controller
    
    private Climber() {
    	climberOne = new VictorSP(RobotMap.C_CLIMB_ONE);
    	climberTwo = new VictorSP(RobotMap.C_CLIMB_TWO);
    	climberTwo.setInverted(true);
    	pot = new AnalogInput(RobotMap.C_CLIMB_POT); 
    	bottomLimit = new DigitalInput(RobotMap.C_BOTTOM_SWITCH);
    	nearBottomLimit = new DigitalInput(RobotMap.C_NEAR_BOTTOM_SWITCH);
    	topLimit = new DigitalInput(RobotMap.C_TOP_SWITCH);
    }
    
    /**
     * Gets the single instance of the Climber subsystem
     * @return The single instance of the Climber subsystem
     */
    public static Climber getInstance() {
    	if (climber == null) climber = new Climber();
    	return climber;
    }
    
    /**
     * Climbs with the given speed. Will not climb past either limit switch.
     * @param speed Speed of climber
     * @return Whether climb was successful, false if switch is triggered and wants to move past
     */
    public boolean climb(double speed) {
    	boolean out = true;
    	
    	if (getBottomLimit()) {
    		canGoDown = false;
    	} else if (getTopLimit()) {
    		canGoUp = false;
    	}
    	
    	if (speed < 0) {
    		canGoDown = true;
    	} else if (speed > 0) {
    		canGoUp = true;
    	}
    	
    	if (getNearBottomLimit() && speed > 0) {
    		speed = 0.5*speed;
    	} else if (!canGoDown && speed > 0) {
    		speed = 0;
    		out = false;
    	} else if (!canGoUp && speed < 0) {
    		speed = 0;
    		out = false;
    	}
    	
    	if (climberOneHot() || climberTwoHot())
    	{
    		speed = 0;
    		out = false;
    	}
    	
    	climberOne.set(speed);
    	climberTwo.set(speed);
    	return out;
    }
    
    /**
     * Gets height of climber
     * @return Height of climber
     */
    public double getVoltage() {
    	return pot.getAverageVoltage();
    }
    
    /**
     * Gets state of bottom limit switch
     * @return True if the switch is pressed
     */
    public boolean getBottomLimit() {
    	if (!bottomLimit.get()) setBottom();
    	return !bottomLimit.get();
    }
    
    /**
     * Gets state of "near" bottom limit switch
     * @return True if the switch is pressed
     */
    public boolean getNearBottomLimit() {
    	return !nearBottomLimit.get();
    }
    
    /**
     * Gets state of top limit switch
     * @return True if the switch is pressed
     */
    public boolean getTopLimit() {
    	if (!topLimit.get()) setTop();
    	return !topLimit.get();
    }
    
    /**
     * Xbox Left Joystick Y
     */
    public void initDefaultCommand() {
    	setDefaultCommand(new ClimberDefault());
    }
    
    /*
     * Sets bottom to current voltage
     */
    private void setBottom() {
    	bottom = getVoltage();
    	top = getVoltage() - CLIMB_RANGE;
    }
    
    /*
     * Sets top to current voltage
     */
    private void setTop() {
    	top = getVoltage();
    	bottom = getVoltage() + CLIMB_RANGE;
    }
    
    /**
     * Gets the lowest height possible
     * @return Lowest height of climber
     */
    public double getBottom() {
    	return bottom;
    }
    
    /**
     * Gets the highest height possible
     * @return Highest height of climber
     */
    public double getTop() {
    	return top;
    }
    
    /**
     * Checks if motor ONE is too hot
     * @return True if it is too hot
     */
    public boolean climberOneHot() {
    	//double current = Robot.pdp.getCurrent(RobotMap.C_CLIMB_PDP_ONE);
    	//rmCurrent1 = rmCurrent1 + tcGain*((current*current) - rmCurrent1); // calculate the RM part of RMS current
    	//return rmCurrent1 > RM_CURRENT_LIMIT;
    	return false;
    }
    
    /**
     * Checks if motor TWO is too hot
     * @return True if it is too hot
     */
    public boolean climberTwoHot() {
    	//double current = Robot.pdp.getCurrent(RobotMap.C_CLIMB_PDP_TWO);
    	//rmCurrent2 = rmCurrent2 + tcGain*((current*current) - rmCurrent2); // calculate the RM part of RMS current
    	//return rmCurrent2 > RM_CURRENT_LIMIT;
    	return false;
    }
}

