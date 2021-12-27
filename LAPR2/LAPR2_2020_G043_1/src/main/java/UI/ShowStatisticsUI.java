/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.ShowGeneralStatisticsController;
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
import org.apache.commons.math.MathException;

/**
 * FXML Controller class
 *
 * @author raulcoelho
 */
public class ShowStatisticsUI implements Initializable {

    @FXML
    private MenuItem mnuClose;
    @FXML
    private ImageView imgView;
    @FXML
    private TextArea txtAreaLeft;
    @FXML
    private TextArea txtAreaBottom;
    @FXML
    private ComboBox<String> cmbOption;
    @FXML
    private ComboBox<Freelancer> cmbCategory;
    @FXML
    private Button btnShow;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    private ShowGeneralStatisticsController appController;
    @FXML
    private BarChart<String, Number> barChart2;

    private ObservableList<Freelancer> obsFreelancer;

    private static final String ALERT_TITLE = "T4J";
    private static final String ALERT_HEADER = "Show Statistics";
    private static final String ALERT_MESSAGE = "Choose a freelancer first!";
    private static final String ERROR_MESSAGE = "There are no freelancers in the system";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cleanGui();
    }

    @FXML
    private void mnuCloseAction(ActionEvent event) {
        cleanGui();
        Window win = mnuClose.getParentPopup().getOwnerWindow();
        win.fireEvent(new WindowEvent(win, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    private void btnShowAction(ActionEvent event) {
        if (appController.getFreelancers().isEmpty() == false) {
            if (cmbOption.getValue() == "All Freelancers") {
                showHistograms(cmbOption.getValue());
            } else if (cmbOption.getValue() == "Each Freelancer" && cmbCategory.getValue() == null) {
                AlertUI.makeAlert(Alert.AlertType.ERROR, ALERT_TITLE, ALERT_HEADER, ALERT_MESSAGE);
            } else {
                showHistograms(cmbOption.getValue(), cmbCategory.getValue());

            }
        } else {
            AlertUI.makeAlert(Alert.AlertType.INFORMATION, ALERT_TITLE, ALERT_HEADER, ERROR_MESSAGE).show();
        }

    }

    private void showHistograms(String type, Freelancer free) {
        List<Integer> histogram = appController.gethistogramDataEachDelay(free);
        List<Integer> histogram2 = appController.gethistogramDataEachPayment(free);

        float freelancersMeanDelay = appController.calculateMeanDelayFreelancer(free);
        float freelancersStandartDelay = appController.calculateStandartDeviationDelayFreelancer(free, freelancersMeanDelay);
        float freelancersMeanPayment = appController.calculateMeanPaymentFreelancer(free);
        float freelancersStandartPayment = appController.calculateStandartDeviationPaymentFreelancer(free, freelancersMeanPayment);

        double limiteA = freelancersMeanDelay - freelancersStandartDelay;
        double limiteB = freelancersMeanDelay + freelancersStandartDelay;
        double limiteC = freelancersMeanPayment - freelancersStandartPayment;
        double limiteD = freelancersMeanPayment + freelancersStandartPayment;

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.UP);
        barChart.getData().clear();
        barChart2.getData().clear();

        XYChart.Series set1 = new XYChart.Series<>();
        XYChart.Series set2 = new XYChart.Series<>();

        set1.setName("Delay");
        set1.getData().add(new XYChart.Data("]-∞," + df.format(limiteA) + "]", histogram.get(0)));
        set1.getData().add(new XYChart.Data("]" + df.format(limiteA) + "," + df.format(limiteB) + "[", histogram.get(1)));
        set1.getData().add(new XYChart.Data("[" + df.format(limiteB) + ", +∞[", histogram.get(2)));

        set2.setName("Payment");
        set2.getData().add(new XYChart.Data("]-∞," + df.format(limiteC) + "]", histogram2.get(0)));
        set2.getData().add(new XYChart.Data("]" + df.format(limiteC) + "," + df.format(limiteD) + "[", histogram2.get(1)));
        set2.getData().add(new XYChart.Data("[" + df.format(limiteD) + ", +∞[", histogram2.get(2)));

        barChart2.getData().addAll(set1);
        barChart.getData().addAll(set2);

    }

    private void showHistograms(String type) {
        List<Integer> histogram = appController.gethistogramDataAllDelays();
        List<Integer> histogram2 = appController.gethistogramDataAllPayments();

        float freelancersMeanDelay = appController.calculateMeanDelayAll();
        float freelancersStandartDelay = appController.calculateStandartDeviationDelayAll(freelancersMeanDelay);
        float freelancersMeanPayment = appController.calculateMeanPaymentAll();
        float freelancersStandartPayment = appController.calculateStandartDeviationPaymentAll(freelancersMeanDelay);

        double limiteA = freelancersMeanDelay - freelancersStandartDelay;
        double limiteB = freelancersMeanDelay + freelancersStandartDelay;
        double limiteC = freelancersMeanPayment - freelancersStandartPayment;
        double limiteD = freelancersMeanPayment + freelancersStandartPayment;

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.UP);
        barChart.getData().clear();
        barChart2.getData().clear();

        XYChart.Series set1 = new XYChart.Series<>();
        XYChart.Series set2 = new XYChart.Series<>();

        set1.setName("Delay");
        set1.getData().add(new XYChart.Data("]-∞," + df.format(limiteA) + "]", histogram.get(0)));
        set1.getData().add(new XYChart.Data("]" + df.format(limiteA) + "," + df.format(limiteB) + "[", histogram.get(1)));
        set1.getData().add(new XYChart.Data("[" + df.format(limiteB) + ", +∞[", histogram.get(2)));

        set2.setName("Payment");
        set2.getData().add(new XYChart.Data("]-∞," + df.format(limiteC) + "]", histogram2.get(0)));
        set2.getData().add(new XYChart.Data("]" + df.format(limiteC) + "," + df.format(limiteD) + "[", histogram2.get(1)));
        set2.getData().add(new XYChart.Data("[" + df.format(limiteD) + ", +∞[", histogram2.get(2)));

        barChart2.getData().addAll(set1);
        barChart.getData().addAll(set2);

    }

    public void associarParentUI(MainWindowUI main) throws MathException {
        File file = new File("t4jbackground.jpg");
        Image image = new Image(file.toURI().toString());
        imgView.setImage(image);
        appController = main.getShowGeneralStatisticsController();
        fillCombo();
        txtAreaBottom.setText(appController.getStatisticsAll());
        txtAreaLeft.setText(appController.getStatisticsEachOne());
        cmbCategory.setDisable(true);
    }

    public void cleanGui() {
        txtAreaBottom.clear();
        txtAreaLeft.clear();
        cmbOption.getItems().clear();
        cmbCategory.getItems().clear();
        barChart.getData().clear();
        cmbCategory.setDisable(true);

    }

    public void fillCombo() {
        obsFreelancer = FXCollections.observableArrayList(appController.getFreelancers());
        cmbOption.getItems().addAll("Each Freelancer", "All Freelancers");
        cmbCategory.getItems().addAll(obsFreelancer);
    }

    @FXML
    private void cmbOptionAction(ActionEvent event) {
        if (cmbOption.getValue() == "Each Freelancer") {
            cmbCategory.setDisable(false);
        }
    }

}
