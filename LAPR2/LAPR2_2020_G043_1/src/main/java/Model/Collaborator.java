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
public class Collaborator implements Serializable {

    /**
     * The name of the collaborator
     */
    private String nameC;

    /**
     * The email of the collaborator
     */
    private String emailC;

    /**
     * Creates an instance of Collaborator receiving the name and the email by
     * parameter
     *
     * @param nameC
     * @param emailC
     */
    public Collaborator(String nameC, String emailC) {
        this.nameC = nameC;
        this.emailC = emailC;
    }

    /**
     * Returns the name of the Collaborator
     *
     * @return the nameC
     */
    public String getName() {
        return nameC;
    }

    /**
     * Returns the email of the Collaborator
     *
     * @return the emailC
     */
    public String getEmail() {
        return emailC;
    }

    /**
     * Returns the textual description of the collaborator with the format:
     * name, email
     *
     * @return the textual description
     */
    @Override
    public String toString() {
        return String.format("Collaborator: %s ; Email: %s", getName(), getEmail());
    }
    
    /**
     * Compares if the collaborator passed by parameter is the same as the current
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
        Collaborator outroColab = (Collaborator) outroObjeto;

        return nameC.equalsIgnoreCase(outroColab.nameC) && emailC.equalsIgnoreCase(outroColab.emailC) ;
    }

}
