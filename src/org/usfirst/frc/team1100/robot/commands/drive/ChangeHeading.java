package org.usfirst.frc.team1100.robot.commands.drive;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

/**
 * This command uses the Drive susbsystem to turn the robot to a desired heading. The direction considered
 * 0 degrees is the direction in which the robot was oriented at the start of the match.
 * Yaw angle (the gyro value) is taken by calling an {@link org.usfirst.frc.team1100.robot.OI#getAHRS OI class method},
 * then calling {@link com.kauailabs.navx.frc.AHRS#getYaw getYaw method}. This command uses a PID controller.
 * @see <a href="http://www.ni.com/white-paper/3782/en/">A lesson on what a PID COntroller is and how it works.</a>
 */
public class ChangeHeading extends PIDCommand {

    private PIDController pidController = getPIDController();
    
	private AHRS ahrs = Robot.getAHRS();
	
	/**
	 * Requires Drive subsystem. Constructor sets up pidController. PID values pretested.
	 * @param target the target heading for this command
	 */
    public ChangeHeading(double target) {
    	super(.08, .01, .2);
        requires(Drive.getInstance()); 
        setSetpoint(target);
        setInputRange(-180.0, 180.0);
        pidController.setContinuous();
        pidController.setPercentTolerance(0.5);
    }

    /**
     * Returns the input for the PID controller. Called by that controller.
     */
    protected double returnPIDInput() {
        return ahrs.getYaw();
    }
    
    /**
     * Takes value given by PID controller to then turn to desired heading. Called by the
     * PID controller.
     */
    protected void usePIDOutput(double output) {
    	Drive.getInstance().tankDrive(-output, output); // TODO: Are the signs still correct?
    }
    int countOnTarget = 0;
    /**
     * Calls pidController's {@link edu.wpi.first.wpilibj.PIDController#onTarget() onTarget() method}
     * @return Boolean representing whether the robot is facing the correct heading or not
     */
    protected boolean isFinished() {
    	if (pidController.onTarget()) {
    		if (countOnTarget == 10) {
    			return true;
    		}
    		countOnTarget++;
    		
    	} else {
    		countOnTarget = 0;
    	}
    	return false;
    }
    }
