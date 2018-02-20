package org.usfirst.frc.team1100.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import org.usfirst.frc.team1100.robot.subsystems.Claw;

/**
 *
 */
public class Proximity extends Trigger {
	private static Proximity proximity;
	private Proximity() {
	}
	
	public static Proximity getInstance() {
		if (proximity == null) proximity = new Proximity();
		return proximity;
	}
    public boolean get() {
        return Claw.getInstance().getProximity();
    }
}
