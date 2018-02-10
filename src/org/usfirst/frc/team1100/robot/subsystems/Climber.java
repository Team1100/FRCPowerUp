package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.climber.ClimberDefault;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * 
 */
public class Climber extends Subsystem {

    private static Climber climber;
    Talon climberOne, climberTwo;
    AnalogInput pot; // potentiometer
    DigitalInput bottomLimit, topLimit; // Limit switches
    double bottom = 0;
    double top = 2.0;
    
    private Climber() {
    	climberOne = new Talon(RobotMap.C_CLIMB_ONE);
    	climberTwo = new Talon(RobotMap.C_CLIMB_TWO);
    	pot = new AnalogInput(RobotMap.C_POT); 
    	bottomLimit = new DigitalInput(RobotMap.C_BOTTOM_SWITCH);
    	topLimit = new DigitalInput(RobotMap.C_TOP_SWITCH);
    }
    
    public static Climber getInstance() {
    	if (climber == null) climber = new Climber();
    	return climber;
    }
    
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
    	return out;
    }
    
    public double getVoltage() {
    	return pot.getAverageVoltage();
    }
    
    /**
     * Gets state of limit switch
     * @return True if the switch is pressed
     */
    public boolean getBottomLimit() {
    	if (!bottomLimit.get()) setBottom();
    	return !bottomLimit.get();
    }
    
    public boolean getTopLimit() {
    	if (!topLimit.get()) setTop();
    	return !topLimit.get();
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new ClimberDefault());
    }
    
    private void setBottom() {
    	bottom = getVoltage();
    }
    
    private void setTop() {
    	top = getVoltage();
    }
    
    public double getBottom() {
    	return bottom;
    }
    
    public double getTop() {
    	return top;
    }
}

