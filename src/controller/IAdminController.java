package controller;

import javafx.collections.ObservableList;
import model.Product;

public interface IAdminController extends IEmployee {

    String showAllWorkRequests();

    void confirmWorkRequest();

    ObservableList<Product> showProductInTheDepartment(int id);
}
