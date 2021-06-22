package pages;

import baseEntities.BasePage;
import core.BrowserService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.Button;

public class ProjectsPage extends BasePage {

    private static final String ENDPOINT = "/projects";
    private static final By createNewProjectButtonBy = By.id("createButton");
    private static final String projectNameTitleBy = "//a[. = 'remove']";
    private static final By menuButtonBy = By.id("user-menu");
    private static final By profileButtonBy =
            By.xpath("//*[@class = 'user-menu-section']/child :: *[contains(concat(' ', a, ' '), ' Profile ')]");

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
            return browserService.getDriver().findElement(createNewProjectButtonBy).isEnabled();
        } catch (Exception ex) {
            return false;
        }
    }

    public Button getCreateNewProjectButton() {
        return new Button(browserService.getDriver(), createNewProjectButtonBy);
    }

    public WebElement getProjectNameTitle(String projectName) {
        return browserService.getWait().waitForVisibility(By.xpath(projectNameTitleBy.replace("remove", projectName)));
    }

    public Button getMenuButton() {
        return new Button(browserService.getDriver(), menuButtonBy);
    }

    public Button getProfileButton() {
        return new Button(browserService.getDriver(), profileButtonBy);
    }
}
