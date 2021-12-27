/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.Serializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Miguel
 */
public class AlertUI  {
     public static Alert makeAlert(Alert.AlertType alertType, String title, String header, String message) {
        Alert alert = new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        if (alertType == Alert.AlertType.CONFIRMATION) {
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        }
        return alert;
    }  
}
