package controller;

import model.Product;

/**
 * Created by Влад on 11.12.2016.
 */
public interface IClientController {

    int sendProductRequest(Product product, String from, String to, int id);
    String whereIsMyProduct(int id);

}
