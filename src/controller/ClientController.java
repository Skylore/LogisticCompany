package controller;

import model.DataBase;
import model.Product;
import model.Request;


public class ClientController implements IClientController{

    private DataBase dataBase;
    private static int id;

    public ClientController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public int sendProductRequest(Product product, String from, String to, int id) {

        dataBase.getRequests().add(new Request(product, from, to, id));

        return 0;
    }

    @Override
    public String whereIsMyProduct(int id) {
        return null;
    }
}
