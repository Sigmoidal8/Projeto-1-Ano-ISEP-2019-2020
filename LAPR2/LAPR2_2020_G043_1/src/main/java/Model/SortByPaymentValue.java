/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Comparator;

/**
 *
 * @author sandr
 */
public class SortByPaymentValue implements Comparator<Freelancer> {

    /**
     * Sortes two freelancers trough their mean payments 
     * 
     * @param value1
     * @param value2
     * @return the diference between the freelancers
     */
    public int compare(Freelancer value1, Freelancer value2) {
        CalculateStatistics calc = new CalculateStatistics();
        if (calc.calculateMeanPaymentFreelancer(value1) - calc.calculateMeanPaymentFreelancer(value2) > 0) {
            return 1;
        } else if (calc.calculateMeanPaymentFreelancer(value1) - calc.calculateMeanPaymentFreelancer(value2) < 0) {
            return -1;
        } else {
            return 0;

        }
    }
}
