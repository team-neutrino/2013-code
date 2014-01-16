/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

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
    Solenoid SolenoidFront1;
    Solenoid SolenoidFront2;
    Solenoid SolenoidBack1;
    Solenoid SolenoidBack2;
    
    ButterflyDrive()
    {
        //set up motors
        FrontRightMotor = new Victor(Constants.FRONT_RIGHT_MOTOR_CHANNEL);
        FrontLeftMotor = new Victor(Constants.FRONT_LEFT_MOTOR_CHANNEL);
        BackRightMotor = new Victor(Constants.BACK_RIGHT_MOTOR_CHANNEL);
        BackLeftMotor = new Victor(Constants.BACK_LEFT_MOTOR_CHANNEL);
        SolenoidFront1 = new Solenoid(Constants.SOLENOID_FRONT_1_SLOT, Constants.SOLENOID_FRONT_1_CHANNEL);
        SolenoidFront2 = new Solenoid(Constants.SOLENOID_FRONT_2_SLOT, Constants.SOLENOID_FRONT_2_CHANNEL);
        SolenoidBack1 = new Solenoid(Constants.SOLENOID_BACK_1_SLOT, Constants.SOLENOID_BACK_1_CHANNEL);
        SolenoidBack2 = new Solenoid(Constants.SOLENOID_BACK_2_SLOT, Constants.SOLENOID_BACK_2_CHANNEL);
    }
    
    public void SetRightSpeed(double speed)
    {
        //set the speed of the motors on the right side of the robot
        FrontRightMotor.set(speed);
        BackRightMotor.set(speed);
    }
    
    public void SetLeftSpeed(double speed)
    {
        //set the speed of the motors on the left side of the robot
        FrontLeftMotor.set(-speed);
        BackLeftMotor.set(-speed);
    }
    
    public void Traction(boolean down)
    {
        //put traction wheels up and down
        if (down)
        {
          SolenoidFront1.set(true);
          SolenoidFront2.set(false);
          SolenoidBack1.set(true);
          SolenoidBack2.set(false);
        }
        else 
        {
          SolenoidFront1.set(false);
          SolenoidFront2.set(true);
          SolenoidBack1.set(false);
          SolenoidBack2.set(true);
        }
    }
}
