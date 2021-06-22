package pages;

import baseEntities.BasePage;
import core.BrowserService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.Button;
import wrappers.RadioButton;
import wrappers.UIElement;

public class CreateProjectPage extends BasePage {
    private static final String ENDPOINT = "/project/create";
    private static final By createProjectButtonBy = By.xpath("//button[contains(text(),'Create project')]");
    private static final By projectNameBy = By.id("inputTitle");
    private static final By projectCodeBy = By.id("inputCode");
    private static final By descriptionBy = By.id("inputDescription");
    private static final By privateAccessTypeBy = By.id("private-access-type");
    private static final By publicAccessTypeBy = By.id("public-access-type");
    private static final By errorMessageBy = By.xpath("//div[contains(text(),'The title may not be greater than 255 characters.')]");

    public CreateProjectPage(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browserService.getDriver().get(ReadProperties.getBaseUrl() + ENDPOINT);
    }

    @Override
    protected boolean isPageOpened() {
        try {
            return browserService.getDriver().findElement(createProjectButtonBy).isEnabled();
        } catch (Exception ex) {
            return false;
        }
    }

    public Button createProjectButton() {
        return new Button(browserService.getDriver(), createProjectButtonBy);
    }

    public WebElement getProjectNameInputBy() {
        return browserService.getWait().waitForVisibility(projectNameBy);
    }

    public WebElement getProjectCodeInputBy() {
        return browserService.getWait().waitForVisibility(projectCodeBy);
    }

    public WebElement getDescriptionInputBy() {
        return browserService.getWait().waitForVisibility(descriptionBy);
    }

    public RadioButton getPrivateAccessTypeInput() {
        return new RadioButton(browserService.getDriver(), privateAccessTypeBy);
    }

    public WebElement getPublicAccessTypeInput() {
        return browserService.getWait().waitForVisibility(publicAccessTypeBy);

    }
        public WebElement getErrorMessage () {
            return browserService.getWait().waitForVisibility(errorMessageBy);
        }
    }

