package org.usfirst.frc.team1100.robot.commands.drive;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1100.robot.subsystems.Drive;
import org.usfirst.frc.team1100.robot.OI;
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

	/**
	 * Left speed values
	 */
	double left;
	/**
	 * Right speed value
	 */
	double right;

	//Heading values
	private double headingNow; 
    private double headingTarget;
    
    private PIDController pidController = getPIDController();
    
	private AHRS ahrs = OI.getInstance().getAHRS();
	
	SendableChooser<Double> angle = new SendableChooser<>();
	
	/**
	 * Requires Drive subsystem. Constructor sets up pidController.
	 * @param target the target heading for this command
	 * @param p the proportional value
	 * @param i the integral value
	 * @param d the derivative value
	 */
    public ChangeHeading(double target, double p, double i, double d) {
    	super(p,i,d);
        requires(Drive.getInstance()); 
        setSetpoint(0);
        setTargetHeading(target);
        setInputRange(-180.0, 180.0);
        pidController.setContinuous();
        pidController.setPercentTolerance(1.0);
        
        //Used for controlling command while testing
        angle.addDefault("0", 0.0);
		angle.addObject("90", 90.0);
		angle.addObject("-90", -90.0);
		angle.addObject("180", 180.0);
		SmartDashboard.putData("navX", angle);
    }

    /**
     * Returns the input for the PID controller. Called by that controller.
     */
    protected double returnPIDInput() {
    	
    	//Incorporated for testing, remove when done.
    	setTargetHeading(angle.getSelected());
    	
    	
        headingNow = ahrs.getYaw();
        return headingNow;
    }
    
    /**
     * Takes value given by PID controller to then turn to desired heading. Called by the
     * PID controller.
     */
    protected void usePIDOutput(double output) {

    	left = -output;
    	right = output;
    	Drive.getInstance().tankDrive(left, right);

    	Drive.getInstance().tankDrive(-output, output); // TODO: Are the signs still correct?
    	SmartDashboard.putNumber("PIDSpeed", output);
    }

    /**
     * Calls pidController's {@link edu.wpi.first.wpilibj.PIDController#onTarget() onTarget() method}
     * @return Boolean representing whether the robot is facing the correct heading or not
     */
    protected boolean isFinished() {
        return pidController.onTarget();
    }
    /**
     * Sets heading which the robot wants to which the robot wants to turn.
     * @param heading Heading is relevant to starting direction
     */
    public void setTargetHeading(double heading) {
        if (heading != headingTarget)
        {
            headingTarget = heading;
            setSetpoint(heading);
        }
    }
}
