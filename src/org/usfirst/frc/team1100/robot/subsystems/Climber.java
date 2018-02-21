package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.climber.DefaultClimber;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
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
    private boolean slowDown;
    
    /*
     * Initializes all hardware instances
     */
    private Climber() {
    	climberOne = new VictorSP(RobotMap.C_CLIMB_ONE);
    	climberTwo = new VictorSP(RobotMap.C_CLIMB_TWO);
    	climberTwo.setInverted(true);
    	pot = new AnalogInput(RobotMap.C_CLIMB_POT); 
    	bottomLimit = new DigitalInput(RobotMap.C_BOTTOM_SWITCH);
    	nearBottomLimit = new DigitalInput(RobotMap.C_NEAR_BOTTOM_SWITCH);
    	topLimit = new DigitalInput(RobotMap.C_TOP_SWITCH);
    	slowDown = false;
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
    		slowDown = false;
    	} else if (speed > 0) {
    		canGoUp = true;
    	}
    	
    	if ((getNearBottomLimit() || slowDown) && speed > 0) {
    		speed = 0.5*speed;
    		slowDown = true;
    	}
    	if (!canGoDown && speed > 0) {
    		speed = 0;
    		out = false;
    	} else if (!canGoUp && speed < 0) {
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
     * Control this subsystem manually with Xbox Left Joystick Y
     */
    public void initDefaultCommand() {
    	setDefaultCommand(new DefaultClimber());
    }
}