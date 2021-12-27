/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Adapter.ExternalAlgorithmConvertCurrenciesAdapter1;
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
public class ExternalAlgorithmConvertCurrenciesTest {
    
    public ExternalAlgorithmConvertCurrenciesTest() {
    }

   /**
     * Test of getConvertedAmount method, of class ExternalAlgorithmConvertCurrencies.
     */
    @Test
    public void testGetConvertedAmountDollar() {
        System.out.println("getConvertedAmountdollar");
        float amount = 32.4f;
        String country = "USA";
        ExternalAlgorithmConvertCurrencies instance = new ExternalAlgorithmConvertCurrenciesAdapter1();
        float expResult = 36.146088f;
        float result = instance.getConvertedAmount(amount, country);
        assertEquals(expResult, result, 0.005);
    }
    
    /**
     * Test of getConvertedAmount method, of class ExternalAlgorithmConvertCurrencies.
     */
    @Test
    public void testGetConvertedAmountPound() {
        System.out.println("getConvertedAmountpound");
        float amount = 32.4f;
        String country = "ENG";
        ExternalAlgorithmConvertCurrencies instance = new ExternalAlgorithmConvertCurrenciesAdapter1();
        float expResult = 28.840F;
        float result = instance.getConvertedAmount(amount, country);
        assertEquals(expResult, result, 0.005);
    }
    
    /**
     * Test of getConvertedAmount method, of class ExternalAlgorithmConvertCurrencies.
     */
    @Test
    public void testGetConvertedAmountYen() {
        System.out.println("getConvertedAmountyen");
        float amount = 32.4f;
        String country = "JPN";
        ExternalAlgorithmConvertCurrencies instance = new ExternalAlgorithmConvertCurrenciesAdapter1();
        float expResult = 3909.384F;
        float result = instance.getConvertedAmount(amount, country);
        assertEquals(expResult, result, 0.005);
    }
    
    /**
     * Test of getConvertedAmount method, of class ExternalAlgorithmConvertCurrencies.
     */
    @Test
    public void testGetConvertedAmountSwissFranc() {
        System.out.println("getConvertedAmountSwissFranc");
        float amount = 32.4f;
        String country = "SWI";
        ExternalAlgorithmConvertCurrencies instance = new ExternalAlgorithmConvertCurrenciesAdapter1();
        float expResult = 34.75386F;
        float result = instance.getConvertedAmount(amount, country);
        assertEquals(expResult, result, 0.005);
    }
    
    /**
     * Test of getConvertedAmount method, of class ExternalAlgorithmConvertCurrencies.
     */
    @Test
    public void testGetConvertedAmountReal() {
        System.out.println("getConvertedAmountReal");
        float amount = 32.4f;
        String country = "BR";
        ExternalAlgorithmConvertCurrencies instance = new ExternalAlgorithmConvertCurrenciesAdapter1();
        float expResult = 180.00144F;
        float result = instance.getConvertedAmount(amount, country);
        assertEquals(expResult, result, 0.005);
    }
    
    /**
     * Test of getConvertedAmount method, of class ExternalAlgorithmConvertCurrencies.
     */
    @Test
    public void testGetConvertedAmountYuan() {
        System.out.println("getConvertedAmountdollar");
        float amount = 32.4f;
        String country = "CH";
        ExternalAlgorithmConvertCurrencies instance = new ExternalAlgorithmConvertCurrenciesAdapter1();
        float expResult = 258.596712F;
        float result = instance.getConvertedAmount(amount, country);
        assertEquals(expResult, result, 0.005);
    }
    
}
