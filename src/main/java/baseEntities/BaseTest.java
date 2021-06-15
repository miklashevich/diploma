package baseEntities;

import core.BrowserService;
import core.ReadProperties;
import org.testng.annotations.*;
import utils.Waits;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public BrowserService browserService;
    protected ReadProperties readProperties;
    protected Waits waits;

//    @BeforeClass
//    public void setupClass() {
//
//    }

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
