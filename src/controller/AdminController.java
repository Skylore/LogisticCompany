package controller;

import database.DataBase;
import geolocation.controller.Location;
import gmailApi.SendMailSSL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Department;
import model.Product;
import model.WorkRequest;
import java.util.*;

public class AdminController implements IAdminController{

    private DataBase dataBase;

    private static final String ADMIN_PASSWORD = "adminPass";
    private static final String BUILDER_PASSWORD = "builderPass";
    private static final String COURIER_PASSWORD = "courierPass";

    public AdminController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public ObservableList<WorkRequest> showAllWorkRequests() {
        List<WorkRequest> requests = dataBase.getWorkRequests();
        ObservableList<WorkRequest> result = FXCollections.observableArrayList();

        requests.stream().filter(Objects::nonNull).forEach(result::add);
        return result;
    }

    @Override
    public void confirmWorkRequest(WorkRequest workRequest) {

            dataBase.getWorkRequests().remove(workRequest);
            String pass;

            if (workRequest.getGoal().contains("admin")) {
                pass = ADMIN_PASSWORD;
            } else if (workRequest.getGoal().contains("builder")) {
                pass = BUILDER_PASSWORD;
            } else if (workRequest.getGoal().contains("courier")) {
                pass = COURIER_PASSWORD;
            } else {
                System.err.println("Incorrect goal");
                return;
            }

            SendMailSSL.sendLetter(workRequest.getEmail(), "Delivery administration", "our congratulations, "
                    + workRequest.getName() + " you have been recruited\nyour password - " + pass);
    }

    @Override
    public void checkIn(String password) throws IllegalAccessException{
        if (!password.equals(ADMIN_PASSWORD)) {
            throw new IllegalAccessException("wrong password");
        }
    }

    @Override
    public ObservableList<Product> showProductInTheDepartment(int id) {

        ObservableList<Product> products = FXCollections.observableArrayList();

        Optional<Location> location = DataBase.getDepartments().stream().filter(d -> d.getId() == id).
                map(Department::getLocation).findFirst();

        // compare department location and product location
        dataBase.getRequests().values().stream().filter(request -> request.getFrom().equals(location.get())).
                forEach(request -> products.add(request.getProduct()));

        dataBase.getDelivered().values().stream().filter(request -> request.getTo().equals(location.get())).
                forEach(request -> products.add(request.getProduct()));

        return products;
    }

}
