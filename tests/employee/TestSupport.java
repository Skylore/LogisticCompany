package employee;

import controller.SupportController;
import database.DataBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSupport {

    private DataBase dataBase;
    private SupportController supportController;

    @Before
    public void setUp(){
        dataBase = new DataBase();
        supportController = new SupportController(dataBase);
    }

    @Test
    public void testReply(){
        supportController.reply(supportController.ask("shalamay.vlad44@gmail.com", "what is this?"),
                "ANSWER");
        Assert.assertEquals(0, dataBase.getSupportRequests().size());
    }

    @Test
    public void testShowRequest(){
        supportController.ask("shalamay.vlad44@gmail.com", "what is this?");
        Assert.assertEquals("what is this?", supportController.showRequests().get(0).getQuestion());
    }
}
