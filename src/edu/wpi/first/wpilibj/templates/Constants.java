/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 * Constants for real bot
 * @author Developer
 */
public class Constants
{
    //auton
    public static final int AUTO_BIT_1_CHANNEL = 3;
    public static final int AUTO_BIT_2_CHANNEL = 4;
    public static final int AUTO_BIT_3_CHANNEL = 5;
    public static final int AUTO_BIT_4_CHANNEL = 6;
    public static final int AUTO_DELAY_CHANNEL = 7;
    public static final int LIGHT_SWITCH_CHANNEL = 8;
    
    //speed
    public static final double REDUCE_DRIVE_SPEED = .6; //decimal between 0 and 1
    public static final int SAFE_MODE_SWITCH_CHANNEL = 2; //channel for switch use slow mode
    
    //motors 
    public static final int DRIVE_ENCODER_A_CHANNEL = 9;
    public static final int DRIVE_ENCODER_B_CHANNEL = 10;
    public static final int FRONT_RIGHT_MOTOR_CHANNEL = 7;
    public static final int FRONT_LEFT_MOTOR_CHANNEL = 4;
    public static final int BACK_RIGHT_MOTOR_CHANNEL = 10;
    public static final int BACK_LEFT_MOTOR_CHANNEL = 1;
    //pneumatics
    public static final int COMPRESSOR_SWITCH = 14;
    public static final int COMPRESSOR_RELAY = 8;
    public static final int SOLENOID_TRACTION_DOWN_SLOT = 1;
    public static final int SOLENOID_TRACTION_DOWN_CHANNEL = 1;
    public static final int SOLENOID_TRACTION_UP_SLOT = 2;
    public static final int SOLENOID_TRACTION_UP_CHANNEL = 1;
    
    //shooter
    //up & down piston
    public static final int SOLENOID_SHOOTER_UP_SLOT = 1;
    public static final int SOLENOID_SHOOTER_UP_CHANNEL = 4;
    public static final int SOLENOID_SHOOTER_DOWN_SLOT = 2;
    public static final int SOLENOID_SHOOTER_DOWN_CHANNEL = 4;
    public static final int SOLENOID_SHOOTER_LOAD_A_SLOT = 1;
    public static final int SOLENOID_SHOOTER_LOAD_A_CHANNEL = 5;
    public static final int SOLENOID_SHOOTER_LOAD_B_SLOT = 2;
    public static final int SOLENOID_SHOOTER_LOAD_B_CHANNEL = 5;
    
    //hopper piston
    public static final int SOLENOID_HOPPER_1_SLOT = 1;
    public static final int SOLENOID_HOPPER_1_CHANNEL = 3;
    public static final int SOLENOID_HOPPER_2_SLOT = 2;
    public static final int SOLENOID_HOPPER_2_CHANNEL = 3;
    
    public static final int SOLENOID_SENSOR_POWER_SLOT = 1;
    public static final int SOLENOID_SENSOR_POWER_CHANNEL = 8;
    //motors
    public static final int PRIMARY_SHOOTER_MOTOR_CHANNEL = 3;
    public static final int SECONDARY_SHOOTER_MOTOR_CHANNEL = 2;
    //speed
    public static final int PRIMARY_SHOOTER_MOTOR_RPM = 4100; //Speed in RPM with sensor control
    public static final int PRIMARY_SHOOTER_MOTOR_REDUCED_RPM = 2000;
    public static final double SECONDARY_SHOTTER_MOTOR_SPEED = 1; //Speed directly set
    //speed sensor
    public static final int SHOOTER_SPEED_SENSOR_CHANNEL = 1;
    
    public static final int AUTO_GYRO_CHANNEL = 2;
    
    
    //arm
    //motors
    public static final int ARM_LIFT_MOTOR_CHANNEL = 9;
    public static final int ARM_PICKUP_MOTOR_CHANNEL = 8;
    //position sensor (encoder)
    public static final int ARM_POSITION_SENSOR_CHANNEL = 7;
    
    //positions
    public static final int ARM_POSITION_0 = 710; //down 730
    public static final int ARM_POSITION_1_DOWN = 230; //load 270
    public static final int ARM_POSITION_1_UP = 180; //200
    public static final int ARM_POSITION_2 = 65; //up 65
    public static final int ARM_POSITION_3 = 580; //low 600
    public static final int ARM_DEFAULT_POSITION = 2;
    
    //kracken
    public static final int SOLENOID_KRACKEN_10PT_DOWN_SLOT = 1;
    public static final int SOLENOID_KRACKEN_10PT_DOWN_CHANNEL = 6;
    public static final int SOLENOID_KRACKEN_10PT_UP_SLOT = 2;
    public static final int SOLENOID_KRACKEN_10PT_UP_CHANNEL = 6;
    public static final int SOLENOID_KRACKEN_FIRE_20PT_SLOT = 1;
    public static final int SOLENOID_KRACKEN_FIRE_20PT_CHANNEL = 2;
    //buttons
    public static final int KRACKEN_FIRE_BUTTON = 8;
    public static final int KRACKEN_IN_OUT_BUTTON = 7;
    
    public static final int LIGHTS_RED_SLOT = 2;
    public static final int LIGHTS_RED_CHANNEL = 7;
    public static final int LIGHTS_BLUE_SLOT = 1;
    public static final int LIGHTS_BLUE_CHANNEL = 7;
    public static final int LIGHTS_WHITE_SLOT = 2;
    public static final int LIGHTS_WHITE_CHANNEL = 8;
    
    //joysticks - driver
    public static final int JOYSTICK_LEFT_PORT = 2;
    public static final int JOYSTICK_RIGHT_PORT = 3;
    //axes
    public static final int DRIVE_AXIS = 2;
    //buttons
    public static final int ARM_UP_BUTTON = 3;
    public static final int ARM_DOWN_BUTTON = 2;
    public static final int ARM_LOW_BUTTON_LEFT = 5;
    public static final int ARM_LOW_BUTTON_RIGHT = 4;
    public static final int DRIVE_PISTON_BUTTON = 1;
    
    //gamepad - buttons
    public static final int GAMEPAD_BUTTONS_PORT = 1;
    //buttons
    public static final int ARM_LOAD_BUTTON = 2;
    public static final int ARM_PICKUP_BUTTON = 5;
    public static final int ARM_PICKUP_BACKWARDS_BUTTON = 6;
    public static final int SHOOTER_SHOOT_BUTTON = 3;
    public static final int SHOOTER_UP_BUTTON = 4;
    public static final int SHOOTER_DOWN_BUTTON = 1;
    //axes
    public static final int SHOOTER_ON_OFF_AXIS = 2;
    
    //Special Pnumatics
    public static final int SPNUMATICS_ALL_BUTTON = 11;
    public static final int SPNUMATICS_DRIVE_BUTTON = 10;
    public static final int SPNUMATICS_KRACKEN_BUTTON = 7;
    public static final int SPNUMATICS_SHOOTER_BUTTON = 6;
    
}
