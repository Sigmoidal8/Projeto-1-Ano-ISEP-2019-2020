/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Miguel
 */
public interface ExternalAlgorithmConvertCurrencies extends Serializable{

    /**
     *  Converts the amount to the country currency
     * 
     * @param amount
     * @param country
     * @return the amount
     */
    public float getConvertedAmount(float amount, String country);    
}
