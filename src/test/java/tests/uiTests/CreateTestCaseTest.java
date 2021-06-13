package tests.uiTests;


import baseEntities.BaseTest;
import enums.ProjectType;
import lombok.extern.slf4j.Slf4j;
import models.project.ProjectLombokBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProjectsPage;
import pages.TestRepositoryPage;
import steps.LoginSteps;
import steps.ProjectsSteps;

@Slf4j
public class CreateTestCaseTest extends BaseTest {

    @Test
    public void createProjectTest() {
        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithCorrectCredentials()
                .openProjectsPage(false);

        ProjectsPage projectsPage = new ProjectsPage(browserService, false);
        projectsPage.createNewProjectButton().click();

        ProjectLombokBuilder projectLombokBuilder = ProjectLombokBuilder.builder()
                .name("Mik Project")
                .code("Amwewe")
                .description("test description")
                .type(ProjectType.PUBLIC)
                .build();
        ProjectsSteps projectsSteps = new ProjectsSteps(browserService);
        projectsSteps.addProject(projectLombokBuilder);

        TestRepositoryPage testRepositoryPage = new TestRepositoryPage(browserService, false);
        testRepositoryPage.isPageOpened();

        Assert.assertTrue(testRepositoryPage.testRepository().isDisplayed());



    }
}
