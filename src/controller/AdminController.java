package controller;

import database.DataBase;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import model.Department;

/**
 * Created by Влад on 12.12.2016.
 */
public class AdminController implements IAdminController{

    private GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
    private DataBase dataBase;

    public AdminController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void addDepartment(int id, Location location) {

        

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
