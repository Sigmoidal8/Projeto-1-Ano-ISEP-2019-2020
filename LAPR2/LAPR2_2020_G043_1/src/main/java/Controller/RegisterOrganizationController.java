/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Autorization.AutorizationFacade;
import Model.Organization;
import Model.OrganizationRegister;
import Model.Platform;
import java.io.IOException;

/**
 *
 * @author Miguel
 */
public class RegisterOrganizationController {
    
    /**
     * The Platform
     */
    private Platform plat;
    
    /**
     * The registry of organization
     */
    private OrganizationRegister ro;
    
    /**
     * An Organization
     */
    private Organization org;
    
    /**
     * The Autorization
     */
    private  AutorizationFacade autorization;
    
    /**
     * Creates an instance of RegisterOrganizationController
     */
    public RegisterOrganizationController(){
        this.plat=AplicationPOT.getInstance().getPlatform();
        this.autorization=plat.getAutorizationFacade();
    }

    /**
     * Creates a new Organization with name, NIF, nameC, emailC, nameM, emailM received by parameter
     * 
     * @param name
     * @param NIF
     * @param nameC
     * @param emailC
     * @param nameM
     * @param emailM
     * @return the textual description of the organization
     */
    public String newOrganization(String name,String NIF,String nameC,String emailC,String nameM,String emailM){
        ro=plat.getOrganizationRegister();
        org = ro.newOrganization(name,NIF,nameC,emailC,nameM,emailM);
        ro.validateOrganization(org);
        
        return org.toString();
    }
    
    /**
     * Registers the organization and adds it to the list of organizations
     * 
     * @return if the organization was added
     * @throws IOException 
     */
    public boolean registerOrganization() throws IOException{
        return ro.registerOrganization(org,plat.getPwdGenerator(), plat);
    }
    
    /**
     * Searchs if the email already exists in the platform
     * 
     * @param email
     * @return if the email exists
     */
    public boolean existsEmail(String email){
        return autorization.hasUser(email);
    }
}

