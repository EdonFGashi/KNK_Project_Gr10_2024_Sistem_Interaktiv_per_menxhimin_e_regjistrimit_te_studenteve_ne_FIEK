package controller.Animations;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class UpLogoAnimate extends Pane{
    private StackPane pane1;
    private LogoAnimationEventHandler event;
   private PaneAnimation animation;
   private StackPane pane2;
   private VBox finalPane;
   private int d1;
   private int d2;
   private String shkrimi;
   private int textGrowth;
   int perseritjaAnimacionit;

   public UpLogoAnimate(int d1, String shkrimi, int textGrowth, int perseritjaAnimacionit){
       this.d1 = d1;
       this.d2 = d1/7;
       this.shkrimi = shkrimi;
       this.textGrowth = textGrowth;
       this.perseritjaAnimacionit = perseritjaAnimacionit;
//       int d1 = 50;
//       int d2 = 6;
       Logo logo1 = new Logo(d1, d2, Color.rgb(206, 42, 45));
       Logo logo2 = new Logo(d1, d2, Color.rgb(25, 19, 16));
       Logo logo3 = new Logo(d1, d2, Color.rgb(96, 93, 92));
       Logo logo4 = new Logo(d1, d2, Color.rgb(113, 113, 111));
       Logo logo5 = new Logo(d1, d2, Color.rgb(133, 132, 131));
       Logo logo6 = new Logo(d1, d2, Color.rgb(151, 150, 149));
       Logo logo7 = new Logo(d1, d2, Color.rgb(170, 171, 170));
       Logo logo8 = new Logo(d1, d2, Color.rgb(195, 195, 194));
       Logo logo9 = new Logo(d1, d2, Color.rgb(223, 222, 222));
       LogoText text = new LogoText(this.shkrimi, (this.d1/2 - this.d2)/2 + textGrowth, Color.BLACK);

       this.event = new LogoAnimationEventHandler(logo1, logo2, logo3, logo4, logo5, logo6, logo7, logo8, logo9, text, 40, 1.7, 0.85, perseritjaAnimacionit);
       this.animation = new PaneAnimation(logo1, this.event);
       this.pane1 = new StackPane(logo9,logo8, logo7, logo6, logo5, logo4, logo3, logo2, logo1);
//       this.pane1.setMinHeight(75);
//       this.pane2.setMinWidth(75);
       this.pane2 = new StackPane(text);
       this.pane2.setTranslateY((this.d1/2)-5);
//       finalPane.getChildren().clear();
//       finalPane.getChildren().addAll(pane1, pane2);
       this.draw();
   }
   public void draw(){
       VBox finalPane = new VBox(this.pane1, this.pane2);
        super.setMaxWidth(130);
        super.setMaxHeight(130);
       super.getChildren().clear();
       super.getChildren().add(finalPane);
   }
   public void startWithMouse(){
       if(event.perfundimi == 0) {
           event.perseritjaAktuale = 0;
           event.step = 1;
           this.animation.start();
           System.out.println("Start");
       } else if (event.perfundimi == 1){
           animation.stop();
           System.out.println("Stop");
       }
       event.perfundimi = 0;
   }

    public void start(){
       this.animation.start();
    }
   public void stop(){this.animation.stop();}
   public void stopAnimation(){
       this.animation.stop();
   }
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

    public int getD2(){
        return this.d2;
    }
    private void draw(){
        Polygon polygon1 = new Polygon(0,(this.d1/2), this.d1/2, (this.d1/2) - (this.d2/2), this.d1, this.d1/2, (this.d1/2), (this.d1/2) + (this.d2/2));
        polygon1.setFill(this.color);
        super.getChildren().clear();
        super.getChildren().addAll(polygon1);
    }
}
class LogoText extends StackPane{
    private Font font;
    private String shkrimi;
    private int size;
    private Color color;
    private Text text;

    public LogoText (String shkrimi, int size, Color color){
        this.text = new Text(shkrimi);
        this.shkrimi = shkrimi;
        this.size = size;
        this.color = color;
        this.draw();
    }

    public void setColor(Color color){
        this.color = color;
        this.draw();
    }

    private void draw(){
        text.setFill(this.color);
        this.text.setFont(Font.font(this.size));
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
    }
    public void start(){
        this.animation.play();

    }
    public void stop() {
        this.animation.stop();
    }
}


class LogoAnimationEventHandler implements EventHandler<ActionEvent>{
    private Logo logo1;
    private Logo logo2;
    private Logo logo3;
    private Logo logo4;
    private Logo logo5;
    private Logo logo6;
    private Logo logo7;
    private Logo logo8;
    private Logo logo9;
    private LogoText text;

    private double rotate;
    private double rRotate;
    public int step = 0;
    private double backward;
    private int red = 120;
    private int perseritjetTotale;
    public int perseritjaAktuale = 0;
    public int perfundimi = 0;

    public int getStep(){return this.step;}

    public LogoAnimationEventHandler(Logo logo1, Logo logo2, Logo logo3, Logo logo4, Logo logo5, Logo logo6, Logo logo7, Logo logo8, Logo logo9, LogoText text, double backward, double rotate, double rRotate, int perseritjetTotale) {
        this.logo1 = logo1;
        this.logo2 = logo2;
        this.logo3 = logo3;
        this.logo4 = logo4;
        this.logo5 = logo5;
        this.logo6 = logo6;
        this.logo7 = logo7;
        this.logo8 = logo8;
        this.logo9 = logo9;
        this.text = text;
        this.perseritjetTotale = perseritjetTotale;
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
            this.red = 1;
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
        } else if (getRotate4 >= -80 && this.step == 1 ){
            this.logo4.setRotate(getRotate4 + this.rotate);
            this.logo5.setRotate(getRotate5 + this.rotate);
            this.logo6.setRotate(getRotate6 + this.rotate);
            this.logo7.setRotate(getRotate7 + this.rotate);
            this.logo8.setRotate(getRotate8 + this.rotate);
            this.logo9.setRotate(getRotate9 + this.rotate);
        } else if (getRotate5 >= -100 && this.step == 1 ){
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
            this.red = 255;
        }else if (getRotate9 >= -180 && this.step == 1){
            this.logo9.setRotate(getRotate9 + this.rotate);
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
            this.red = 0;
        }



        if(this.backward >= 90){
            this.perseritjaAktuale += 1;
            this.step = 3;
            this.backward = 40;
            System.out.println("Perseritja: " + this.perseritjaAktuale + "Totale: " + this.perseritjetTotale);
        }

        if(this.step == 3){
            if(getRotate1 >= 0){
                this.logo1.setRotate(getRotate1 + this.rotate);
                this.logo2.setRotate(getRotate2 + this.rotate);
                this.logo3.setRotate(getRotate3 + this.rotate);
                this.logo4.setRotate(getRotate4 + this.rotate);
                this.logo5.setRotate(getRotate5 + this.rotate);
                this.logo6.setRotate(getRotate6 + this.rotate);
                this.logo7.setRotate(getRotate7 + this.rotate);
                this.logo8.setRotate(getRotate8 + this.rotate);
                this.logo9.setRotate(getRotate9 + this.rotate);
            }  else if( getRotate1 <= 0){
                if(this.perseritjaAktuale >= this.perseritjetTotale) {
//                this.backward = 40;
                    this.step = 4;
                    System.out.println("test1");
                    this.perfundimi = 1;
//                backward += 20;
                    this.red = 1;
                } else {
                    this.step = 1;
                    System.out.println("test2");
                }
            }
        }

        if(this.step == 0){
            if(red >= 8){
                this.text.setColor(Color.rgb(( red -= 7), 0, 0));
            }
        } else if(this.step == 1){
            if(red <= 252) {
                this.text.setColor(Color.rgb((red += 2), 0, 0));
            }
        } else if( this.step == 2){
            if(red >= 2) {
                this.text.setColor(Color.rgb((red -= 0.01), 0, 0));
            }
        }

    }
}