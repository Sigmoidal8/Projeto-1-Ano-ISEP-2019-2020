/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Autorization.UserSession;
import Model.CalculateStatistics;
import Model.Freelancer;
import Model.Organization;
import Model.OrganizationRegister;
import Model.PaymentTransaction;
import Model.PaymentTransactionList;
import Model.Platform;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sandr
 */
public class FreelancerPaymentsController {

    /**
     * The Platform
     */
    private Platform plat;
    
    /**
     * An Organization
     */
    private Organization org;
    
    /**
     * The list of payment transactions
     */
    private PaymentTransactionList paymentTransactionList;
    
    /**
     * The class that has the calculating methods
     */
     private CalculateStatistics calc;
   
     /**
      * Creates an instance of FreelancerPaymentsController and initializes calc
      */
     public FreelancerPaymentsController() {
        this.calc = new CalculateStatistics();
    }

     /**
      * Initializes the variables by getting the organization of the user logged in
      */
    public void getOrg() {
        AplicationPOT app = AplicationPOT.getInstance();
        plat = app.getPlatform();
        UserSession session = app.getAtualSession();
        String email = session.getUserEmail();
        OrganizationRegister rorg = plat.getOrganizationRegister();
        org = rorg.getOrganizationByEmail(email);
        paymentTransactionList = org.getPaymentTransactionList();
    }

    /**
     * Returns a list of freelancers sorted by name
     * 
     * @param listaFreelancers
     * @return the list of freelancers
     */
    public String getFreelancersListSortedByName(List<Freelancer> listaFreelancers) {
        return paymentTransactionList.toStringFreelancersListSortedByName(listaFreelancers);
    }

    /**
     * Returns a list of freelancers sorted by payment value
     * 
     * @param listaFreelancers
     * @return the list of freelancers
     */
    public String getFreelancersListSortedByPaymentValue(List<Freelancer> listaFreelancers) {
        return paymentTransactionList.toStringFreelancersListSortedByPaymentValue(listaFreelancers);
    }

    /**
     * Returns the payment transaction list
     * 
     * @return the paymentTransactionList
     */
    public PaymentTransactionList getPaymentTransactionList() {
        return paymentTransactionList;
    }

    /**
     * Returns the list of freelancers that work for the company and the mean and standart deviation of the payment values
     * 
     * @return a string of the data
     */
    public String getStatisticsEachOne() {
        List<Freelancer> freelancerList = getFreelancers();
        StringBuilder t = new StringBuilder();
        for (Freelancer freelancer : freelancerList) {

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
     * Returns the list of freelancers working for the organization of the user logged in
     * 
     * @return the list of freelancers
     */
    public List<Freelancer> getFreelancers(){
         List<PaymentTransaction> pymrTransacList = org.getPaymentTransactionList().getPaymentTransactionList();
        List<Freelancer> freelancerList = new ArrayList<>();

        for (PaymentTransaction pymrTransac : pymrTransacList) {
            if (freelancerList.contains(pymrTransac.getFreelancer()) == false) {
                freelancerList.add(pymrTransac.getFreelancer());
            }
        }
        return freelancerList;
    }
}
