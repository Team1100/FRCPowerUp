package org.usfirst.frc.team1100.robot.commands.drive;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.PIDController;
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
 * @see <a href="http://www.ni.com/white-paper/3782/en/">this website</a>
 */
public class ChangeHeading extends PIDCommand {
<<<<<<< HEAD
	/**
	 * Left speed values
	 */
	double left;
	/**
	 * Right speed value
	 */
	double right;
=======
>>>>>>> 720f0b47a95432dd73987a59b1b5873c264d0219
	
	/**
	 * Heading values
	 */
	private double headingNow; 
    private double headingTarget;
    private double headingError;

    private PIDController pidController = getPIDController();
    
	private AHRS ahrs = OI.getInstance().getAHRS();
	
	/**
	 * Requires Drive subsystem.
	 * Provide Target, P,I,D parameters
	 * @param target the target heading for this command
	 * @param p the proportional value
	 * @param i the integral value
	 * @param d the derivative value
	 */
    public ChangeHeading(double target, double p, double i, double d) {
    	super(p,i,d);
        requires(Drive.getInstance()); 
<<<<<<< HEAD
        setSetpoint(0);
=======
>>>>>>> 720f0b47a95432dd73987a59b1b5873c264d0219
        setTargetHeading(target);
        setInputRange(-180.0, 180.0);
        pidController.setContinuous();
        pidController.setPercentTolerance(1.0);
    }

    /**
     * Returns the input for the PID controller. Called by that controller.
     */
    protected double returnPIDInput() {
    	setTargetHeading(Robot.angle.getSelected());
        headingNow = ahrs.getYaw();
        return headingNow;
    }
    
    /**
     * Takes value given by PID controller to then turn to desired heading. Called by the
     * PID controller.
     */
    protected void usePIDOutput(double output) {
<<<<<<< HEAD
    	left = -output;
    	right = output;
    	Drive.getInstance().tankDrive(left, right);
=======
    	Drive.getInstance().tankDrive(-output, output); // TODO: Are the signs still correct?
>>>>>>> 720f0b47a95432dd73987a59b1b5873c264d0219
    	SmartDashboard.putNumber("PIDSpeed", output);
    }

    /**
     * Unused.
     * \n\n
     * TODO: Use error to determine if robot is "close enough"
     */
    protected boolean isFinished() {
<<<<<<< HEAD
        return false;
=======
        return pidController.onTarget();
>>>>>>> 720f0b47a95432dd73987a59b1b5873c264d0219
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
<<<<<<< HEAD
=======
    /**
     * 
     * @param tolerance tolerance in degrees
     */
    public void setHeadingPercentTolerance(double tolerance) {
    	pidController.setPercentTolerance(tolerance);
    }
>>>>>>> 720f0b47a95432dd73987a59b1b5873c264d0219
}
