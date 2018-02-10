package org.usfirst.frc.team1100.robot.subsystems;

import org.usfirst.frc.team1100.robot.RobotMap;
import org.usfirst.frc.team1100.robot.commands.climber.ClimberDefault;
import org.usfirst.frc.team1100.robot.commands.climber.PIDClimber;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 *
 */
public class Climber extends Subsystem {

    private static Climber climber;
    Talon climberOne, climberTwo;
    AnalogInput analog;
    
    private Climber() {
    	climberOne = new Talon(RobotMap.C_CLIMB_ONE);
    	climberTwo = new Talon(RobotMap.C_CLIMB_TWO);
    	analog = new AnalogInput(RobotMap.C_POT);
    }
    
    public static Climber getInstance() {
    	if (climber == null) climber = new Climber();
    	return climber;
    }
    
    public void climb(double speed) {
    	climberOne.set(speed);
    	climberOne.set(speed);
    }
    
    public double getVoltage() {
    	return analog.getAverageVoltage();
    }
    
    public double getAccumulatorValue() {
    	return 0;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new ClimberDefault());
    }
}

