/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import Api.ExternalAlgorithmGeneratePwdApi1;
import Model.ExternalAlgorithmGeneratePwd;
import java.io.Serializable;

/**
 *
 * @author José Forno
 */
public class ExternalAlgorithmGeneratePwdAdapter1 implements ExternalAlgorithmGeneratePwd, Serializable {
    
    /**
     * Reference to the password generator 1 api
     */
    private ExternalAlgorithmGeneratePwdApi1 api;
    
    /**
     * Creates an instance of the Adapter1 with no parameters and initializes the password generator api 1
     */
    public ExternalAlgorithmGeneratePwdAdapter1(){
        this.api=new ExternalAlgorithmGeneratePwdApi1();
    }

    /**
     * Generates de password
     * 
     * @return the password
     */
    @Override
    public String generatePassword() {
       return api.generatePassword();
    }
}
