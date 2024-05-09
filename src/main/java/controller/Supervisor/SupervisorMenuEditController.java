package controller.Supervisor;


import app.Navigatior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.dto.Supervisor.SupervisorCreateInterfaceDto;
import model.dto.Supervisor.SupervisorEditDto;
import model.dto.Supervisor.SupervisorTableModel;
import repository.Supervisor.SupervisorRepository;
import service.Supervisor.SupervisorService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SupervisorMenuEditController {
    @FXML
    private TextField txtSearchSupervisorByEmail;
    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField pwdResetPasswordSupervisor;

    @FXML
    private void initialize(){
//
//        try {
//            this.imgProfileIcon.setImage(new Image(new FileInputStream("Images/profileIcon.png")));
//            this.imgStudentIcon.setImage(new Image(new FileInputStream("Images/studentIcon.png")));
//            this.imgSupervisorIcon.setImage(new Image(new FileInputStream("Images/supervisorIcon.png")));
//            this.imgInboxIcon.setImage(new Image(new FileInputStream("Images/inboxIcon.png")));
//            this.imgUpLogo.setImage(new Image(new FileInputStream("Images/upLogoNoRing.png")));
//
//            // System.out.println("Image set successfully.");
//        } catch (FileNotFoundException e) {
//            System.out.println("Image not found");
//        }
    }
    @FXML
    private void handleSearchSupervisorByEmail(ActionEvent ae) {
//        Pane pane1 = Animation.getAnimation();
//        this.animationPane.getChildren().addAll(pane);
//        Navigatior.navigate(this.animationPane, String.valueOf(pane1));
        boolean isFound = SupervisorService.supervisorIsFoundByEmail(this.txtSearchSupervisorByEmail.getText());
        if (!isFound) {
            Navigatior.loading("Jepni email valide !", false, "");
        }
        SupervisorTableModel supervisor = SupervisorRepository.getSupervisorByEmail(this.txtSearchSupervisorByEmail.getText());
        this.txtEmail.setText(supervisor.getEmail());
        this.txtLastname.setText(supervisor.getLastName());
        this.txtFirstname.setText(supervisor.getFirstName());
    }

    @FXML
    private void handleEditInformationSupervisor(ActionEvent ae){

    }
    @FXML
    private void handleSaveInformationSupervisor(ActionEvent ae){
        SupervisorEditDto addNewSupervisor = new SupervisorEditDto(
                this.txtFirstname.getText(),
                this.txtLastname.getText(),
                this.txtEmail.getText()
        );

    }

    @FXML
    private void handleResetPasswordSupervisor(ActionEvent ae){

    }


}
