import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.MainViewController;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("");
        initMainView();
    }

    private void initMainView() {
        try {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MainView.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Scene scene = new Scene(pane);

            primaryStage.setScene(scene);
            // Give the controller access to the main app.
            MainViewController controller = loader.getController();
            primaryStage.show();

            controller.onInit();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
