/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Miguel
 */
public class Manager implements Serializable {

    /**
     * The name of the manager
     */
    private String nameM;

    /**
     * The email of the manager
     */
    private String emailM;

    /**
     * Creates an instance of the Manager with the name and email passed by
     * parameter
     *
     * @param nameM
     * @param emailM
     */
    public Manager(String nameM, String emailM) {
        this.nameM = nameM;
        this.emailM = emailM;
    }

    /**
     * Returns the name of the manager
     *
     * @return the nameM
     */
    public String getName() {
        return nameM;
    }

    /**
     * Returns the email of the manager
     *
     * @return the emailM
     */
    public String getEmail() {
        return emailM;
    }

    /**
     * Returns a textual description of the manager with the format: name, email
     *
     * @return the textual description
     */
    @Override
    public String toString() {
        return String.format("Manager: %s ; Email: %s", getName(), getEmail());
    }
    
    /**
     * Compares if the manager passed by parameter is the same as the current
     * 
     * @param outroObjeto
     * @return if it is the same
     */
    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto) {
            return true;
        }
        if (outroObjeto == null || this.getClass() != outroObjeto.getClass()) {
            return false;
        }
        Manager outroMana = (Manager) outroObjeto;

        return nameM.equalsIgnoreCase(outroMana.nameM) && emailM.equalsIgnoreCase(outroMana.emailM);
    }
}
