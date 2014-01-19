/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Developer
 */
public class Arm implements Runnable
{
    //motors
    Victor LiftMotor; //motor that lifts the arm up and down
    Victor PickupMotor;
    
    AnalogChannel PositionSensor; //encoder to sense arm position
    
    Shooter ShooterInst;
    
    int Position; // the position of the arm, 0 - down, 1 - load, 2 - up
    
    boolean Running; //tells if the arm is running for the thread
    boolean Disabled; //disabled for climb
    
    Arm(Shooter shooter)
    {
        //set up objects
        LiftMotor = new Victor(Constants.ARM_LIFT_MOTOR_CHANNEL);
        PickupMotor = new Victor(Constants.ARM_PICKUP_MOTOR_CHANNEL);
        PositionSensor = new AnalogChannel(Constants.ARM_POSITION_SENSOR_CHANNEL);
        Position = Constants.ARM_DEFAULT_POSITION; //the default arm position
        ShooterInst = shooter;
    }
    
    public void setPosition(int position)
    {
        //set the position of the arm
        Position = position;
    }
    
    public int getPosition()
    {
        //get the position of the arm
        return Position;
    }
    
    public void pickupBackwards()
    {
        //turn the pickup on backward
        if(Position != 1)
        {
            PickupMotor.set(-.5);
        }
    }
    
    public void pickupOn()
    {
        //turn on the pickup
        if(Position == 0)
        {
            PickupMotor.set(.8);
        }
    }
    
    public void pickupOff()
    {
        //turn off the pickup
        if(Position != 1)
        {
            PickupMotor.set(0);
        }
    }
    
    public void start()
    {
        if(!Running)
        {
            //start the thread to position the arm
            Thread thread = new Thread(this);
            thread.start();
        }
    }
    
    public void setDisabled(boolean disabled)
    {
        Disabled = disabled;
    }

    public void run() 
    {
        //thread to adjust the position of the arm
        int setPoint; //the target point of the arm
        int currentPoint; //the location of the arm retrieved from the sensor
        double speed = 0;
        double error;
        while (true) 
        {            
            // change the set point of the arm based on what its position is set to
            if (Position == 0) 
            {
                setPoint = Constants.ARM_POSITION_0;
            }
            else if (Position == 1)
            {
                if(ShooterInst.getPositionUp())
                {
                    setPoint = Constants.ARM_POSITION_1_UP;
                }
                else
                {
                    setPoint = Constants.ARM_POSITION_1_DOWN;
                }
            }
            else if (Position == 2)
            {
                setPoint = Constants.ARM_POSITION_2;
            }
            else 
            {
                setPoint = Constants.ARM_POSITION_3;
            }
            
            currentPoint = PositionSensor.getValue(); //get the position from the sensor
                
            //System.out.println("Set Point " + setPoint);
            //System.out.println("Current Point " + currentPoint);
            error = setPoint - currentPoint; //get distance between current point and the target point
            //System.out.println("error " + error);
            speed = (error / 340)*Math.abs(error/340); //calculate the speed to move the arm
            if(speed > 1)
            {
                speed = 1;
            }
            if(speed < -1)
            {
                speed = -1;
            }
                    
            
            //minimum speed
            if (speed < .6 && speed > 0 && error > 12) {
                speed = .6;
            }
            else if(speed > -.6 && speed < 0 && error < -12){
                speed = -.6;
            }
            //System.out.println("Speed" + speed);
            if(speed > 0 && Position == 0) //slow down for bottom position
            {
                speed = speed / 4;
            }
            
            //Encoder Unplugged
            if(Math.abs(PositionSensor.getValue()) < 5)
            {
                speed = 0;
                System.out.println("Arm Encoder Unpluged");
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, "Warning: Arm Encoder");
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "Disconnected");
                DriverStationLCD.getInstance().updateLCD();
            }
            
            if(Disabled)
            {
                speed = 0;
            }
            
            
            LiftMotor.set(-speed); //update motor
            
            if(Position == 1) //Automated Feed
            {
                if(Math.abs(error) < 20)
                {
                    PickupMotor.set(.55);
                }
                else
                {
                    PickupMotor.set(0);
                }
            }
        }
    }
}
