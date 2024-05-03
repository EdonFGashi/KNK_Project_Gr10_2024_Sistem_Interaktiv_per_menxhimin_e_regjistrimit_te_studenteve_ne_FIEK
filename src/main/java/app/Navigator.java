package app;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    public final static String LOGIN_PAGE = "login_form.fxml";
    public final static String HOME_PAGE = "home.fxml";
    public final static String CREATE_ACCOUNT_PAGE = "create_user_form.fxml";

    public static void navigate(Event event, String form){
        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        navigate(stage, form);
    }

    public static void navigate(Stage stage, String form){
        Pane formPane = loadPane(form);
        Scene newScene = new Scene(formPane);
        stage.setScene(newScene);
        stage.show();
    }


    //Shton Pane brenda panit aktual
    public static void navigate(Pane pane, String addedPane){
        Pane formPane = loadPane(addedPane);
        pane.getChildren().clear();
        pane.getChildren().add(formPane);
    }

    private static Pane loadPane(String page){
        FXMLLoader loader = new FXMLLoader(
                Navigator.class.getResource(page)
        );
        try {
            return loader.load();
        }catch (IOException ioe){
            return null;
        }
    }
}