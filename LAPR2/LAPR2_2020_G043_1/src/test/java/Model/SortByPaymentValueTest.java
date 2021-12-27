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
public class SortByPaymentValueTest {
    
    public SortByPaymentValueTest() {
    }
    
    /**
     * Test of compare method, of class SortByPaymentValue.
     */
    @Test
    public void testCompareToZero() {
        System.out.println("compareToZero");
        Freelancer value1 = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        Freelancer value2 = new Freelancer("RC1", "Raul Coelho", "junior", "raulcoelho@gmail.com", "233828789", "PT50 562 567890909884323 54", "Rua Trident Sense, 54, Santo Tirso", "Portugal");
        Task task1 = new Task("12", "Design", 12, 2.3F, "Designer");
        Task task2 = new Task("14", "Design", 12, 2.3F, "Designer");
        PaymentTransaction pay1 = new PaymentTransaction(task1, value1, "123", "12-7-2020", 25.0f, "low quality");
        PaymentTransaction pay2 = new PaymentTransaction(task2, value2, "234", "12-7-2020", 26.0f, "medium quality");
        SortByPaymentValue instance = new SortByPaymentValue();
        int expResult = 0;
        int result = instance.compare(value1, value2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of compare method, of class SortByPaymentValue.
     */
    @Test
    public void testCompareToPos() {
        System.out.println("compareToPos");
        Freelancer value1 = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        Freelancer value2 = new Freelancer("RC1", "Raul Coelho", "Senior", "raulcoelho@gmail.com", "233828789", "PT50 562 567890909884323 54", "Rua Trident Sense, 54, Santo Tirso", "Portugal");
        Task task1 = new Task("12", "Design", 12, 2.3F, "Designer");
        Task task2 = new Task("14", "Design", 11, 2.0F, "Designer");
        PaymentTransaction pay1 = new PaymentTransaction(task1, value1, "123", "12-7-2020", 25.0f, "low quality");
        PaymentTransaction pay2 = new PaymentTransaction(task2, value2, "234", "12-7-2020", 26.0f, "medium quality");
        SortByPaymentValue instance = new SortByPaymentValue();
        int expResult = 1;
        int result = instance.compare(value2, value1);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of compare method, of class SortByPaymentValue.
     */
    @Test
    public void testCompareToNeg() {
        System.out.println("compareToNeg");
        Freelancer value1 = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        Freelancer value2 = new Freelancer("RC1", "Raul Coelho", "Senior", "raulcoelho@gmail.com", "233828789", "PT50 562 567890909884323 54", "Rua Trident Sense, 54, Santo Tirso", "Portugal");
        Task task1 = new Task("12", "Design", 12, 2.3F, "Designer");
        Task task2 = new Task("14", "Design", 11, 2.0F, "Designer");
        PaymentTransaction pay1 = new PaymentTransaction(task1, value1, "123", "12-7-2020", 25.0f, "low quality");
        PaymentTransaction pay2 = new PaymentTransaction(task2, value2, "234", "12-7-2020", 26.0f, "medium quality");
        SortByPaymentValue instance = new SortByPaymentValue();
        int expResult = -1;
        int result = instance.compare(value1, value2);
        assertEquals(expResult, result);
    }

}