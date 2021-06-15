package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import pages.ProjectsPage;
import pages.TestRepositoryOfPublicProjectPage;

public class TestRepositoryOfPublicProjectSteps extends BaseStep {

    private TestRepositoryOfPublicProjectPage testRepo = null;

    public TestRepositoryOfPublicProjectSteps openTestRepositoryOfPublicProjectPage(boolean openByUrl) {
        testRepo = new TestRepositoryOfPublicProjectPage(browserService, openByUrl);
        return this;
    }

    public TestRepositoryOfPublicProjectSteps(BrowserService browserService) {
        super(browserService);
    }
}
