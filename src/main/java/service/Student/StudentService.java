package service.Student;

import javafx.scene.chart.XYChart;
import model.RegisteredStudent;
import model.dto.RegisteredStudents.ChartInformationEnrolledStudents;
import model.dto.RegisteredStudents.RegisteredStudents;
import repository.AdminRepository;
import repository.StudentRepository;

import java.util.ArrayList;

public class StudentService {
    public static int numriMeshkujve = 0;
    public static int numriFemrave = 0;
    public static int numriTotalIStudentve = 0;
    public static XYChart.Series<String, Integer> displayEnrolledStudentsChartByLevelAndGender(String niveli, String gjinia){
        ArrayList<ChartInformationEnrolledStudents> listaInformacionevePerChart = StudentRepository.getStudentForChart(niveli, gjinia);
            XYChart.Series<String, Integer>  chart = new XYChart.Series();
            chart.getData().clear();
            for(ChartInformationEnrolledStudents studenti : listaInformacionevePerChart){
                if(gjinia.equals("M")){
                    System.out.println("U rrit mashkulli");
                    numriMeshkujve += studenti.getNumri();
                } else if(gjinia.equals("F")){
                    numriFemrave += studenti.getNumri();
                    System.out.println("U rrit femra");
                }
                chart.getData().add(new XYChart.Data(studenti.getDepartamenti(), studenti.getNumri()));
            }
        numriTotalIStudentve = numriMeshkujve + numriFemrave;
            chart.setName(gjinia);
            return chart;
    }

    public static XYChart.Series displayTotalApplicationStudentsChart(){
        ArrayList<ChartInformationEnrolledStudents> listaInformacionevePerChart = StudentRepository.getApplicationsForChart();
        XYChart.Series chart = new XYChart.Series();
        chart.getData().clear();
        for(ChartInformationEnrolledStudents studenti : listaInformacionevePerChart){
            chart.getData().add(new XYChart.Data(String.valueOf(studenti.getViti()), studenti.getNumri()));
        }
        chart.setName("Aplikimet");
        return chart;
    }
    public static RegisteredStudent getRegisteredStudent(int userId){
        return StudentRepository.getRegisteredStudent(userId);
    }


    public static boolean registerStundent(RegisteredStudents student){
        return StudentRepository.registerStundentInDb(student);
    }
}
