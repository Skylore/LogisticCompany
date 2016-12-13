package controller;

import database.DataBase;
import geolocation.controller.Location;
import gmailApi.SendMailSSL;
import model.Department;
import model.WorkRequest;
import java.util.*;

public class AdminController implements IEmployee, IAdminController{

    private DataBase dataBase;

    private static final String ADMIN_PASSWORD = "adminPass";
    private static final String BUILDER_PASSWORD = "builderPass";
    private static final String COURIER_PASSWORD = "courierPass";

    private boolean inSystem = false;

    public AdminController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String showAllWorkRequests() {
        List<WorkRequest> requests = dataBase.getWorkRequests();
        StringBuilder sb = new StringBuilder();

        requests.stream().filter((a) -> a != null).forEach((a) -> sb.append(a.toString()).append("\n"));
        return sb.toString();
    }

    @Override
    public void confirmWorkRequest() {
        if (inSystem && dataBase.getWorkRequests().size() != 0) {
            WorkRequest res = dataBase.removeWorkRequest();

            String pass;

            if (res.getGoal().contains("admin")) {
                pass = ADMIN_PASSWORD;
            } else if (res.getGoal().contains("builder")) {
                pass = BUILDER_PASSWORD;
            } else if (res.getGoal().contains("courier")) {
                pass = COURIER_PASSWORD;
            } else {
                System.err.println("Incorrect goal");
                return;
            }

            SendMailSSL.sendLetter(res.getEmail(), "Delivery administration", "our congratulations, "
                    + res.getName() + " you have been recruited\nyour password - " + pass);
        }
    }

    @Override
    public void checkIn(String password) {
        if (password.equals(ADMIN_PASSWORD)) {
            inSystem = true;
        }
    }

    @Override
    public void checkOut() {
        inSystem = false;
    }

    @Override
    public String showRequestsInTheDepartment(int id) {

        Location departmentLocation = new Location();
        StringBuilder res = new StringBuilder();

        // search department location
        for (Department department : DataBase.getDepartments()) {
            if (department.getId() == id){
                departmentLocation = department.getLocation();
                break;
            }
        }

        final Location location = departmentLocation;

        // compare department location and product location
        dataBase.getRequests().stream().filter(request -> request.getFrom().equals(location)).
                forEach(request -> res.append(request.toString()));

        dataBase.getDelivered().stream().filter(request -> request.getTo().equals(location)).
                forEach(request -> res.append(request.toString()));

        return res.toString();
    }

}
