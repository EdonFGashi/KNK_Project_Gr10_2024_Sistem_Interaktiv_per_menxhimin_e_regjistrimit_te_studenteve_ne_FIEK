package controller.Overall;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Error404Controller {
    @FXML
    private ImageView imgError404;


    @FXML
    public void initialize() {
        try {
            FileInputStream inputStream = new FileInputStream("Images/Error404.png");

            Image image = new Image(inputStream);
            imgError404.setImage(image);
            System.out.println("Image set successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Image not found");
        }
    }
}
