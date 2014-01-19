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
    
    Solenoid Down10Pt;
    Solenoid Up10Pt;
    Solenoid Fire20Pt;
    
    LightManager LightManager;
    Arm Arm;
    
    boolean PnumaticsDisabled;
    public Kracken(LightManager lightManager, Arm arm)
    {
        Down10Pt = new Solenoid(Constants.SOLENOID_KRACKEN_10PT_DOWN_SLOT, Constants.SOLENOID_KRACKEN_10PT_DOWN_CHANNEL);
        Up10Pt = new Solenoid(Constants.SOLENOID_KRACKEN_10PT_UP_SLOT, Constants.SOLENOID_KRACKEN_10PT_UP_CHANNEL);
        Fire20Pt = new Solenoid(Constants.SOLENOID_KRACKEN_FIRE_20PT_SLOT, Constants.SOLENOID_KRACKEN_FIRE_20PT_CHANNEL);
        LightManager = lightManager;
        Arm = arm;
        
        hang10Pt(false);
        PnumaticsDisabled = false;
    }
    
    public void hang10Pt(boolean up)
    {
        if(!PnumaticsDisabled)
        {
            if(up)
            {
                Down10Pt.set(false);
                Up10Pt.set(true);
                Arm.setDisabled(true);
                LightManager.startFlash();
            }
            else
            {
                Down10Pt.set(true);
                Up10Pt.set(false);
                Arm.setDisabled(false);
                LightManager.stopFlash();
            }
        }
    }
    
    public void fire20Pt()
    {
        if(!PnumaticsDisabled)
        {
            Fire20Pt.set(true);
   //         LightManager.startFlash();
        }
    }

    public void disablePnumatics() 
    {
        Down10Pt.set(false);
        Up10Pt.set(false);
        Fire20Pt.set(false);
        PnumaticsDisabled = true;
    }
    
    public void enablePnumatics() 
    {
        Down10Pt.set(true);
        Up10Pt.set(false);
        PnumaticsDisabled = false;
    }
}
