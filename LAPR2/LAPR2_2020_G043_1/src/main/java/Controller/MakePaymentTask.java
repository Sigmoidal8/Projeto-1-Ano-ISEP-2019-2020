/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ExternalAlgorithmConvertCurrencies;
import Model.Freelancer;
import Model.FreelancerRegister;
import Model.Organization;
import Model.PaymentTransaction;
import Model.Platform;
import Model.Task;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.TimerTask;

/**
 *
 * @author raulcoelho
 */
public class MakePaymentTask extends TimerTask implements Serializable {

    /**
     * The Platform
     */
    private Platform plat;

    /**
     * An Organization
     */
    private Organization org;
    
    /**
     * The Currency Converter
     */
    private ExternalAlgorithmConvertCurrencies alg;

    /**
     * Sets the organization to the one received by parameter
     *
     * @param org
     */
    public void setOrganization(Organization org) {
        this.org = org;
    }

    /**
     * Makes the payments of the list of payment transaction that have not been
     * payed
     */
    public void makePaymentsUnpaid() {
        FreelancerRegister freeReg = plat.getFreelancerRegister();
        List<Freelancer> lstFree = freeReg.getFreelancersList();
        for (Freelancer free : lstFree) {
            List<Task> lstTask = free.getTaskList();
            String email = "";
            float total = 0;
            for (Task task : lstTask) {
                for (Task obj : org.getTaskList().getTaskList()) {
                    if (task == obj) {
                        PaymentTransaction pay = task.getPaymentTransaction();
                        if (pay != null) {
                            boolean payed = pay.getPaymentMade();
                            if (payed == false) {
                                float amount = pay.getAmount();
                                String country = free.getCountry();
                                alg = plat.getCurrenciesConvert();
                                float amountCnv = alg.getConvertedAmount(amount, country);
                                String desc = task.getBriefDescription();
                                pay.setPayed(true);
                                email = writeEmail(email, amount, amountCnv, desc, country, free);
                                total += amount;
                                plat.enterNewPayment(task, free, amount, amountCnv);
                            }
                        }
                    }
                }
            }
            float totalConverted = alg.getConvertedAmount(total, free.getCountry());
            sendEmail(email, free, total, totalConverted);
        }
    }

    /**
     * Adds the task and the price to the email
     *
     * @param email
     * @param amount
     * @param amountCnv
     * @param desc
     * @param country
     * @param free
     * @return the email
     */
    public String writeEmail(String email, float amount, float amountCnv, String desc, String country, Freelancer free) {
        String line = String.format("Task: %s, Price in Euro: %.2f€ , Converted to %s Currency: %.2f", desc, amount, country, amountCnv);
        return email.concat(String.format("%s\n", line));
    }

    /**
     * Sends the email
     *
     * @param email
     * @param free
     * @return if the email was sent
     */
    public boolean sendEmail(String email, Freelancer free, float total, float totalConverted) {
        try {
            if (email.equals("") == false) {
                PrintWriter print = new PrintWriter(new FileWriter("e-mail.txt", true));
                try {
                    print.println(String.format("\n\n\n####Receipt####\n\n%s\n%s\nTotal: %.2f€       Total Converted to %s Currency: %.2f", free.getName().trim(), email,total, free.getCountry(), totalConverted));
                    return true;

                } finally {
                    print.close();
                }
            } else {
                return false;
            }
        } catch (FileNotFoundException fnf) {
            return false;
        } catch (IOException io) {
            return false;
        }
    }

    /**
     * makes the payments
     */
    @Override
    public void run() {
        this.plat = AplicationPOT.getInstance().getPlatform();
        makePaymentsUnpaid();
    }
}
