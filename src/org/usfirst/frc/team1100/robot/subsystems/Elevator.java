package org.usfirst.frc.team1100.robot.subsystems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.elevator.DefaultElevator;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * This subsystem controls the elevator/elevator
 */
public class Elevator extends Subsystem {

    private static Elevator elevator;
    VictorSP climberOne;
	VictorSP climberTwo;
	VictorSP climberThree;
    AnalogInput pot; // potentiometer
    DigitalInput bottomLimit, nearBottomLimit, topLimit; // Limit switches
    double bottom;
    double top;
    boolean canGoUp = true;
    boolean canGoDown = true;
    private boolean slowDown;
    final String filename = "/home/lvuser/heights.txt";
    RandomAccessFile writer;
    BufferedReader reader;
    String[] line;
    Double lastTop, lastBottom;
    
    /*
     * Initializes all hardware instances
     */
    private Elevator() {
    	climberOne = new VictorSP(RobotMap.C_CLIMB_ONE);
    	climberTwo = new VictorSP(RobotMap.C_CLIMB_TWO);
    	climberThree = new VictorSP(RobotMap.C_CLIMB_THREE);
    	climberTwo.setInverted(true);
    	pot = new AnalogInput(RobotMap.C_CLIMB_POT); 
    	bottomLimit = new DigitalInput(RobotMap.C_BOTTOM_SWITCH);
    	nearBottomLimit = new DigitalInput(RobotMap.C_NEAR_BOTTOM_SWITCH);
    	topLimit = new DigitalInput(RobotMap.C_TOP_SWITCH);
    	slowDown = false;
    	try {
    		writer = new RandomAccessFile(new File(filename), "rw");
    		reader = new BufferedReader(new FileReader(filename));
    		reader.mark(800);
    	} catch(IOException e) {
    		e.printStackTrace();
    		System.err.println("Big error, file did a die");
    	}
    	
    	try {
    		line = reader.readLine().split(" ");
    		if (line.length == 2) {
	    		top = Double.parseDouble(line[0]);
	    		bottom = Double.parseDouble(line[1]);
    		} else {
    			top = 0.8;
    			bottom = 4.0;
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    		top = 0.8;
			bottom = 4.0;
    	}
    }
    
    /**
     * Gets the single instance of the Elevator subsystem
     * @return The single instance of the Elevator subsystem
     */
    public static Elevator getInstance() {
    	if (elevator == null) elevator = new Elevator();
    	return elevator;
    }
    
    /**
     * Climbs with the given speed. Will not climb past either limit switch.
     * @param speed Speed of elevator
     * @return Whether climb was successful, false if switch is triggered and wants to move past
     */
    public boolean climb(double speed) {
    	boolean out = true;
    	
    	//Don't move because of limit switches or potentiometer
    	if (getBottomLimit() || getVoltage() > bottom) {
    		canGoDown = false;
    	} else if (getTopLimit() || getVoltage() < top) {
    		canGoUp = false;
    	} else if (getNearBottomLimit()) {
    		canGoUp = true;
    		canGoDown = true;
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
    	climberThree.set(speed);
    	return out;
    }
    
    /**
     * Gets height of elevator
     * @return Height of elevator
     */
    public double getVoltage() {
    	try {
			reader.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
    	top = bottom - 3.5;
    	double tmpTop = Math.round(top * 10) / 10;
		double tmpBottom = Math.round(bottom * 10) / 10;
    	try {
    		writer.seek(0);
    		line = reader.readLine().split(" ");
    		if (line.length == 2) {
	    		lastTop = Double.parseDouble(line[0]);
	    		lastBottom = Double.parseDouble(line[1]);
	    		if (tmpBottom != lastBottom)
	    			writer.write((Double.toString(tmpTop) + " "+ Double.toString(tmpBottom)).getBytes());
    		} else {
    			writer.write((Double.toString(tmpTop) + " "+ Double.toString(tmpBottom)).getBytes());
    		}
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /* 
     *                                      
     * Sets top to current voltage
     */
    private void setTop() {
    	top = getVoltage();
    	bottom = top + 3.5;
    	bottom = getVoltage();
    	top = bottom - 3.5;
    	double tmpTop = Math.round(top * 10) / 10;
		double tmpBottom = Math.round(bottom *10) / 10;
    	try {
    		writer.seek(0);
    		line = reader.readLine().split(" ");
    		if (line.length == 2) {
	    		lastTop = Double.parseDouble(line[0]);
	    		lastBottom = Double.parseDouble(line[1]);
	    		if (tmpTop != lastTop)
	    			writer.write((Double.toString(tmpTop) + " "+ Double.toString(tmpBottom)).getBytes());
    		} else {
    			writer.write((Double.toString(tmpTop) + " "+ Double.toString(tmpBottom)).getBytes());
    		}
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Gets the lowest height possible
     * @return Lowest height of elevator
     */
    public double getBottom() {
    	return bottom;
    }
    
    /**
     * Gets the highest height possible
     * @return Highest height of elevator
     */
    public double getTop() {
    	return top;
    }
    
    /**
     * Control this subsystem manually with Xbox Left Joystick Y
     */
    public void initDefaultCommand() {
    	setDefaultCommand(new DefaultElevator());
    }
}