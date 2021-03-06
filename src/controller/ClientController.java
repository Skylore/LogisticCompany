package controller;

import com.sun.istack.internal.NotNull;
import exceptions.BookedLoginException;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import database.DataBase;
import gmailApi.SendMailSSL;
import model.Product;
import model.Request;
import model.User;
import model.WorkRequest;
import utils.SecurityUtils;

import java.util.Map;
import java.util.NoSuchElementException;

public class ClientController implements IClientController{

    private static final int PRICE_BY_KILOMETER = 20;

    private GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
    private DataBase dataBase;

    private User inSystem;

    public ClientController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void registration(String email, String login, String password) throws BookedLoginException {
        dataBase.addUser(new User(login, email, SecurityUtils.hashMD5(password)));
    }

    @Override
    public void logIn(String login, String password) throws IllegalAccessException {
        if (!dataBase.getUsers().containsKey(login)) {
            throw new NoSuchElementException();
        }
        User user = dataBase.getUsers().get(login);

        if (user.getPassword().equals(SecurityUtils.hashMD5(password))) {
            this.inSystem = user;
            return;
        }

        throw new IllegalAccessException();
    }

    @Override
    public int sendProductRequest(Product product,String email, Location from, Location to) {

        double allDistance = googleMapsAPI.getDistance(from, to) / 1000;

        int id = dataBase.addRequest(email, product,
                ((int) (allDistance * PRICE_BY_KILOMETER)), from, to);

        SendMailSSL.sendLetter(email, "Delivery company", "you have ordered delivery of " + product.getName() +
                " by address " + to.getFormattedAddress() + "\nyour product's id is " + id);
        return id;
    }

    @Override
    public String whereIsMyProduct(int id) {
        if (dataBase.getRequests().containsKey(id))
            return "Your product is awaiting";
        if (dataBase.getDelivered().containsKey(id))
            return "Your product is delivered";
        return "id does not exist";
    }

    @Override
    public Product getProduct(int id) {

        Request res = dataBase.removeDelivered(id);

        SendMailSSL.sendLetter(res.getEmail(), "Delivery company", "you could get your product by address:\n" +
        res.getTo().getFormattedAddress());

        return res.getProduct();
    }

    @Override
    public void sentWorkRequest(@NotNull String name, @NotNull String email,
                                @NotNull String goal, @NotNull int salary) {

        dataBase.addWorkRequest(new WorkRequest(name, email, goal, salary));
        System.out.println("Please expect");
    }

    @Override
    public void updateEmail(String newEmail, String scope) {
        if (!dataBase.getUsers().containsKey(scope)) {
            throw new NoSuchElementException();
        }

        User user = dataBase.removeUser(scope);
        user.setEmail(newEmail);

        try {
            dataBase.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePass(String newPass, String scope) {
        if (!dataBase.getUsers().containsKey(scope)) {
            throw new NoSuchElementException();
        }

        User user = dataBase.removeUser(scope);
        user.setPassword(newPass);

        try {
            dataBase.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public User getInSystem() {
        return this.inSystem;
    }

}
