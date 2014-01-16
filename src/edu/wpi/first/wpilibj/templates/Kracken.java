/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Developer
 */

public class Kracken
{
    
    Solenoid In;
    Solenoid Out;
    Solenoid Fire;
    
    public Kracken()
    {
        In = new Solenoid(Constants.SOLENOID_KRACKEN_IN_SLOT, Constants.SOLENOID_KRACKEN_IN_CHANNEL);
        Out = new Solenoid(Constants.SOLENOID_KRACKEN_OUT_SLOT, Constants.SOLENOID_KRACKEN_OUT_CHANNEL);
        Fire = new Solenoid(Constants.SOLENOID_KRACKEN_FIRE_SLOT, Constants.SOLENOID_KRACKEN_FIRE_CHANNEL);
        In.set(true);
    }
    
    public void out(boolean out)
    {
        if(out)
        {
            In.set(false);
            Out.set(true);
        }
        else
        {
            In.set(true);
            Out.set(false);
        }
    }
    
    public void fire()
    {
        Fire.set(true);
    }
}
