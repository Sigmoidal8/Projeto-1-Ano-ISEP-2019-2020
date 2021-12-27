/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Freelancer;
import Model.FreelancerRegister;
import Model.Platform;

/**
 *
 * @author Miguel
 */
public class RegisterFreelancerController {

    /**
     * The Platform
     */
    private Platform plat;
    
    /**
     * The registry of freelancers
     */
    private FreelancerRegister rfree;
    
    /**
     * A Freelancer
     */
    private Freelancer freelancer;

    /**
     * Creates an instance of RegisterFreelancerController and gets the aplicationPot and the freelancer registry
     */
    public RegisterFreelancerController() {
        this.plat = AplicationPOT.getInstance().getPlatform();
        rfree = plat.getFreelancerRegister();
    }

    /**
     * Creates a new Freelancer with the name, level of expertise, email, NIF, IBAN, adress and country received by parameter
     * 
     * @param name
     * @param levelOfExpertise
     * @param email
     * @param NIF
     * @param IBAN
     * @param Address
     * @param country
     * @return 
     */
    public boolean newFreelancer(String name, String levelOfExpertise, String email, String NIF, String IBAN, String Address, String country) {
        rfree = plat.getFreelancerRegister();
        if (!rfree.validaFreelancerByEmail(email)) {
            freelancer = rfree.newFreelancer(name, levelOfExpertise, email, NIF, IBAN, Address, country);
            return true;
        }
        return false;
    }

    /**
     * Registers the freelancer and adds it to the freelancers list
     * 
     * @return if the freelancer was added
     */
    public boolean registerFreelancer() {
       return rfree.registerFreelancer(freelancer);
    }
}
