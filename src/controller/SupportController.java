package controller;

import com.sun.istack.internal.NotNull;
import database.DataBase;
import gmailApi.SendMailSSL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.SupportRequest;

public class SupportController implements ISupport{

    private DataBase dataBase;
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
    public SupportRequest ask(@NotNull String email, @NotNull String question) {
        SupportRequest request = new SupportRequest(email, question);
        dataBase.addSupportRequest(request);
        return request;
    }

    @Override
    public void reply(SupportRequest supportRequest, String text) {

        SupportRequest sup = dataBase.removeSupportRequest(supportRequest);
        SendMailSSL.sendLetter(sup.getEmail(), "Delivery company", text);
    }

    @Override
    public ObservableList<SupportRequest> showRequests() {

        ObservableList<SupportRequest> requests = FXCollections.observableArrayList();
        dataBase.getSupportRequests().values().forEach(requests::add);

        return requests;
    }
}
