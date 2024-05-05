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
<<<<<<< HEAD
        Navigatior.navigate(stage,Navigatior.ADMIN_RIBBON,Navigatior.ADMIN_PROFILE);
//        Navigatior.navigate(stage,Navigatior.SUPERVISOR_RIBBON,Navigatior.SUPERVISOR_MENU);
=======
        Navigatior.navigate(stage,Navigatior.ADMIN_RIBBON);
>>>>>>> 1b5b4cf5ebfc597b44039de1ba494df0d29cc761
    }
}
