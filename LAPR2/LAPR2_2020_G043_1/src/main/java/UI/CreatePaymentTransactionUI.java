/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.CreatePaymentTransactionController;
import Model.Freelancer;
import Model.Task;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Miguel
 */
public class CreatePaymentTransactionUI implements Initializable {

    @FXML
    private MenuItem mnuClose;
    @FXML
    private DatePicker dpEndDate;
    @FXML
    private ComboBox<Task> cmbTask;
    @FXML
    private ComboBox<Freelancer> cmbFreelancer;
    @FXML
    private TextField txtDelay;
    @FXML
    private TextField txtDescription;
    @FXML
    private Button btnRegisterPayment;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField txtTransID;
    @FXML
    private Menu mnuHelp;
    @FXML
    private MenuItem mnuInfo;

    private static final String ALERT_TITLE = "T4J";
    private static final String ALERT_HEADER = "Create Payment Transaction";
    private static final String ERROR_MESSAGE = "No data detected or invalid data.";
    private static final String INFORMATION_MESSAGE = "Your Payment transaction has been successfully created";
    private static final String CONFIRMATION_MESSAGE = "Do you confirm the registration of the paymentTransaction with the amount of %.2f €";
    private static final String HELP_MESSAGE = "If the task or freelancer don't exist yet, please go back and regist them.";
    private static final String HELP_HEADER = "Info";

    private CreatePaymentTransactionController appController;
    private ObservableList<Task> obsTask;
    private ObservableList<Freelancer> obsFreelancer;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void associarParentUI(MainWindowUI main) {
        File file = new File("t4jbackground.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        appController = main.getCreatePaymentTransactionController();
        startCombobox();
    }

    public void cleanGui() {
        txtDelay.clear();
        txtDescription.clear();
        cmbTask.getItems().clear();
        cmbFreelancer.getItems().clear();
        dpEndDate.getEditor().clear();
        txtTransID.clear();
    }

    @FXML
    private void mnuCloseAction(ActionEvent event) {
        cleanGui();
        Window win = mnuClose.getParentPopup().getOwnerWindow();
        win.fireEvent(new WindowEvent(win, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    private void btnRegisterPaymentAction(ActionEvent event) {
        try {
            if (!cmbTask.getSelectionModel().isEmpty() && !cmbFreelancer.getSelectionModel().isEmpty()
                    && !txtTransID.getText().isEmpty() && !txtDelay.getText().isEmpty() && !txtDescription.getText().isEmpty() && dpEndDate.getValue() != null) {
                dpEndDate.setConverter(new StringConverter<LocalDate>() {
                    @Override
                    public String toString(LocalDate object) {
                        if (object != null) {
                            return formatter.format(object);
                        }
                        return null;
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        if (string != null && !string.trim().isEmpty()) {
                            return LocalDate.parse(string, formatter);
                        }
                        return null;
                    }
                });
                appController.addTaskToFreelancer(cmbFreelancer.getSelectionModel().getSelectedItem(), cmbTask.getSelectionModel().getSelectedItem());
                appController.newPaymentTransaction(cmbTask.getSelectionModel().getSelectedItem(), cmbFreelancer.getSelectionModel().getSelectedItem(),
                        txtTransID.getText(), formatter.format(dpEndDate.getValue()), Float.parseFloat(txtDelay.getText()), txtDescription.getText());
                Alert alert = AlertUI.makeAlert(Alert.AlertType.CONFIRMATION, ALERT_TITLE, ALERT_HEADER, String.format(CONFIRMATION_MESSAGE, appController.calculatePayment(cmbFreelancer.getSelectionModel().getSelectedItem(), cmbTask.getSelectionModel().getSelectedItem())));
                if (alert.showAndWait().get() == ButtonType.OK) {
                    appController.registerPaymentTransaction();
                    AlertUI.makeAlert(Alert.AlertType.INFORMATION, ALERT_TITLE, ALERT_HEADER, INFORMATION_MESSAGE).show();
                } else {
                    event.consume();
                }
                cleanGui();
                ((Node) event.getSource()).getScene().getWindow().hide();
            } else {
                AlertUI.makeAlert(Alert.AlertType.INFORMATION, ALERT_TITLE, ALERT_HEADER, ERROR_MESSAGE).show();
            }
        } catch (Exception e) {
            AlertUI.makeAlert(Alert.AlertType.INFORMATION, ALERT_TITLE, ALERT_HEADER, ERROR_MESSAGE).show();
        }

    }

    public void startCombobox() {
        obsTask = FXCollections.observableArrayList(appController.getTaskList());
        obsFreelancer = FXCollections.observableArrayList(appController.getFreelancerList());
        cmbTask.setItems(obsTask);
        cmbFreelancer.setItems(obsFreelancer);
    }

    @FXML
    private void mnuHelp(ActionEvent event) {
        AlertUI.makeAlert(Alert.AlertType.INFORMATION, ALERT_TITLE, HELP_HEADER, HELP_MESSAGE).showAndWait();
    }

}
