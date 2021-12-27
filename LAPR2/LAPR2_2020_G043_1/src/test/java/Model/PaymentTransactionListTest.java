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
public class PaymentTransactionListTest {
    
    public PaymentTransactionListTest() {
    }
    
    
    /**
     * Test of newPaymentTransaction method, of class PaymentTransactionList.
     */
    @Test
    public void testNewPaymentTransaction() {
        System.out.println("newPaymentTransaction");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        String transId = "123";
        String endDate = "12-7-2020";
        float delay = 25.0f;
        String BriefDescriptionQuality = "higth quality";
        PaymentTransactionList instance = new PaymentTransactionList();
        PaymentTransaction expResult = new PaymentTransaction(task, freelancer, transId, endDate, delay, BriefDescriptionQuality);
        PaymentTransaction result = instance.newPaymentTransaction(task, freelancer, transId, endDate, delay, BriefDescriptionQuality);
        result.equals(expResult);
    }

    /**
     * Test of registerPaymentTransaction method, of class PaymentTransactionList.
     */
    @Test
    public void testRegisterPaymentTransaction() {
        System.out.println("registerPaymentTransaction");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, "123", "12-7-2020", 25.0f, "higth quality");
        PaymentTransactionList instance = new PaymentTransactionList(); 
        boolean expResult = true;
        boolean result = instance.registerPaymentTransaction(paymentTransaction);
        instance.registerPaymentTransaction(paymentTransaction);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validatePaymentTransaction method, of class PaymentTransactionList.
     */
    @Test
    public void testValidatePaymentTransaction() {
        System.out.println("validatePaymentTransaction");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, "123", "12-7-2020", 25.0f, "higth quality");
        PaymentTransactionList instance = new PaymentTransactionList(); 
        boolean expResult = false;
        boolean result = instance.validatePaymentTransaction(paymentTransaction);
        assertEquals(expResult, result);
    }

    /**
     * Test of addFreelancer method, of class PaymentTransactionList.
     */
    @Test
    public void testAddFreelancer() {
        System.out.println("addFreelancer");
         Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, "123", "12-7-2020", 25.0f, "higth quality");
        PaymentTransactionList instance = new PaymentTransactionList(); 
        boolean expResult = true;
        boolean result = instance.addPaymentTransaction(paymentTransaction);
        assertEquals(expResult, result);
    }

}
