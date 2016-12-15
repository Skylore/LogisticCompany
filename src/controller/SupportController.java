package controller;

import com.sun.istack.internal.NotNull;
import database.DataBase;
import gmailApi.SendMailSSL;
import model.SupportRequest;

import java.security.AccessControlException;

public class SupportController implements ISupport{

    private DataBase dataBase;
    private boolean inSystem;

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
    public void ask(@NotNull SupportRequest supportRequest) {
        dataBase.addSupportRequest(supportRequest);
    }

    @Override
    public void reply(int id, String text) {

        SupportRequest sup = dataBase.removeSupportRequest(id);
        SendMailSSL.sendLetter(sup.getEmail(), "Delivery company", text);
    }

    @Override
    public String showRequests() {

        StringBuilder sb = new StringBuilder();
        dataBase.getSupportRequests().forEach((e) -> sb.append(e.toString()).append("\n"));

        return sb.toString();
    }
}
