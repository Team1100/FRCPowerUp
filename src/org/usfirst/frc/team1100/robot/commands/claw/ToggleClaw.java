package org.usfirst.frc.team1100.robot.commands.claw;

import org.usfirst.frc.team1100.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ToggleClaw extends CommandGroup {

    public ToggleClaw() {
    	Claw claw = Claw.getInstance();
    	if (!claw.isOpen()) {
    		addSequential(new CloseClaw());
    	} else {
    		addSequential(new OpenClaw());
    	}
    }
}
