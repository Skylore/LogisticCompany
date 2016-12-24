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
        supportController.ask("shalamay.vlad44@gmail.com", "what is this?");
    }

    @Test
    public void testReply(){
        supportController.reply(0, "ANSWER");
        Assert.assertEquals(0, dataBase.getSupportRequests().size());
    }

    @Test
    public void testShowRequest(){
        Assert.assertEquals(1, supportController.showRequests().get(0).getId());
    }
}
