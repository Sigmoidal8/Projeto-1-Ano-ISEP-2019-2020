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
public interface ExternalAlgorithmGeneratePwd extends Serializable{
    
    /**
     * Generates a password
     * 
     * @return a password
     */
    public String generatePassword();
}

