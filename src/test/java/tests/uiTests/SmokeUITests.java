package tests.uiTests;

import baseEntities.BaseTest;
import core.Retry;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import models.project.Project;
import models.testcase.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import steps.*;
import testData.staticProviders.LoginProvider;
import testData.staticProviders.ProjectProvider;
import testData.staticProviders.TestCaseProvider;

import java.util.concurrent.TimeoutException;

@Slf4j
public class SmokeUITests extends BaseTest {

    @Feature("Feature1")
    @Story("Story1")
    @Flaky
    @Owner("Dzmitry Rudak")
    @TmsLink("16")
    @Link(name = "Text Link",
            url = "https://thumbs.dreamstime.com/z/funny-cartoon-bug-vector-illustration-cute-beetle-50577038.jpg")
    @Issue("QO-88")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Create new project",
            dataProvider = "Create project with the help of Builder",
            dataProviderClass = ProjectProvider.class)
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

    @Feature("Feature1")
    @Story("Story2")
    @Flaky
    @Owner("Andrei Miklashevich")
    @TmsLink("16")
    @Issue("QO-88")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Create new project with name length more than permitted ",
            dataProvider = "Create project with the name more than 255",
            dataProviderClass = ProjectProvider.class)
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

    @Feature("Feature2")
    @Story("Story3")
    @Flaky
    @Owner("Dzmitry Rudak")
    @TmsLink("16")
    @Link(name = "Text Link",
            url = "https://thumbs.dreamstime.com/z/funny-cartoon-bug-vector-illustration-cute-beetle-50577038.jpg")
    @Issue("QO-88")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Create new Test Case",
            dataProvider = "Create a Test Case",
            dataProviderClass = TestCaseProvider.class)
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

        TestRepositoryOfPublicProjectPage testCaseCreatedValidation =
                new TestRepositoryOfPublicProjectPage(browserService, false);

        Assert.assertEquals(
                testCaseCreatedValidation
                        .getTestCaseName(testCase.getTitle())
                        .getText(),
                testCase.getTitle());

        Assert.assertEquals(
                testCaseCreatedValidation
                        .getValidationAlertMessage()
                        .getText(),
                "Test case was created successfully!"
        );
    }

    @Feature("Feature2")
    @Story("Story4")
    @Flaky
    @Owner("Dzmitry Rudak")
    @TmsLink("16")
    @Issue("QO-88")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Delete an existing Test Case",
            dataProvider = "Delete a Test Case",
            dataProviderClass = TestCaseProvider.class,
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

        Assert.assertFalse(
                new TestRepositoryOfPublicProjectPage(browserService, false)
                .getAllTestCasesByName(testCase.getTitle()).size() > 0);
    }

    @Feature("Feature3")
    @Story("Story5")
    @Flaky
    @Owner("Andrei Miklashevich")
    @TmsLink("16")
    @Link(name = "Text Link",
            url = "https://thumbs.dreamstime.com/z/funny-cartoon-bug-vector-illustration-cute-beetle-50577038.jpg")
    @Issue("QO-88")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login with incorrect credential",
            dataProvider = "Use Incorrect Credentials",
            dataProviderClass = LoginProvider.class)
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

    @Feature("Feature4")
    @Story("Story6")
    @Flaky
    @Owner("Dzmitry Rudak")
    @TmsLink("16")
    @Issue("QO-88")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Attachment to profile", retryAnalyzer = Retry.class)
    public void uploadAttachmentTest1() {

        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithCorrectCredentials()
                .openProjectsPage(false);

        ProfileSteps profileSteps = new ProfileSteps(browserService);
        profileSteps
                .openProjectsPage(false)
                .openProfilePage(false)
                .uploadAttachment()
                .openProfilePage(false);

        ProfilePage profileImageUpdate = new ProfilePage(browserService, false);
        Assert.assertNotEquals(
                profileImageUpdate.getProfileImage().getAttribute("src"),
                profileImageUpdate.getProfileImage().getAttribute("data-src"));

    }

    @Feature("Feature4")
    @Story("Story7")
    @Flaky
    @Owner("Andrei Miklashevich")
    @TmsLink("16")
    @Link(name = "Text Link", url = "https://thumbs.dreamstime.com/z/funny-cartoon-bug-vector-illustration-cute-beetle-50577038.jpg")
    @Issue("QO-88")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Validation of a Pop-up Message Test")
    public void popUpTest() {

        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithCorrectCredentials()
                .openProjectsPage(false);

        ProfileSteps profileSteps = new ProfileSteps(browserService);
        profileSteps
                .openProjectsPage(false)
                .openProfilePage(false)
                .updateProfileTitle("Qa1")
                .openProfilePage(false);

        ProfilePage profilePage = new ProfilePage(browserService, false);

        Assert.assertEquals(profilePage
                .getAlertMessage()
                .getText(), "Profile data was successfully updated.");
    }

    @Feature("Feature1")
    @Story("Story8")
    @Flaky
    @Owner("Andrei Miklashevich")
    @TmsLink("16")
    @Link(name = "Text Link", url = "https://thumbs.dreamstime.com/z/funny-cartoon-bug-vector-illustration-cute-beetle-50577038.jpg")
    @Issue("QO-88")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Failing Scenario Test",
            dataProvider = "Create project with the help of Builder",
            dataProviderClass = ProjectProvider.class,
            expectedExceptions = TimeoutException.class)
    public void bugReproduceTest(Project project) {

        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithCorrectCredentials()
                .openProjectsPage(false);

        ProjectsSteps projectsSteps = new ProjectsSteps(browserService);
        projectsSteps
                .openProjectsPage(false)
                .addProjectBugReproduce(project)
                .openTestRepositoryOfPublicProject(false);

        TestRepositoryOfPublicProjectSteps publicProjectSteps
                = new TestRepositoryOfPublicProjectSteps(browserService);
        publicProjectSteps.setSetting();

        ProjectSettingPage projectSettingPage = new ProjectSettingPage(browserService, false);

        Assert.assertEquals(projectSettingPage
                .getAlertMessage()
                .getText(), "Project settings were successfully updated!");
    }
}
