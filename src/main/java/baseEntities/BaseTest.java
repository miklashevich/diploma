package baseEntities;

import core.BrowserService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public BrowserService browserService;

    @BeforeClass
    public void setupClass() {
        browserService = new BrowserService();
        browserService.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    }

    @AfterClass
    public void tearDownClass() {
        browserService.getDriver().quit();
    }
}
