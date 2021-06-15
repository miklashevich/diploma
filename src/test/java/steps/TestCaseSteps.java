package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import io.qameta.allure.Step;
import models.project.Project;
import models.testcase.TestCase;
import pages.CreateTestCasePage;
import pages.ProjectsPage;
import pages.TestRepositoryOfPublicProjectPage;

public class TestCaseSteps extends BaseStep {

    private ProjectsPage projectsPage = null;
    private TestRepositoryOfPublicProjectPage testRepository = null;

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
        testRepository.getCreateNewTestCaseButton().click();
        return this;
    }

    @Step("Create New Test Case: 'testCase'")
    public TestRepositoryOfPublicProjectSteps addTestCase(TestCase testCase) {
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
}
