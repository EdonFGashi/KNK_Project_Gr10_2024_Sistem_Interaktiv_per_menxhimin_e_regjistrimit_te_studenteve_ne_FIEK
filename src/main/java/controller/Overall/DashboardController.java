package controller.Overall;

import app.Navigatior;
import controller.Admin.NjoftimPaneController;
import controller.SESSION;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Njoftim;
import model.dto.Admin.comunicateControllerdto;

import javafx.scene.text.Text;
import model.filter.NjoftimPagination;
import service.Admin.AdminService;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.HostServices;

import java.net.URI;
public class DashboardController {

    @FXML
    private ImageView mainImage;
    @FXML
    private Button buttoni_qasja;
    @FXML
    private ImageView infoimg;

    @FXML
    private Pagination pagPagination;

    private VBox contentVBox = new VBox();
    private final int njoftimePerPage = 1;
    private int totalNjoftime = AdminService.getTotalNjoftime();


    @FXML
    private ImageView imgTranslate;


    private void setPagination(){

    }

    @FXML
    private Button buttonDummyButton;


    @FXML
    protected void initialize() {

        try {
          //  this.mainImage.setImage(new Image(new FileInputStream("Images/fieku.png")));
            this.infoimg.setImage(new Image(new FileInputStream("Images/info-icon.png")));
          //  this.changeLanguageIcon.setImage(new Image(new FileInputStream("Images/language-icon.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        pagPagination.setPageCount((int) Math.ceil((double) totalNjoftime / njoftimePerPage));
        pagPagination.setPageFactory(this::createPage);
        this.contentVBox.setStyle("-fx-background-color:transparent");

        try {
            if(SESSION.isToggleShqip()){
                this.imgTranslate.setImage(new Image(new FileInputStream("src/main/resources/Images/language-en.png")));
            }
            else {
                this.imgTranslate.setImage(new Image(new FileInputStream("src/main/resources/Images/language-sq.png")));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private VBox createPage(int pageIndex) {

        int offset = (totalNjoftime - pageIndex*this.njoftimePerPage -this.njoftimePerPage);

        ArrayList<Njoftim> njoftimet = merrNjoftimWithPagination(offset, njoftimePerPage);
        Collections.reverse(njoftimet);
        contentVBox.getChildren().clear();
        for (Njoftim njoftim : njoftimet) {
            try {

                // FXMLLoader loader = new FXMLLoader(Main.class.getResource("admin-inbol-njoftimPane.fxml"));

                comunicateControllerdto data = Navigatior.loadAndReturnController(Navigatior.ADMIN_NJOFTIM_MODULE);

                if(data.getParent() != null && data.getController()!=null) {
                    Parent PaneNjoftimi = data.getParent();
                    contentVBox.getChildren().add(PaneNjoftimi);
                    NjoftimPaneController controller = (NjoftimPaneController) data.getController();
                    controller.setNjoftimi(njoftim);
                    //controller.setController(this);
                    //contentVBox.getChildren().add(PaneNjoftimi);
                }else{
                    System.out.println("Pane nuk u gjet!");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return contentVBox;
    }


    private ArrayList<Njoftim> merrNjoftimWithPagination(int offset, int size) {
        if(offset == -1 ){
            offset = 0;
            size = 1;
        }
        NjoftimPagination pagination = new NjoftimPagination(offset, size);
        return AdminService.getNjoftimiWithPagination(pagination);
    }




    @FXML
    private void handleChangeLanguage(MouseEvent me){
        SESSION.switchLanguage();
        Navigatior.navigate(me,Navigatior.DASHBOARD);
        System.out.println("U Ekxekutu");
    }

    @FXML
    public void handleQasja(ActionEvent event) {
        this.qasu();
    }
    @FXML
    private void handleTextQasja(MouseEvent me){
        this.qasu();
    }

    private void qasu(){
        comunicateControllerdto data = Navigatior.loadAndReturnController(Navigatior.LOGIN);

        Parent loginPane = data.getParent();

        LoginController controller = (LoginController) data.getController();

        controller.setDashboardController(this);

        Navigatior.navigateNewStageByPane(loginPane);
    }

    @FXML
    public void buttoni_qasja_hover(MouseEvent event) {
        buttoni_qasja.setStyle("-fx-opacity: 0.9;" +
                "-fx-background-color:  linear-gradient(to bottom, #0a84ff, #00a5ff, #00c0ff, #00d7f7, #12ebe5)");
    }


    @FXML
    public void buttoni_qasja_exit(MouseEvent event) {

        buttoni_qasja.setStyle("-fx-opacity: 0.5;" +
                "-fx-background-color:  linear-gradient(to bottom, #0a84ff, #00a5ff, #00c0ff, #00d7f7, #12ebe5)");
    }

    @FXML
    public void teksti_qasja_hover(MouseEvent event) {
        buttoni_qasja.setStyle("-fx-opacity: 0.9;" +
                "-fx-background-color:  linear-gradient(to bottom, #0a84ff, #00a5ff, #00c0ff, #00d7f7, #12ebe5)");
    }

    @FXML
    public void teksti_qasja_exit(MouseEvent event) {

        buttoni_qasja.setStyle("-fx-opacity: 0.5;" +
                "-fx-background-color:  linear-gradient(to bottom, #0a84ff, #00a5ff, #00c0ff, #00d7f7, #12ebe5)");
    }

    public void close(){
        this.buttonDummyButton.fire();
    }

     @FXML
    private void handleCloseStage(ActionEvent ae){
         Navigatior.closeStageAfterDelay(ae, Duration.millis(20));
     }

     @FXML
    private void handleInfo(MouseEvent me){
        Navigatior.navigateNewStage(Navigatior.HELP_DASHBOARD);
     }

     @FXML
    private void handleRektorati(ActionEvent ae){
         String url = "https://www.example.com";
         openWebpage(url);
     }
    private void openWebpage(String urlString) {
        String url = "https://www.example.com";
      //  getHostServices().showDocument(url);
    }




}



