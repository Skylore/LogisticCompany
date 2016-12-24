package init;

import controller.*;
import database.Converter;
import database.DataBase;
import database.Logger;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

    private static DataBase dataBase = new DataBase();

    private static Map<String, Object> map = new HashMap<>();

    static {
        map.put("AdminController", new AdminController(dataBase));
        map.put("ClientController", new ClientController(dataBase));
        map.put("BuilderController", new BuilderController(dataBase));
        map.put("CourierController", new CourierController(dataBase));
        map.put("SupportController", new SupportController(dataBase));
    }

    {
        String fromJson = new Logger().read();
        if (fromJson.isEmpty()) {
            dataBase = new DataBase();
        } else {
            dataBase = Converter.fromJson(fromJson, DataBase.class);
        }
    }


    public Object getController(String key) {

        return map.get(key);
    }

    public DataBase getDataBase() {
        return dataBase;
    }
}
