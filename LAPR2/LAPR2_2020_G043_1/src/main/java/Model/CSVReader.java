/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Autorization.UserSession;
import Controller.AplicationPOT;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Float.parseFloat;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author sandr
 */
public class CSVReader implements FileReader {

    /**
     * The Platform
     */
    private Platform plat;
    /**
     * An Organization
     */
    private Organization org;

    /**
     * The list of payment transaction
     */
    private PaymentTransactionList paymentTransactionList;
    /**
     * The list of tasks
     */
    private TaskList taskList;
    /**
     * The list of Freelancers
     */
    private List<Freelancer> freelancerList;
    /**
     * The Registry of Freelancers
     */
    private FreelancerRegister rfree;

    /**
     * The Freelancer
     */
    private Freelancer freelancer;

    /**
     * Reads the file received by parameter
     *
     * @param fileImported
     */
    @Override
    public void read(File fileImported) {
        AplicationPOT app = AplicationPOT.getInstance();
        plat = app.getPlatform();
        UserSession session = app.getAtualSession();
        String email = session.getUserEmail();
        OrganizationRegister rorg = plat.getOrganizationRegister();
        org = rorg.getOrganizationByEmail(email);
        paymentTransactionList = org.getPaymentTransactionList();
        taskList = org.getTaskList();
        rfree = plat.getFreelancerRegister();
        freelancerList = rfree.getFreelancersList();
        try {
            Scanner ler = new Scanner(fileImported);
            String cabecalho = ler.nextLine();
            try {
                while (ler.hasNextLine()) {
                    String linha = ler.nextLine();
                    String[] itens = linha.split(";");

                    Task task = new Task(itens[1], itens[2], Integer.parseInt(itens[3]), parseFloat(itens[4]), (itens[5]));
                    taskList.taskRegister(task);
                    if (rfree.validaFreelancerByEmail(itens[12].trim()) == false) {
                        freelancer = rfree.newFreelancer(itens[9], itens[10], itens[11], itens[12], itens[13], itens[14], itens[15], itens[16]);
                        freelancerList.add(freelancer);
                    } else {
                        freelancer = rfree.getFreelancerById(itens[9]);
                    }
                    PaymentTransaction pay = paymentTransactionList.newPaymentTransaction(task, freelancer, itens[0], itens[6], Float.parseFloat(itens[7]), itens[8]);
                }
            } finally {
                ler.close();
            }
        } catch (FileNotFoundException fnfe) {
        }
    }
}
