package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import pages.ProjectsPage;

public class ProjectsSteps extends BaseStep {

    private ProjectsPage projectsPage = null;

    public ProjectsSteps(BrowserService browserService) {
        super(browserService);
    }

    public ProjectsSteps openProjectsPage(boolean openByUrl) {
        projectsPage = new ProjectsPage(browserService, openByUrl);
        return this;
    }
}
