/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class OrganizationTest {

    public OrganizationTest() {
    }

    /**
     * Test of newCollaborator method, of class Organization.
     */
    @Test
    public void testNewCollaborator() {
        System.out.println("newCollaborator");
        String nameC = "Sandro Moura";
        String emailC = "sandromoura@gmail.com";
        Organization instance = new Organization("Barraquinhas ilimitadas", "123654789");
        Collaborator expResult = new Collaborator("Sandro Moura", "sandromoura@gmail.com");
//        instance.newCollaborator("Sandro Moura","sandromoura@gmail.com");
        Collaborator result = instance.newCollaborator(nameC, emailC);
        assertEquals(expResult, result);
    }

    /**
     * Test of newManager method, of class Organization.
     */
    @Test
    public void testNewManager() {
        System.out.println("newManager");
        String nameM = "Raul Coelho";
        String emailM = "raulcoelho@gmail.com";
        Organization instance = new Organization("Securitas Direct", "789654123");
        Manager expResult = new Manager("Raul Coelho", "raulcoelho@gmail.com");
        Manager result = instance.newManager(nameM, emailM);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Organization.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Organization instance = new Organization("Securitas Direct", "789654123");
        instance.newCollaborator("Joao", "email");
        instance.newManager("Raul Coelho", "raulcoelho@gmail.com");

        Organization otherObject = new Organization("Securitas Direct", "789654123");
        otherObject.newCollaborator("Joao", "email");
        otherObject.newManager("Raul Coelho", "raulcoelho@gmail.com");

        boolean expResult = true;
        boolean result = instance.equals(otherObject);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Organization.
     */
    @Test
    public void testNotEquals() {
        System.out.println("not equals");

        Organization instance = new Organization("Sandrina", "53453435");
        instance.newCollaborator("Luis", "email");
        instance.newManager( "Coelho", "life@gmail.com");

        Organization otherObject = new Organization("Securitas Direct", "789654123");
        otherObject.newCollaborator("Joao", "email");
        otherObject.newManager("Raul Coelho", "raulcoelho@gmail.com");

        boolean expResult = false;
        boolean result = instance.equals(otherObject);
        assertEquals(expResult, result);
    }


    /**
     * Test of validateDate method, of class Organization.
     */
    @Test
    public void testValidateDate() {
        System.out.println("validateDate");
        String day = "20-04-2020 17:54:36";
        Organization instance = new Organization("egiro", "147852392");
        Calendar expResult = new GregorianCalendar(2020, 04, 20, 17, 54, 36);
        Calendar result = instance.validateDate(day);
        result.equals(expResult);
    }
}
