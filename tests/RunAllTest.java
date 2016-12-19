import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAdminController.class,
        TestBuilderController.class,
        TestDelivery.class,
        TestGeolacation.class,
        TestGmailApi.class,
        TestSupport.class,
        TestUserController.class
})
public class RunAllTest {
}
