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
import Model.Task;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sandr
 */
public class TaskExecutionTimeController {

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
     * The Calculate Statistics
     */
    private CalculateStatistics calc;

    /**
     * Create an instance of TaskExecutionTimeController
     */
    public TaskExecutionTimeController() {
        calc = new CalculateStatistics();
    }

    /**
     * Initializes the variables by getting the organization of the user logged
     * in
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
     * @return the list
     */
    public String getFreelancersListSortedByName(List<Freelancer> listaFreelancers) {
        return paymentTransactionList.toStringFreelancersDelaySortedByName(listaFreelancers);
    }

    /**
     * Returns a list of freelancers sorted by payment value
     * 
     * @param listaFreelancers
     * @return the list
     */
    public String getFreelancersListSortedByPaymentValue(List<Freelancer> listaFreelancers) {
        return paymentTransactionList.toStringFreelancersListSortedByPaymentValue(listaFreelancers);
    }

    /**
     * Returns the mean and standart deviation of the delays of each freelancers of the organization
     *
     * @return a string of the data
     */
    public String getStatisticsEachOne() {
        List<Freelancer> freelancerList = getFreelancersByOrg();

        StringBuilder t = new StringBuilder();

        for (Freelancer freelancer : freelancerList) {

            t.append(freelancer.getName());
            t.append(" : ");
            t.append(calc.calculateMeanDelayFreelancer(freelancer));
            t.append(" ; ");
            t.append(calc.calculateStandartDeviationDelayFreelancer(freelancer, calc.calculateMeanDelayFreelancer(freelancer)));
            t.append("\n");
        }
        return t.toString().trim();
    }

    /**
     * Returns the mean of delays of a freelancer
     * 
     * @param free
     * @return the mean
     */
    public float getMeanDelayFreelancer(Freelancer free) {
        return calc.calculateMeanDelayFreelancer(free);
    }

    /**
     * Returns the standart deviation of a freelancer
     * 
     * @param free
     * @return the standart deviation
     */
    public float getStandartDeviationFreelancer(Freelancer free) {
        return calc.calculateStandartDeviationDelayFreelancer(free, calc.calculateMeanDelayFreelancer(free));
    }

    /**
     * Returns the mean of delays of all freelancers
     * 
     * @return the mean
     */
    public float getMeanDelayAll() {
        return calc.calculateMeanDelayAll(getFreelancersByOrg());
    }

    /**
     * Returns the standart deviation of delaus of all freelancers
     * 
     * @return 
     */
    public float getStandartDeviationAll() {
        return calc.calculateStandartDeviationDelayAll(getFreelancersByOrg(), getMeanDelayAll());
    }

    /**
     * Returns the list of integers for the histogram of all freelancers
     * 
     * @return the list
     */
    public List<Integer> gethistogramDataAll() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(0);
        float freelancersMeanDelay = getMeanDelayAll();
        float freelancersStandartDDelay = getStandartDeviationAll();

        double limiteA = freelancersMeanDelay - freelancersStandartDDelay;
        double limiteB = freelancersMeanDelay + freelancersStandartDDelay;
        for (Freelancer free : getFreelancersByOrg()) {
            if (getMeanDelayFreelancer(free) <= limiteA) {
                int a = list.get(0);
                a++;
                list.set(0, a);
            } else if (getMeanDelayFreelancer(free) < limiteB) {
                int a = list.get(1);
                a++;
                list.set(1, a);
            } else {
                int a = list.get(2);
                a++;
                list.set(2, a);
            }
        }
        return list;
    }

    /**
     * Returns the list of integers for the histogram of a freelancer
     * 
     * @param free
     * @return the list
     */
    public List<Integer> gethistogramDataEach(Freelancer free) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(0);
        float freelancersMeanDelay = getMeanDelayFreelancer(free);
        float freelancersStandartDDelay = getStandartDeviationFreelancer(free);

        double limiteA = freelancersMeanDelay - freelancersStandartDDelay;
        double limiteB = freelancersMeanDelay + freelancersStandartDDelay;

        for (Task task : free.getTaskList()) {
            if (task.getPaymentTransaction().getExecutionTask().getDelay() <= limiteA) {
                int a = list.get(0);
                a++;
                list.set(0, a);
            } else if (task.getPaymentTransaction().getExecutionTask().getDelay() < limiteB) {
                int a = list.get(1);
                a++;
                list.set(1, a);
            } else {
                int a = list.get(2);
                a++;
                list.set(2, a);
            }
        }
        return list;
    }

    /**
     * Returns the list of freelancers that work for the organization
     * 
     * @return 
     */
    public List<Freelancer> getFreelancersByOrg() {
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
