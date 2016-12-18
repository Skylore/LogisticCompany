package controller;

import database.DataBase;
import geolocation.controller.Location;
import model.Department;

/**
 * Created by Влад on 13.12.2016.
 */
public class BuilderController implements IBuilder{

    private DataBase dataBase;
    private boolean inSystem = false;
    private static final String PASS = "builderPass";

    public BuilderController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean isInSystem() {
        return inSystem;
    }

    @Override
    public void build(Location location) {
        dataBase.getDepartments().add(new Department(dataBase.getDepartments().size(), location));
    }

    @Override
    public void checkIn(String password) {

        if (password.equals(PASS)){
            inSystem = true;
        }
    }

    @Override
    public void checkOut() {
        inSystem = false;
    }
}
