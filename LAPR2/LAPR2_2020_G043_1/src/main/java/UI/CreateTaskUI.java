/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.CreateTaskController;
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
public class CreateTaskUI implements Initializable {

    @FXML
    private MenuItem mnuClose;
    @FXML
    private ImageView ImageView;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtbriefDes;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextField txtCost;
    @FXML
    private TextField txtTime;
    @FXML
    private Button btnConfirm;

    private static final String ALERT_TITLE = "T4J";
    private static final String ALERT_HEADER = "Create Task";
    private static final String ALERT_HEADER2 = "Task";
    private static final String ALERT_MESSAGE = "This Task already exists in the system";
    private static final String CONFIRMATION_HEADER = "Confirm your data.";
    private static final String INFORMATION_MESSAGE = "The Task has been successfully created in the system";

    private CreateTaskController appController;
    private MainWindowUI mainWindowUI;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void mnuCloseAction(ActionEvent event) {
        Window win = mnuClose.getParentPopup().getOwnerWindow();
        win.fireEvent(new WindowEvent(win, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    private void btnConfirmAction(ActionEvent event) {

        try {
            String id = txtId.getText().trim();
            String brief = txtbriefDes.getText().trim();
            int time = Integer.parseInt(txtTime.getText().trim());
            float cost = Float.parseFloat(txtCost.getText().trim());
            String cat = txtCategory.getText().trim();

            if (appController.newTask(id, brief, time, cost, cat) == true) {
                String confirmacao = String.format("ID: %s;Brief Description: %s; Time Duration: %s; Cost Per Hour: %.02f; Task Category: %s",
                        txtId.getText(), txtbriefDes.getText(), Integer.parseInt(txtTime.getText()), Float.parseFloat(txtCost.getText()), txtCategory.getText());
                Alert alert = AlertUI.makeAlert(Alert.AlertType.CONFIRMATION, ALERT_TITLE, CONFIRMATION_HEADER, confirmacao);
                if (alert.showAndWait().get() == ButtonType.OK) {
                    appController.taskRegister();
                    AlertUI.makeAlert(Alert.AlertType.INFORMATION, ALERT_TITLE, ALERT_HEADER, INFORMATION_MESSAGE).show();
                    cleanGui();
                    ((Node) event.getSource()).getScene().getWindow().hide();
                } else {
                    event.consume();
                }
            } else {
                cleanGui();
                AlertUI.makeAlert(Alert.AlertType.ERROR, ALERT_TITLE, ALERT_HEADER2, ALERT_MESSAGE).show();
            }
        } catch (IllegalArgumentException iae) {
            AlertUI.makeAlert(Alert.AlertType.ERROR, "Task", "Incorrect Data!", iae.getMessage()).show();
        }

    }

    public void associarParentUI(MainWindowUI main) {
        File file = new File("t4jbackground.jpg");
        Image image = new Image(file.toURI().toString());
        ImageView.setImage(image);

        this.mainWindowUI = main;
        appController = mainWindowUI.getCreateTaskController();
    }

    public void cleanGui() {
        txtId.clear();
        txtbriefDes.clear();
        txtTime.clear();
        txtCost.clear();
        txtCategory.clear();
    }
}
