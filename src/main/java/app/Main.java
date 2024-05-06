package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);

        Navigatior.navigate(stage,Navigatior.ADMIN_RIBBON);
//        Navigatior.navigate(stage,Navigatior.SUPERVISOR_RIBBON);

    }
}
