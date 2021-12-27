/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Api;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author José Forno
 */
public class ExternalAlgorithmGeneratePwdApi1 implements Serializable{

    /**
     * Generates the password 
     * 
     * @return the password
     */
    public String generatePassword(){
        int lftLimit = 48; //a
        int rgtLimit= 122; //z
        int stringLength = 7;
        Random random= new Random();
        
        String pwd = random.ints(lftLimit,rgtLimit + 1).filter(i -> (i <= 57 || i>=65) && (i <= 90 || i>=97)).limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        
       return pwd.trim();
    }
}
