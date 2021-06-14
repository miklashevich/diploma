package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import io.qameta.allure.Step;
import models.project.Project;
import pages.CreateProjectPage;
import pages.ProjectsPage;
import pages.TestRepositoryOfPrivateProjectPage;

public class ProjectsSteps extends BaseStep {

    private ProjectsPage projectsPage = null;

    public ProjectsSteps(BrowserService browserService) {
        super(browserService);
    }

    public ProjectsSteps openProjectsPage(boolean openByUrl) {
        projectsPage = new ProjectsPage(browserService, openByUrl);
        return this;
    }

    @Step("Create New Project with the name length equals to 255 symbols: 'project'")
    public TestRepositoryOfPrivateProjectPage addProject(Project project) {
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

        return new TestRepositoryOfPrivateProjectPage(browserService, false);
    }
}
