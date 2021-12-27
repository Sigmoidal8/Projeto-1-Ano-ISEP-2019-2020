package Controller;

import Model.CalculateStatistics;
import Model.Freelancer;
import Model.FreelancerRegister;
import Model.Platform;
import Model.Task;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math.MathException;

public class ShowGeneralStatisticsController {

    /**
     * The list of Freelancers
     */
    private List<Freelancer> freeL;

    /**
     * The Registry of Freelancers
     */
    private FreelancerRegister freeReg;

    /**
     * The Platform
     */
    private Platform plat;

    /**
     * The Calculate Statistics
     */
    private CalculateStatistics calc;

    /**
     * Creates an instance of ShowGeneralStatisticsController
     */
    public ShowGeneralStatisticsController() {
        this.calc = new CalculateStatistics();
    }

    /**
     * Returns the mean and standart deviation of the payment values and delays of a freelancer
     *
     * @return a string of the data
     */
    public String getStatisticsEachOne() {

        StringBuilder t = new StringBuilder();

        for (Freelancer freelancer : getFreelancers()) {

            t.append(freelancer.getName());
            t.append(": ");
            t.append(calc.calculateMeanDelayFreelancer(freelancer));
            t.append("; ");
            t.append(calc.calculateStandartDeviationDelayFreelancer(freelancer, calc.calculateMeanDelayFreelancer(freelancer)));
            t.append("; ");
            t.append(calc.calculateMeanPaymentFreelancer(freelancer));
            t.append("; ");
            t.append(calc.calculateStandartDeviationPaymentFreelancer(freelancer, calc.calculateMeanPaymentFreelancer(freelancer)));
            t.append("\n");
        }
        return t.toString().trim();
    }

    /**
     * Returns the mean and standart deviation of the payment values and delays of all freelancers
     *
     * @return a string of the data 
     * @throws MathException
     */
    public String getStatisticsAll() throws MathException {

        StringBuilder t = new StringBuilder();

        t.append("Mean Delay: ");
        t.append(calc.calculateMeanDelayAll(getFreelancers()));
        t.append("\nStandart Deviation Delay: ");
        t.append(calc.calculateStandartDeviationDelayAll(getFreelancers(), calc.calculateMeanDelayAll(getFreelancers())));
        t.append("\nMean Payments: ");
        t.append(calc.calculateMeanPaymentAll(freeL));
        t.append("\nStandart Deviation Payments: ");
        t.append(calc.calculateStandartDeviationPaymentAll(getFreelancers(), calc.calculateMeanPaymentAll(getFreelancers())));
        t.append("\nPrevision: ");
        t.append(calc.calculatePrevision());

        return t.toString().trim();
    }

    /**
     * Returns the list of integer for a histogram of all payments
     * 
     * @return the list of integer
     */
    public List<Integer> gethistogramDataAllPayments() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(0);
        float freelancersMeanDelay = calculateMeanPaymentAll();
        float freelancersStandartDDelay = calculateStandartDeviationPaymentAll(calculateMeanPaymentAll());

        double limiteA = freelancersMeanDelay - freelancersStandartDDelay;
        double limiteB = freelancersMeanDelay + freelancersStandartDDelay;

        for (Freelancer free : getFreelancers()) {
            if (calculateStandartDeviationPaymentFreelancer(free, calculateMeanPaymentFreelancer(free)) <= limiteA) {
                int a = list.get(0);
                a++;
                list.set(0, a);
            } else if (calculateStandartDeviationPaymentFreelancer(free, calculateMeanPaymentFreelancer(free)) < limiteB) {
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
     * Returns the list of integer for a histogram of a freelancer
     * 
     * @param free
     * @return he list of integer
     */
    public List<Integer> gethistogramDataEachPayment(Freelancer free) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(0);
        float freelancersMeanDelay = calculateMeanPaymentFreelancer(free);
        float freelancersStandartDDelay = calculateStandartDeviationPaymentFreelancer(free, calculateMeanPaymentFreelancer(free));

        double limiteA = freelancersMeanDelay - freelancersStandartDDelay;
        double limiteB = freelancersMeanDelay + freelancersStandartDDelay;

        for (Task task : free.getTaskList()) {
            if (task.getPaymentTransaction().getAmount() <= limiteA) {
                int a = list.get(0);
                a++;
                list.set(0, a);
            } else if (task.getPaymentTransaction().getAmount() < limiteB) {
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
     * Returns the list of integer for a histogram of all delays
     * 
     * @return the list of integer
     */
    public List<Integer> gethistogramDataAllDelays() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(0);
        float freelancersMeanDelay = calculateMeanDelayAll();
        float freelancersStandartDDelay = calculateStandartDeviationDelayAll(calculateMeanDelayAll());

        double limiteA = freelancersMeanDelay - freelancersStandartDDelay;
        double limiteB = freelancersMeanDelay + freelancersStandartDDelay;

        for (Freelancer free : getFreelancers()) {
            if (calculateStandartDeviationDelayFreelancer(free, calculateMeanDelayFreelancer(free)) <= limiteA) {
                int a = list.get(0);
                a++;
                list.set(0, a);
            } else if (calculateStandartDeviationDelayFreelancer(free, calculateMeanDelayFreelancer(free)) < limiteB) {
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
     * Returns the list of integer for a histogram of a freelancer
     * 
     * @param free
     * @return he list of integer
     */
    public List<Integer> gethistogramDataEachDelay(Freelancer free) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(0);
        float freelancersMeanDelay = calculateMeanDelayFreelancer(free);
        float freelancersStandartDDelay = calculateStandartDeviationDelayFreelancer(free, calculateMeanDelayFreelancer(free));

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
     * Returns the list of freelancers
     * 
     * @return the list of freelancers
     */
    public List<Freelancer> getFreelancers() {
        plat = AplicationPOT.getInstance().getPlatform();
        freeReg = plat.getFreelancerRegister();
        freeL = freeReg.getFreelancersList();
        return freeL;
    }

    /**
     * Calculates the mean of the delays of a freelancer
     * 
     * @param freelancer
     * @return the delay
     */
    public float calculateMeanDelayFreelancer(Freelancer freelancer) {
        return calc.calculateMeanDelayFreelancer(freelancer);
    }

    /**
     * Calculates the standart deviation of the delays of a freelancer
     * 
     * @param freelancer
     * @param freelancerMeanDelay
     * @return the standart deviation
     */
    public float calculateStandartDeviationDelayFreelancer(Freelancer freelancer, float freelancerMeanDelay) {
        return calc.calculateStandartDeviationDelayFreelancer(freelancer, freelancerMeanDelay);
    }

    /**
     * Calculates the mean of payments of a freelancer
     * 
     * @param freelancer
     * @return the mean
     */
    public float calculateMeanPaymentFreelancer(Freelancer freelancer) {
        return calc.calculateMeanPaymentFreelancer(freelancer);
    }

    /**
     * Calculates the standart deviation of the payments of a freelancer
     * 
     * @param freelancer
     * @param meanPay
     * @return the standart deviation
     */
    public float calculateStandartDeviationPaymentFreelancer(Freelancer freelancer, float meanPay) {
        return calc.calculateStandartDeviationPaymentFreelancer(freelancer, meanPay);
    }

    /**
     * Calculates the mean delay of all freelancers
     * 
     * @return the mean
     */
    public float calculateMeanDelayAll() {
        return calc.calculateMeanDelayAll(getFreelancers());
    }

    /**
     * Calculates the standart deviation of delays of all freelancers
     * 
     * @param meanDelayAll
     * @return 
     */
    public float calculateStandartDeviationDelayAll(float meanDelayAll) {
        return calc.calculateStandartDeviationDelayAll(getFreelancers(), meanDelayAll);
    }

    /**
     * Calculates de mean of payments of all freelancers
     * 
     * @return the mean 
     */
    public float calculateMeanPaymentAll() {
        return calc.calculateMeanPaymentAll(getFreelancers());
    }

    /**
     * Calculates the standart deviation of payments of all freelancers
     * 
     * @param meanPaymentAll
     * @return the standart deviation
     */
    public float calculateStandartDeviationPaymentAll(float meanPaymentAll) {
        return calc.calculateStandartDeviationPaymentAll(getFreelancers(), meanPaymentAll);
    }
}
