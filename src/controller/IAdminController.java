package controller;

import javafx.collections.ObservableList;
import model.Product;
import model.WorkRequest;

public interface IAdminController extends IEmployee {

    ObservableList<WorkRequest> showAllWorkRequests();

    void confirmWorkRequest(WorkRequest workRequest);

    ObservableList<Product> showProductInTheDepartment(int id);
}
