/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Miguel
 */
public class PaymentTransactionList implements Serializable {

    /**
     * The list of payment transactions
     */
    private List<PaymentTransaction> ptl;
    
    /**
     * The Calculate Statistics 
     */
    private CalculateStatistics calc;

    /**
     * Creates an instance of PaymentTransactionList
     */
    public PaymentTransactionList() {
        ptl = new ArrayList<>();
    }
    
    /**
     * Returns the list of payment transactions
     * 
     * @return the list
     */
    public List<PaymentTransaction> getPaymentTransactionList() {
        return ptl;
    }

    /**
     * Creates a new Payment Transactio receiving the task, freelancer, transid, end date, delay, brief description quality
     * 
     * @param task
     * @param freelancer
     * @param transId
     * @param endDate
     * @param delay
     * @param BriefDescriptionQuality
     * @return the payment transaction
     */
    public PaymentTransaction newPaymentTransaction(Task task, Freelancer freelancer, String transId, String endDate, float delay, String BriefDescriptionQuality) {
        PaymentTransaction paymentTransaction = new PaymentTransaction(task, freelancer, transId, endDate, delay, BriefDescriptionQuality);
        ptl.add(paymentTransaction);
        return paymentTransaction;
    }

    /**
     * Registers the payment transaction and adds it to the list
     * 
     * @param paymentTransaction
     * @return if it was added
     */
    public boolean registerPaymentTransaction(PaymentTransaction paymentTransaction) {
        if (!validatePaymentTransaction(paymentTransaction)) {
            return addPaymentTransaction(paymentTransaction);
        }
        return false;
    }

    /**
     * Validates if the payment transaction is in the list already
     * 
     * @param paymentTransaction
     * @return if the payment transaction is in the list already
     */
    public boolean validatePaymentTransaction(PaymentTransaction paymentTransaction) {
        return getPaymentTransactionList().contains(paymentTransaction);
    }

    /**
     *  Adds a payment transaction to the list
     * 
     * @param paymentTransaction
     * @return if it was added
     */
    public boolean addPaymentTransaction(PaymentTransaction paymentTransaction) {
        return getPaymentTransactionList().add(paymentTransaction);
    }

    /**
     * Returns a list of freelancers sorted by name presenting the name, mean and standart deviation of the payments
     * 
     * @param listaFreelancers
     * @return the String of freelancers
     */
    public String toStringFreelancersListSortedByName(List<Freelancer> listaFreelancers) {
        this.calc = new CalculateStatistics();
        SortByName sortByName = new SortByName();
        Collections.sort(listaFreelancers, sortByName);
        StringBuilder t = new StringBuilder();
        for (Freelancer freelancer : listaFreelancers) {
            t.append(freelancer.getName());
            t.append(" : ");
            t.append(calc.calculateMeanPaymentFreelancer(freelancer));
            t.append(" ; ");
            t.append(calc.calculateStandartDeviationPaymentFreelancer(freelancer, calc.calculateMeanPaymentFreelancer(freelancer)));
            t.append("\n");
        }
        return t.toString().trim();
    }

    /**
     * Returns a list of freelancers sorted by payment value presenting the name, mean and standart deviation of the payments
     * 
     * @param listaFreelancers
     * @return 
     */
    public String toStringFreelancersListSortedByPaymentValue(List<Freelancer> listaFreelancers) {
        SortByPaymentValue sortByPaymentValue = new SortByPaymentValue();
        this.calc = new CalculateStatistics();

        Collections.sort(listaFreelancers, sortByPaymentValue);

        StringBuilder t = new StringBuilder();

        for (Freelancer freelancer : listaFreelancers) {

            t.append(freelancer.getName());
            t.append(" : ");
            t.append(calc.calculateMeanPaymentFreelancer(freelancer));
            t.append(" ; ");
            t.append(calc.calculateStandartDeviationPaymentFreelancer(freelancer, calc.calculateMeanPaymentFreelancer(freelancer)));
            t.append("\n");
        }
        return t.toString().trim();
    }

    /**
     * Returns a list of freelancers sorted by name presenting the name, mean and standart deviation of the delays
     * 
     * @param listaFreelancers
     * @return 
     */
    public String toStringFreelancersDelaySortedByName(List<Freelancer> listaFreelancers) {
        this.calc = new CalculateStatistics();
        SortByName sortByName = new SortByName();      
        Collections.sort(listaFreelancers, sortByName);
        StringBuilder t = new StringBuilder();
        for (Freelancer freelancer : listaFreelancers) {
            t.append(freelancer.getName());
            t.append(" : ");
            t.append(calc.calculateMeanDelayFreelancer(freelancer));
            t.append(" ; ");
            t.append(calc.calculateStandartDeviationDelayFreelancer(freelancer, calc.calculateMeanDelayFreelancer(freelancer)));
            t.append("\n");
        }
        return t.toString().trim();
    }
}
