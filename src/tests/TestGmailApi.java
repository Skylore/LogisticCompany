package tests;

import gmailApi.SendMailSSL;

public class TestGmailApi {

    public static void main(String[] args) {

        SendMailSSL.sendLetter("shalamay.vlad44@gmail.com", "some tittle", "some text");

        try {
            SendMailSSL.sendLetter(null, null, null);
        } catch (NullPointerException e) {
            System.err.println("WRONG!!");
        }
    }
}
