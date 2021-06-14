package tests.uiTests;

import baseEntities.BaseTest;
import lombok.extern.slf4j.Slf4j;
import models.project.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProjectsPage;
import pages.TestRepositoryPage;
import steps.LoginSteps;
import steps.ProjectsSteps;
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
        TestRepositoryPage testRepositoryPage = projectsSteps.addProject(project);

        Assert.assertEquals(testRepositoryPage.getTestRepositoryName().getText().length(), PROJECT_NAME_LENGTH);
    }
}
