package dao;

import controller.*;
import database.Converter;
import database.DataBase;
import database.Logger;

import java.util.HashMap;
import java.util.Map;

// todo
// ClientController cl = (ClientController) ObjectFactory.get("clientController")
// Map<String, Object> context todo coding bat map
// context.put("clientController", new ClientController())
// context.get("clientController");
public class ControllerFactory {

    private DataBase dataBase = new DataBase();

    {
        try {
            dataBase = Converter.fromJson(new Logger().read(), DataBase.class);
        } catch (Exception e) {
            System.out.println("Log created");
        }
    }

    public Object getController(String key) {
        Map<String, Object> map = new HashMap<>();

        map.put("AdminController", new AdminController(dataBase));
        map.put("ClientController", new ClientController(dataBase));
        map.put("BuilderController", new BuilderController(dataBase));
        map.put("CourierController", new CourierController(dataBase));
        map.put("SupportController", new SupportController(dataBase));

        return map.get(key);
    }

    public DataBase getDataBase() {
        return dataBase;
    }
}
