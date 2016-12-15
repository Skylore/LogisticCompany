package controller;

import com.sun.istack.internal.NotNull;
import database.DataBase;
import gmailApi.SendMailSSL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.SupportRequest;

import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;

public class SupportController implements ISupport{

    private DataBase dataBase;
    private boolean inSystem;
    private static int id = 0;

    public SupportController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean isInSystem() {
        return inSystem;
    }

    @Override
    public void checkIn(String password) {
        if (password.equals("supportPass")) {
            inSystem = true;
            return;
        }

        throw new AccessControlException("incorrect password");
    }

    @Override
    public void checkOut() {
        inSystem = false;
    }

    @Override
    public void ask(@NotNull String email, @NotNull String question) {
        dataBase.addSupportRequest(new SupportRequest(email, question, id));
        System.out.println("done");
        id++;
    }

    @Override
    public void reply(int id, String text) {

        SupportRequest sup = dataBase.removeSupportRequest(id);
        SendMailSSL.sendLetter(sup.getEmail(), "Delivery company", text);
    }

    @Override
    public ObservableList<SupportRequest> showRequests() {

        ObservableList<SupportRequest> requests = FXCollections.observableArrayList();
        dataBase.getSupportRequests().forEach(requests::add);

        return requests;
    }
}
