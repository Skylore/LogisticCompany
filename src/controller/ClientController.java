package controller;

import com.sun.istack.internal.NotNull;
import geolocation.controller.GoogleMapsAPI;
import geolocation.controller.GoogleMapsAPIImpl;
import geolocation.controller.Location;
import database.DataBase;
import gmailApi.SendMailSSL;
import model.Product;
import model.Request;
import model.WorkRequest;

public class ClientController implements IClientController{

    private static final int PRICE_BY_KILOMETER = 20;

    private GoogleMapsAPI googleMapsAPI = new GoogleMapsAPIImpl();
    private DataBase dataBase;
    private static int id = 0;

    public ClientController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void sendProductRequest(Product product,String email, Location from, Location to) {

        double allDistance = googleMapsAPI.getDistance(from, to) / 1000;

        dataBase.addRequest(new Request(id, email, product,
                ((int) (allDistance * PRICE_BY_KILOMETER)), from, to));

        id++;

        SendMailSSL.sendLetter(email, "Delivery company", "you have ordered delivery of " + product.getName() +
                " by address " + to.getFormattedAddress() + "\nyour product's id is " + id);
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
}
