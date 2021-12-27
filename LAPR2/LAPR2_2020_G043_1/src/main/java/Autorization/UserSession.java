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
public class UserSession implements Serializable{
    
    /**
     * The user
     */
    private User user = null;

    /**
     * Creates an instance of UserSession receiving the user by parameter
     * 
     * @param oUser 
     */
    public UserSession(User oUser){
        if (oUser == null){
            throw new IllegalArgumentException("Argumento não pode ser nulo.");
        }
        this.user = oUser;
    }
    
    /**
     * Logs the user out
     */
    public void doLogout(){
        this.user = null;
    }
    
    /**
     * Verifies if there is any user logged in
     * 
     * @return if somebody is logged in
     */
    public boolean isLoggedIn(){
        return this.user != null;
    }
    
    /**
     * Returns the name of the user logged in
     * 
     * @return the name
     */
    public String getUserName()
    {
        if (isLoggedIn())
            this.user.getName();
        return null;
    }

    /**
     * Returns the email of the user logged in
     * 
     * @return the email
     */
    public String getUserEmail()
    {
        if (isLoggedIn()==true){
            return this.user.getEmail();
        }
        return null;
    }
    
    /**
     * Return the role of the user logged in
     * 
     * @return the role
     */
    public String getUserRole()
    {
        return this.user.getRole();
    }
    
    /**
     * Returns the textual description of the user logged in
     * 
     * @return the user data
     */
    public String toString(){
        return user.toString();
    }
}
