package org.usfirst.frc.team1100.robot.commands.vision;

import org.usfirst.frc.team1100.robot.subsystems.Intake;
import org.usfirst.frc.team1100.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 * THis command runs the intake wheels until the cube is in the claw
 */
public class IntakeUntilIn extends Command {

    Intake intake;
    Timer t;
    
    /**
     * Uses intake subsystem
     */
    public IntakeUntilIn() {
    	requires(Intake.getInstance());
        intake = Intake.getInstance();
    }

    /**
     * Runs intake wheels
     */
    protected void initialize() {
    	intake.setSpeed(-.5);
    	intake.spinWheels();
    }

    /**
     * Runs intake wheels
     */
    protected void execute() {
    	intake.spinWheels();
    }

    /**
     * True if cube is in claw
     */
    protected boolean isFinished() {
    	//My logic is that if the cube is so close to the camera that everything is too dark to
    	//detect the cube, or if 65% of the image is the cube, the robot has the cube
        return Limelight.getInstance().getArea()==-1 || Limelight.getInstance().getArea() > 65;
    }

    /**
     * Stops intake wheels
     */
    protected void end() {
    	intake.stop();
    }

    /**
     * Stops intake wheels
     */
    protected void interrupted() {
    	intake.stop();
    }
}
