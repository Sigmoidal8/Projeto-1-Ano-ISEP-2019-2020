/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.LoginController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author raulcoelho
 */
public class LoginUI implements Initializable {

    @FXML
    private Label lblConfirm;
    @FXML
    private Button btnConfirmarRegisto;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Label lblApp;
    @FXML
    private Label lblUser;
    @FXML
    private Label lblPass;

    private final static String ALERT_TITLE = "T4J";
    private final static String ALERT_HEADER = "Login";
    private final static String ALERT_MESSAGE = "Login successful";

    private LoginController appController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public String getPapeisUtilizador() {
        return this.appController.getUserRole();
    }

    public void associarParentUI(MainWindowUI main) {
        appController = main.getLoginController();
    }

    public void cleanGui() {
        txtUser.clear();
        txtPass.clear();
    }

    @FXML
    private void btnConfirmarRegistoAction(ActionEvent event) {
        boolean sucesso = false;
        sucesso = appController.doLogin(txtUser.getText(), txtPass.getText());
        if (sucesso == false) {
            lblConfirm.setText("Invalid Login");
        }
        cleanGui();
        if (sucesso == true) {
            AlertUI.makeAlert(Alert.AlertType.INFORMATION, ALERT_TITLE, ALERT_HEADER, ALERT_MESSAGE).showAndWait();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } 
    }

}
