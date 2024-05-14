package controller.Animations;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.HashMap;

public class Tick extends StackPane {
    private TickObject tickObject;
    private EventHandler<ActionEvent> event;
    private TickAnimation animation;
    public Tick(double dimensions){
        this.tickObject = new TickObject(dimensions);
        this.event = new TickEvent(this.tickObject);
        this.animation = new TickAnimation(this.tickObject,this.event);
        super.getChildren().add(this.tickObject);
        this.animation.start();
    }
}


class TickObject extends Pane {
    private double widthAndHeight;
    private Polygon tick;
    private HashMap<Integer,Polygon> polygonHashMap = new HashMap<>();

    private void fillPolygonHashMap(){
        double x = widthAndHeight/10;

        polygonHashMap.put(1,new Polygon(
                0,6*x,
                1*x,5*x,
                2*x,6*x,
                1*x,7*x
        ));
        polygonHashMap.put(2, new Polygon(
                0,6*x,
                1*x,5*x,
                3*x,7*x,
                2*x,8*x
        ));
        polygonHashMap.put(3,new Polygon(
                0,6*x,
                1*x,5*x,
                4*x,8*x,
                3*x,9*x
        ));
        polygonHashMap.put(4,new Polygon(
                0,6*x,
                1*x,5*x,
                5*x,9*x,
                4*x,10*x
        ));
        polygonHashMap.put(5,new Polygon(
                0,6*x,
                1*x,5*x,
                4*x,8*x,
                5*x,6*x,
                6*x,8*x,
                4*x,10*x
        ));
        polygonHashMap.put(6,new Polygon(
                0,6*x,
                1*x,5*x,
                4*x,8*x,
                6*x,5*x,
                7*x,7*x,
                4*x,10*x
        ));
        polygonHashMap.put(7,new Polygon(
                0,6*x,
                1*x,5*x,
                4*x,8*x,
                7*x,4*x,
                8*x,6*x,
                4*x,10*x
        ));
        polygonHashMap.put(8,new Polygon(
                0,6*x,
                1*x,5*x,
                4*x,8*x,
                8*x,3*x,
                9*x,5*x,
                4*x,10*x
        ));
        polygonHashMap.put(9,new Polygon(
                0,6*x,
                1*x,5*x,
                4*x,8*x,
                9*x, 2*x,
                10*x,4*x,
                4*x,10*x
        ));
        polygonHashMap.put(10,new Polygon(
                0,6*x,
                1*x,5*x,
                4*x,8*x,
                10*x, 1*x,
                10*x,4*x,
                4*x,10*x
        ));
        for (Polygon polygon : polygonHashMap.values()) {
            polygon.setStyle("-fx-fill: green; -fx-stroke: white; -fx-stroke-width: 3;");
        }
    }



    public TickObject(double widthAndHeight){
        this.widthAndHeight = widthAndHeight;
        this.fillPolygonHashMap();

        super.setMaxHeight(this.widthAndHeight);
        super.setMaxWidth(this.widthAndHeight);
        this.tick = this.polygonHashMap.get(1);
        this.draw();
    }

    private void draw(){
        Circle circle = new Circle(this.widthAndHeight/2,this.widthAndHeight/2,(this.widthAndHeight/2)-10);
        circle.setStroke(Color.GREEN);
        circle.setStrokeWidth(this.widthAndHeight/15);
        circle.setFill(null);

        if (this.tick != null) {
            super.getChildren().clear();
            super.getChildren().addAll(circle, this.tick);
        }
    }

    public void refreshTick(int iterator){
        this.tick = polygonHashMap.get(iterator);
        this.draw();
    }

}

class TickAnimation{
    private TickObject object;
    private EventHandler<ActionEvent> event;
    private KeyFrame keyframe;
    private Timeline animation;

    public TickAnimation(TickObject object, EventHandler<ActionEvent> event) {
        this.object = object;
        this.event = event;
        this.keyframe = new KeyFrame(Duration.millis(50), this.event);
        this.animation = new Timeline(keyframe);
        this.animation.setCycleCount(20);

    }
    public void start(){

        this.animation.play();
    }
}


class TickEvent implements EventHandler<ActionEvent> {
    private TickObject object;
    private int iterator;

    public TickEvent(TickObject object){
        this.object = object;
    }
    @Override
    public void handle(ActionEvent ae){
        this.object.refreshTick(this.iterator);
        this.iterator++;

    }
}