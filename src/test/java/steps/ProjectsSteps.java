package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import models.project.ProjectLombokBuilder;
import pages.CreateProjectPage;
import pages.ProjectsPage;
import pages.TestRepositoryPage;

public class ProjectsSteps extends BaseStep {

    private ProjectsPage projectsPage = null;

    public ProjectsSteps(BrowserService browserService) {
        super(browserService);
    }

    public ProjectsSteps openProjectsPage(boolean openByUrl) {
        projectsPage = new ProjectsPage(browserService, openByUrl);
        return this;
    }

    public TestRepositoryPage addProject(ProjectLombokBuilder projectLombokBuilder) {
        CreateProjectPage createProjectPage = new CreateProjectPage(browserService, false);
        createProjectPage.getProjectNameInputBy().sendKeys(projectLombokBuilder.getName());
        createProjectPage.getProjectCodeInputBy().sendKeys((projectLombokBuilder.getCode()));
        createProjectPage.getDescriptionInputBy().sendKeys(projectLombokBuilder.getDescription());

        if(projectLombokBuilder.getType().toString().equals("PRIVATE")) createProjectPage.privateAccessTypeInput().click();
        if(projectLombokBuilder.getType().toString().equals("PUBLIC")) createProjectPage.publicAccessTypeInput().click();
        createProjectPage.createProjectButton().click();

        return new TestRepositoryPage(browserService, false);

    }

}
