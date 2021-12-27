/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.DefinePaymentTimeController;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class DefinePaymentTimeUI implements Initializable {

    private DefinePaymentTimeController appController;

    @FXML
    private MenuItem mnuClose;
    @FXML
    private Button btnConfirm;
    @FXML
    private Label lblPayment;
    @FXML
    private ImageView imgViewImage;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ");

    @FXML
    private DatePicker datePicker;
    @FXML
    private Label lblHour;
    @FXML
    private TextField txtHour;

    private static final String ERROR_TITLE = "ERROR";
    private static final String ERROR_HEADER = "Define Payment Time";
    private static final String ERRO_MESSAGE = "Invalid data";
    private static final String CONFIRMATION_TITLE = "Confirmation";
    private static final String CONFIRMATION_HEADER = "Define Payment Time";
    private static final String CONFIRMATION_MESSAGE = "Deseja introduzir os seguintes dados?";

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
    private void btnConfirmAction(ActionEvent event) {
        String[] hour = txtHour.getText().split(":");
        if (!txtHour.getText().isEmpty() && hour.length==3 &&  datePicker.getValue() != null) {
            Alert alerta = AlertUI.makeAlert(Alert.AlertType.CONFIRMATION, CONFIRMATION_TITLE, CONFIRMATION_HEADER, CONFIRMATION_MESSAGE + "Date:" + datePicker.getValue().toString() + "\nHour:" + txtHour.getText());
            if (alerta.showAndWait().get() == ButtonType.OK) {
                datePicker.setConverter(new StringConverter<LocalDate>() {
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
                appController.getOrg();
                Calendar cal = appController.validateDate(formatter.format(datePicker.getValue()).concat(txtHour.getText()));
                appController.schedulePayment(cal);
                cleanGui();
                ((Node) event.getSource()).getScene().getWindow().hide();
            } else {
                this.cleanGui();
            }
        } else {
            AlertUI.makeAlert(Alert.AlertType.ERROR, ERROR_TITLE, ERROR_HEADER, ERRO_MESSAGE).show();
        }
    }

    public void associarParentUI(MainWindowUI main) {
        File file = new File("t4jbackground.jpg");
        Image image = new Image(file.toURI().toString());
        imgViewImage.setImage(image);
        appController = main.getDefinePaymentController();
    }

    public void cleanGui() {
        txtHour.clear();
        txtHour.setFocusTraversable(false);
        datePicker.getEditor().clear();
    }
}
