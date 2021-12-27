/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raulcoelho
 */
public class ExternalAlgorithmGeneratePwdTest {
    
    public ExternalAlgorithmGeneratePwdTest() {
    }
    
     /**
     * Test of generatePassword method, of class ExternalAlgorithmGeneratePwd.
     */
    @Test
    public void testGeneratePassword() {
        System.out.println("generatePassword");
        ExternalAlgorithmGeneratePwd instance = new ExternalAlgorithmGeneratePwdImpl();
        String expResult = "a34d6ad";
        String result = instance.generatePassword();
        assertEquals(expResult, result);
    }

    public class ExternalAlgorithmGeneratePwdImpl implements ExternalAlgorithmGeneratePwd {

        public String generatePassword() {
            return "a34d6ad";
        }
    }
   
}
