package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.PasswordHasher;
import service.StudentPdfCreatorService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        stage.getIcons().add(new Image("upLogoNoRing.png"));
        stage.setTitle("Fiek Registration Menagment");


//        Navigatior.navigate(stage,Navigatior.ADMIN_RIBBON);
//
        // Per me testu ribbonin e mbikqyrsve
//        Navigatior.navigate(stage, Navigatior.SUPERVISOR_MENU);

        // Per me testu Dashboardin
//        Navigatior.navigate(stage,Navigatior.DASHBOARD);
        Navigatior.navigateNewStage(Navigatior.DASHBOARD);
//        Navigatior.navigateNewStage(Navigatior.LOGIN);
//        Navigatior.navigate(stage, Navigatior.SUPERVISOR_PROFILE);

    }
}
