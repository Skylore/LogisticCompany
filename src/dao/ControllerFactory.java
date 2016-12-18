package dao;

import controller.*;
import database.DataBase;

// todo
// ClientController cl = (ClientController) ObjectFactory.get("clientController")
// Map<String, Object> context todo coding bat map
// context.put("clientController", new ClientController())
// context.get("clientController");
public class ControllerFactory {

    private DataBase dataBase = new DataBase();

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
