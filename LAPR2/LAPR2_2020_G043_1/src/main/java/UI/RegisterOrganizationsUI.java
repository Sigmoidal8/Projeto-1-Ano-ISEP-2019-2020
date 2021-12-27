/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.RegisterOrganizationController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Miguel
 */
public class RegisterOrganizationsUI implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private TextField txtNameOrg;
    @FXML
    private TextField txtNIFOrg;
    @FXML
    private TextField txtEmailCollab;
    @FXML
    private TextField txtNameCollab;
    @FXML
    private TextField txtEmailMana;
    @FXML
    private TextField txtNameMana;
    @FXML
    private Button btnConfirm;

    private RegisterOrganizationController appController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void btnConfirmAction(ActionEvent event) {
        if ((!txtNameOrg.getText().isEmpty()) && (!txtNIFOrg.getText().isEmpty()) && (!txtNameCollab.getText().isEmpty())
                && (!txtEmailCollab.getText().isEmpty()) && (!txtNameMana.getText().isEmpty()) && (!txtEmailMana.getText().isEmpty())) {

            Boolean existsCollab = appController.existsEmail(txtEmailCollab.getText());
            Boolean existsMana = appController.existsEmail(txtEmailMana.getText());
            if (existsCollab == false) {
                
                if (existsMana == false) {
                    String a = appController.newOrganization(txtNameOrg.getText(), txtNIFOrg.getText(), txtNameCollab.getText(), txtEmailCollab.getText(), txtNameMana.getText(), txtEmailMana.getText());

                    Alert al = AlertUI.makeAlert(Alert.AlertType.CONFIRMATION, "RegisterOrganization", "Do you wish to confirm this data?", a);

                    if (al.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    } else {
                        try {
                            appController.registerOrganization();
                            AlertUI.makeAlert(Alert.AlertType.INFORMATION, "RegisterOrganization", "Success", "Your organization has been successfully registered!").show();
                            cleanGui();
                            ((Node) event.getSource()).getScene().getWindow().hide();
                            //quero que esconda a janela depois
                        } catch (IOException ex) {
                            Logger.getLogger(RegisterOrganizationsUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }else{
                    AlertUI.makeAlert(Alert.AlertType.INFORMATION, "T4J", "Duplicate information", "This Manager already exists in the system").show();
                }
            }else{
                AlertUI.makeAlert(Alert.AlertType.INFORMATION, "T4J", "Duplicate information", "This Collaborator already exists in the system").show();
            }
            //alert de confirmação
        } else {
            //alerta a avisar que não está completo
            AlertUI.makeAlert(Alert.AlertType.ERROR, "Register Organization", "Error",
                    "Incomplete parameters").show();
        }

    }

    public void associarParentUI(MainWindowUI main) {
        File file = new File("t4jbackground.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        appController = main.getRegisterOrganizationController();
    }

    public void cleanGui() {
        txtNameOrg.clear();
        txtNIFOrg.clear();
        txtEmailCollab.clear();
        txtNameCollab.clear();
        txtEmailMana.clear();
        txtNameMana.clear();
    }
}
