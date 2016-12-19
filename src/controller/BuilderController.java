package controller;

import database.DataBase;
import geolocation.controller.Location;
import model.Department;

/**
 * Created by Влад on 13.12.2016.
 */
public class BuilderController implements IBuilder{

    private DataBase dataBase;
    private static final String PASS = "builderPass";

    public BuilderController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void build(Location location) {
        DataBase.getDepartments().add(new Department(DataBase.getDepartments().size(), location));
    }

    @Override
    public void checkIn(String password) throws IllegalAccessException{

        if (!password.equals(PASS)){
            throw new IllegalAccessException("wrong password");
        }
    }
}
