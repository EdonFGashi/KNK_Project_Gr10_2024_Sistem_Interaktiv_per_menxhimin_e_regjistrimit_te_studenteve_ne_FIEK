package controller.StudentStatistics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.dto.RegisteredStudents.ChartInformationEnrolledStudents;
import service.DBConnector;
import service.Student.StudentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMenuStatisticsController {

    @FXML
    private ImageView imgSearchStatistics;

    @FXML
    private TextField txtSearch;
    @FXML
    private BarChart<String, Integer> BSCChart;

    @FXML
    private BarChart<String, Integer> MSCChart;

    @FXML
    private BarChart<String, Integer> PHDChart;

    @FXML
    private LineChart totalChart;

    @FXML
    private Label txtFemaleTotalStudents;

    @FXML
    private Label txtMaleTotalStudents;

    @FXML
    private Label txtTotalStudents;

    @FXML
    private void initialize(){

    }

    @FXML
    void handleShow(ActionEvent event) {
        StudentService.numriMeshkujve = 0;
        StudentService.numriFemrave = 0;
        StudentService.numriTotalIStudentve = 0;
        BSCChart.getData().clear();
        XYChart.Series<String, Integer> chartBSCMashkull = StudentService.displayEnrolledStudentsChartByLevelAndGender("BSC", "Mashkull");
        XYChart.Series<String, Integer> charBSCtFemer = StudentService.displayEnrolledStudentsChartByLevelAndGender("BSC", "Femer");
        BSCChart.getData().addAll(chartBSCMashkull, charBSCtFemer);

        MSCChart.getData().clear();
        XYChart.Series<String, Integer> chartMSCMashkull = StudentService.displayEnrolledStudentsChartByLevelAndGender("MSC", "Mashkull");
        XYChart.Series<String, Integer> chartMSCFemer = StudentService.displayEnrolledStudentsChartByLevelAndGender("MSC", "Femer");
        MSCChart.getData().addAll(chartMSCMashkull, chartMSCFemer);

        PHDChart.getData().clear();
        XYChart.Series<String, Integer> charPHDMashkull = StudentService.displayEnrolledStudentsChartByLevelAndGender("PHD", "Mashkull");
        XYChart.Series<String, Integer> chartPHDFemer = StudentService.displayEnrolledStudentsChartByLevelAndGender("PHD", "Femer");
        PHDChart.getData().addAll(charPHDMashkull, chartPHDFemer);

        totalChart.getData().clear();
        XYChart.Series charTotalAplikimet = StudentService.displayTotalApplicationStudentsChart();
        totalChart.getData().add(charTotalAplikimet);

        this.txtFemaleTotalStudents.setText(String.valueOf(StudentService.numriFemrave));
        this.txtMaleTotalStudents.setText(String.valueOf(StudentService.numriMeshkujve));
        this.txtTotalStudents.setText(String.valueOf(StudentService.numriTotalIStudentve));

        System.out.println(StudentService.numriFemrave);
        System.out.println(StudentService.numriMeshkujve);
        System.out.println(StudentService.numriTotalIStudentve);

    }

    public void displayEnrolledBSCStudentsChart(){
        BSCChart.getData().clear();

        String queryMashkull = """
                SELECT registered.emriDepartamentit, COUNT(useri.userId) AS total_male_BSC_students
                FROM tblUserStudent useri
                JOIN tblRegisteredStudents registered ON useri.userId = registered.userId
                WHERE COALESCE(useri.gjinia, '') = 'Mashkull' AND COALESCE(registered.niveli, '') = 'BSC'
                GROUP BY registered.emriDepartamentit;
                """;

        String queryFemer = """
                SELECT registered.emriDepartamentit, COUNT(useri.userId) AS total_male_BSC_students
                FROM tblUserStudent useri
                JOIN tblRegisteredStudents registered ON useri.userId = registered.userId
                WHERE COALESCE(useri.gjinia, '') = 'Femer' AND COALESCE(registered.niveli, '') = 'BSC'
                GROUP BY registered.emriDepartamentit;
                """;

        Connection connection = DBConnector.getConnection();
        try{
            XYChart.Series<String, Integer>  chartFemer = new XYChart.Series();
            XYChart.Series<String, Integer>  chartMashkull = new XYChart.Series();

            PreparedStatement pstFemer = connection.prepareStatement(queryFemer);
            ResultSet resultFemer = pstFemer.executeQuery();

            PreparedStatement pstMashkull = connection.prepareStatement(queryMashkull);
            ResultSet resultMashkull = pstMashkull.executeQuery();


            while(resultMashkull.next()){
                ChartInformationEnrolledStudents stdMashkull = getSupervisorFromResultSet(resultMashkull);
                chartMashkull.getData().add(new XYChart.Data(stdMashkull.getDepartamenti(), stdMashkull.getNumri()));

            }

            while(resultFemer.next()){
                ChartInformationEnrolledStudents stdFemer = getSupervisorFromResultSet(resultFemer);
                chartFemer.getData().add(new XYChart.Data(stdFemer.getDepartamenti(), stdFemer.getNumri()));

            }


            chartFemer.setName("F");
            chartMashkull.setName("M");
            BSCChart.getData().addAll(chartMashkull, chartFemer);
//                connection.close();
                System.out.println("Nuk ka");;

            } catch (SQLException e){
            System.out.println("Gabim");
        }
    }

    private static ChartInformationEnrolledStudents getSupervisorFromResultSet(ResultSet result){
        try{
                int numri = result.getInt("total_male_BSC_students");
                String emriDepartamentit = result.getString("emriDepartamentit");
                return new ChartInformationEnrolledStudents(numri, emriDepartamentit);
        } catch (Exception e){
            System.out.println("Nuk ka te dhenaaaa!");
            return null;
        }
    }
}






















