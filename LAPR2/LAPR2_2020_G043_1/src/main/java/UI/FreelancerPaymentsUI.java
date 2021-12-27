package UI;

import Controller.FreelancerPaymentsController;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Miguel
 */
public class FreelancerPaymentsUI implements Initializable {

    private FreelancerPaymentsController appController;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btnSortByName;
    @FXML
    private Button btnSortByPaymentValue;
    @FXML
    private TextArea txtAreaFreelancersList;
    @FXML
    private MenuItem mnuClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void btnSortByNameAction(ActionEvent event) {
        txtAreaFreelancersList.setText(appController.getFreelancersListSortedByName(appController.getFreelancers()));
    }

    @FXML
    private void btnSortByPaymentValueAction(ActionEvent event) {
        txtAreaFreelancersList.setText(appController.getFreelancersListSortedByPaymentValue(appController.getFreelancers()));
    }

    public void associarParentUI(MainWindowUI main) {
        File file = new File("t4jbackground.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        appController = main.getFreelancerPaymentsController();
        appController.getOrg();
        txtAreaFreelancersList.setText(appController.getStatisticsEachOne());
    }

    @FXML
    private void mnuCloseAction(ActionEvent event) {
        Window win = mnuClose.getParentPopup().getOwnerWindow();
        win.fireEvent(new WindowEvent(win, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
