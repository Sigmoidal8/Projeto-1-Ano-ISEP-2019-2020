/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import Api.ExternalAlgorithmConvertCurrenciesApi1;
import Model.ExternalAlgorithmConvertCurrencies;
import java.io.Serializable;

/**
 *
 * @author raulcoelho
 */
public class ExternalAlgorithmConvertCurrenciesAdapter1 implements ExternalAlgorithmConvertCurrencies ,Serializable{
    
    /**
     * Reference to the currency converter 1api
     */
    private ExternalAlgorithmConvertCurrenciesApi1 api;
    
    /**
     * Creates an instance of the Adapter1 with no parameters and initializes the currency converter api 1
     */
    public ExternalAlgorithmConvertCurrenciesAdapter1(){
        this.api = new ExternalAlgorithmConvertCurrenciesApi1();
    }
    
    /**
     * Returns the amount converted by the Api receiving by parameter the amount of the task and the country of the freelancer
     * 
     * @param amount
     * @param country
     * @return the amount converted to freelancer currency
     */
    @Override
    public float getConvertedAmount(float amount, String country) {
        return api.convertCurrencies(amount, country);
    }
    
}

