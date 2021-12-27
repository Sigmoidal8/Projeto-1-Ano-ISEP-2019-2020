/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
public class FreelancerRegisterTest {

    public FreelancerRegisterTest() {
    }

    /**
     * Test of newFreelancer method, of class FreelancerRegister.
     */
    @Test
    public void testNewFreelancer_7args() {
        System.out.println("newFreelancer_7args");
        String name = "Miguel Silva";
        String levelOfExpertise = "Junior";
        String email = "miguelsilva@gmail.com";
        String NIF = "123456789";
        String IBAN = "PT50 1234 567890987654323 45";
        String address = "Rua Santo Antonio, 54, Porto";
        String country = "Portugal";
        FreelancerRegister instance = new FreelancerRegister();
        Freelancer expResult = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        Freelancer result = instance.newFreelancer(name, levelOfExpertise, email, NIF, IBAN, address, country);
        assertEquals(expResult, result);
    }

    /**
     * Test of newFreelancer method, of class FreelancerRegister.
     */
    @Test
    public void testNewFreelancer_8args() {
        System.out.println("newFreelancer_8args");
        String id = "MS1";
        String name = "Miguel Silva";
        String levelOfExpertise = "Junior";
        String email = "miguelsilva@gmail.com";
        String NIF = "123456789";
        String IBAN = "PT50 1234 567890987654323 45";
        String address = "Rua Santo Antonio, 54, Porto";
        String country = "Portugal";
        FreelancerRegister instance = new FreelancerRegister();
        Freelancer expResult = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        Freelancer result = instance.newFreelancer(id, name, levelOfExpertise, email, NIF, IBAN, address, country);
        assertEquals(expResult, result);
    }

    /**
     * Test of validaFreelancerByEmail method, of class FreelancerRegister.
     */
    @Test
    public void testValidaFreelancerByEmail() {
        System.out.println("validaFreelancerByEmail");
        String email = "miguelsilva@gmail.com";
        FreelancerRegister instance = new FreelancerRegister();
        boolean expResult = false;
        boolean result = instance.validaFreelancerByEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateFreelancer method, of class FreelancerRegister.
     */
    @Test
    public void testValidateFreelancer() {
        System.out.println("validateFreelancer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        FreelancerRegister instance = new FreelancerRegister();
        boolean expResult = false;
        boolean result = instance.validateFreelancer(freelancer);
        assertEquals(expResult, result);
    }

    /**
     * Test of addFreelancer method, of class FreelancerRegister.
     */
    @Test
    public void testAddFreelancer() {
        System.out.println("addFreelancer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        FreelancerRegister instance = new FreelancerRegister();
        boolean expResult = true;
        boolean result = instance.addFreelancer(freelancer);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerFreelancer method, of class FreelancerRegister.
     */
    @Test
    public void testRegisterFreelancer() {
        System.out.println("registerFreelancer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        FreelancerRegister instance = new FreelancerRegister();
        boolean expResult = true;
        boolean result = instance.registerFreelancer(freelancer);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFreelancerById method, of class FreelancerRegister.
     */
    @Test
    public void testGetFreelancerById() {
        System.out.println("getFreelancerById");
        String freelancerId = "MS1";
        Freelancer expResult = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        FreelancerRegister instance = new FreelancerRegister();
        instance.registerFreelancer(new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal"));
        Freelancer result = instance.getFreelancerById(freelancerId);
        result.equals(expResult);
    }

    /**
     * Test of generateId method, of class FreelancerRegister.
     */
    @Test
    public void testGenerateId() {
        System.out.println("generateId");
        String name = "Miguel Silva";
        FreelancerRegister instance = new FreelancerRegister();
        String expResult = "MS1";
        String result = instance.generateId(name);
        assertEquals(expResult, result);
    }

}
