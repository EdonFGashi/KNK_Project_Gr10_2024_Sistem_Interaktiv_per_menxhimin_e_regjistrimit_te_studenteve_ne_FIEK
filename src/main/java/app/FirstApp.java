package app;

import javafx.application.Application;
import javafx.stage.Stage;
import service.PasswordHasher;


//Klas e Testimeve (MOS E MIRRNI SERIOZISHT)




public class FirstApp extends Application {

    @Override
    public void start(Stage stage){

       Navigatior.tick(200);





    }

    public static void main(String[] args) {
       String JoniSalt = PasswordHasher.generateSalt();
        System.out.println("JoniSalt: "+JoniSalt);

        String EdoniSalt = PasswordHasher.generateSalt();
        System.out.println("EdoniSalt: "+EdoniSalt);
        System.out.println(PasswordHasher.generateSaltedHash("Isaku1234",JoniSalt));
        System.out.println(PasswordHasher.generateSaltedHash("Isaku1234",EdoniSalt));


    }


}
