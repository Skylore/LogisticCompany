package database;

import com.sun.istack.internal.NotNull;
import exceptions.BookedLoginException;
import geolocation.controller.Location;
import model.*;

public interface Dao {

    void addUser(@NotNull User user) throws BookedLoginException;

    User removeUser(@NotNull String password);

    void addWorkRequest(@NotNull WorkRequest request);

    int addRequest(String email, Product product, int price, Location from, Location to);

    Request requestToDelivered(String name);

    Request removeDelivered(int id);

    void addSupportRequest(@NotNull SupportRequest supportRequest);

    SupportRequest removeSupportRequest(SupportRequest supportRequest);
}
