/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Autorization.AutorizationFacade;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Miguel
 */
public class OrganizationRegister implements Serializable {

    /**
     * The Collaborator
     */
    private Collaborator colab;

    /**
     * The Manager
     */
    private Manager mana;

    /**
     * The Organization
     */
    private Organization org;

    /**
     * The list of organizations
     */
    private List<Organization> orgRe;

    /**
     * Creates an instance of OrganizationRegister
     */
    public OrganizationRegister() {
        orgRe = new ArrayList<>();
    }

    /**
     * Returns the list of organizations
     *
     * @return the list
     */
    public List<Organization> getOrganizationRegist() {
        return orgRe;
    }

    /**
     * Returns an organization that has either a freelancer or a manager with
     * the email passed by parameter
     *
     * @param email
     * @return the organization
     */
    public Organization getOrganizationByEmail(String email) {
        for (Organization org : orgRe) {
            if (org.getMana().getEmail().equalsIgnoreCase(email) || org.getColab().getEmail().equalsIgnoreCase(email)) {
                return org;
            }
        }
        return null;
    }

    /**
     * Creates a new Organization with the name, NIF, name of collaborator,
     * email of collaborator, name of manager, email of manager passed by
     * parameter
     *
     * @param name
     * @param NIF
     * @param nameC
     * @param emailC
     * @param nameM
     * @param emailM
     * @return the organization
     */
    public Organization newOrganization(String name, String NIF, String nameC, String emailC, String nameM, String emailM) {
        this.org = new Organization(name, NIF);
        colab = org.newCollaborator(nameC, emailC);
        mana = org.newManager(nameM, emailM);
        return org;
    }

    /**
     * Verifies if a company is in the organization list
     * 
     * @param org
     * @return if a company is in the organization list
     */
    public boolean validateOrganization(Organization org) {
        return getOrganizationRegist().contains(org);
    }

    /**
     * Registers a organization and adds it to the list
     * 
     * @param org
     * @param alg
     * @param plat
     * @return if the organization was added
     * @throws IOException 
     */
    public boolean registerOrganization(Organization org, ExternalAlgorithmGeneratePwd alg, Platform plat) throws IOException {
        validateOrganization(org);
        colab = org.getColab();
        mana = org.getMana();
        registerCollaboratorAsUser(colab, alg, plat);
        registerManagerAsUser(mana, alg, plat);
        return addOrganization(org);
    }

    /**
     * Registers a collaborator as a user
     * 
     * @param colab
     * @param alg
     * @param plat
     * @throws IOException 
     */
    private void registerCollaboratorAsUser(Collaborator colab, ExternalAlgorithmGeneratePwd alg, Platform plat) throws IOException {
        String name = colab.getName();
        String email = colab.getEmail();
        String pwd = alg.generatePassword();
        AutorizationFacade aut = plat.getAutorizationFacade();
        aut.registerUserWithRole(name, email, pwd, "Collaborator");
        sendPwd(email, pwd, "Collaborator");
    }

    /**
     * Registers a manager as a user
     * 
     * @param mana
     * @param alg
     * @param plat
     * @throws IOException 
     */
    private void registerManagerAsUser(Manager mana, ExternalAlgorithmGeneratePwd alg, Platform plat) throws IOException {
        String name = mana.getName();
        String email = mana.getEmail();
        //chamar algoritmo externo
        String pwd = alg.generatePassword();
        AutorizationFacade aut = plat.getAutorizationFacade();
        aut.registerUserWithRole(name, email, pwd, "Manager");
        sendPwd(email, pwd, "Manager");
    }

    /**
     * Adds a organization to the list of organizations
     * 
     * @param org
     * @return if the organization was added
     */
    private boolean addOrganization(Organization org) {
        return validateOrganization(org) == true ? false : getOrganizationRegist().add(org);
    }
    
    /**
     * Sends the password by email
     * 
     * @param email
     * @param pwd
     * @param function
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void sendPwd(String email, String pwd, String function) throws FileNotFoundException, IOException {
        try (PrintWriter pr = new PrintWriter(new FileWriter("e-mail.txt", true))) {
            pr.println();
            pr.println("\n\n###Email###");
            pr.println("\n    ##Passwords and registration##\n");
            String ptrn = "dd-MM-yyy";
            SimpleDateFormat sDT = new SimpleDateFormat(ptrn);

            String date = sDT.format(new Date());

            pr.println("Date: " + date + "\n");
            pr.println("Hello dear user. Your email is: " + email + " and your password will be: " + pwd + "\n You will have the " + function + " function.\n   Best Regards, T4J");

            try {
            } finally {
                pr.close();
            }
        }
    }
}
