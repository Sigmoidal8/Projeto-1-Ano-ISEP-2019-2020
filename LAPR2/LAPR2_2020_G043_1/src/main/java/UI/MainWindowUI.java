/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.AplicationPOT;
import Controller.CreatePaymentTransactionController;
import Controller.CreateTaskController;
import Controller.DefinePaymentTimeController;
import Controller.FreelancerPaymentsController;
import Controller.LoadFileController;
import Controller.LoginController;
import Controller.NotifyDelaysToFreelancerTask;
import Controller.RegisterFreelancerController;
import Controller.RegisterOrganizationController;
import Controller.ShowGeneralStatisticsController;
import Controller.TaskExecutionTimeController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import org.apache.commons.math.MathException;

/**
 * FXML Controller class
 *
 * @author Miguel
 */
public class MainWindowUI implements Initializable {

    @FXML
    private Menu mnuCreate;
    @FXML
    private MenuItem mnuTask;
    @FXML
    private MenuItem mnuFreelancer;
    @FXML
    private MenuItem mnuPayment;
    @FXML
    private MenuItem mnuFreelancerPayment;
    @FXML
    private MenuItem mnuTaskExecution;
    @FXML
    private MenuItem mnuLoad;
    @FXML
    private MenuItem mnuSair;
    @FXML
    private MenuItem mnuAutomaticPayment;
    @FXML
    private MenuItem mnuAbout;
    @FXML
    private MenuItem mnLogout;
    @FXML
    private Menu mnuShowStatistics;
    @FXML
    private Menu mnuEdit;
    @FXML
    private Menu mnuSend;
    @FXML
    private ImageView imageview;
    @FXML
    private Button btnRegisterOrganization;
    @FXML
    private MenuItem mnuEnviarEmails;
    @FXML
    private Button btnShowFreelancerStatistics;
    @FXML
    private Label lblName;

    //Controller
    private RegisterOrganizationController registerOrganizationController;
    private CreateTaskController createTaskController;
    private ShowGeneralStatisticsController showGeneralStatisticsController;
    private DefinePaymentTimeController definePaymentController;
    private CreatePaymentTransactionController createPaymentTransactionController;
    private RegisterFreelancerController registerFreelancerController;
    private LoadFileController loadFileController;
    private LoginController loginController;
    private NotifyDelaysToFreelancerTask notifyDelaysToFreelancerController;
    private FreelancerPaymentsController freelancerPaymentsController;
    private TaskExecutionTimeController taskExecutionTimeController;

    //UI
    private RegisterOrganizationsUI registerOrganizationUI;
    private DefinePaymentTimeUI definePaymentTimeUI;
    private CreateTaskUI createTaskUI;
    private CreatePaymentTransactionUI createPaymentTransactionUI;
    private RegisterFreelancerUI registerFreelancerUI;
    private LoginUI loginUI;
    private FreelancerPaymentsUI freelancerPaymentsUI;
    private ShowStatisticsUI showStatisticsUI;
    private TaskExecutionTimeUI taskExecutionTimeUI;

    //Stage
    private Stage definePaymentTimeStage;
    private Stage registerOrganizationStage;
    private Stage registerFreelancerStage;
    private Stage createTaskStage;
    private Stage createPaymentTransactionStage;
    private Stage loginStage;
    private Stage freelancerPaymentsStage;
    private Stage showStatisticsStage;
    private Stage taskExecutionTimeStage;

    //Strings
    private static final String DEFINE_PAYMENT = "Define Payment Time";
    private static final String REGISTER_ORGANIZATION = "Register Organization";
    private static final String REGISTER_FREELANCER = "Register Freelancer";
    private static final String CREATE_TASK = "Create Task";
    private static final String CREATE_TRANSACTION = "Create Payment Transaction";
    private static final String LOAD_FILE = "Load File";
    private static final String EMAIL = "Emails";
    private static final String LOGIN = "Login";
    private static final String APP = "T4J";
    private static final String FREELANCER_PAYMENTS = "Freelancer Payments";
    private static final String TASK_EXECUTION = "Task Execution Times";
    private static final String LOAD_FILE_ERROR = "No file selected!";
    private static final String LOGIN_MESSAGE = "Failed to load a window";

    private AplicationPOT appPot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        appPot = new AplicationPOT();
        appPot.getPlatform().scheduleEmails();
        File file = new File("t4j.jpg");
        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);

        try {

            //Send email delays
            notifyDelaysToFreelancerController = new NotifyDelaysToFreelancerTask();

            //Login
            loginController = new LoginController();
            loginStage = new Stage();
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.setTitle(LOGIN);
            loginStage.setResizable(false);
            FXMLLoader loader10 = new FXMLLoader(getClass().getResource("/fxml/LoginApp.fxml"));
            Parent root10 = loader10.load();
            Scene scene10 = new Scene(root10);
            loginStage.setScene(scene10);
            loginStage.sizeToScene();
            loginUI = loader10.getController();
            loginUI.associarParentUI(this);
            loginStage.showAndWait();

            //Register Org
            registerOrganizationController = new RegisterOrganizationController();
            registerOrganizationStage = new Stage();
            registerOrganizationStage.initModality(Modality.APPLICATION_MODAL);
            registerOrganizationStage.setTitle(REGISTER_ORGANIZATION);
            registerOrganizationStage.setResizable(false);
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/fxml/RegisterOrganizations.fxml"));
            Parent root1 = loader1.load();
            Scene scene1 = new Scene(root1);
            registerOrganizationStage.setScene(scene1);
            registerOrganizationStage.sizeToScene();
            registerOrganizationUI = loader1.getController();
            registerOrganizationUI.associarParentUI(this);

            //Edit Payment Time
            definePaymentController = new DefinePaymentTimeController();
            definePaymentTimeStage = new Stage();
            definePaymentTimeStage.initModality(Modality.APPLICATION_MODAL);
            definePaymentTimeStage.setTitle(DEFINE_PAYMENT);
            definePaymentTimeStage.setResizable(false);
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/DefinePaymentTime.fxml"));
            Parent root2 = loader2.load();
            Scene scene2 = new Scene(root2);
            definePaymentTimeStage.setScene(scene2);
            definePaymentTimeStage.sizeToScene();
            definePaymentTimeUI = loader2.getController();
            definePaymentTimeUI.associarParentUI(this);

            //Create Task
            createTaskController = new CreateTaskController();
            createTaskStage = new Stage();
            createTaskStage.initModality(Modality.APPLICATION_MODAL);
            createTaskStage.setTitle(CREATE_TASK);
            createTaskStage.setResizable(false);
            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/fxml/CreateTask.fxml"));
            Parent root3 = loader3.load();
            Scene scene3 = new Scene(root3);
            createTaskStage.setScene(scene3);
            createTaskStage.sizeToScene();
            createTaskUI = loader3.getController();
            createTaskUI.associarParentUI(this);

            //Register Freelancer
            registerFreelancerController = new RegisterFreelancerController();
            registerFreelancerStage = new Stage();
            registerFreelancerStage.initModality(Modality.APPLICATION_MODAL);
            registerFreelancerStage.setTitle(REGISTER_FREELANCER);
            registerFreelancerStage.setResizable(false);
            FXMLLoader loader8 = new FXMLLoader(getClass().getResource("/fxml/RegisterFreelancer.fxml"));
            Parent root8 = loader8.load();
            Scene scene8 = new Scene(root8);
            registerFreelancerStage.setScene(scene8);
            registerFreelancerStage.sizeToScene();
            registerFreelancerUI = loader8.getController();
            registerFreelancerUI.associarParentUI(this);

            //Create Transaction
            createPaymentTransactionController = new CreatePaymentTransactionController();
            createPaymentTransactionStage = new Stage();
            createPaymentTransactionStage.initModality(Modality.APPLICATION_MODAL);
            createPaymentTransactionStage.setTitle(CREATE_TRANSACTION);
            createPaymentTransactionStage.setResizable(false);
            FXMLLoader loader6 = new FXMLLoader(getClass().getResource("/fxml/CreatePaymentTransaction.fxml"));
            Parent root6 = loader6.load();
            Scene scene6 = new Scene(root6);
            createPaymentTransactionStage.setScene(scene6);
            createPaymentTransactionStage.sizeToScene();
            createPaymentTransactionUI = loader6.getController();

            //Load File
            loadFileController = new LoadFileController();

            //Freelancer Payments
            freelancerPaymentsController = new FreelancerPaymentsController();
            freelancerPaymentsStage = new Stage();
            freelancerPaymentsStage.initModality(Modality.APPLICATION_MODAL);
            freelancerPaymentsStage.setTitle(FREELANCER_PAYMENTS);
            freelancerPaymentsStage.setResizable(false);
            FXMLLoader loader9 = new FXMLLoader(getClass().getResource("/fxml/FreelancerPayments.fxml"));
            Parent root9 = loader9.load();
            Scene scene9 = new Scene(root9);
            freelancerPaymentsStage.setScene(scene9);
            freelancerPaymentsStage.sizeToScene();
            freelancerPaymentsUI = loader9.getController();

            //Show Statistics 
            showGeneralStatisticsController = new ShowGeneralStatisticsController();
            showStatisticsStage = new Stage();
            showStatisticsStage.initModality(Modality.APPLICATION_MODAL);
            showStatisticsStage.setTitle(FREELANCER_PAYMENTS);
            showStatisticsStage.setResizable(false);
            FXMLLoader loader11 = new FXMLLoader(getClass().getResource("/fxml/ShowStatistics.fxml"));
            Parent root11 = loader11.load();
            Scene scene11 = new Scene(root11);
            showStatisticsStage.setScene(scene11);
            showStatisticsStage.sizeToScene();
            showStatisticsUI = loader11.getController();

            //Task Execution
            taskExecutionTimeController = new TaskExecutionTimeController();
            taskExecutionTimeStage = new Stage();
            taskExecutionTimeStage.initModality(Modality.APPLICATION_MODAL);
            taskExecutionTimeStage.setTitle(TASK_EXECUTION);
            taskExecutionTimeStage.setResizable(false);
            FXMLLoader loader12 = new FXMLLoader(getClass().getResource("/fxml/TaskExecutionTime.fxml"));
            Parent root12 = loader12.load();
            Scene scene12 = new Scene(root12);
            taskExecutionTimeStage.setScene(scene12);
            taskExecutionTimeStage.sizeToScene();
            taskExecutionTimeUI = loader12.getController();

            startWindowControls();
            lblName.setText(appPot.getAtualSession().toString());

            loginStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.exit();
                }
            });
        } catch (IOException ex) {
            AlertUI.makeAlert(Alert.AlertType.ERROR, APP, LOGIN, LOGIN_MESSAGE).show();
        }
    }

    @FXML
    private void mnuTaskAction(ActionEvent event) {
        createTaskStage.show();
    }

    @FXML
    private void mnuFreelancerAction(ActionEvent event) {
        registerFreelancerStage.show();
    }

    @FXML
    private void mnuPaymentAction(ActionEvent event) {
        createPaymentTransactionController.getFreelancerList();
        createPaymentTransactionController.getTaskList();
        createPaymentTransactionUI.associarParentUI(this);
        createPaymentTransactionStage.showAndWait();
    }

    @FXML
    private void mnuFreelancerPaymentAction(ActionEvent event) {
        freelancerPaymentsUI.associarParentUI(this);
        freelancerPaymentsStage.showAndWait();
    }

    @FXML
    private void mnuTaskExecutionAction(ActionEvent event) {
        taskExecutionTimeUI.associarParentUI(this);
        taskExecutionTimeStage.show();
    }

    @FXML
    private void mnuLoadAction(ActionEvent event) {
        FileChooser flChooser = FileChooserUI.createFileChooser();
        File fileImported = flChooser.showSaveDialog(imageview.getScene().getWindow());

        if (fileImported != null) {
            loadFileController.loadFile(fileImported);
            AlertUI.makeAlert(Alert.AlertType.INFORMATION, APP, LOAD_FILE, LOAD_FILE).show();
        } else {
            AlertUI.makeAlert(Alert.AlertType.ERROR, APP, LOAD_FILE, LOAD_FILE_ERROR).show();
        }
    }

    @FXML
    private void mnuSairAction(ActionEvent event) {
        Window win = mnuSair.getParentPopup().getOwnerWindow();
        win.fireEvent(new WindowEvent(win, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    private void mnuAutomaticPaymentAction(ActionEvent event) {
        definePaymentTimeStage.show();
    }

    @FXML
    private void mnuAboutAction(ActionEvent event) {
        AlertUI.makeAlert(Alert.AlertType.INFORMATION, APP, "About",
                "@Copyright\nRaúl Coelho\nMiguel Silva\nSandro Moura\nJosé Forno\nTiago Magalhães").show();
    }

    @FXML
    private void btnRegisterOrganizationAction(ActionEvent event) {
        registerOrganizationStage.show();
    }

    @FXML
    private void mnuEnviarEmailsAction(ActionEvent event) {
        notifyDelaysToFreelancerController.run();
        AlertUI.makeAlert(Alert.AlertType.INFORMATION, MainApp.APP_TITLE, EMAIL,
                "Emails sent!").show();

    }

    @FXML
    private void btnShowFreelancerStatisticsAction(ActionEvent event) throws MathException {
        showStatisticsUI.associarParentUI(this);
        showStatisticsStage.show();
    }

    @FXML
    private void mnLogoutAction(ActionEvent event) {
        loginController.doLogout();
        loginStage.showAndWait();
        if (appPot.getAtualSession() == null) {
            Window win = mnLogout.getParentPopup().getOwnerWindow();
            win.fireEvent(new WindowEvent(win, WindowEvent.WINDOW_CLOSE_REQUEST));
        } else {
            startWindowControls();
            lblName.setText(appPot.getAtualSession().toString());
        }
    }

    public DefinePaymentTimeController getDefinePaymentController() {
        return definePaymentController;
    }

    public RegisterFreelancerController getRegisterFreelancerController() {
        return registerFreelancerController;
    }

    public RegisterOrganizationController getRegisterOrganizationController() {
        return registerOrganizationController;
    }

    public CreateTaskController getCreateTaskController() {
        return createTaskController;
    }

    public ShowGeneralStatisticsController getShowGeneralStatisticsController() {
        return showGeneralStatisticsController;
    }

    public CreatePaymentTransactionController getCreatePaymentTransactionController() {
        return createPaymentTransactionController;
    }

    public LoadFileController getLoadFileController() {
        return loadFileController;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public FreelancerPaymentsController getFreelancerPaymentsController() {
        return freelancerPaymentsController;
    }

    public TaskExecutionTimeController getTaskExecutionTimeController() {
        return taskExecutionTimeController;
    }

    public AplicationPOT getAplicationPOT() {
        return appPot;
    }

    public void startWindowControls() {

        String user = appPot.getAtualSession().getUserRole();

        switch (user) {

            case "Administrator":
                mnuCreate.setVisible(false);
                mnuLoad.setVisible(false);
                mnuShowStatistics.setVisible(false);
                mnuSend.setVisible(true);
                mnuEdit.setVisible(false);
                btnRegisterOrganization.setVisible(true);
                btnShowFreelancerStatistics.setVisible(true);

                break;

            case "Collaborator":
                mnuCreate.setVisible(true);
                mnuLoad.setVisible(false);
                mnuShowStatistics.setVisible(true);
                mnuSend.setVisible(false);
                mnuEdit.setVisible(false);
                btnRegisterOrganization.setVisible(false);
                btnShowFreelancerStatistics.setVisible(false);
                break;

            case "Manager":
                mnuCreate.setVisible(false);
                mnuLoad.setVisible(true);
                mnuShowStatistics.setVisible(false);
                mnuSend.setVisible(false);
                mnuEdit.setVisible(true);
                btnRegisterOrganization.setVisible(false);
                btnShowFreelancerStatistics.setVisible(false);
                break;
        }
    }
}
