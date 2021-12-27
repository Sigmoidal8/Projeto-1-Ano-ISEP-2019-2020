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
public class PaymentTransactionTest {
    
    public PaymentTransactionTest() {
    }
    
     /**
     * Test of calculatePayment method, of class PaymentTransaction.
     */
    @Test
    public void testCalculatePayment() {
        System.out.println("calculatePayment");
       Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        PaymentTransaction instance = new PaymentTransaction(task, freelancer, "1", "2018-12-2", 20, "Description");
        float expResult = 27.6F;
        float result = instance.calculatePayment(freelancer, task);
        assertEquals(expResult, result, 0.05);
    }
    
}
