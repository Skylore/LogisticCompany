package controller;

import com.sun.istack.internal.NotNull;
import geolocation.controller.Location;
import model.Product;

public interface IClientController {

    int sendProductRequest(Product product, String email, Location from, Location to);

    String whereIsMyProduct(int id);

    Product getProduct(int id);

    void sentWorkRequest(@NotNull String name, @NotNull String email,
                         @NotNull String goal,@NotNull int salary);

}
