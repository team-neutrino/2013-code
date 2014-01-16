/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 * Constants for practice bot
 * @author Developer
 */
public class ConstantsPractice
{
    //speed
    public static final double REDUCE_DRIVE_SPEED = .8; //decimal between 0 and 1
    public static final int SAFE_MODE_SWITCH_CHANNEL = 1; //channel for switch use slow mode
    
    //motors 
    public static final int FRONT_RIGHT_MOTOR_CHANNEL = 7;
    public static final int FRONT_LEFT_MOTOR_CHANNEL = 4;
    public static final int BACK_RIGHT_MOTOR_CHANNEL = 10;
    public static final int BACK_LEFT_MOTOR_CHANNEL = 1;
    //pneumatics
    public static final int COMPRESSOR_SWITCH = 2;
    public static final int COMPRESSOR_RELAY = 1;
    public static final int SOLENOID_FRONT_1_SLOT = 1;
    public static final int SOLENOID_FRONT_1_CHANNEL = 1;
    public static final int SOLENOID_FRONT_2_SLOT = 1;
    public static final int SOLENOID_FRONT_2_CHANNEL = 3;
    public static final int SOLENOID_BACK_1_SLOT = 1;
    public static final int SOLENOID_BACK_1_CHANNEL = 2;
    public static final int SOLENOID_BACK_2_SLOT = 1;
    public static final int SOLENOID_BACK_2_CHANNEL = 4;
    
    //shooter
    //up & down piston
    public static final int SOLENOID_SENSOR_POWER_SLOT = 1;
    public static final int SOLENOID_SENSOR_POWER_CHANNEL = 8;
    public static final int SOLENOID_SHOOTER_UP_SLOT = 1;
    public static final int SOLENOID_SHOOTER_UP_CHANNEL = 3;
    public static final int SOLENOID_SHOOTER_DOWN_SLOT = 2;
    public static final int SOLENOID_SHOOTER_DOWN_CHANNEL = 3;
    //hopper piston
    public static final int SOLENOID_HOPPER_1_SLOT = 1;
    public static final int SOLENOID_HOPPER_1_CHANNEL = 4;
    public static final int SOLENOID_HOPPER_2_SLOT = 2;
    public static final int SOLENOID_HOPPER_2_CHANNEL = 4;
    //motors
    public static final int PRIMARY_SHOOTER_MOTOR_CHANNEL = 2;
    public static final int SECONDARY_SHOOTER_MOTOR_CHANNEL = 3;
    //speed
    public static final int PRIMARY_SHOOTER_MOTOR_RPM = 500; //Speed in RPM with sensor control
    public static final double SECONDARY_SHOTTER_MOTOR_SPEED = 0.7; //Speed directly set
    //speed sensor
    public static final int SHOOTER_SPEED_SENSOR_CHANNEL = 2;
    
    //arm
    //motors
    public static final int ARM_LIFT_MOTOR_CHANNEL = 9;
    public static final int ARM_PICKUP_MOTOR_CHANNEL = 8;
    //position sensor (encoder)
    public static final int ARM_POSITION_SENSOR = 7;
    //positions
    public static final int ARM_POSITION_0 = 0; //down
    public static final int ARM_POSITION_1 = 500; //load
    public static final int ARM_POSITION_2 = 1000; //up
    public static final int ARM_DEFAULT_POSITION = 2;
    
    //gamepad - drive
    public static final int GAMEPAD_DRIVE_PORT = 1;
    //axes
    public static final int RIGHT_DRIVE_AXIS = 5;
    public static final int LEFT_DRIVE_AXIS = 2;
    //buttons
    public static final int ARM_UP_BUTTON = 4;
    public static final int ARM_DOWN_BUTTON = 1;
    public static final int FRONT_DRIVE_PISTON_BUTTON = 5;
    public static final int BACK_DRIVE_PISTON_BUTTON = 6;
    
    //gamepad - buttons
    public static final int GAMEPAD_BUTTONS_PORT = 2;
    //buttons
    public static final int ARM_LOAD_BUTTON = 2;
    public static final int ARM_PICKUP_BUTTON = 5;
    public static final int ARM_PICKUP_BACKWARDS_BUTTON = 6;
    public static final int SHOOTER_SHOOT_BUTTON = 3;
    public static final int SHOOTER_UP_BUTTON = 4;
    public static final int SHOOTER_DOWN_BUTTON = 1;
    //axes
    public static final int SHOOTER_ON_OFF_AXIS = 2;
}
