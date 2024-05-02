package app;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminNavigator {
    private final static String ADMIN_RIBBON = "";
    private final static String ADMIN_PROFILE = "";
    private final static String ADMIN_INBOX = "";

    private final static String ADMIN_MENU = "";

    private static VBox mainPane;
    public static Pane getRibbon(){
        try {
            return (new FXMLLoader(
                    AdminNavigator.class.getResource(ADMIN_RIBBON)
            )).load();
        }catch(IOException e){
            System.out.println("Ribbon nuk u gjet!");
            return null;
        }
    }
    public static void navigate(Stage stage, String page){
        FXMLLoader loader = new FXMLLoader(
                AdminNavigator.class.getResource(page)
        );
        mainPane.getChildren().add(getRibbon());

        try {
            mainPane.getChildren().add(loader.load());
            Scene scene = new Scene(mainPane);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.println("Gabim Admin Navigate");
        }

    }

    public static void navigate(Event event, String page){
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        navigate(stage,page);
    }


}

