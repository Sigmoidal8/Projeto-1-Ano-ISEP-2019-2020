/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Autorization.AutorizationFacade;
import Autorization.UserSession;
import Model.Platform;
import UI.AlertUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.scene.control.Alert;

/**
 *
 * @author Miguel
 */
public class AplicationPOT implements Serializable{

    /**
     * The plaform
     */
    private final Platform plat;
    
    /**
     * The autorization 
     */
    private final AutorizationFacade autorization;
    
    /**
     * Instance of AplicationPot
     */
    private static AplicationPOT singleton = null;
    
    /**
     * Name of the file where the platform will be saved
     */
    private static final String NOME_FICHEIRO = "_Platform.pla";

    /**
     * Creates an instance of AplicationPOT
     */
    public AplicationPOT() {
        plat = ler();
        autorization = this.plat.getAutorizationFacade();
        singleton = this;
        bootstrap();
        plat.scheduleEmails();
    }

    /**
     * Returns the platform associated to the AplicationPOT
     * 
     * @return the platform
     */
    public Platform getPlatform() {
        return this.plat;
    }

    /**
     * Returrns the atual session
     * 
     * @return the session
     */
    public UserSession getAtualSession() {
        return this.autorization.getAtualSession();
    }
    
    /**
     * Returns the AplicationPOT that is being used
     * 
     * @return the AplicationPOT
     */
    public static AplicationPOT getInstance() {
        if (singleton == null) {
            synchronized (AplicationPOT.class) {
                singleton = new AplicationPOT();
            }
        }
        return singleton;
    }

    /**
     * Does the login of the user receiving the email and password by parameter
     * 
     * @param strId
     * @param strPwd
     * @return if the user was logged in
     */
    public boolean doLogin(String strId, String strPwd) {
        return this.autorization.doLogin(strId, strPwd) != null;
    }

    /**
     * Does the logout of the user
     */
    public void doLogout() {
        this.autorization.doLogout();
    }

    /**
     * Initializes the administrators of the platform
     */
    private void bootstrap() {
        this.autorization.registerUserWithRole("Administrator 1", "adm1@isep.pt", "a123456", "Administrator");
        this.autorization.registerUserWithRole("Administrator 2", "a", "a", "Administrator");
    }

    /**
     * Reads the file name
     * 
     * @return the platform
     */
    public Platform ler() {
        return ler(NOME_FICHEIRO);
    }

    /**
     *  Reads the file
     *
     * @param nomeFicheiro
     * @return the platform
     */
    public Platform ler(String nomeFicheiro) {
        return ler(new File(nomeFicheiro));
    }

    /**
     * Reads the platform inside the file
     * 
     * @param ficheiro
     * @return the platform
     */
    public Platform ler(File ficheiro) {
        Platform plat;
        try {
            FileInputStream file = new FileInputStream(ficheiro);
            ObjectInputStream in = new ObjectInputStream(file);
            try {
                plat = (Platform) in.readObject();
            } finally {
                in.close();
            }
            System.out.println(plat.toString());
            return plat;
        } catch (IOException | ClassNotFoundException ex) {
            return new Platform();
        }
    }

    /**
     * Passes the file name and the platform
     * 
     * @return if the file was saved
     */
    public boolean guardar() {
        return guardar(NOME_FICHEIRO, plat);
    }

    /**
     * Creates the file and passes the platform
     * 
     * @param nomeFicheiro
     * @param plat
     * @return if the file was saved
     */
    public boolean guardar(String nomeFicheiro, Platform plat) {
        return guardar(new File(nomeFicheiro), plat);
    }

    /**
     * Saves the platform inside the file
     * 
     * @param ficheiro
     * @param plat
     * @return if the file was saved
     */
    public boolean guardar(File ficheiro, Platform plat) {
        try {
            FileOutputStream file = new FileOutputStream(ficheiro);
            ObjectOutputStream out = new ObjectOutputStream(file);
            try {
                out.writeObject(plat);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println(ex.toString());
            ex.printStackTrace();
            AlertUI.makeAlert(Alert.AlertType.ERROR, NOME_FICHEIRO, NOME_FICHEIRO, "Error saving the file").showAndWait();
            return false;
        }
    }
}
