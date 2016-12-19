import gmailApi.SendMailSSL;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestGmailApi {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGmail(){
        SendMailSSL.sendLetter("shalamay.vlad44@gmail.com", "some tittle", "some text");
    }

    @Test
    public void negativeTestGmail(){
        exception.expect(NullPointerException.class);
        SendMailSSL.sendLetter(null, null, null);
    }

}
