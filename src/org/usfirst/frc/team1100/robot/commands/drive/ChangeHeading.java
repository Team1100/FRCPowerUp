package org.usfirst.frc.team1100.robot.commands.drive;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.PIDController;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import com.kauailabs.navx.frc.AHRS;

/**
 * This command uses the Drive susbsystem to turn the robot to a desired heading. The direction considered
 * 0 degrees is the direction in which the robot was oriented at the start of the match.
 * Yaw angle (the gyro value) is taken by calling an {@link org.usfirst.frc.team1100.robot.subsystems.Drive#getNavX OI class method},
 * then calling {@link com.kauailabs.navx.frc.AHRS#getYaw getYaw method}. This command uses a PID controller.
 * @see <a href="http://www.ni.com/white-paper/3782/en/">A lesson on what a PID Controller is and how it works.</a>
 */
public class ChangeHeading extends PIDCommand {

    private PIDController pidController = getPIDController();
	private AHRS ahrs = Drive.getInstance().getNavX();
	private int countOnTarget;
	
	
	/**
	 * Uses Drive subsystem. Sets up pidController.
	 * @param target desired heading for robot, based on position set when robot was enabled (0)
	 * @param speed the limiting speed of the robot while turning
	 */
    public ChangeHeading(double target, double speed) {
    	super(.067, .03, .1);
        requires(Drive.getInstance());
        countOnTarget = 0;
        setSetpoint(target);
        setInputRange(-180.0, 180.0);
        pidController.setContinuous();
        pidController.setOutputRange(-speed, speed);
        pidController.setPercentTolerance(4);
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
    	Drive.getInstance().tankDrive(-output,output);
    }
    
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