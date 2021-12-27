/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Adapter.ExternalAlgorithmGeneratePwdAdapter1;
import Controller.AplicationPOT;
import java.util.List;
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
public class OrganizationRegisterTest {

    public OrganizationRegisterTest() {
    }

    /**
     * Test of newOrganization method, of class OrganizationRegister.
     */
    @Test
    public void testNewOrganization() {
        System.out.println("newOrganization");
        String name = "Barraca LDA";
        String NIF = "312659887";
        String nameC = "Miguel Silva";
        String emailC = "miguelsilva@gmail.com";
        String nameM = "Raul Coelho";
        String emailM = "raulcoelho@gmail.com";
        OrganizationRegister instance = new OrganizationRegister();
        Organization expResult = new Organization("Barraca LDA", "312659887");
        expResult.newManager("Raul Coelho", "raulcoelho@gmail.com");
        expResult.newCollaborator("Miguel Silva", "miguelsilva@gmail.com");
        Organization result = instance.newOrganization(name, NIF, nameC, emailC, nameM, emailM);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateOrganization method, of class OrganizationRegister.
     */
    @Test
    public void testValidateOrganization() {
        System.out.println("validateOrganization");
        Organization org = new Organization("Source Tree", "000000000");
        org.newManager("Raul Coelho", "raulcoelho@gmail.com");
        org.newCollaborator("Miguel Silva", "miguelsilva@gmail.com");
        OrganizationRegister instance = new OrganizationRegister();
        boolean expResult = false;
        boolean result = instance.validateOrganization(org);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrganizationByEmail method, of class OrganizationRegister.
     */
    @Test
    public void testGetOrganizationByEmail() {
        System.out.println("getOrganizationByEmail");
        String email = "raulcoelho@gmail.com";
        OrganizationRegister instance = new OrganizationRegister();
        Organization expResult = null;
        Organization result = instance.getOrganizationByEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerOrganization method, of class OrganizationRegister.
     */
    @Test
    public void testRegisterOrganization() throws Exception {
        System.out.println("registerOrganization");
        Organization org = new Organization("Barraca LDA", "312659887");
        org.newManager("Raul Coelho", "raulcoelho@gmail.com");
        org.newCollaborator("Miguel Silva", "miguelsilva@gmail.com");
        ExternalAlgorithmGeneratePwd alg = new ExternalAlgorithmGeneratePwdAdapter1();
        Platform plat = new Platform();
        OrganizationRegister instance = plat.getOrganizationRegister();
        boolean expResult = true;
        boolean result = instance.registerOrganization(org, alg, plat);
        assertEquals(expResult, result);
    }

}
