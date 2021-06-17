package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import pages.ProfilePage;
import pages.ProjectsPage;
import utils.Waits;

import java.io.File;

public class ProfileSteps extends BaseStep {

    private Waits waits = new Waits(browserService.getDriver());

    public ProfileSteps(BrowserService browserService) {
        super(browserService);
    }

    public ProfileSteps openProfilePage(boolean openPageByUrl) {
        new ProfilePage(browserService, openPageByUrl);
        return this;
    }

    public ProfileSteps openProjectsPage(boolean openPageByUrl) {
        ProjectsPage projectsPage = new ProjectsPage(browserService, false);
        projectsPage.getMenuButton().hover();
        projectsPage.getMenuButton().click();
        projectsPage.getProfileButton().click();

        return this;
    }

    public ProfileSteps uploadAttachment() {

        String path = new File("src/test/java/testData/testCaseAttach.jpeg").getAbsolutePath();

        ProfilePage profilePage = new ProfilePage(browserService, false);
        profilePage.getUpdateButton().click();
        profilePage.getPictureInput().sendKeys(path);
        waits.waitForElementToBeClickable(profilePage.getUpdateButtonBy());

        return new ProfileSteps(browserService);
    }
}