package baseEntities;

import core.BrowserService;
import core.ReadProperties;
import org.testng.annotations.*;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {

    public BrowserService browserService;
    protected ReadProperties readProperties;

    @BeforeMethod
    public void setupMethod() {
        browserService = new BrowserService();
        browserService.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    }

    @AfterMethod
    public void tearDownMethod() {
        browserService.getDriver().quit();
    }
}
