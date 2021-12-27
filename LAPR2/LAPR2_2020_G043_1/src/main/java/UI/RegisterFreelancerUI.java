/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.RegisterFreelancerController;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Miguel
 */
public class RegisterFreelancerUI implements Initializable {

    @FXML
    private MenuItem mnuClose;
    @FXML
    private Button btnRegisterFreelancer;
    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox<String> cmbFreelancer;
    @FXML
    private TextField txtNIF;
    @FXML
    private TextField txtIBAN;
    @FXML
    private TextField txtCountry;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;

    private static final String ALERT_TITLE = "T4J - Information";
    private static final String ALERT_HEADER = "Register Freelancer";
    private static final String ALERT_MESSAGE = "This freelancer already exists in the system";
    private static final String CONFIRMATION_HEADER = "Confirmation of data.";
    private static final String ERROR_MESSAGE = "No data detected or invalid data.";
    private static final String INFORMATION_MESSAGE = "The Freelancer has been successfully registed in the system";

    private RegisterFreelancerController appController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void mnuCloseAction(ActionEvent event) {
        Window win = mnuClose.getParentPopup().getOwnerWindow();
        win.fireEvent(new WindowEvent(win, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    private void btnRegisterFreelancerAction(ActionEvent event) {
        try {
            if (!cmbFreelancer.getSelectionModel().isEmpty() && !txtNIF.getText().isEmpty() && !txtIBAN.getText().isEmpty()
                    && !txtEmail.getText().isEmpty() && !txtCountry.getText().isEmpty() && !txtName.getText().isEmpty() && !txtAddress.getText().isEmpty()) {

                if (appController.newFreelancer(txtName.getText(), cmbFreelancer.getSelectionModel().getSelectedItem(), txtEmail.getText(),
                        txtNIF.getText(), txtIBAN.getText(), txtAddress.getText(), txtCountry.getText())) {
                    String confirmacao = String.format("Name:%s, LevelOfExpertise:%s, Email:%s, NIF:%s, IBAN:%s, Adress:%s, Country:%s",
                            txtName.getText(), cmbFreelancer.getSelectionModel().getSelectedItem(), txtEmail.getText(), txtNIF.getText(), txtIBAN.getText(), txtAddress.getText(), txtCountry.getText());
                    Alert alert = AlertUI.makeAlert(Alert.AlertType.CONFIRMATION, ALERT_TITLE, CONFIRMATION_HEADER, confirmacao);
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        appController.registerFreelancer();
                        cleanGui();
                        AlertUI.makeAlert(Alert.AlertType.INFORMATION, ALERT_TITLE, ALERT_HEADER, INFORMATION_MESSAGE).show();
                        ((Node) event.getSource()).getScene().getWindow().hide();
                    } else {
                        event.consume();
                    }
                } else {
                    cleanGui();
                    AlertUI.makeAlert(Alert.AlertType.ERROR, ALERT_TITLE, ALERT_HEADER, ALERT_MESSAGE).show();
                    ((Node) event.getSource()).getScene().getWindow().hide();
                }
            } else {
                AlertUI.makeAlert(Alert.AlertType.INFORMATION, ALERT_TITLE, ALERT_HEADER, ERROR_MESSAGE).show();
            }
        } catch (Exception e) {
            AlertUI.makeAlert(Alert.AlertType.INFORMATION, ALERT_TITLE, ALERT_HEADER, "Exception").show();
        }

    }

    public void startCombobox() {
        //cmbFreelancer = new ComboBox<>();
        cmbFreelancer.getItems().addAll("Junior", "Senior");
    }

    public void associarParentUI(MainWindowUI main) {
        File file = new File("t4jbackground.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        appController = main.getRegisterFreelancerController();
        startCombobox();
    }

    public void cleanGui() {
        cmbFreelancer.getSelectionModel().clearSelection();
        txtNIF.clear();
        txtIBAN.clear();
        txtCountry.clear();
        txtEmail.clear();
        txtName.clear();
        txtAddress.clear();
    }

}
