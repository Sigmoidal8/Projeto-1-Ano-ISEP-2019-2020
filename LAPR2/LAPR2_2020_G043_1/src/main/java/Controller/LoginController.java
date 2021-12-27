/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Miguel
 */
public class LoginController {

    /**
     * The AplicationPOT
     */
    private AplicationPOT app;

    /**
     * Creates an instance of LoginController initializing the AplicationPOT
     */
    public LoginController() {
        this.app = AplicationPOT.getInstance();
    }

    /**
     * Returns the user role of the user logged in
     *
     * @return the role
     */
    public String getUserRole() {
        if (this.app.getAtualSession().isLoggedIn()) {
            return this.app.getAtualSession().getUserRole();
        }
        return null;
    }

    /**
     * Logs a user in receiving the email and password by parameter
     *
     * @param strId
     * @param strPwd
     * @return if the user was logged in
     */
    public boolean doLogin(String strId, String strPwd) {
        return this.app.doLogin(strId, strPwd);
    }

    /**
     * Logs the user out
     */
    public void doLogout() {
        this.app.doLogout();
    }
}
