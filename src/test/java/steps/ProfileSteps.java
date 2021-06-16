package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.ProfilePage;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProfileSteps extends BaseStep {

    private ProfilePage profilePage;

    public ProfileSteps(BrowserService browserService) {
        super(browserService);
    }

    public ProfileSteps openProfilePage() {
        profilePage = new ProfilePage(browserService, true);
        return this;
    }

    public ProfilePage uploadAttachment() {

        ProfilePage profilePage = new ProfilePage(browserService, false);
        profilePage.getUpdateButton().click();

//        JavascriptExecutor executor = (JavascriptExecutor) browserService.getDriver();
//        executor.executeScript("arguments[0].style.display='block';", profilePage.getPictureInput());

        String fileName = "/src/test/java/testData/testCaseAttach.jpeg";
        String filePath = System.getProperty("user.dir") + fileName;

        profilePage.getPictureInput().sendKeys(filePath);
        return new ProfilePage(browserService, false);
    }
}