package app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import controller.Animations.Tick;
import controller.Animations.UpLogoAnimate;

public class PopUp {
    public static void tick(double dimension){
        Tick tick = new Tick(dimension);
        Scene scene = new Scene(tick,dimension,dimension);
        Stage stage = new Stage();
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        Timeline timeline2= new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        }));
        timeline2.play();
    }

    public static void loading(String message,boolean successful, String logoText){
        UpLogoAnimate pane = new UpLogoAnimate(100, logoText, 10, 10);
        pane.start();
        pane.setTranslateY(50);
        StackPane stackPane = new StackPane();

        Text text = new Text();
        text.setText(message);
        if (successful) {
            text.setStyle("-fx-font-weight: bold; -fx-font-size: 19; -fx-fill: green;");
        } else {
            text.setStyle("-fx-font-weight: bold; -fx-font-size: 20; -fx-fill: red;");
        }
        text.setTranslateY(110);
        StackPane stack = new StackPane(text);
        VBox mainPane = new VBox();
        mainPane.getChildren().addAll(stackPane,stack);

        stackPane.getChildren().add(pane);
        Scene scene = new Scene(mainPane,280,200);


        Stage stage = new Stage();
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3300), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        }));
        timeline.play();


    }
}
