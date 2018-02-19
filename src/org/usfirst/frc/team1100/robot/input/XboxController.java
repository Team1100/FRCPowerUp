package org.usfirst.frc.team1100.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is for an XboxController. All information about any given button
 * or axis can be accessed through the functions written here.
 */
public class XboxController extends Joystick {

	private JoystickButton buttonA;
	private JoystickButton buttonB;
	private JoystickButton buttonX;
	private JoystickButton buttonY;
	private JoystickButton buttonLeftBumper;
	private JoystickButton buttonRightBumper;
	private JoystickButton buttonBack;
	private JoystickButton buttonStart;
	private JoystickButton buttonLeftStick;
	private JoystickButton buttonRightStick;
	private DirectionalPad dpad;
	private double deadband;	

	/**
	 * This enumeration is used for the 6 axes: the x and y of the two joysticks, as
	 * well as the two triggers.
	 *
	 */
	public enum XboxAxis {
		/**
		 * Left stick, x axis
		 */
		kXLeft(0),
		/**
		 * Left stick, y axis
		 */
		kYLeft(1),
		/**
		 * Left trigger
		 */
		kLeftTrigger(2),
		/**
		 * Right trigger
		 */
		kRightTrigger(3),
		/**
		 * Right stick, x axis
		 */
		kXRight(4),
		/**
		 * RIght stick, y axis
		 */
		kYRight(5);

		public final int key;

		/**
		 * This is the constructor of the enumeration. The keys provided to the
		 * constructor are used to access the value of each axis in getAxis().
		 * 
		 * @param key
		 *            the magical number assigned by the Driver Station
		 */
		private XboxAxis(int key) {
			this.key = key;
		}
	}

	/**
	 * Initializes a XBOX Controller on a specific channel, mapping the buttons. The
	 * JoyStick will never return a value in between +/- the deadband value.
	 * 
	 * @param channel
	 *            the channel the Controller is plugged into
	 * @param deadband
	 *            the value of the deadband, from 0 to 1
	 */
	public XboxController(int channel, double deadband) {
		super(channel);

		buttonA = new JoystickButton(this, 1);
		buttonB = new JoystickButton(this, 2);
		buttonX = new JoystickButton(this, 3);
		buttonY = new JoystickButton(this, 4);
		buttonLeftBumper = new JoystickButton(this, 5);
		buttonRightBumper = new JoystickButton(this, 6);
		buttonBack = new JoystickButton(this, 7);
		buttonStart = new JoystickButton(this, 8);
		buttonLeftStick = new JoystickButton(this, 9);
		buttonRightStick = new JoystickButton(this, 10);
		dpad = new DirectionalPad(this);

		this.deadband = deadband;
	}

	/**
	 * Gets the A Button from the Controller
	 * 
	 * @return the A Button
	 */
	public JoystickButton getButtonA() {
		return buttonA;
	}

	/**
	 * Gets the B Button from the Controller
	 * 
	 * @return the B Button
	 */
	public JoystickButton getButtonB() {
		return buttonB;
	}

	/**
	 * Gets the X Button from the Controller
	 * 
	 * @return the X Button
	 */
	public JoystickButton getButtonX() {
		return buttonX;
	}

	/**
	 * Gets the Y Button from the Controller
	 * 
	 * @return the Y Button
	 */
	public JoystickButton getButtonY() {
		return buttonY;
	}

	/**
	 * Gets the Left Bumper from the Controller
	 * 
	 * @return the Left Bumper
	 */
	public JoystickButton getButtonLeftBumper() {
		return buttonLeftBumper;
	}

	/**
	 * Gets the Right Bumper from the Controller
	 * 
	 * @return the Right Bumper
	 */
	public JoystickButton getButtonRightBumper() {
		return buttonRightBumper;
	}

	/**
	 * Gets the Back Button from the Controller
	 * 
	 * @return the Back Button
	 */
	public JoystickButton getButtonBack() {
		return buttonBack;
	}

	/**
	 * Gets the Start Button from the Controller
	 * 
	 * @return the Start Button
	 */
	public JoystickButton getButtonStart() {
		return buttonStart;
	}

	/**
	 * Gets the Left Stick Button from the Controller
	 * 
	 * @return Left Stick Button
	 */
	public JoystickButton getButtonLeftStick() {
		return buttonLeftStick;
	}

	/**
	 * Gets the Right Stick Button from the Controller
	 * 
	 * @return Right Stick Button
	 */
	public JoystickButton getButtonRightStick() {
		return buttonRightStick;
	}

	/**
	 * Gets position of specified axis, accounting for the deadband
	 *
	 * @param axis
	 *            the XboxAxis (XboxController.XboxAxis.k___) to retrieve
	 * @return the value of the axis, with the deadband factored in
	 */
	public double getAxis(XboxAxis axis) {
		double val = getRawAxis(axis.key);
		if (Math.abs(val) <= deadband) {
			val = 0.0;
		}
		return val;
	}
	
	public DirectionalPad getDPAD() {
		return dpad;
	}
	
    /**
     * This is the relation of direction and number for .getPOV() used
     * in the DirectionalPad class.
     */
    public static enum DPAD {
        kUp(0),
        kUpRight(45),
        kRight(90),
        kDownRight(135),
        kDown(180),
        kDownLeft(225),
        kLeft(270),
        kUpLeft(315);

        private int value;

        /**
         * Constructor
         * @param value
         */
        DPAD(final int value) {
            this.value = value;
        }
        
        /**
         * Convert integers to DPAD values
         * @param value
         * @return DPAD with matching angle
         */
        public static DPAD getEnum(int angle) {
            angle = Math.abs(angle);
            angle %= 360;
            angle = Math.round(angle / 45) * 45;    // May have rounding errors. Due to rounding errors.
            
            DPAD[] all = DPAD.values();
            
            for(int i = 0; i < all.length; i++) {
                if (all[i].value == angle) {
                    return all[i] ;
                }
            }
            return DPAD.kUp;
        }
    }
	
	public static class DirectionalPad extends Button {
        
        private final Joystick parent;
        
        public final Button up;
        public final Button upRight;
        public final Button right;
        public final Button downRight;
        public final Button down;
        public final Button downLeft;
        public final Button left;
        public final Button upLeft;
        
        /**
         * Initializes buttons
         * @param parent 
         */
        DirectionalPad(final Joystick parent) {
            
            this.parent	    = parent;
            this.up         = new DPadButton(this, DPAD.kUp);
            this.upRight    = new DPadButton(this, DPAD.kUpRight);
            this.right      = new DPadButton(this, DPAD.kRight);
            this.downRight  = new DPadButton(this, DPAD.kDownRight);
            this.down       = new DPadButton(this, DPAD.kDown);
            this.downLeft   = new DPadButton(this, DPAD.kDownLeft);
            this.left       = new DPadButton(this, DPAD.kDown);
            this.upLeft     = new DPadButton(this, DPAD.kUpLeft);
        }
        
        /**
         * This class is used to represent each of the 8 values a
         * dPad has as a button.
         */
        public static class DPadButton extends Button {

            private final DPAD direction;
            private final DirectionalPad parent;

            /**
             * Constructor
             * @param parent
             * @param dPad
             */
            DPadButton(final DirectionalPad parent, final DPAD dPadDirection) {
                this.direction  = dPadDirection;
                this.parent     = parent;
            }
            
            @Override
            public boolean get() {
                return parent.getPOV() == direction.value;
            }
        }
        
        public int getPOV() {
        	return parent.getPOV();
        }
        
        /**
         * Just like getAngle, but returns a direction instead of an angle
         * @return A DPAD direction
         */
        public DPAD getDirection() {
            return DPAD.getEnum(parent.getPOV());
        }

		@Override
		public boolean get() {
			return getPOV() != -1;
		}
		
		public boolean getUp() {
			return up.get();
		}
		
		public boolean getUpRight() {
			return upRight.get();
		}
		
		public boolean getRight() {
			return right.get();
		}
		
		public boolean getDownRight() {
			return downRight.get();
		}
		
		public boolean getDown() {
			return down.get();
		}
		
		public boolean getDownLeft() {
			return downLeft.get();
		}
		
		public boolean getLeft() {
			return left.get();
		}
		
		public boolean getUpLeft() {
			return upLeft.get();
		}
		
	}
}
