package controller;

import database.DataBase;
import geolocation.controller.Location;
import model.Department;

public class AdminController implements IAdminController{

    private DataBase dataBase;

    public AdminController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void addDepartment(Location location) {

        Department department = new Department(DataBase.getDepartments().size(), location, null);

        DataBase.getDepartments().get(DataBase.getDepartments().size() - 1).setNext(department);
        DataBase.getDepartments().add(department);

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
