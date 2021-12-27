/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Api;

import java.io.Serializable;

/**
 *
 * @author raulcoelho
 */
public class ExternalAlgorithmConvertCurrenciesApi1 implements Serializable {

    /**
     * Dollar value compared to euro
     */
    public float dollar = 1.11562f;

    /**
     * Pound value compared to euro
     */
    public float pound = 0.89013f;

    /**
     * Yen value compared to euro
     */
    public float yen = 120.66f;

    /**
     * Swiss Franc value compared to euro
     */
    public float swissFranc = 1.07265f;

    /**
     * Real value compared to euro
     */
    public float real = 5.5556f;

    /**
     * Yuan value compared to euro
     */
    public float yuan = 7.98138f;

    /**
     * Converts the amount received by parameter to the currency used in the
     * country received by parameter
     *
     * @param amount
     * @param country
     * @return the converted amount
     */
    public float convertCurrencies(float amount, String country) {
        float amountCnv;

        switch (country) {
            case ("England"):
            case ("ENG"):
                amountCnv = amount * pound;
                return amountCnv;
            case ("United States"):
            case ("USA"):
                amountCnv = amount * dollar;
                return amountCnv;
            case ("Japan"):
            case ("JPN"):
                amountCnv = amount * yen;
                return amountCnv;
            case ("Switzerland"):
            case ("SWI"):
                amountCnv = amount * swissFranc;
                return amountCnv;
            case ("Brazil"):
            case ("BR"):
                amountCnv = amount * real;
                return amountCnv;
            case ("China"):
            case ("CH"):
                amountCnv = amount * yuan;
                return amountCnv;
            default:
                return amount;
        }
    }
}
