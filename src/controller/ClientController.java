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

import java.util.NoSuchElementException;

public class ClientController implements IClientController{

    private static final int PRICE_BY_KILOMETER = 20;

    private GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
    private DataBase dataBase;
    private static int id = 0;

    private User inSystem;

    public ClientController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void registration(String email, String login, String password) throws BookedLoginException {
        dataBase.addUser(new User(login, email, password));
    }

    @Override
    public void logIn(String login, String password) throws NoSuchElementException {
        if (!dataBase.getUsers().containsKey(login)) {
            throw new NoSuchElementException();
        }
        User user = dataBase.getUsers().get(login);

        if (user.getPassword().equals(password)) {
            this.inSystem = user;
        }
    }

    @Override
    public void sendProductRequest(Product product,String email, Location from, Location to) {

        double allDistance = googleMapsAPI.getDistance(from, to) / 1000;

        dataBase.addRequest(new Request(id, email, product,
                ((int) (allDistance * PRICE_BY_KILOMETER)), from, to));

        SendMailSSL.sendLetter(email, "Delivery company", "you have ordered delivery of " + product.getName() +
                " by address " + to.getFormattedAddress() + "\nyour product's id is " + id);
        id++;
    }

    @Override
    public String whereIsMyProduct(int id) {

        for (Request o : dataBase.getRequests()) {
            if (o.getId() == id) {
                return "Your product is awaiting";
            }
        }

        for (Request o : dataBase.getDelivered()) {
            if (o.getId() == id) {
                return "Your product delivered";
            }
        }

        return "Your product is delivering";
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

    public User getInSystem() {
        return this.inSystem;
    }

    public static int getId() {
        return id;
    }
}
