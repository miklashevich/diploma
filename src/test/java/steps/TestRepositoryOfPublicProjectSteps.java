package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import models.testcase.TestCase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.CreateTestCasePage;
import pages.ProjectSettingPage;
import pages.ProjectsPage;
import pages.TestRepositoryOfPublicProjectPage;

public class TestRepositoryOfPublicProjectSteps extends BaseStep {

    private TestRepositoryOfPublicProjectPage testRepo = null;
    private Actions actions = new Actions(browserService.getDriver());

    public TestRepositoryOfPublicProjectSteps openTestRepositoryOfPublicProjectPage(boolean openByUrl) {
        testRepo = new TestRepositoryOfPublicProjectPage(browserService, openByUrl);
        return this;
    }

    public TestRepositoryOfPublicProjectSteps(BrowserService browserService) {
        super(browserService);
    }


    public ProjectSettingPage setSetting() {

        TestRepositoryOfPublicProjectPage publicProjectPage =new TestRepositoryOfPublicProjectPage(browserService, false);
        publicProjectPage.getSettingsButton().click();

        ProjectSettingPage projectSettingPage = new ProjectSettingPage(browserService, false);

        projectSettingPage.getProjectCodeInput().click();
        actions
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();

        projectSettingPage.getUpdateSettingButton().click();

        return new ProjectSettingPage(browserService, false);
    }


}

