/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Autorization.UserSession;
import Model.Manager;
import Model.Organization;
import Model.OrganizationRegister;
import Model.Platform;
import java.util.Calendar;

/**
 *
 * @author Miguel
 */
public class DefinePaymentTimeController {
    
    /**
     * The Platform
     */
    private Platform plat;
    
    /**
     * An Organization
     */
    private Organization org;
    
    /**
     * The Manager of the organization
     */
    private Manager manager;
    
    /**
     * The AplicationPOT
     */
     private AplicationPOT aplicationPOT;
    
     /**
      * Returns the organization of the user that is currently logged in
      * 
      * @return the org
      */
    public Organization getOrg(){
        plat = aplicationPOT.getInstance().getPlatform();
        AplicationPOT app = aplicationPOT.getInstance();
        UserSession session = app.getAtualSession();
        String email = session.getUserEmail();
        OrganizationRegister rorg = plat.getOrganizationRegister();
        org = rorg.getOrganizationByEmail(email);
        manager = org.getMana();
        return org;
    }
    
    /**
     * Validates and registers the date of the payment
     * 
     * @param day
     * @return the date
     */
   public Calendar validateDate(String day){
       Calendar cal = org.validateDate(day);
       return cal;
   }
   
   /**
    * Schedules the payments that have not been made
    * 
    * @param cal 
    */
   public void schedulePayment(Calendar cal){
       org.schedulePayment(cal.getTime());
   }
}