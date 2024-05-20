package model.filter;

public class DepartmantByAfatFilter {
    public static String buildQuery(String afat) {
        String columnAfat = "nrStudentaveAfat1";
        String columnMinoritetAfat = "nrStudentaveMinoritetAfat1";

        if ("2".equalsIgnoreCase(afat)) {
            columnAfat = "nrStudentaveAfat2";
            columnMinoritetAfat = "nrStudentaveMinoritetAfat2";
        }

        return "SELECT emri, " + columnAfat + " AS nrStudentaveAfat, " + columnMinoritetAfat + " AS nrStudentaveMinoritetAfat " +
                "FROM tblDepartamenti WHERE niveli = ? AND emri IN ('IKS', 'EAR', 'EE', 'TIK')";
    }

}
