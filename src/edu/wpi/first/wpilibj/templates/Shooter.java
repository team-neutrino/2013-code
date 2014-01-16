/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Developer
 */
public class Shooter implements Runnable
{
    //motors
    Victor PrimaryMotor;
    Victor SecondaryMotor;
    
    //up & down piston
    Solenoid SolenoidUp;
    Solenoid SolenoidDown;
    
    //piston to feed frisbees into the shooter
    Solenoid SolenoidHopper1;
    Solenoid SolenoidHopper2;
    Solenoid SolenoidSensorPower;
    
    Solenoid SolenoidLightsWhite;
    Solenoid SolenoidLightsRed;
    Solenoid SolenoidLightsBlue;
    
    //sensor to check the speed of the primary motor
    Counter SpeedSensor;
    
    //tells if the shooter is running for the thread
    boolean Running;
    
    boolean PositionUp;
    
    public Shooter()
    {
        //set up objects
        PrimaryMotor = new Victor(Constants.PRIMARY_SHOOTER_MOTOR_CHANNEL);
        SecondaryMotor = new Victor(Constants.SECONDARY_SHOOTER_MOTOR_CHANNEL);
        SolenoidUp = new Solenoid(Constants.SOLENOID_SHOOTER_UP_SLOT,Constants.SOLENOID_SHOOTER_UP_CHANNEL);
        SolenoidDown = new Solenoid(Constants.SOLENOID_SHOOTER_DOWN_SLOT, Constants.SOLENOID_SHOOTER_DOWN_CHANNEL);
        SolenoidHopper1 = new Solenoid(Constants.SOLENOID_HOPPER_1_SLOT, Constants.SOLENOID_HOPPER_1_CHANNEL);
        SolenoidHopper2 = new Solenoid(Constants.SOLENOID_HOPPER_2_SLOT,Constants.SOLENOID_HOPPER_2_CHANNEL);
        SpeedSensor = new Counter(Constants.SHOOTER_SPEED_SENSOR_CHANNEL);
        SpeedSensor.start();
        Running = false;
        SolenoidSensorPower = new Solenoid(Constants.SOLENOID_SENSOR_POWER_SLOT, Constants.SOLENOID_SENSOR_POWER_CHANNEL);
        SolenoidSensorPower.set(true);
        PositionUp = false;
        SolenoidLightsWhite = new Solenoid(Constants.LIGHTS_WHITE_SLOT,Constants.LIGHTS_WHITE_CHANNEL);
        SolenoidLightsRed = new Solenoid(Constants.LIGHTS_RED_SLOT,Constants.LIGHTS_RED_CHANNEL);
        SolenoidLightsBlue = new Solenoid(Constants.LIGHTS_BLUE_SLOT,Constants.LIGHTS_BLUE_CHANNEL);
        
    }
    
    public void setPositionUp(boolean position) 
    {
        //move the shooter between two positions
        PositionUp = position;
        if(position)
        {
            SolenoidUp.set(true);
            SolenoidLightsBlue.set(true);
            SolenoidDown.set(false);
            SolenoidLightsRed.set(false);
        }
        else
        {
            SolenoidUp.set(false);
            SolenoidLightsBlue.set(false);
            SolenoidDown.set(true);
            SolenoidLightsRed.set(true);
        }
    }
    
    public boolean getPositionUp()
    {
        return PositionUp;
    }
    
    public void load()
    {
        //feed frisbee into the shooter
        SolenoidHopper1.set(true);
        SolenoidHopper2.set(false);
    }
    public void shoot() 
    {
        //shoot the frisbee
        SolenoidHopper1.set(false);
        SolenoidHopper2.set(true);
        
    }
    
    public void start()
    {
        //start the shooter
        if (!Running)
        {
            SecondaryMotor.set(Constants.SECONDARY_SHOTTER_MOTOR_SPEED); //start the secondary motor
            Running = true; //tell the tread that the shooter is running
            Thread thread = new Thread(this); //make a thread to control the primary motor & start it
            thread.start();
        }
    }
    
    public void stop()
    {
        SecondaryMotor.set(0); //stop the secondary motor
        Running = false; //tell the thread to stop
        SolenoidLightsWhite.set(false);
    }

    public void run() 
    {
        double motorSpeed = .7; //the inital speed the motor is set to
        double sensorRawValue; //the raw value from the sensor
        double sensorRPM; //the value from the sensor converted to RPM
        while(Running)
        {
            //get the value from the sensor and convert it to RPM
            sensorRawValue = SpeedSensor.getPeriod();
            sensorRPM = 1 / sensorRawValue * 60;
            //System.out.println("Speed Sensor Raw Value:" + sensorRawValue);
            //System.out.println("Speed Sensor Converted to RPM:" + sensorRPM);
            
            //if the motor is spinning slower than the target RPM increase it's speed, if it's faster, decrease it's speed
            if((sensorRPM < Constants.PRIMARY_SHOOTER_MOTOR_RPM) && (motorSpeed < 1)) 
            {
                //System.out.println("SpeedingUp");
                motorSpeed = motorSpeed + .01;
            }
            else if ((sensorRPM > Constants.PRIMARY_SHOOTER_MOTOR_RPM) && (motorSpeed > 0))
            {
                 //System.out.println("SlowingDwon"); 
                 motorSpeed = motorSpeed - .01;
            }
            if(Math.abs(sensorRPM - Constants.PRIMARY_SHOOTER_MOTOR_RPM) < 200)
            {
                SolenoidLightsWhite.set(true);
            }
            else
            {
                SolenoidLightsWhite.set(false);
            }
            //System.out.println("Shooter Motor Speed:" + motorSpeed);
            
            //update the motor on it's speed
            //System.out.println("RPM:" + sensorRPM);
            //System.out.println("motorSpeed:" + motorSpeed + "\n");
            PrimaryMotor.set(motorSpeed);
        }
        PrimaryMotor.set(0); //turn off the motor
    }
}
