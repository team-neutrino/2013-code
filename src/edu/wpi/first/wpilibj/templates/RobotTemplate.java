

/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;



import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot 
{
    //Joystick GamepadDrive;
    Joystick JoystickLeft;
    Joystick JoystickRight;
    Joystick GamepadButtons;
    
    DigitalInput SafeModeSwitch;
    DigitalInput Auton1;
    DigitalInput Auton2;
    DigitalInput Auton3;
    DigitalInput Auton4;
    Compressor MainCompressor;
    ButterflyDrive Drive;
    Arm Arm;
    Shooter Shooter;
    Kracken Kracken;
    DigitalInput LightColorSwitch;
    Gyro AutoGyro;
    /**
     * This function is called when the robot has been turned on.
     */
    public void robotInit()
    {
        //setup objects
        //GamepadDrive = new Joystick(Constants.GAMEPAD_DRIVE_PORT);
        JoystickLeft = new Joystick(Constants.JOYSTICK_LEFT_PORT);
        JoystickRight = new Joystick(Constants.JOYSTICK_RIGHT_PORT);
        GamepadButtons = new Joystick(Constants.GAMEPAD_BUTTONS_PORT);
        Drive = new ButterflyDrive();
        SafeModeSwitch = new DigitalInput(Constants.SAFE_MODE_SWITCH_CHANNEL);
        Shooter = new Shooter();
        Arm = new Arm(Shooter);
        Kracken = new Kracken();
        MainCompressor = new Compressor(Constants.COMPRESSOR_SWITCH, Constants.COMPRESSOR_RELAY);
        Arm.start();
        //start the compressor
        MainCompressor.start();
        LightColorSwitch = new DigitalInput(Constants.LIGHT_SWITCH_CHANNEL);
        AutoGyro = new Gyro(Constants.AUTO_GYRO_CHANNEL);
        //set the arm position up and start it
        Arm.setPosition(2);
        
        //Shoot Plunger Default Out
        Shooter.load();
        
        //set the shooter down
        Shooter.setPositionUp(false);
    }
    
    /**
 
 
 * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() 
    {
        DigitalInput autoBit1Switch = new DigitalInput(Constants.AUTO_BIT_1_CHANNEL);
        DigitalInput autoBit2Switch = new DigitalInput(Constants.AUTO_BIT_2_CHANNEL);
        DigitalInput autoBit3Switch = new DigitalInput(Constants.AUTO_BIT_3_CHANNEL);
        DigitalInput autoBit4Switch = new DigitalInput(Constants.AUTO_BIT_4_CHANNEL);
        DigitalInput autoDelaySwitch = new DigitalInput(Constants.AUTO_DELAY_CHANNEL);
        int autoInput = 0;
        if(!autoBit1Switch.get())
        {
            autoInput = autoInput + 1;
        }
        if(!autoBit2Switch.get())
        {
            autoInput = autoInput + 2;
        }
        if(!autoBit3Switch.get())
        {
            autoInput = autoInput + 4;
        }
        if(!autoBit4Switch.get())
        {
            autoInput = autoInput + 8;
        }
        if(!autoDelaySwitch.get())
        {
            try {
                System.out.println("Delay");
                Thread.sleep(3000); //delay 3 seconds
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        if(autoInput == 0)
        {
            auto0();
        }
        
        if(autoInput == 1)
        {
            //Main 7 disc
            auto1();
        }
        else if(autoInput == 2)
        {
            //5 disc front
            auto2();
        }
        else if(autoInput == 3)
        {
            //4 disc middle
            auto3();
        }
        else if(autoInput == 4)
        {
            //3 disc back
            auto4();
        }
        else if(autoInput == 5)
        {
            //3 disc front
            auto5();
        }
        else if(autoInput == 6)
        {
            //3 disc back turn
            auto6();
        }
        else if(autoInput == 7)
        {
            //
            auto7();
        }
        else if(autoInput == 8)
        {
            auto8();
        }
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() 
    { 
        int driverPosition = Arm.getPosition(); //position requested by the driver
        boolean krackenInOutButtonOld = false;
        boolean krackenOut = false;
        
        while(true)
        {
            //arm position control
            //driver joystick
            if(JoystickLeft.getRawButton(Constants.ARM_UP_BUTTON) || JoystickRight.getRawButton(Constants.ARM_UP_BUTTON))
            {
                driverPosition = 2;
            }
            else if(JoystickLeft.getRawButton(Constants.ARM_DOWN_BUTTON) || JoystickRight.getRawButton(Constants.ARM_DOWN_BUTTON))
            {
                driverPosition = 0;
            }
            else if(JoystickLeft.getRawButton(Constants.ARM_LOW_BUTTON_LEFT) || JoystickRight.getRawButton(Constants.ARM_LOW_BUTTON_RIGHT))
            {
                driverPosition = 3;
            }
            //buttons override
            if(GamepadButtons.getRawButton(Constants.ARM_LOAD_BUTTON))
            {
                Arm.setPosition(1);
            }
            else
            {
                Arm.setPosition(driverPosition);
            }
            
            //pickup control
            if(GamepadButtons.getRawButton(Constants.ARM_PICKUP_BUTTON))
            {
                Arm.pickupOn();
            }
            else if(GamepadButtons.getRawButton(Constants.ARM_PICKUP_BACKWARDS_BUTTON))
            {
                Arm.pickupBackwards();
            }
            else
            {
                Arm.pickupOff();
            }
            
            //shooter
            //on-off
            if(GamepadButtons.getRawAxis(Constants.SHOOTER_ON_OFF_AXIS) == -1)
            {
                Shooter.start();
            }
            else if(GamepadButtons.getRawAxis(Constants.SHOOTER_ON_OFF_AXIS) == 1)
            {
                Shooter.stop();
            }
            //shoot
            if (GamepadButtons.getRawButton(Constants.SHOOTER_SHOOT_BUTTON))
            {
                Shooter.shoot();
            } 
            else
            {
                Shooter.load();
            }
            //up-down
            if(GamepadButtons.getRawButton(Constants.SHOOTER_UP_BUTTON))
            {
                Shooter.setPositionUp(true);
            }
            else if(GamepadButtons.getRawButton(Constants.SHOOTER_DOWN_BUTTON))
            {
                Shooter.setPositionUp(false);
            }
            
            //drive control
            double leftJoystickValue = -JoystickLeft.getRawAxis(Constants.DRIVE_AXIS);
            double rightJoystickValue = -JoystickRight.getRawAxis(Constants.DRIVE_AXIS);
            if(!SafeModeSwitch.get())
            {
                //slow mode
                Drive.SetRightSpeed(rightJoystickValue*Constants.REDUCE_DRIVE_SPEED);
                Drive.SetLeftSpeed(leftJoystickValue*Constants.REDUCE_DRIVE_SPEED);
            }
            else
            {
                //fast mode
                Drive.SetLeftSpeed(leftJoystickValue*Math.abs(leftJoystickValue));
                Drive.SetRightSpeed(rightJoystickValue*Math.abs(rightJoystickValue));
            }
            
            //traction piston control
            Drive.Traction(JoystickRight.getRawButton(Constants.DRIVE_PISTON_BUTTON) || JoystickLeft.getRawButton(Constants.DRIVE_PISTON_BUTTON));
            
            //kracken control
            if(GamepadButtons.getRawButton(Constants.KRACKEN_FIRE_BUTTON))
            {
                Kracken.fire();
            }
            boolean krackenInOutButtonNew = GamepadButtons.getRawButton(Constants.KRACKEN_IN_OUT_BUTTON);
            if(krackenInOutButtonNew && (krackenInOutButtonNew != krackenInOutButtonOld))
            {
                krackenOut = !krackenOut;
                Kracken.out(krackenOut);
            }
            krackenInOutButtonOld = krackenInOutButtonNew;
            
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() 
    {
    
    }
    
    public void autoShoot(int numberOfShots)
    {
        int shotsShot = 0;
        try
        {
            while((numberOfShots - shotsShot) > 1) 
            {
                Shooter.shoot();
                Thread.sleep(200);
                Shooter.load();
                Thread.sleep(300);
                shotsShot++;
                System.out.println("Shoot " + shotsShot);
                if((numberOfShots - shotsShot) > 1)
                {
                    Shooter.shoot();
                    Thread.sleep(200);
                    Shooter.load();
                    Thread.sleep(250);
                    shotsShot++;
                    System.out.println("Shoot " + shotsShot);
                }
            }
            Shooter.shoot();
            Thread.sleep(150);
            shotsShot++;
            System.out.println("Shoot " + shotsShot);
        } catch (Exception e) 
        {
        }
    }
    
    public void auto0()
    {
        try
        {
            System.out.println("Start Auton0");
            Arm.setPosition(0); //lower arm
            Drive.Traction(true);
            System.out.println("End Auton0");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void auto1()
    {
        //Main Auton
        try 
        {
            System.out.println("Start Auton1");
            Shooter.start();
            Shooter.setPositionUp(false);
            Arm.setPosition(0); //lower arm
            Drive.Traction(true);
            Thread.sleep(2000);
            
            //Shoot!!!
            autoShoot(3);
            
            System.out.println("Drive forward 1");
            Drive.SetLeftSpeed(1);
            Drive.SetRightSpeed(1);
            Shooter.stop();
            Shooter.load();
            Arm.pickupOn();
            Thread.sleep(1200);
            
            System.out.println("Arm Load 1");
            Arm.pickupOff();
            Arm.setPosition(1);
            Drive.SetLeftSpeed(0);
            Drive.SetRightSpeed(0);
            Thread.sleep(2000);
            
            System.out.println("Lower Arm 1");
            Arm.setPosition(0);
            Arm.pickupOn();
            Thread.sleep(750);
            
            System.out.println("Drive Forward 2");
            Drive.SetLeftSpeed(1);
            Drive.SetRightSpeed(1);
            Thread.sleep(750);
            
            System.out.println("Arm Load");
            Drive.SetLeftSpeed(0);
            Drive.SetRightSpeed(0);
            Arm.pickupOff();
            Arm.setPosition(1);
            Thread.sleep(1900);
            
            System.out.println("Drive Backward");
            Shooter.setPositionUp(true);
            Drive.SetLeftSpeed(-1);
            Drive.SetRightSpeed(-1);
            Shooter.start();
            Thread.sleep(400);
            
            System.out.println("Arm Up");
            Arm.setPosition(2);
            Arm.pickupOff();
            Drive.SetLeftSpeed(-.5);
            Drive.SetRightSpeed(-.5);
            Thread.sleep(650);
            
            System.out.println("Stop Drive");
            Drive.SetLeftSpeed(0);
            Drive.SetRightSpeed(0);
            
            Thread.sleep(400);
            
            //Shoot!!!
            autoShoot(4);
            Shooter.stop();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    public void auto2()
    {
        //5-disc Start Back
        try
        {
            System.out.println("Start Auton2");
            Shooter.start();
            Shooter.setPositionUp(false);
            Arm.setPosition(0); //lower arm
            Drive.Traction(true);
            Thread.sleep(2000);
            
            //Shoot!!!
            autoShoot(3);
            
            System.out.println("Drive forward 1");
            Drive.SetLeftSpeed(1);
            Drive.SetRightSpeed(1);
            Shooter.load();
            Arm.pickupOn();
            Thread.sleep(500);
            
            System.out.println("Arm Load 1");
            Arm.pickupOff();
            Arm.setPosition(1);
            Drive.SetLeftSpeed(0);
            Drive.SetRightSpeed(0);
            Thread.sleep(3000);
            
            System.out.println("Arm Up");
            Arm.setPosition(2);
            Arm.pickupOff();
            Thread.sleep(1000);
            
            System.out.println("Backup");
            Drive.SetLeftSpeed(-1);
            Drive.SetRightSpeed(-1);
            Thread.sleep(510);
            
            Drive.SetLeftSpeed(0);
            Drive.SetRightSpeed(0);
            autoShoot(2);
            
            Shooter.stop();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void auto3()
    {
        //5-disc Start Front
        try
        {
            System.out.println("Start Auton3");
            Shooter.start();
            Shooter.setPositionUp(true);
            Arm.setPosition(0); //lower arm
            Drive.Traction(true);
            Thread.sleep(2000);
            
            //Shoot!!!
            autoShoot(3);
            
            System.out.println("Drive forward 1");
            Drive.SetLeftSpeed(1);
            Drive.SetRightSpeed(1);
            Shooter.load();
            Arm.pickupOn();
            Thread.sleep(700);
            
            System.out.println("Arm Load 1");
            Arm.pickupOff();
            Arm.setPosition(1);
            Drive.SetLeftSpeed(0);
            Drive.SetRightSpeed(0);
            Thread.sleep(3000);
            
            System.out.println("Arm Up");
            Arm.setPosition(2);
            Arm.pickupOff();
            Thread.sleep(1000);
            
            System.out.println("Backup");
            Drive.SetLeftSpeed(-1);
            Drive.SetRightSpeed(-1);
            Thread.sleep(710);
            
            Drive.SetLeftSpeed(0);
            Drive.SetRightSpeed(0);
            autoShoot(2);
            Shooter.stop();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void auto4()
    {
        //Shoot 3 Back
        try
        {
            System.out.println("Start Auton4");
            Arm.setPosition(0); //lower arm
            Drive.Traction(true);
            Shooter.setPositionUp(false);
            Shooter.start();
            Thread.sleep(2000);
            
            autoShoot(3);
            
            Shooter.stop();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void auto5()
    {
        //Shoot 3 Front
        try
        {
            System.out.println("Start Auton5");
            Arm.setPosition(0); //lower arm
            Drive.Traction(true);
            Shooter.setPositionUp(true);
            Shooter.start();
            Thread.sleep(2000);
            
            autoShoot(3);
            
            Shooter.stop();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void auto6()
    {
        //shoot 3 back turn
        try
        {
            System.out.println("Start Auton6");
            Drive.Traction(true);
            Arm.setPosition(2);
            Shooter.setPositionUp(false);
            Shooter.start();
            Thread.sleep(2000);
            
            autoShoot(3);
            
            System.out.println("Drive Backwards");
            Shooter.stop();
            Drive.Traction(false);
            Drive.SetLeftSpeed(-1);
            Drive.SetRightSpeed(-1);
            Thread.sleep(250);
            
            System.out.println("Stop");
            Drive.SetLeftSpeed(0);
            Drive.SetRightSpeed(0);
            Thread.sleep(100);
            
            System.out.println("Turn");
            Drive.SetLeftSpeed(-1);
            Drive.SetRightSpeed(1);
            Thread.sleep(500);
            
            System.out.println("Stop");
            Drive.SetLeftSpeed(0);
            Drive.SetRightSpeed(0);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void auto7()
    {
        //
        try
        {
            System.out.println("Start Auton 7");
            Arm.setPosition(0); //lower arm
            Drive.Traction(true);
            Shooter.setPositionUp(true);
            Shooter.start();
            Thread.sleep(2000);
            
            autoShoot(3);
            
            Shooter.stop();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void auto8()
    {
        //
        try
        {
            System.out.println("Start Auton 8");
            Shooter.setPositionUp(false);
            Shooter.start();
            Thread.sleep(2000);
            
            System.out.println("Shoot 3");
            autoShoot(3);
            
            System.out.println("Back Up");
            Shooter.stop();
            Drive.SetLeftSpeed(-1);
            Drive.SetRightSpeed(-1);
            Thread.sleep(250);
            
            System.out.println("Turn");
            Arm.setPosition(0);
            AutoGyro.reset();
            while(AutoGyro.getAngle()<180)
            {
                Drive.SetLeftSpeed(.4);
                Drive.SetRightSpeed(-.4);
                System.out.println("Angle " + AutoGyro.getAngle());
            }
            Drive.SetLeftSpeed(0);
            Drive.SetRightSpeed(0);
            
            System.out.println("Drive Forward");
            Arm.pickupOn();
            Drive.Traction(true);
            Drive.SetLeftSpeed(1);
            Drive.SetRightSpeed(1);
            Thread.sleep(3000);
            
            System.out.println("Stop Pickup");
            Arm.pickupOff();
            Arm.setPosition(1);
            Thread.sleep(1250);
            
            System.out.println("Turn");
            AutoGyro.reset();
            Thread.sleep(10);
            System.out.println("Initial Angle " + AutoGyro.getAngle());
            while (AutoGyro.getAngle()<180)
            {
                Drive.SetLeftSpeed(.4);
                Drive.SetRightSpeed(-.4);
                System.out.println("Angle 2 " + AutoGyro.getAngle());
            }
            Drive.SetLeftSpeed(0);
            Drive.SetRightSpeed(0);
            
            System.out.println("Lower Arm");
            Arm.setPosition(0);
            Shooter.start();
            Thread.sleep(200);
            
            System.out.println("Drive Forward");
            Drive.SetLeftSpeed(1);
            Drive.SetRightSpeed(1);
            Thread.sleep(3000);
            
            System.out.println("Raise Arm");
            Arm.setPosition(2);
            Thread.sleep(200);
            
            System.out.println("Back Up");
            Drive.SetLeftSpeed(1);
            Drive.SetRightSpeed(1);
            Thread.sleep(300);
            
            System.out.println("Shoot");
            Drive.SetLeftSpeed(0);
            Drive.SetRightSpeed(0);
            autoShoot(3);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
}
}

