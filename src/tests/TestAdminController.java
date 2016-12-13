package tests;

import controller.AdminController;
import database.DataBase;
import model.WorkRequest;

public class TestAdminController {

    public static void main(String[] args) {

        System.out.println("testShowWorkRequests() --> " + testShowWorkRequests() +
                           "\ntestConfirmWorkRequest() --> " + testConfirmWorkRequest());
    }

    private static boolean testShowWorkRequests() {
        DataBase db = new DataBase();
        AdminController adminController = new AdminController(db);

        WorkRequest request = new WorkRequest("Ivan", "iturchin98@gmail.com", "builder", 1000);
        db.addWorkRequest(request);

        String res = adminController.showAllWorkRequests();

        return res.contains(request.toString());
    }

    private static boolean testConfirmWorkRequest() {
        DataBase db = new DataBase();
        AdminController adminController = new AdminController(db);

        WorkRequest request = new WorkRequest("Ivan", "iturchin98@gmail.com", "builder", 1000);
        db.addWorkRequest(request);

        adminController.checkIn("adminPass");

        adminController.confirmWorkRequest();

        return !db.getWorkRequests().contains(request);
    }
}
