/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.TaskExecutionTimeController;
import Model.Freelancer;
import java.io.File;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class TaskExecutionTimeUI implements Initializable {

    private TaskExecutionTimeController appController;

    @FXML
    private MenuItem mnuClose;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private ImageView imgView;
    @FXML
    private TextArea txtAreaLeft;
    @FXML
    private TextArea txtAreaBottom;
    @FXML
    private Button btnSortName;
    @FXML
    private ComboBox<String> cmbOption;
    @FXML
    private Button btnShow;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private ComboBox<Freelancer> cmbFreelancers;

    private ObservableList<Freelancer> obsFreelancer;
    
    private static final String ALERT_TITLE = "T4J";
    private static final String ALERT_HEADER = "Show Statistics";
    private static final String ALERT_MESSAGE = "Choose a freelancer first!";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void mnuCloseAction(ActionEvent event) {
        cleanGui();
        Window win = mnuClose.getParentPopup().getOwnerWindow();
        win.fireEvent(new WindowEvent(win, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    private void btnSortNameAction(ActionEvent event) {
        txtAreaLeft.setText(appController.getFreelancersListSortedByName(appController.getFreelancersByOrg()));
    }

    @FXML
    private void btnShowAction(ActionEvent event) {
        if (cmbOption.getValue() == "All Freelancers") {
            showHistograms(cmbOption.getValue());
        } else if (cmbOption.getValue() == "Each Freelancer" && cmbFreelancers.getValue() == null) {
            AlertUI.makeAlert(Alert.AlertType.ERROR, ALERT_TITLE, ALERT_HEADER, ALERT_MESSAGE);
        } else {
            showHistograms(cmbOption.getValue(), cmbFreelancers.getValue());
        }

    }

    private void showHistograms(String type, Freelancer free) {
        List<Integer> histogram = appController.gethistogramDataEach(free);

        float freelancersMeanDelay = appController.getMeanDelayFreelancer(free);
        float freelancersStandartDDelay = appController.getStandartDeviationFreelancer(free);

        double limiteA = freelancersMeanDelay - freelancersStandartDDelay;
        double limiteB = freelancersMeanDelay + freelancersStandartDDelay;

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.UP);
        barChart.getData().clear();

        XYChart.Series set1 = new XYChart.Series<>();
        set1.setName("Delay");
        set1.getData().add(new XYChart.Data("]-∞," + df.format(limiteA) + "]", histogram.get(0)));
        set1.getData().add(new XYChart.Data("]" + df.format(limiteA) + "," + df.format(limiteB) + "[", histogram.get(1)));
        set1.getData().add(new XYChart.Data("[" + df.format(limiteB) + ", +∞[", histogram.get(2)));

        barChart.getData().addAll(set1);

    }

    private void showHistograms(String type) {
        List<Integer> histogram = appController.gethistogramDataAll();

        float allMeanDelay = appController.getMeanDelayAll();
        float allStandartDeviation = appController.getStandartDeviationAll();

        double limiteA = allMeanDelay - allStandartDeviation;
        double limiteB = allMeanDelay + allStandartDeviation;

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.UP);
        barChart.getData().clear();

        XYChart.Series set1 = new XYChart.Series<>();
        set1.setName("Delay");
        set1.getData().add(new XYChart.Data("]-∞," + df.format(limiteA) + "]", histogram.get(0)));
        set1.getData().add(new XYChart.Data("]" + df.format(limiteA) + "," + df.format(limiteB) + "[", histogram.get(1)));
        set1.getData().add(new XYChart.Data("[" + df.format(limiteB) + ", +∞[", histogram.get(2)));

        barChart.getData().addAll(set1);
    }

    public void associarParentUI(MainWindowUI main) {
        File file = new File("t4jbackground.jpg");
        Image image = new Image(file.toURI().toString());
        imgView.setImage(image);
        appController = main.getTaskExecutionTimeController();
        appController.getOrg();
        fillCombo();
        txtAreaLeft.setText(appController.getStatisticsEachOne());
        txtAreaBottom.setText(String.format("Mean Task Execution Delay: %f\nStandart Deviation Task Execution Delay: %f", appController.getMeanDelayAll(), appController.getStandartDeviationAll()));
        cmbFreelancers.setDisable(true);
    }

    public void cleanGui() {
        txtAreaBottom.clear();
        txtAreaLeft.clear();
        cmbOption.getItems().clear();
        cmbFreelancers.getItems().clear();
        barChart.getData().clear();
        cmbFreelancers.setDisable(true);

    }

    public void fillCombo() {
        obsFreelancer = FXCollections.observableArrayList(appController.getFreelancersByOrg());
        cmbOption.getItems().addAll("Each Freelancer", "All Freelancers");
        cmbFreelancers.getItems().addAll(obsFreelancer);
    }

    @FXML
    private void cmbOptionAction(ActionEvent event) {
        if (cmbOption.getValue() == "Each Freelancer") {
            cmbFreelancers.setDisable(false);
        }
    }

}
