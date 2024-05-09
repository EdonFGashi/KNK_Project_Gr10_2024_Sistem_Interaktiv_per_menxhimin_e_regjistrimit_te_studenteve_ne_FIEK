package app;

import javafx.application.Application;
import javafx.stage.Stage;
import service.PasswordHasher;


//Klas e Testimeve (MOS E MIRRNI SERIOZISHT)




public class FirstApp extends Application {

    @Override
    public void start(Stage stage){

     //  Navigatior.tick(200);



    }

    public static void main(String[] args) {
        String salt = PasswordHasher.generateSalt();
        System.out.println(salt);
        System.out.println(PasswordHasher.generateSaltedHash("Isaku1234",salt));
        String salt2 = PasswordHasher.generateSalt();
        System.out.println(salt2);
        System.out.println(PasswordHasher.generateSaltedHash("Isaku1234",salt2));
    }




}
