package org.usfirst.frc.team1100.robot.commands.vision;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import org.usfirst.frc.team1100.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SaveCubePNG extends Thread{
	
	private static final int[] START_BYTES = new int[]{0xFF, 0xD8};
	private static final int[] END_BYTES = new int[]{0xFF, 0xD9};
	private static final String STREAM_PREFIX = "mjpg:";
	public final String url = STREAM_PREFIX + "http://10.11.00.11:5800";
	private InputStream stream;
	private BufferedImage imageToDraw;
		  
	Limelight lime = Limelight.getInstance();
	public boolean imageCaptured = false;
	
	/**
	 * Sets up name of thread
	 */
	public SaveCubePNG() {
		super("Limelight Image Capture");
	}
	
	/**
	 * Saves the image that is deemed save-able by run(). File name: 
	 * Event name + Match type + "Match" + Match number
	 */
	public void save() {
		BufferedImage drawnImage = imageToDraw;
		String matchNumber = Integer.toString(DriverStation.getInstance().getMatchNumber());
		String matchType = DriverStation.getInstance().getMatchType().toString();
		String event = DriverStation.getInstance().getEventName();
		File outputfile = new File("/home/lvuser/Images/"+event+matchType+"Match"+matchNumber+".png");
		try {
			ImageIO.write(drawnImage, "png", outputfile);
			System.err.println("Image saved!");
			imageCaptured = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes stream and interrupts thread
	 */
	@Override
	public void interrupt() {
		  try {
			  if (stream != null) {
				  stream.close();
			  }
		  } catch (IOException ex) {
			  ex.printStackTrace();
		  }
		  super.interrupt();
		}
	  
	  
	/**
	 * Runs thread. Detects if image should be saved, saves it if so.
	 */
	@Override
	public void run() {
		SmartDashboard.putBoolean("Image Captured", imageCaptured);
		ByteArrayOutputStream imageBuffer = new ByteArrayOutputStream();
		long lastCheck = 0;
		
		loop:while (!interrupted()) {
			stream = getCameraStream();
			try {
				while (!interrupted() && stream != null) {
					while (System.currentTimeMillis() - lastCheck < 10) {
						stream.skip(stream.available());
						Thread.sleep(1);
					}
					lastCheck = System.currentTimeMillis();
					stream.skip(stream.available());
	
			        imageBuffer.reset();
			        readUntil(stream, START_BYTES);
			        Arrays.stream(START_BYTES).forEachOrdered(imageBuffer::write);
			        readUntil(stream, END_BYTES, imageBuffer);

			        if (lime.readNetworkTable() && !imageCaptured) {
			        	ByteArrayInputStream tmpStream = new ByteArrayInputStream(imageBuffer.toByteArray());
				        imageToDraw = ImageIO.read(tmpStream);
			        	save();
			        	SmartDashboard.putBoolean("Image Captured", imageCaptured);
			        	Thread.currentThread().interrupt();
			        	break loop;
			        }
				}
	
			} catch (ArrayIndexOutOfBoundsException ex) {
				// Something really bad happened but we want to recover
				ex.printStackTrace();
			} catch (IOException ex) {
		      imageToDraw = null;
		      System.out.println(ex.getMessage());
		      Thread.currentThread().interrupt();
		    } catch (InterruptedException ex) {
		    	Thread.currentThread().interrupt();
		    	throw new RuntimeException(ex);
		    } finally {
		    	try {
		    		if (stream != null) {
		    			stream.close();
		    		}
		    	} catch (IOException ex) {
		    		ex.printStackTrace();
		    	}
		    }
		}
	}
	
	/**
	 * Gets all streams
	 * @return Stream object
	 */
	public Stream<String> streamPossibleCameraUrls() {
		//TODO: Check output of this, see if it's necessary at all. Make sure
		//      that CameraServer isn't a stream as well.
		return Stream.of(url);
	}
	
	/**
	 * Filters out all streams except Limelight stream from streamPossibleCameraUrls
	 * @return Camera stream
	 */
	private InputStream getCameraStream() {
		while (!interrupted()) {
			for (String streamUrl : streamPossibleCameraUrls()
			.filter(s -> s.startsWith(STREAM_PREFIX))
			.map(s -> s.substring(STREAM_PREFIX.length()))
			.collect(Collectors.toSet())) {
				System.out.println("Trying to connect to: " + streamUrl);
				try {
				    URL url = new URL(streamUrl);
				    URLConnection connection = url.openConnection();
				    connection.setConnectTimeout(500);
				    connection.setReadTimeout(5000);
				    InputStream stream = connection.getInputStream();
				    System.err.println("Connected to: " + streamUrl);
				    //ends while loop
				    return stream;
		      	} catch (IOException e) {
		      		System.err.println("Limelight not connected! SaveCubePNG.java:159");
		      		imageToDraw = null;
		      		try {
		      			Thread.sleep(500);
		      			System.err.println("Trouble connecting to Limelight, retrying! SaveCUbePNG.java:163");
		      		} catch (InterruptedException ex) {
		      			//ends while loop
		      			Thread.currentThread().interrupt();
		      			throw new RuntimeException(ex);
		      		}
		      	}
			}
		}
	return null;
	}
	
	
	private void readUntil(InputStream stream, int[] bytes) throws IOException {
		readUntil(stream, bytes, null);
	}
	
	private void readUntil(InputStream stream, int[] bytes, ByteArrayOutputStream buffer)
	    throws IOException {
		for (int i = 0; i < bytes.length; ) {
		    int b = stream.read();
		    if (b == -1) {
		    	throw new IOException("End of Stream reached");
			    }
			    if (buffer != null) {
			    	buffer.write(b);
			    }
			    if (b == bytes[i]) {
			    	i++;
			    } else {
			    	i = 0;
			    }
			}
		}
	}
