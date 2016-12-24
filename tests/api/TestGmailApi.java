package api;

import gmailApi.SendMailSSL;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestGmailApi {

    @Test
    public void testGmail(){
        SendMailSSL.sendLetter("shalamay.vlad44@gmail.com", "some tittle", "some text");
    }

    @Test(expected = NullPointerException.class)
    public void negativeTestGmail(){
        SendMailSSL.sendLetter(null, null, null);
    }

}
