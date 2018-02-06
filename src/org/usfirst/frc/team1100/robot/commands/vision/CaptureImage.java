package org.usfirst.frc.team1100.robot.commands.vision;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.command.Command;


public class CaptureImage extends Command {
	boolean nfile = false;
	
    public CaptureImage() {
    }


    protected void initialize() {

    }
    

    protected void execute() {
    	
    	String text = "Hello world Linux";
        try {
        	File file = new File("/home/lvuser/Images/tp.png");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    	nfile = true;
    }


    protected boolean isFinished() {
		return nfile;

    }


    protected void end() {

    }


    protected void interrupted() {

    }
}
