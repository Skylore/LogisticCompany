package tests;

import controller.SupportController;
import database.DataBase;
import model.SupportRequest;

public class TestSupport {

    public static void main(String[] args) {

        System.out.println("testReply() --> " + testReply() +
                           "\ntestShowAllSupportRequests() --> " + testShowRequests());
    }

    private static boolean testReply() {
        DataBase dataBase = new DataBase();
        SupportController supportController = new SupportController(dataBase);

        supportController.ask("shalamay.vlad44@gmail.com", "what is this?");
        supportController.reply(0, "ANSWER");

        return dataBase.getSupportRequests().size() == 0;
    }

    private static boolean testShowRequests() {

        DataBase dataBase = new DataBase();
        SupportController supportController = new SupportController(dataBase);
        supportController.ask("shalamay.vlad44@gmail.com", "what is this?");

        System.out.println(supportController.showRequests().get(0).getId());

        return supportController.showRequests().get(0).getId() == 1;
    }

}
