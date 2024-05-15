//package controller.Overall;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import model.dto.Overall.UserDto;
//import service.Student.UserService;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.sql.SQLException;
//
//public class SignUpController {
//
//    @FXML
//    private ImageView imgIcon;
//
//    @FXML
//    private PasswordField pwdConfirmPassword;
//
//    @FXML
//    private TextField txtEmail;
//
//    @FXML
//    private TextField txtName;
//
//    @FXML
//    private PasswordField pwdPassword;
//
//    @FXML
//    private TextField txtSurname;
//
//    @FXML
//    void handleBack(ActionEvent event) {
//
//    }
//
//    @FXML
//    private void handleSignUp(ActionEvent ae) throws SQLException {
//        UserDto userSignUpData = new UserDto(
//                this.txtName.getText(),
//                this.txtSurname.getText(),
//                this.txtEmail.getText(),
//                this.pwdPassword.getText(),
//                this.pwdConfirmPassword.getText()
//        );
//
//        boolean response = UserService.signUp(userSignUpData);
//
//        if(response){
//          System.out.println("Okej");
//        }
//
//    }
//
//    @FXML
//    public void initialize() {
//        try {
//            this.imgIcon.setImage(new Image(new FileInputStream("Images/graduation2.png")));
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Image not found");
//        }
//
//    }
//}
