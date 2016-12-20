package controller;

import com.sun.istack.internal.NotNull;
import database.DataBase;
import gmailApi.SendMailSSL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.SupportRequest;

public class SupportController implements ISupport{

    private DataBase dataBase;
    private static int id = 0;
    private static final String PASS = "supportPass";

    public SupportController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void checkIn(String password) throws IllegalAccessException {
        if (!password.equals(PASS)) {
            throw new IllegalAccessException("wrong exception");
        }
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
