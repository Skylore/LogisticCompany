import controller.AdminController;
import database.DataBase;
import model.WorkRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAdminController {

    private DataBase db;
    private AdminController adminController;
    private WorkRequest request;

    @Before
    public void setUp(){
        db = new DataBase();
        adminController = new AdminController(db);
        request = new WorkRequest("Vlad", "shalamay.vlad44@gmail.com", "builder", 1000);
        db.addWorkRequest(request);
    }

    @Test
    public void testShowWorkRequest(){
        Assert.assertTrue(adminController.showAllWorkRequests().size() == 1);
    }

    @Test
    public void testConfirmWorkRequest(){
        adminController.confirmWorkRequest(request);
        Assert.assertFalse(db.getWorkRequests().contains(request));
    }


}
