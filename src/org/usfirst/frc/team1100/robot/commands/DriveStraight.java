package org.usfirst.frc.team1100.robot.commands;

import org.usfirst.frc.team1100.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import com.kauailabs.navx.frc.AHRS;

/**
 *
 */
public class DriveStraight extends PIDCommand {

    private PIDController pidController = getPIDController();

    private AHRS ahrs = Robot.getAHRS();

	Timer t;
	double duration;
    double speed;
    boolean stopWhenFinished;

    /**
     * The DriveStraight constructor has three arguments to drive 
     * for duration in seconds, at a specified motor speed, in the 
     * direction of heading. 
     * 
     * @author C. Perkins (2/2/2018)
     * 
     * @param duration - time to run in seconds
     * @param speed - motor speed between -1 (reverse) and +1 
     *              (forward)
     * @param heading - direction in degrees (-180 to +180) relative 
     *                to starting direction
     */
    public DriveStraight(double duration, double speed, double heading) {
        this(duration, speed, heading, true);
    }

    /**
     * The DriveStraight constructor has three arguments to drive 
     * for duration in seconds, at a specified motor speed, in the 
     * direction of heading. 
     * 
     * @author C. Perkins (2/2/2018)
     * 
     * @param duration - time to run in seconds
     * @param speed - motor speed between -1 (reverse) and +1 
     *              (forward)
     * @param heading - direction in degrees (-180 to +180) relative 
     *                to starting direction
     * @param stopWhenFinished - motors are not stopped if set to 
     *                         false. Use the other constructor to
     *                         stop by default.
     */
    public DriveStraight(double duration, double speed, double heading, boolean stopWhenFinished) {
        super(.02, 0, 0);  // TODO - Tune these parameters (probably all P, no I or D)
        requires(Drive.getInstance());
        this.duration = duration;
        this.speed = -speed; // TODO - Why do we need the sign correction?
        this.stopWhenFinished = stopWhenFinished;

        setSetpoint(heading);
        setInputRange(-180.0, 180.0);
        pidController.setContinuous();
        pidController.setPercentTolerance(0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	t = new Timer();
    	t.start();
        Drive.getInstance().tankDrive(speed, speed);
    }

    // Called repeatedly when this Command is scheduled to run
    //protected void execute() {
    //}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return t.get() >= duration;
    }

    // Called once after isFinished returns true
    protected void end() {
        if (stopWhenFinished)
        {
            Drive.getInstance().tankDrive(0, 0);
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Drive.getInstance().tankDrive(0, 0);
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
        double left, right;
        left = speed * (1 - output);
        right = speed * (1 + output);
    	Drive.getInstance().tankDrive(left, right); // TODO: Are the signs still correct?
    }

}
