package pages;

import baseEntities.BasePage;
import core.BrowserService;
import core.ReadProperties;
import org.openqa.selenium.By;
import wrappers.Button;

public class ProjectsPage extends BasePage {

    private static final String ENDPOINT = "/projects";
    private static final By createNewProjectButtonBy = By.id("createButton");

    public ProjectsPage(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browserService.getDriver().get(ReadProperties.getBaseUrl() + ENDPOINT);
    }

    @Override
    protected boolean isPageOpened() {
        try{
            return browserService.getDriver().findElement(createNewProjectButtonBy).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public Button getCreateNewProjectButton() {
        return new Button(browserService.getDriver(), createNewProjectButtonBy);
    }
}
