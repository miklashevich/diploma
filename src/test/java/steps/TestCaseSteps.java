package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import io.qameta.allure.Step;
import models.project.Project;
import models.testcase.TestCase;
import org.openqa.selenium.interactions.Actions;
import pages.CreateTestCasePage;
import pages.ProjectsPage;
import pages.TestRepositoryOfPublicProjectPage;
import utils.Waits;

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
        waits.waitForElementToBeClickable(deleteTestCase.getTestCaseNameBy(testCase.getTitle()));
        deleteTestCase.getTestCaseName(testCase.getTitle()).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deleteTestCase.getDeleteTestCaseButton().click();
        deleteTestCase.getConfirmDeleteTestCaseButton().click();
        deleteTestCase.getNoResultsTextMessage().isDisplayed();
        waits.waitForInVisibilityOfElement(deleteTestCase.getConfirmDeleteTestCaseButtonBy());
        return new TestRepositoryOfPublicProjectSteps(browserService);
    }
}
