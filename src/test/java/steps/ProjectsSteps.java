package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import io.qameta.allure.Step;
import models.project.Project;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.CreateProjectPage;
import pages.ProjectsPage;
import pages.TestRepositoryOfPublicProjectPage;

public class ProjectsSteps extends BaseStep {

    private ProjectsPage projectsPage = null;
    private Actions actions = new Actions(browserService.getDriver());

    public ProjectsSteps(BrowserService browserService) {
        super(browserService);
    }

    public ProjectsSteps openProjectsPage(boolean openByUrl) {
        projectsPage = new ProjectsPage(browserService, openByUrl);
        return this;
    }

    public ProjectsSteps openCreateProjectPage(boolean openByUrl) {
        new CreateProjectPage(browserService, openByUrl);
        return this;
    }

    public ProjectsSteps openTestRepositoryOfPublicProject(boolean openByUrl) {

        new TestRepositoryOfPublicProjectPage(browserService, false);
        return this;
    }

    @Step("Create New Project with the name length equals to 255 symbols: 'project'")
    public ProjectsSteps addProject(Project project) {

        projectsPage.getCreateNewProjectButton().click();

        CreateProjectPage createProjectPage = new CreateProjectPage(browserService, false);
        createProjectPage.getProjectNameInputBy().sendKeys(project.getTitle());
        createProjectPage.getProjectCodeInputBy().click();
        actions
                .keyDown(Keys.CONTROL)
                .sendKeys("A")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();
        createProjectPage.getProjectCodeInputBy().sendKeys(project.getCode());
        createProjectPage.getDescriptionInputBy().sendKeys(project.getDescription());
        switch (project.getAccess()) {
            case ALL:
                createProjectPage.getPublicAccessTypeInput().click();
                break;
            case GROUP:
                createProjectPage.getPrivateAccessTypeInput().click();
                break;
            default:
                break;
        }
        createProjectPage.createProjectButton().click();

        return this;
    }

    @Step("Create New Project with the name length more than 255 symbols: 'project'")
    public ProjectsSteps addProjectNoPermittedLength(Project project) {

        projectsPage.getCreateNewProjectButton().click();

        CreateProjectPage createProjectPage = new CreateProjectPage(browserService, false);

        createProjectPage.getProjectNameInputBy().sendKeys(project.getTitle());
        createProjectPage.getProjectCodeInputBy().sendKeys(project.getCode());
        createProjectPage.getDescriptionInputBy().sendKeys(project.getDescription());
        switch (project.getAccess()) {
            case ALL:
                createProjectPage.getPublicAccessTypeInput().click();
                break;
            case GROUP:
                createProjectPage.getPrivateAccessTypeInput().click();
                break;
            default:
                break;
        }
        createProjectPage.createProjectButton().click();

        return this;
    }

    public ProjectsSteps addProjectBugReproduce(Project project) {

        projectsPage.getCreateNewProjectButton().click();

        CreateProjectPage createProjectPage = new CreateProjectPage(browserService, false);
        createProjectPage.getProjectNameInputBy().sendKeys(project.getTitle());
        createProjectPage.getProjectCodeInputBy().click();

        createProjectPage.getProjectCodeInputBy().sendKeys(project.getCode());
        createProjectPage.getDescriptionInputBy().sendKeys(project.getDescription());
        switch (project.getAccess()) {
            case ALL:
                createProjectPage.getPublicAccessTypeInput().click();
                break;
            case GROUP:
                createProjectPage.getPrivateAccessTypeInput().click();
                break;
            default:
                break;
        }
        createProjectPage.createProjectButton().click();

        return this;

    }
}