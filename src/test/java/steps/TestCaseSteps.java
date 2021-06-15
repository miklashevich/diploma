package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import io.qameta.allure.Step;
import models.project.Project;
import models.testcase.TestCase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.CreateTestCasePage;
import pages.ProjectsPage;
import pages.TestRepositoryOfPublicProjectPage;
import utils.Waits;

import javax.sql.rowset.CachedRowSet;
import java.security.Key;
import java.util.concurrent.TimeUnit;

public class TestCaseSteps extends BaseStep {

    private ProjectsPage projectsPage = null;
    private TestRepositoryOfPublicProjectPage testRepository = null;
    private Actions actions = new Actions(browserService.getDriver());
    private Waits waits = new Waits(browserService.getDriver());

    public TestCaseSteps(BrowserService browserService) {
        super(browserService);
    }

    public TestCaseSteps openProjectsPage(Project project, boolean openByUrl) {
        projectsPage = new ProjectsPage(browserService, openByUrl);
        projectsPage.getProjectNameTitle(project.getTitle()).click();
        return this;
    }

    public TestCaseSteps openTestRepositoryOfPublicProjectPage(boolean openByUrl) {
        testRepository = new TestRepositoryOfPublicProjectPage(browserService, openByUrl);
        return this;
    }

    @Step("Create New Test Case: 'testCase'")
    public TestRepositoryOfPublicProjectSteps addTestCase(TestCase testCase) {

        testRepository.getCreateNewTestCaseButton().click();
        CreateTestCasePage createTestCase = new CreateTestCasePage(browserService, false);

        createTestCase.getTitleInput().sendKeys(testCase.getTitle());
        createTestCase.getSelectSeverity().click();
        switch (testCase.getSeverity()) {
            case CRITICAL:
                createTestCase.getSelectSeverityOptionBy().selectByIndex(3);
            default:
                break;
        }
        createTestCase.getSaveNewTestCaseButton().click();
        return new TestRepositoryOfPublicProjectSteps(browserService);
    }

    public TestRepositoryOfPublicProjectSteps deleteTestCase(TestCase testCase) {
        TestRepositoryOfPublicProjectPage deleteTestCase = new TestRepositoryOfPublicProjectPage(browserService, false);
        deleteTestCase.getSearchTestCaseInput().sendKeys(testCase.getTitle());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deleteTestCase.getTestCaseName(testCase.getTitle()).click();
        deleteTestCase.getDeleteTestCaseButton().click();
        deleteTestCase.getConfirmDeleteTestCaseButton().click();
        deleteTestCase.getNoResultsTextMessage().isDisplayed();
        waits.waitForInVisibilityOfElement(deleteTestCase.getConfirmDeleteTestCaseButtonBy());
        return new TestRepositoryOfPublicProjectSteps(browserService);
    }
}
