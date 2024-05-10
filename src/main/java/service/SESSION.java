package service;

public class SESSION {
    private static String loggedUserEmail = "jon@admin.uni-pr.edu";
    private static String adminMenu = "";
    private static String admin_registration_lastSearch = "";
    private SESSION() {
    }

    public static String getAdmin_registration_lastSearch() {
        return admin_registration_lastSearch;
    }

    public static void setAdmin_registration_lastSearch(String admin_registration_lastSearch) {
        SESSION.admin_registration_lastSearch = admin_registration_lastSearch;
    }

    public static String getLoggedUserEmail() {
        return loggedUserEmail;
    }

    public static void setLoggedUserEmail(String loggedUserEmail) {
        SESSION.loggedUserEmail = loggedUserEmail;
    }

    public static String getAdminMenu() {
        return adminMenu;
    }

    public static void setAdminMenu(String adminMenu) {
        SESSION.adminMenu = adminMenu;
    }
}
