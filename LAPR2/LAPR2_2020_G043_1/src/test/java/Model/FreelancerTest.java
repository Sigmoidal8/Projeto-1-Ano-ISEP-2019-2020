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
public class FreelancerTest {
    
    public FreelancerTest() {
    }

    /**
     * Test of addTask method, of class Freelancer.
     */
    @Test
    public void testAddTask() {
        System.out.println("addTask");
        Task task = new Task("id", "briefDescription", 2, 4.0F , "taskCategory");
        Freelancer instance = new Freelancer("id","name", "levelOfExpertise", "email", "NIF", "IBAN", "address", "country");
        boolean expResult = true;
        boolean result = instance.addTask(task);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Freelancer.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Freelancer outroObjeto = new Freelancer("id","name", "levelOfExpertise", "email", "NIF", "IBAN", "address", "country");
        Freelancer instance = new Freelancer("id","name", "levelOfExpertise", "email", "NIF", "IBAN", "address", "country");
        boolean expResult = true;
        boolean result = instance.equals(outroObjeto);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of equals method, of class Freelancer.
     */
    @Test
    public void testNotEquals() {
        System.out.println("not equals");
        Freelancer outroObjeto = new Freelancer("id1","name1", "levelOfExpertise1", "email1", "NIF1", "IBAN1", "address1", "country1");
        Freelancer instance = new Freelancer("id","name", "levelOfExpertise", "email", "NIF", "IBAN", "address", "country");
        boolean expResult = false;
        boolean result = instance.equals(outroObjeto);
        assertEquals(expResult, result);
    }

}
