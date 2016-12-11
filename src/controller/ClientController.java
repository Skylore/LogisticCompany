package controller;

import model.Product;
import model.Request;

/**
 * Created by Влад on 11.12.2016.
 */
public class ClientController implements IClientController{

    private DataBase dataBase;

    public ClientController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public int sendProductRequest(Product product, String from, String to) {

        dataBase.requests.add(new Request(product, from, to))

        return ;
    }

    @Override
    public String whereIsMyProduct(int id) {
        return null;
    }
}
