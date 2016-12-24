package controller;

import com.sun.istack.internal.NotNull;
import exceptions.BookedLoginException;
import geolocation.controller.Location;
import model.Product;
import model.User;

public interface IClientController {

    void registration(String email, String login, String password) throws BookedLoginException;

    void logIn(String login, String password) throws IllegalAccessException;

    int sendProductRequest(Product product, String email, Location from, Location to) throws IllegalAccessException;

    String whereIsMyProduct(int id);

    Product getProduct(int id);

    void sentWorkRequest(@NotNull String name, @NotNull String email,
                         @NotNull String goal,@NotNull int salary);

    void updateInfo(User user, String scope) throws BookedLoginException;

}
