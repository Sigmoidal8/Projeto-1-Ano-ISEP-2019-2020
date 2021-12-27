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
public class SortByNameTest {
    
    public SortByNameTest() {
    }
    
    /**
     * Test of compare method, of class SortByName.
     */
    @Test
    public void testCompareToZero() {
        System.out.println("compareToZero");
        Freelancer name = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        Freelancer anotherName = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        SortByName instance = new SortByName();
        int expResult = 0;
        int result = instance.compare(name, anotherName);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of compare method, of class SortByName.
     */
    @Test
    public void testCompareToPos() {
        System.out.println("compareToPos");
        Freelancer name = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        Freelancer anotherName = new Freelancer("RC1", "Raul Coelho", "Senior", "raulcoelho@gmail.com", "233828789", "PT50 562 567890909884323 54", "Rua Trident Sense, 54, Santo Tirso", "Portugal");
        SortByName instance = new SortByName();
        int expResult = -5;
        int result = instance.compare(name, anotherName);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of compare method, of class SortByName.
     */
    @Test
    public void testCompareToNeg() {
        System.out.println("compareToNeg");
        Freelancer anotherName = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        Freelancer name = new Freelancer("RC1", "Raul Coelho", "Senior", "raulcoelho@gmail.com", "233828789", "PT50 562 567890909884323 54", "Rua Trident Sense, 54, Santo Tirso", "Portugal");
        SortByName instance = new SortByName();
        int expResult = 5;
        int result = instance.compare(name, anotherName);
        assertEquals(expResult, result);
    }
}
