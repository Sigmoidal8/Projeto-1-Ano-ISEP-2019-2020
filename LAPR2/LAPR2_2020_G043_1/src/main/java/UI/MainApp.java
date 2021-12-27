package UI;

import Controller.AplicationPOT;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {

    public static final String APP_TITLE = "T4J";

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("T4J");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.getOnCloseRequest();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                AplicationPOT app = ((MainWindowUI) loader.getController()).getAplicationPOT();
                app.guardar();
                AlertUI.makeAlert(Alert.AlertType.INFORMATION, APP_TITLE,"information", "The program is saving and closing.").show();
                Platform.exit();
                System.exit(0);

            }
        });
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
