package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.climber.ClimberDefault;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
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
    DigitalInput bottomLimit, topLimit; // Limit switches
    double bottom = 0;
    double top = 2.0;
    final double CLIMB_RANGE = 1.2; //Volts
    
    private Climber() {
    	climberOne = new VictorSP(RobotMap.C_CLIMB_ONE);
    	climberTwo = new VictorSP(RobotMap.C_CLIMB_TWO);
    	pot = new AnalogInput(RobotMap.C_CLIMB_POT); 
    	bottomLimit = new DigitalInput(RobotMap.C_BOTTOM_SWITCH);
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
    	if (Climber.getInstance().getBottomLimit() && speed > 0) {
    		speed = 0;
    		out = false;
    	} else if (Climber.getInstance().getTopLimit() && speed < 0) {
    		speed = 0;
    		out = false;
    	}
    	climberOne.set(speed);
    	climberTwo.set(speed);
    	SmartDashboard.putNumber("Diff", top-bottom);
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
     * Gets state of top limit switch
     * @return True if the switch is pressed
     */
    public boolean getTopLimit() {
    	if (!topLimit.get()) setTop();
    	return !topLimit.get();
    }
    
    /**
     * Unused
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
    	bottom = getVoltage() - CLIMB_RANGE;
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
}

