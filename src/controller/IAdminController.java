package controller;

import geolocation.controller.Location;

/**
 * Created by Влад on 12.12.2016.
 */
public interface IAdminController {

    void addDepartment(Location location);
    String showRequestsInTheDepartment(int id);

}