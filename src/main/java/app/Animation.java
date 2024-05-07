package app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

//public class Animation extends Application {
//    @Override
//    public void start(Stage primaryStage){
//        Logo logo1 = new Logo(600, 90, Color.rgb(206, 42, 45));
//        Logo logo2 = new Logo(600, 90, Color.rgb(25, 19, 16));
//        Logo logo3 = new Logo(600, 90, Color.rgb(96, 93, 92));
//        Logo logo4 = new Logo(600, 90, Color.rgb(113, 113, 111));
//        Logo logo5 = new Logo(600, 90, Color.rgb(133, 132, 131));
//        Logo logo6 = new Logo(600, 90, Color.rgb(151, 150, 149));
//        Logo logo7 = new Logo(600, 90, Color.rgb(170, 171, 170));
//        Logo logo8 = new Logo(600, 90, Color.rgb(195, 195, 194));
//        Logo logo9 = new Logo(600, 90, Color.rgb(223, 222, 222));
////        logo1.setRotate(-20);
////        logo2.setRotate(-40);
////        logo3.setRotate(-60);
////        logo4.setRotate(-80);
////        logo5.setRotate(-100);
////        logo6.setRotate(-120);
////        logo7.setRotate(-140);
////        logo8.setRotate(-160);
////        logo9.setRotate(-180);
//
////        logo1.setTranslateY(-18);
////        logo2.setTranslateY(-22);
////        logo3.setTranslateY(-70);
//
//        CrossCircleMoveEventHandler event1 = new CrossCircleMoveEventHandler(logo1, logo2, logo3, logo4, logo5, logo6, logo7, logo8, logo9, -20, 0, 40, 1.7, 0.85);
//
//
//        PaneAnimation animation1 = new PaneAnimation(event1);
//
//        animation1.start();
//
//
//        Pane pane = new StackPane(logo9,logo8, logo7, logo6, logo5, logo4, logo3, logo2, logo1);
////        Pane pane = new StackPane(logo2, logo1);
//        Scene scene = new Scene(pane, 700, 700);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//}

public class Animation {
    public static Pane getAnimation(){
        int d1 = 100;
        int d2 = 15;
        Logo logo1 = new Logo(d1, d2, Color.rgb(206, 42, 45));
        Logo logo2 = new Logo(d1, d2, Color.rgb(25, 19, 16));
        Logo logo3 = new Logo(d1, d2, Color.rgb(96, 93, 92));
        Logo logo4 = new Logo(d1, d2, Color.rgb(113, 113, 111));
        Logo logo5 = new Logo(d1, d2, Color.rgb(133, 132, 131));
        Logo logo6 = new Logo(d1, d2, Color.rgb(151, 150, 149));
        Logo logo7 = new Logo(d1, d2, Color.rgb(170, 171, 170));
        Logo logo8 = new Logo(d1, d2, Color.rgb(195, 195, 194));
        Logo logo9 = new Logo(d1, d2, Color.rgb(223, 222, 222));
        LogoText text = new LogoText(40, Color.RED);

        CrossCircleMoveEventHandler event1 = new CrossCircleMoveEventHandler(logo1, logo2, logo3, logo4, logo5, logo6, logo7, logo8, logo9, -20, 0, 40, 1.7, 0.85);
        PaneAnimation animation1 = new PaneAnimation(logo1, event1);
        animation1.start();

        Pane pane1 = new StackPane(logo9,logo8, logo7, logo6, logo5, logo4, logo3, logo2, logo1);
        pane1.setMinHeight(100);
        pane1.setMinWidth(100);
        Pane pane2 = new StackPane(text);
        VBox finalPane = new VBox(pane1, pane2);
        return finalPane;
    }

//    animation1.start();

//    Pane pane = new StackPane(logo9, logo8, logo7, logo6, logo5, logo4, logo3, logo2, logo1);
//    Scene scene = new Scene(pane, 700, 700);
//        primaryStage.setScene(scene);
//        primaryStage.show();
}

class Logo extends StackPane{
    private int d1;
    private int d2;
    private Color color;

    public Logo (int d1, int d2, Color color){
        this.d1 = d1;
        this.d2 = d2;
        this.color = color;
        this.draw();
    }

    public void setColor(Color color){
        this.color = color;
        this.draw();
    }


//    private Polygon polygon(){
//        Polygon polygon1 = new Polygon(0,(this.d1/2), this.d1/2, (this.d1/2) - (this.d2/2), this.d1, this.d1/2, (this.d1/2), (this.d1/2) + (this.d2/2));
//        return polygon1;
//    }
    private void draw(){
        Polygon polygon1 = new Polygon(0,(this.d1/2), this.d1/2, (this.d1/2) - (this.d2/2), this.d1, this.d1/2, (this.d1/2), (this.d1/2) + (this.d2/2));
//        Polygon polygon2 = new Polygon(0,(this.d1/2), this.d1/2, (this.d1/2) - (this.d2/2), this.d1, this.d1/2, (this.d1/2), (this.d1/2) + (this.d2/2));
//        Polygon polygon3 = new Polygon(0,(this.d1/2), this.d1/2, (this.d1/2) - (this.d2/2), this.d1, this.d1/2, (this.d1/2), (this.d1/2) + (this.d2/2));
//        Polygon polygon4 = new Polygon(0,(this.d1/2), this.d1/2, (this.d1/2) - (this.d2/2), this.d1, this.d1/2, (this.d1/2), (this.d1/2) + (this.d2/2));
//        Polygon polygon5 = new Polygon(0,(this.d1/2), this.d1/2, (this.d1/2) - (this.d2/2), this.d1, this.d1/2, (this.d1/2), (this.d1/2) + (this.d2/2));
//        Polygon polygon6 = new Polygon(0,(this.d1/2), this.d1/2, (this.d1/2) - (this.d2/2), this.d1, this.d1/2, (this.d1/2), (this.d1/2) + (this.d2/2));
//        Polygon polygon7 = new Polygon(0,(this.d1/2), this.d1/2, (this.d1/2) - (this.d2/2), this.d1, this.d1/2, (this.d1/2), (this.d1/2) + (this.d2/2));
//        Polygon polygon8 = new Polygon(0,(this.d1/2), this.d1/2, (this.d1/2) - (this.d2/2), this.d1, this.d1/2, (this.d1/2), (this.d1/2) + (this.d2/2));
//        Polygon polygon9 = new Polygon(0,(this.d1/2), this.d1/2, (this.d1/2) - (this.d2/2), this.d1, this.d1/2, (this.d1/2), (this.d1/2) + (this.d2/2));
//        polygon1.setRotate(-20);
//        polygon2.setRotate(-40);
//        polygon3.setRotate(-60);
//        polygon4.setRotate(-80);
//        polygon5.setRotate(-100);
//        polygon6.setRotate(-120);
//        polygon7.setRotate(-140);
//        polygon8.setRotate(-160);
//        polygon9.setRotate(-180);
//        polygon1.setFill(Color.rgb(206, 42, 45));
//        polygon2.setFill(Color.rgb(25, 19, 16));
//        polygon3.setFill(Color.rgb(96, 93, 92));
//        polygon4.setFill(Color.rgb(113, 113, 111));
//        polygon5.setFill(Color.rgb(133, 132, 131));
//        polygon6.setFill(Color.rgb(151, 150, 149));
//        polygon7.setFill(Color.rgb(170, 171, 170));
//        polygon8.setFill(Color.rgb(195, 195, 194));
//        polygon9.setFill(Color.rgb(223, 222, 222));

        polygon1.setFill(this.color);
//        polygon1.setTranslateY(-20);

        super.getChildren().clear();
//        super.getChildren().addAll(polygon9, polygon8,polygon7,polygon6,polygon5,polygon4,polygon3,polygon2, polygon1);
        super.getChildren().addAll(polygon1);
    }
}
class LogoText extends StackPane{
    private Font font;
    private int size;
    private Color color;

    public LogoText (int size, Color color){
        this.size = size;
        this.color = color;
        this.draw();
    }

    public void setColor(Color color){
        this.color = color;
        this.draw();
    }

//    }
    private void draw(){
        Text text = new Text("FIEK Management");
        text.setFill(this.color);

        super.getChildren().clear();
        super.getChildren().addAll(text);
    }
}
class PaneAnimation{
    private Pane logoPart;
    private EventHandler<ActionEvent> event;
    private KeyFrame frame;
    private Timeline animation;

    public PaneAnimation(Logo logoPart,  EventHandler<ActionEvent> event){
        this.logoPart = logoPart;
        this.event = event;
        this.frame = new KeyFrame(Duration.millis(6), this.event);
        this.animation = new Timeline(this.frame);
        this.animation.setCycleCount(Timeline.INDEFINITE);
//        this.start();
    }
    public void start(){
        this.animation.play();
    }
    public void test(){
        System.out.println("Testt");
    }
}

class CrossCircleMoveEventHandler implements EventHandler<ActionEvent>{
    private Logo logo1 = new Logo(600, 90, Color.rgb(206, 42, 45));
    private Logo logo2 = new Logo(600, 90, Color.rgb(25, 19, 16));
    private Logo logo3 = new Logo(600, 90, Color.rgb(96, 93, 92));
    private Logo logo4 = new Logo(600, 90, Color.rgb(113, 113, 111));
    private Logo logo5 = new Logo(600, 90, Color.rgb(133, 132, 131));
    private Logo logo6 = new Logo(600, 90, Color.rgb(151, 150, 149));
    private Logo logo7 = new Logo(600, 90, Color.rgb(170, 171, 170));
    private Logo logo8 = new Logo(600, 90, Color.rgb(195, 195, 194));
    private Logo logo9 = new Logo(600, 90, Color.rgb(223, 222, 222));
    private double minRotate;
    private double maxRotate;

    private double rotate;
    private double rRotate;
    private int step ;
    private double backward;


    public CrossCircleMoveEventHandler(Logo logo1,Logo logo2,Logo logo3,Logo logo4,Logo logo5,Logo logo6,Logo logo7,Logo logo8,Logo logo9, double minRotate, double maxRotate, double backward, double rotate, double rRotate) {
        this.logo1 = logo1;
        this.logo2 = logo2;
        this.logo3 = logo3;
        this.logo4 = logo4;
        this.logo5 = logo5;
        this.logo6 = logo6;
        this.logo7 = logo7;
        this.logo8 = logo8;
        this.logo9 = logo9;
        this.minRotate = minRotate;
        this.maxRotate = maxRotate;
        this.backward = backward;
        this.rotate = -rotate;
        this.rRotate = rRotate;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        double getRotate1 = this.logo1.getRotate();
        double getRotate2 = this.logo2.getRotate();
        double getRotate3 = this.logo3.getRotate();
        double getRotate4 = this.logo4.getRotate();
        double getRotate5 = this.logo5.getRotate();
        double getRotate6 = this.logo6.getRotate();
        double getRotate7 = this.logo7.getRotate();
        double getRotate8 = this.logo8.getRotate();
        double getRotate9 = this.logo9.getRotate();

//        this.rotate = 10;
        if(getRotate1 <= backward  && this.step == 0){
            this.logo1.setRotate(getRotate1 + this.rRotate);
            this.logo2.setRotate(getRotate2 + this.rRotate);
            this.logo3.setRotate(getRotate3 + this.rRotate);
            this.logo4.setRotate(getRotate4 + this.rRotate);
            this.logo5.setRotate(getRotate5 + this.rRotate);
            this.logo6.setRotate(getRotate6 + this.rRotate);
            this.logo7.setRotate(getRotate7 + this.rRotate);
            this.logo8.setRotate(getRotate8 + this.rRotate);
            this.logo9.setRotate(getRotate9 + this.rRotate);
        }  else if( getRotate1 >= backward && this.step == 0){
            this.step = 1;
            backward += 20;
        }
        if(getRotate1 >= -20 && step == 1) {
            this.logo1.setRotate(getRotate1 + this.rotate);
            this.logo2.setRotate(getRotate2 + this.rotate);
            this.logo3.setRotate(getRotate3 + this.rotate);
            this.logo4.setRotate(getRotate4 + this.rotate);
            this.logo5.setRotate(getRotate5 + this.rotate);
            this.logo6.setRotate(getRotate6 + this.rotate);
            this.logo7.setRotate(getRotate7 + this.rotate);
            this.logo8.setRotate(getRotate8 + this.rotate);
            this.logo9.setRotate(getRotate9 + this.rotate);
        } else if (getRotate2 >= -40 && this.step == 1){
            this.logo2.setRotate(getRotate2 + this.rotate);
            this.logo3.setRotate(getRotate3 + this.rotate);
            this.logo4.setRotate(getRotate4 + this.rotate);
            this.logo5.setRotate(getRotate5 + this.rotate);
            this.logo6.setRotate(getRotate6 + this.rotate);
            this.logo7.setRotate(getRotate7 + this.rotate);
            this.logo8.setRotate(getRotate8 + this.rotate);
            this.logo9.setRotate(getRotate9 + this.rotate);
        } else if (getRotate3 >= -60 && this.step == 1){
            this.logo3.setRotate(getRotate3 + this.rotate);
            this.logo4.setRotate(getRotate4 + this.rotate);
            this.logo5.setRotate(getRotate5 + this.rotate);
            this.logo6.setRotate(getRotate6 + this.rotate);
            this.logo7.setRotate(getRotate7 + this.rotate);
            this.logo8.setRotate(getRotate8 + this.rotate);
            this.logo9.setRotate(getRotate9 + this.rotate);
        } else if (getRotate4 >= -80 && this.step == 1){
            this.logo4.setRotate(getRotate4 + this.rotate);
            this.logo5.setRotate(getRotate5 + this.rotate);
            this.logo6.setRotate(getRotate6 + this.rotate);
            this.logo7.setRotate(getRotate7 + this.rotate);
            this.logo8.setRotate(getRotate8 + this.rotate);
            this.logo9.setRotate(getRotate9 + this.rotate);
        } else if (getRotate5 >= -100 && this.step == 1){
            this.logo5.setRotate(getRotate5 + this.rotate);
            this.logo6.setRotate(getRotate6 + this.rotate);
            this.logo7.setRotate(getRotate7 + this.rotate);
            this.logo8.setRotate(getRotate8 + this.rotate);
            this.logo9.setRotate(getRotate9 + this.rotate);
        } else if (getRotate6 >= -120 && this.step == 1){
            this.logo6.setRotate(getRotate6 + this.rotate);
            this.logo7.setRotate(getRotate7 + this.rotate);
            this.logo8.setRotate(getRotate8 + this.rotate);
            this.logo9.setRotate(getRotate9 + this.rotate);
        } else if (getRotate7 >= -140 && this.step == 1){
            this.logo7.setRotate(getRotate7 + this.rotate);
            this.logo8.setRotate(getRotate8 + this.rotate);
            this.logo9.setRotate(getRotate9 + this.rotate);
        } else if (getRotate8 >= -160 && this.step == 1){
            this.logo8.setRotate(getRotate8 + this.rotate);
            this.logo9.setRotate(getRotate9 + this.rotate);
        } else if(getRotate9 <= -180  && this.step == 1){
            this.step = 2;
        }else if (getRotate9 >= -180 && this.step == 1){
            this.logo9.setRotate(getRotate9 + this.rotate);
//            this.step = 3;
        }

        if(getRotate1 < backward && this.step == 2){
            this.logo1.setRotate(getRotate1 + this.rRotate);
            this.logo2.setRotate(getRotate2 + this.rRotate);
            this.logo3.setRotate(getRotate3 + this.rRotate);
            this.logo4.setRotate(getRotate4 + this.rRotate);
            this.logo5.setRotate(getRotate5 + this.rRotate);
            this.logo6.setRotate(getRotate6 + this.rRotate);
            this.logo7.setRotate(getRotate7 + this.rRotate);
            this.logo8.setRotate(getRotate8 + this.rRotate);
            this.logo9.setRotate(getRotate9 + this.rRotate);
        } else if(getRotate2 <= backward && this.step == 2){
            this.logo2.setRotate(getRotate2 + this.rRotate);
            this.logo3.setRotate(getRotate3 + this.rRotate);
            this.logo4.setRotate(getRotate4 + this.rRotate);
            this.logo5.setRotate(getRotate5 + this.rRotate);
            this.logo6.setRotate(getRotate6 + this.rRotate);
            this.logo7.setRotate(getRotate7 + this.rRotate);
            this.logo8.setRotate(getRotate8 + this.rRotate);
            this.logo9.setRotate(getRotate9 + this.rRotate);
        } else if(getRotate3 <= backward && this.step == 2){
            this.logo3.setRotate(getRotate3 + this.rRotate);
            this.logo4.setRotate(getRotate4 + this.rRotate);
            this.logo5.setRotate(getRotate5 + this.rRotate);
            this.logo6.setRotate(getRotate6 + this.rRotate);
            this.logo7.setRotate(getRotate7 + this.rRotate);
            this.logo8.setRotate(getRotate8 + this.rRotate);
            this.logo9.setRotate(getRotate9 + this.rRotate);
        } else if(getRotate4 <= backward && this.step == 2){
            this.logo4.setRotate(getRotate4 + this.rRotate);
            this.logo5.setRotate(getRotate5 + this.rRotate);
            this.logo6.setRotate(getRotate6 + this.rRotate);
            this.logo7.setRotate(getRotate7 + this.rRotate);
            this.logo8.setRotate(getRotate8 + this.rRotate);
            this.logo9.setRotate(getRotate9 + this.rRotate);
        } else if(getRotate5 <= backward && this.step == 2){
            this.logo5.setRotate(getRotate5 + this.rRotate);
            this.logo6.setRotate(getRotate6 + this.rRotate);
            this.logo7.setRotate(getRotate7 + this.rRotate);
            this.logo8.setRotate(getRotate8 + this.rRotate);
            this.logo9.setRotate(getRotate9 + this.rRotate);
        } else if(getRotate6 <= backward && this.step == 2){
            this.logo6.setRotate(getRotate6 + this.rRotate);
            this.logo7.setRotate(getRotate7 + this.rRotate);
            this.logo8.setRotate(getRotate8 + this.rRotate);
            this.logo9.setRotate(getRotate9 + this.rRotate);
        } else if(getRotate7 <= backward && this.step == 2){
            this.logo7.setRotate(getRotate7 + this.rRotate);
            this.logo8.setRotate(getRotate8 + this.rRotate);
            this.logo9.setRotate(getRotate9 + this.rRotate);
        } else if(getRotate8 <= backward && this.step == 2){
            this.logo8.setRotate(getRotate8 + this.rRotate);
            this.logo9.setRotate(getRotate9 + this.rRotate);
        } else if(getRotate9 <= backward && this.step == 2){
            this.logo9.setRotate(getRotate9 + this.rRotate);
        }else if( getRotate9 >= backward && this.step == 2){
            this.step = 0;
        }

        if( backward >= 90){
            backward = 40;
        }

//        if(getRotate1 <= backward + 30  && this.step == 3){
//            this.logo1.setRotate(getRotate1 + this.rRotate);
//            this.logo2.setRotate(getRotate2 + this.rRotate);
//            this.logo3.setRotate(getRotate3 + this.rRotate);
//            this.logo4.setRotate(getRotate4 + this.rRotate);
//            this.logo5.setRotate(getRotate5 + this.rRotate);
//            this.logo6.setRotate(getRotate6 + this.rRotate);
//            this.logo7.setRotate(getRotate7 + this.rRotate);
//            this.logo8.setRotate(getRotate8 + this.rRotate);
//            this.logo9.setRotate(getRotate9 + this.rRotate);
//        }  else if( getRotate1 >= 45 && this.step == 0){
//            this.step = 1;
//        }

    }

    private Color generateRandomColor(){
        return new Color(
                Math.random(), Math.random(), Math.random(), 1
        );
    }
}