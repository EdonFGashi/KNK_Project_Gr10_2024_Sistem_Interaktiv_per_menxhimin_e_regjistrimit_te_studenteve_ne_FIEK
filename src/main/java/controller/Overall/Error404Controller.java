package controller.Overall;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Error404Controller {
    @FXML
    private ImageView imgError404;



    public Error404Controller(){
        Image image= new Image("Images/Error 404.png");
        this.imgError404.setImage(image);
    }
}
