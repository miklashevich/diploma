package tests.uiTests;

import baseEntities.BaseTest;
import lombok.extern.slf4j.Slf4j;
import models.project.Project;
import models.testcase.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import steps.LoginSteps;
import steps.ProfileSteps;
import steps.ProjectsSteps;
import steps.TestCaseSteps;
import testData.StaticProvider;

@Slf4j
public class SmokeUITests extends BaseTest {

    @Test(description = "Create new project",
            dataProvider = "Create project with the help of Builder",
            dataProviderClass = StaticProvider.class)
    public void createProjectTestBoundaryValueAnalysis(Project project) {
        final int PROJECT_NAME_LENGTH = 255;

        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithCorrectCredentials()
                .openProjectsPage(false);

        ProjectsSteps projectsSteps = new ProjectsSteps(browserService);
        projectsSteps
                .openProjectsPage(false)
                .addProject(project)
                .openTestRepositoryOfPublicProject(false);

        Assert.assertEquals(
                new TestRepositoryOfPublicProjectPage(browserService, false)
                        .getTestRepositoryName()
                        .getText()
                        .length(),
                PROJECT_NAME_LENGTH);
    }

    @Test(description = "Create new project with name length more than permitted ",
            dataProvider = "Create project with the name more than 255",
            dataProviderClass = StaticProvider.class)
    public void createProjectTestLengthNameMorePermitted(String projectName, Project project) {

        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithCorrectCredentials()
                .openProjectsPage(false);

        ProjectsSteps projectsSteps = new ProjectsSteps(browserService);
        projectsSteps
                .openProjectsPage(false)
                .addProjectNoPermittedLength(project)
                .openCreateProjectPage(false);

        Assert.assertEquals(
                new CreateProjectPage(browserService, false)
                        .getErrorMessage()
                        .getText(),
                "The title may not be greater than 255 characters.");
    }

    @Test(description = "Create new Test Case",
            dataProvider = "Create a Test Case",
            dataProviderClass = StaticProvider.class)
    public void createTestCaseCreatingEntityTest(Project project, TestCase testCase) {

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

    @Test(description = "Delete an existing Test Case",
            dataProvider = "Delete a Test Case",
            dataProviderClass = StaticProvider.class,
            dependsOnMethods = "createTestCaseCreatingEntityTest")
    public void deleteTestCaseDeletingEntityTest(Project project, TestCase testCase) {

        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithCorrectCredentials()
                .openProjectsPage(false);

        TestCaseSteps deleteTestCaseSteps = new TestCaseSteps(browserService);
        deleteTestCaseSteps
                .openProjectsPage(project, false)
                .openTestRepositoryOfPublicProjectPage(false)
                .deleteTestCase(testCase)
                .openTestRepositoryOfPublicProjectPage(false);

        Assert.assertEquals(new TestRepositoryOfPublicProjectPage(browserService, false)
                        .getNoResultsTextMessage()
                        .getText(),
                "Cases matching your criteria are not found.");
    }

    @Test(description = "Login with incorrect credential", dataProvider = "use incorrect credential", dataProviderClass = StaticProvider.class)
    public void loginWithIncorrectCredentialTest(String email, String password) {

        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithIncorrectCredentials(email, password);

        Assert.assertEquals(new LoginPage(browserService, false)
                        .getErrorMessage()
                        .getText(),
                "These credentials do not match our records.");
    }

    @Test(description = "Attachment to profile")
    public void uploadAttachmentTest1() {

        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithCorrectCredentials()
                .openProjectsPage(false);

        ProfileSteps profileSteps = new ProfileSteps(browserService);
        profileSteps
                .openProjectsPage(false)
                .openProfilePage(false);

        ProfilePage profileImage = new ProfilePage(browserService, false);
        Assert.assertEquals(
                profileImage.getProfileImage().getAttribute("src"),
                profileImage.getProfileImage().getAttribute("data-src"));

        profileSteps
                .uploadAttachment()
                .openProfilePage(false);

        ProfilePage profileImageUpdate = new ProfilePage(browserService, false);
        Assert.assertNotEquals(
                profileImageUpdate.getProfileImage().getAttribute("src"),
                profileImageUpdate.getProfileImage().getAttribute("data-src"));
    }
}
