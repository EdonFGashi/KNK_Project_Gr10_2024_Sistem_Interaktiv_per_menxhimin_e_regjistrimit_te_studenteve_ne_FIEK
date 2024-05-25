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

        //Startimi i aplikacionit Fiek Student Menagment System
        //Start of Apllication Fiek Student Menagment System

        // Sistem plotesisht funksional per menagjimin e studenteve
        // Fully functional system for menaging students

        Navigatior.navigate(stage,Navigatior.DASHBOARD);
    }
}
