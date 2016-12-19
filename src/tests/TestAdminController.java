package tests;

import controller.AdminController;
import database.DataBase;
import javafx.collections.ObservableList;
import model.WorkRequest;

// todo JUnit test framework
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

        ObservableList<WorkRequest> requests = adminController.showAllWorkRequests();

        return requests.size() == 1;
    }

    private static boolean testConfirmWorkRequest() {
        DataBase db = new DataBase();
        AdminController adminController = new AdminController(db);

        WorkRequest request = new WorkRequest("Ivan", "iturchin98@gmail.com", "builder", 1000);
        db.addWorkRequest(request);

        adminController.checkIn("adminPass");

        adminController.confirmWorkRequest(request);

        return !db.getWorkRequests().contains(request);
    }
}
