package pages;

import baseEntities.BasePage;
import core.BrowserService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrappers.Button;
import wrappers.UIElement;


public class TestRepositoryOfPrivateProjectPage extends BasePage {

    private static final By createNewTestCaseButtonBy = By.xpath("//a[contains(text(),'Create new case')]");
    private static final By testRepositoryTitleBy = By.xpath("//h1[contains(text(),'Test repository')]");
    private static final By testRepositoryNameBy = By.xpath("//p[@class = 'header']");

    public TestRepositoryOfPrivateProjectPage(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    protected void openPage() {

    }

    @Override
    public boolean isPageOpened() {
        try {
            return browserService.getDriver().findElement(createNewTestCaseButtonBy).isEnabled();
        } catch (Exception ex) {
            return false;
        }
    }

    public Button getCreateNewTestCaseButton() {
        return new Button(browserService.getDriver(), createNewTestCaseButtonBy);
    }

    public WebElement getTestRepositoryTitle() {
        return browserService.getWait().waitForVisibility(testRepositoryTitleBy);
    }

    public WebElement getTestRepositoryName() {
        return browserService.getWait().waitForVisibility(testRepositoryNameBy);
    }
}