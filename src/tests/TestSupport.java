package tests;

import controller.SupportController;
import database.DataBase;
import model.SupportRequest;

public class TestSupport {

    public static void main(String[] args) {

        System.out.println("testReply() --> " + testReply() +
                           "testShowAllSupportRequests() --> " + testShowRequests());
    }

    private static boolean testReply() {
        DataBase dataBase = new DataBase();
        SupportController supportController = new SupportController(dataBase);

        SupportRequest supportRequest = new SupportRequest("iturchin98@gmail.com", "what is this?", 0);

        supportController.ask(supportRequest.getEmail(), supportRequest.getQuestion());

        supportController.reply(0, "ANSWER");

        return !dataBase.getSupportRequests().contains(supportRequest);
    }

    private static boolean testShowRequests() {

        DataBase dataBase = new DataBase();
        SupportController supportController = new SupportController(dataBase);

        SupportRequest supportRequest = new SupportRequest("iturchin98@gmail.com", "what is this?", 0);

        supportController.ask(supportRequest.getEmail(), supportRequest.getQuestion());

        return supportController.showRequests().contains(supportRequest.toString());
    }

}
