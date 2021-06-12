package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import pages.HeaderPage;

public abstract class HeaderSteps extends BaseStep {
    private HeaderPage headerPage = null;

    public HeaderSteps(BrowserService browserService) {
        super(browserService);
    }

    public void openAdministration() {

    }
}
