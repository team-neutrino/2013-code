/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author 
 */
public class LightManager implements Runnable{
    
    Solenoid SolenoidLightsWhite;
    Solenoid SolenoidLightsRed;
    Solenoid SolenoidLightsBlue;
    
    DigitalInput LightColorSwitch;
    
    boolean Flashing;
    
    public LightManager()
    {
        SolenoidLightsWhite = new Solenoid(Constants.LIGHTS_WHITE_SLOT, Constants.LIGHTS_WHITE_CHANNEL);
        SolenoidLightsRed = new Solenoid(Constants.LIGHTS_RED_SLOT, Constants.LIGHTS_RED_CHANNEL);
        SolenoidLightsBlue = new Solenoid(Constants.LIGHTS_BLUE_SLOT, Constants.LIGHTS_BLUE_CHANNEL);
        
        LightColorSwitch = new DigitalInput(Constants.LIGHT_SWITCH_CHANNEL);
        
        Flashing = false;
    }
    
    public void setWhite(boolean on)
    {
        if((!Flashing) && (LightColorSwitch.get()))
        {
            SolenoidLightsWhite.set(on);
        }
    }
    
    public void setRed(boolean on)
    {
        if((!Flashing) && (LightColorSwitch.get()))
        {
            SolenoidLightsRed.set(on);
        }
    }
    
    public void setBlue(boolean on)
    {
        if((!Flashing) && (LightColorSwitch.get()))
        {
            SolenoidLightsBlue.set(on);
        }
    }
    
    public void startFlash()
    {
        if (LightColorSwitch.get()) {
            Flashing = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }
    
    public void stopFlash()
    {
        Flashing = false;
        SolenoidLightsWhite.set(false);
        SolenoidLightsRed.set(false);
        SolenoidLightsBlue.set(false);
    }

    public void run()
    {
        SolenoidLightsWhite.set(false);
        SolenoidLightsRed.set(false);
        SolenoidLightsBlue.set(false);
        while(Flashing)
        {
            try 
            {
                for (int i = 0; i < 10; i++) 
                {
                    if(!Flashing) break;
                    SolenoidLightsBlue.set(true);
                    Thread.sleep(100);
                    SolenoidLightsBlue.set(false);
                    SolenoidLightsWhite.set(true);
                    Thread.sleep(100);
                    SolenoidLightsWhite.set(false);
                    SolenoidLightsRed.set(true);
                    Thread.sleep(100);
                    SolenoidLightsRed.set(false);
                }
                for (int i = 0; i < 5; i++) 
                {
                    if(!Flashing) break;
                    Thread.sleep(200);
                    SolenoidLightsWhite.set(true);
                    SolenoidLightsRed.set(true);
                    SolenoidLightsBlue.set(true);
                    Thread.sleep(200);
                    SolenoidLightsWhite.set(false);
                    SolenoidLightsRed.set(false);
                    SolenoidLightsBlue.set(false);
                }
                System.out.println("flashing");
            } catch (Exception e) 
            {
            }
        }
    }
}
