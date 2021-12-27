/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Autorization;

import java.io.Serializable;

/**
 *
 * @author Miguel
 */
public class AutorizationFacade implements Serializable{
   
    /**
     * The session logged in
     */
    private UserSession Session = null;
    
    /**
     * The user registry of the platform
     */
    private final UserRegister user = new UserRegister();
    
    /**
     * Registers a user with a specific role in the Platform
     * 
     * @param strNome
     * @param strEmail
     * @param strPassword
     * @param role
     * @return if the user was added
     */
    public boolean registerUserWithRole(String strNome, String strEmail, String strPassword,String role) {
        User usr = this.user.newUser(strNome,strEmail,strPassword,role);
        return this.user.addUser(usr);
    }
    
    /**
     * Verifies if the user email exists in the platform
     * 
     * @param strId
     * @return if the user exists in the platform
     */
    public boolean hasUser(String strId){
        return this.user.hasUtilizador(strId);
    }
    
    /**
     * Logs the user in the application
     * 
     * @param strId
     * @param strPwd
     * @return the user session
     */
    public UserSession doLogin(String strId, String strPwd){
        User usr = this.user.searchUser(strId);
        if (usr != null)
        {
            if (usr.hasPassword(strPwd)){
                this.Session = new UserSession(usr);
            }
        }
        return getAtualSession();
    }
    
    /**
     * Returns the session of the user logged in
     * 
     * @return the session
     */
    public UserSession getAtualSession(){
        return this.Session;
    }
    
    /**
     * Logs the user out
     */
    public void doLogout()
    {
        if (this.Session != null){
            this.Session.doLogout();
        }
        this.Session = null;
    }
}
