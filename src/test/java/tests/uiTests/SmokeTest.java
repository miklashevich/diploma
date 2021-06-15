package tests.uiTests;

import baseEntities.BaseTest;
import lombok.extern.slf4j.Slf4j;
import models.project.Project;
import models.testcase.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateProjectPage;
import pages.ProjectsPage;
import pages.TestRepositoryOfPrivateProjectPage;
import pages.TestRepositoryOfPublicProjectPage;
import steps.LoginSteps;
import steps.ProjectsSteps;
import steps.TestCaseSteps;
import testData.StaticProvider;

@Slf4j
public class SmokeTest extends BaseTest {

    @Test(description = "Create new project", dataProvider = "Create project with the help of Builder", dataProviderClass = StaticProvider.class)
    public void createProjectTestBoundaryValueAnalysis(String projectName, Project project) {
        final int PROJECT_NAME_LENGTH = 255;

        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithCorrectCredentials()
                .openProjectsPage(false);

        ProjectsPage projectsPage = new ProjectsPage(browserService, false);
        projectsPage.getCreateNewProjectButton().click();

        ProjectsSteps projectsSteps = new ProjectsSteps(browserService);
        TestRepositoryOfPrivateProjectPage testRepositoryOfPrivateProjectPage = projectsSteps.addProject(project);

        Assert.assertEquals(testRepositoryOfPrivateProjectPage.getTestRepositoryName().getText().length(), PROJECT_NAME_LENGTH);
    }

    @Test(description = "Create new project name length more than permitted ", dataProvider ="Create project with the name more than 255", dataProviderClass = StaticProvider.class)
    public void createProjectTestLengthNameMorePermitted(String projectName, Project project) {

        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithCorrectCredentials()
                .openProjectsPage(false);

        ProjectsPage projectsPage = new ProjectsPage(browserService, false);
        projectsPage.getCreateNewProjectButton().click();

        ProjectsSteps projectsSteps = new ProjectsSteps(browserService);
        CreateProjectPage createProjectPage = projectsSteps.addProjectNoPermittedLength(project);


        Assert.assertEquals(createProjectPage.getErrorMessage().getText(), "The title may not be greater than 255 characters.");
    }

    @Test(description = "Create new Test Case", dataProvider = "Create Test Case with the help of Builder", dataProviderClass = StaticProvider.class)
    public void createTestCaseCreatingEntityTest(String projectName, Project project, TestCase testCase) {

        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithCorrectCredentials()
                .openProjectsPage(false);

        TestCaseSteps createNewTestCaseSteps = new TestCaseSteps(browserService);
        createNewTestCaseSteps
                .openProjectsPage(project, false)
                .openTestRepositoryOfPublicProjectPage(false)
                .addTestCase(testCase)
                .openTestRepositoryOfPublicProjectPage(false);

        Assert.assertEquals(
                new TestRepositoryOfPublicProjectPage(browserService, false)
                        .getTestCaseName(testCase.getTitle())
                        .getText(),
                testCase.getTitle());
    }

}
