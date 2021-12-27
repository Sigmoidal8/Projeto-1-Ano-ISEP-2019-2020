/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
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
public class CalculateStatisticsTest {
    
    public CalculateStatisticsTest() {
    }

    /**
     * Test of calculateMeanDelayFreelancer method, of class CalculateStatistics.
     */
    @Test
    public void testCalculateMeanDelayFreelancer() {
        System.out.println("calculateMeanDelayFreelancer");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, "123", "12-7-2020", 25.0f, "higth quality");
        CalculateStatistics instance = new CalculateStatistics();
        float expResult = 25.0F;
        float result = instance.calculateMeanDelayFreelancer(freelancer);
        assertEquals(expResult, result, 0.005);
    }

    /**
     * Test of calculateStandartDeviationDelayFreelancer method, of class CalculateStatistics.
     */
    @Test
    public void testCalculateStandartDeviationDelayFreelancer() {
        System.out.println("calculateStandartDeviationDelayFreelancer");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Task task2 = new Task("12", "Design", 10, 2.0F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, "123", "12-7-2020", 25.0f, "higth quality");
        PaymentTransaction paymentTransaction2 = new PaymentTransaction(task2, freelancer, "123", "12-7-2020", 20.0f, "higth quality");
        float freelancerMeanDelay = 22.5F;
        CalculateStatistics instance = new CalculateStatistics();
        float expResult = 2.5F;
        float result = instance.calculateStandartDeviationDelayFreelancer(freelancer, freelancerMeanDelay);
        assertEquals(expResult, result, 0.05);
    }

    /**
     * Test of calculateMeanPaymentFreelancer method, of class CalculateStatistics.
     */
    @Test
    public void testCalculateMeanPaymentFreelancer() {
        System.out.println("calculateMeanPaymentFreelancer");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Task task2 = new Task("12", "Design", 10, 2.0F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, "123", "12-7-2020", 25.0f, "higth quality");
        PaymentTransaction paymentTransaction2 = new PaymentTransaction(task2, freelancer, "123", "12-7-2020", 20.0f, "higth quality");
        CalculateStatistics instance = new CalculateStatistics();
        float expResult = 23.8F;
        float result = instance.calculateMeanPaymentFreelancer(freelancer);
        assertEquals(expResult, result, 0.05);
    }

    /**
     * Test of calculateStandartDeviationPaymentFreelancer method, of class CalculateStatistics.
     */
    @Test
    public void testCalculateStandartDeviationPaymentFreelancer() {
        System.out.println("calculateStandartDeviationPaymentFreelancer");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Task task2 = new Task("12", "Design", 10, 2.0F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, "123", "12-7-2020", 25.0f, "higth quality");
        PaymentTransaction paymentTransaction2 = new PaymentTransaction(task2, freelancer, "123", "12-7-2020", 20.0f, "higth quality");
        float meanPay = 23.8F;
        CalculateStatistics instance = new CalculateStatistics();
        float expResult = 3.8F;
        float result = instance.calculateStandartDeviationPaymentFreelancer(freelancer, meanPay);
        assertEquals(expResult, result, 0.05);
    }

    /**
     * Test of calculateMeanDelayAll method, of class CalculateStatistics.
     */
    @Test
    public void testCalculateMeanDelayAll() {
        System.out.println("calculateMeanDelayAll");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Task task2 = new Task("12", "Design", 10, 2.0F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        Freelancer freelancer2 = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, "123", "12-7-2020", 25.0f, "higth quality");
        PaymentTransaction paymentTransaction2 = new PaymentTransaction(task2, freelancer2, "123", "12-7-2020", 20.0f, "higth quality");
        List<Freelancer> listFree = new ArrayList<>();
        listFree.add(freelancer);
        listFree.add(freelancer2);
        CalculateStatistics instance = new CalculateStatistics();
        float expResult = 22.5F;
        float result = instance.calculateMeanDelayAll(listFree);
        assertEquals(expResult, result, 0.05);
    }

    /**
     * Test of calculateStandartDeviationDelayAll method, of class CalculateStatistics.
     */
    @Test
    public void testCalculateStandartDeviationDelayAll() {
        System.out.println("calculateStandartDeviationDelayAll");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Task task2 = new Task("12", "Design", 10, 2.0F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        Freelancer freelancer2 = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, "123", "12-7-2020", 25.0f, "higth quality");
        PaymentTransaction paymentTransaction2 = new PaymentTransaction(task2, freelancer2, "123", "12-7-2020", 20.0f, "higth quality");
        List<Freelancer> listFree = new ArrayList<>();
        listFree.add(freelancer);
        listFree.add(freelancer2);
        float meanDelayAll = 22.5F;
        CalculateStatistics instance = new CalculateStatistics();
        float expResult = 2.5F;
        float result = instance.calculateStandartDeviationDelayAll(listFree, meanDelayAll);
        assertEquals(expResult, result, 0.05);
    }

    /**
     * Test of calculateMeanPaymentAll method, of class CalculateStatistics.
     */
    @Test
    public void testCalculateMeanPaymentAll() {
        System.out.println("calculateMeanPaymentAll");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Task task2 = new Task("12", "Design", 10, 2.0F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        Freelancer freelancer2 = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, "123", "12-7-2020", 25.0f, "higth quality");
        PaymentTransaction paymentTransaction2 = new PaymentTransaction(task2, freelancer2, "123", "12-7-2020", 20.0f, "higth quality");
        List<Freelancer> listFree = new ArrayList<>();
        listFree.add(freelancer);
        listFree.add(freelancer2);
        CalculateStatistics instance = new CalculateStatistics();
        float expResult = 23.8F;
        float result = instance.calculateMeanPaymentAll(listFree);
        assertEquals(expResult, result, 0.05);
    }

    /**
     * Test of calculateStandartDeviationPaymentAll method, of class CalculateStatistics.
     */
    @Test
    public void testCalculateStandartDeviationPaymentAll() {
        System.out.println("calculateStandartDeviationPaymentAll");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        Task task2 = new Task("12", "Design", 10, 2.0F, "Designer");
        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        Freelancer freelancer2 = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, "123", "12-7-2020", 25.0f, "higth quality");
        PaymentTransaction paymentTransaction2 = new PaymentTransaction(task2, freelancer2, "123", "12-7-2020", 20.0f, "higth quality");
        List<Freelancer> listFree = new ArrayList<>();
        listFree.add(freelancer);
        listFree.add(freelancer2);
        float meanPaymentAll = 23.8F;
        CalculateStatistics instance = new CalculateStatistics();
        float expResult = 3.8F;
        float result = instance.calculateStandartDeviationPaymentAll(listFree, meanPaymentAll);
        assertEquals(expResult, result, 0.05);
    }

//    /**
//     * Test of calculatePrevision method, of class CalculateStatistics.
//     */
//    @Test
//    public void testCalculatePrevision() throws Exception {
//        System.out.println("calculatePrevision");
//        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
//        Task task2 = new Task("12", "Design", 10, 2.0F, "Designer");
//        Freelancer freelancer = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
//        Freelancer freelancer2 = new Freelancer("MS1", "Miguel Silva", "Junior", "miguelsilva@gmail.com", "123456789", "PT50 1234 567890987654323 45", "Rua Santo Antonio, 54, Porto", "Portugal");
//        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, "123", "12-7-2020", 25.0f, "higth quality");
//        PaymentTransaction paymentTransaction2 = new PaymentTransaction(task2, freelancer2, "123", "12-7-2020", 20.0f, "higth quality");
//        List<Freelancer> listFree = new ArrayList<>();
//        listFree.add(freelancer);
//        listFree.add(freelancer2);
//        CalculateStatistics instance = new CalculateStatistics();
//        float expResult = 15.86F;
//        float result = instance.calculatePrevision();
//        assertEquals(expResult, result, 0.05);
//    }
//    
}
