package org.usfirst.frc.team1100.robot.commands.intake;

import org.usfirst.frc.team1100.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 * This command pulls a cube in via the intake wheels
 */
public class PullCubeIn extends Command {

    Intake intake;
    Timer t;
    double time;
    
    /**
     * Uses intake subsystem, initializes timer
     * @param time time desired to pull in cube
     */
    public PullCubeIn(double time) {
    	requires(Intake.getInstance());
        intake = Intake.getInstance();
        this.time = time;
        t = new Timer();
    }

    /**
     * Starts timer, spins wheels
     */
    protected void initialize() {
    	t.start();
    	intake.setSpeed(-.5);
    	intake.spinWheels();
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
        return t.get()>this.time;
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
