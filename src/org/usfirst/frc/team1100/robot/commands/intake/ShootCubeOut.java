package org.usfirst.frc.team1100.robot.commands.intake;

import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command shoots out a cube via the intake wheels
 */
public class ShootCubeOut extends Command {

    Intake intake;
    Timer t;
    double time;
    double speed;
    
    /**
     * Uses intake subsystem, sets up timer
     * @param time time which the wheels will roll
     */
    public ShootCubeOut(double time, double speed) {
    	requires(Intake.getInstance());
        intake = Intake.getInstance();
        t = new Timer();
        this.time = time;
        this.speed = speed;
    }

    /**
     * Starts timer, spins wheels
     */
    protected void initialize() {
    	intake.setSpeed(speed);
    	intake.spinWheels();
    	t.start();
    }

    /**
     * Spins intake wheels
     */
    protected void execute() {
    	intake.spinWheels();
    }

    /**
     * True if timer is over desired time
     */
    protected boolean isFinished() {
    	return t.get()>time;
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
