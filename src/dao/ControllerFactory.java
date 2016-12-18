package dao;

import controller.*;
import database.DataBase;
import utils.DBSaver;

import java.io.*;

public class ControllerFactory {

    private static DataBase dataBase = DBSaver.load();

    public static DataBase getDataBase() {
        return dataBase;
    }

    private AdminController adminController = new AdminController(dataBase);

    private ClientController clientController = new ClientController(dataBase);

    private BuilderController builderController = new BuilderController(dataBase);

    private CourierController courierController = new CourierController(dataBase);

    private SupportController supportController = new SupportController(dataBase);

    public AdminController getAdminController() {
        return adminController;
    }

    public ClientController getClientController() {
        return clientController;
    }

    public BuilderController getBuilderController() {
        return builderController;
    }

    public CourierController getCourierController() {
        return courierController;
    }

    public SupportController getSupportController() {
        return supportController;
    }
}
