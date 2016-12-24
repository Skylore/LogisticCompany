package dao;

import controller.*;
import database.Converter;
import database.DataBase;
import database.Logger;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

    private DataBase dataBase = new DataBase();

    {
        String fromJson = new Logger().read();
        if (fromJson.isEmpty()) {
            dataBase = new DataBase();
        } else {
            dataBase = Converter.fromJson(fromJson, DataBase.class);
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
