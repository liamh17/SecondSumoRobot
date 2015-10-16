
package org.usfirst.frc.team3167.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	private Joystick driveStick; 
	
	private Jaguar leftMotor; 
	private Jaguar rightMotor;
	private RobotDrive drive;
	private Ultrasonic sensor; 
	double distance; 
	
    public void robotInit()    
    {
 	driveStick = new Joystick(1); 
    	leftMotor = new Jaguar(1); 
    	rightMotor = new Jaguar(2); 
    	drive = new RobotDrive(leftMotor, rightMotor);
    	sensor = new Ultrasonic(1, 2);
    	
    	sensor.setEnabled(true);
    	sensor.setAutomaticMode(true);
    	distance = sensor.getRangeInches(); 
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
    	try
    	{
    		auton(); 
    	}
    	catch(InterruptedException ex) 
    	{
    		System.out.println("Autonomous didn't work! Error: " + ex.getMessage());
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
    	while(true) 
    	{
    		if(distance > 10) 
    		{
    	    	try 
    	    	{
    	    		drive();
    	    	}
    	    	catch(InterruptedException ex) 
    	    	{
    	    		System.out.println("Driving didn't work! Error: " + ex.getMessage());
    	    	}
    	    	
    	    	if(driveStick.getRawButton(5)) 
    	    	{
    	    		stop();
    	    	}
    		}
    		
    		else if(distance < 10) 
    		{
    			stop(); 
    		}
    	}

    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {

    }
    
    public void auton() throws InterruptedException
    {	
    	while(true) 
    	{
    	if(distance < 10.0) 
    	{
    		stop(); 
    		reverse(); 
    		leftTurn(); //or rightTurn(); 
    		
    	}
    	else if(distance > 10.0);
    	{
    		drive(); 
    	}
    	}
    }
    
    public void drive() throws InterruptedException 
    {
    	double forwardsBackwards = driveStick.getY(); 
    	double twist = driveStick.getTwist();
    	
    	drive.arcadeDrive(-forwardsBackwards, twist);
    }
    
    public void foward()
    {
    	leftMotor.set(0.5);
    	rightMotor.set(0.5);
    }
    
    public void reverse()
    {
    	leftMotor.set(-0.5);
    	rightMotor.set(-0.5);
    }
    
    public void leftTurn()
    {
    	leftMotor.set(-0.5);
    	rightMotor.set(-0.5);
    }
    
    public void rightTurn()
    {
    	leftMotor.set(0.5);
    	rightMotor.set(-0.5);
    } 

    public void stop() 
    {
    	leftMotor.set(0.0);
    	rightMotor.set(0.0);
    } 
}
