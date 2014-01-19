/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Developer
 */
public class ButterflyDrive 
{
    //motors
    Victor FrontRightMotor;
    Victor FrontLeftMotor;
    Victor BackRightMotor;
    Victor BackLeftMotor;
    Solenoid SolenoidTractionDown;
    Solenoid SolenoidTractionUp;
    Encoder Encoder;
    
    Gyro Gyro;
    
    boolean TractionDown;
    
    boolean PnumaticsDisabled;
    ButterflyDrive()
    {
        //set up motors
        FrontRightMotor = new Victor(Constants.FRONT_RIGHT_MOTOR_CHANNEL);
        FrontLeftMotor = new Victor(Constants.FRONT_LEFT_MOTOR_CHANNEL);
        BackRightMotor = new Victor(Constants.BACK_RIGHT_MOTOR_CHANNEL);
        BackLeftMotor = new Victor(Constants.BACK_LEFT_MOTOR_CHANNEL);
        SolenoidTractionDown = new Solenoid(Constants.SOLENOID_TRACTION_DOWN_SLOT, Constants.SOLENOID_TRACTION_DOWN_CHANNEL);
        SolenoidTractionUp = new Solenoid(Constants.SOLENOID_TRACTION_UP_SLOT, Constants.SOLENOID_TRACTION_UP_CHANNEL);
        
        
        //Gyro
        Gyro = new Gyro(Constants.AUTO_GYRO_CHANNEL);
        
        Encoder = new Encoder(Constants.DRIVE_ENCODER_A_CHANNEL, Constants.DRIVE_ENCODER_B_CHANNEL);
        Encoder.start();
        
        TractionDown = false;
        
        PnumaticsDisabled = false;
    }
    
    public void driveInches(double distance, double leftSpeed, double rightSpeed)
    {
        if(!(leftSpeed == 0))
        {
            Encoder.reset();
            setRightSpeed(rightSpeed);
            setLeftSpeed(leftSpeed);
            while(Encoder.getDistance() < inchesToTicks(distance));
            setRightSpeed(0);
            setLeftSpeed(0);
        }
    }
    
    public void driveLeft(double degrees, double speed)
    {
        if(!(speed == 0))
        {
            Gyro.reset();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
            setRightSpeed(speed);
            setLeftSpeed(-speed);
            while(Gyro.getAngle() < degrees);
            setRightSpeed(0);
            setLeftSpeed(0);
        }
    }
    
    public void setRightSpeed(double speed)
    {
        //set the speed of the motors on the right side of the robot
        FrontRightMotor.set(speed);
        BackRightMotor.set(speed);
    }
    
    public void setLeftSpeed(double speed)
    {
        //set the speed of the motors on the left side of the robot
        FrontLeftMotor.set(-speed);
        BackLeftMotor.set(-speed);
    }
    
    public void tractionDown(boolean down)
    {
        if(!PnumaticsDisabled)
        {
            TractionDown = down;
            //put traction wheels up and down
            if (down)
            {
              SolenoidTractionDown.set(true);
              SolenoidTractionUp.set(false);
            }
            else 
            {
              SolenoidTractionDown.set(false);
              SolenoidTractionUp.set(true);
            }
        }
    }
    
    private double inchesToTicks(double inches)
    {
        if(TractionDown)
        {
            return inches * 220;
        }
        else
        {
            return inches * 100;
        }
    }

    void disablePnumatics()
    {
        SolenoidTractionDown.set(false);
        SolenoidTractionUp.set(false);
        
        PnumaticsDisabled = true;
        TractionDown = false;
    }
    
    void enablePnumatics()
    {
        SolenoidTractionDown.set(false);
        SolenoidTractionUp.set(true);
        
        PnumaticsDisabled = false;
        TractionDown = false;
    }
}
