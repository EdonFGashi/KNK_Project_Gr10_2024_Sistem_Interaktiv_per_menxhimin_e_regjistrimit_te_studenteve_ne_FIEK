package service;

public class SESSION {
    private static String loggedUserEmail = "jon@admin.uni-pr.edu";
    private SESSION() {
    }

    public static String getLoggedUserEmail() {
        return loggedUserEmail;
    }

    public static void setLoggedUserEmail(String loggedUserEmail) {
        SESSION.loggedUserEmail = loggedUserEmail;
    }
}
