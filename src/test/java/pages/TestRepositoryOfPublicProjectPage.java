package pages;

import baseEntities.BasePage;
import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.Button;


public class TestRepositoryOfPublicProjectPage extends BasePage {

    private static final By createNewTestCaseButtonBy = By.id("create-case-button");
    private static final By testRepositoryTitleBy = By.xpath("//h1[contains(text(),'Test repository')]");
    private static final By testRepositoryNameBy = By.xpath("//p[@class = 'header']");
    private static final String testCaseNameBy = "//*[@class = 'case-row-title' and contains(text(), 'remove')]";

    public TestRepositoryOfPublicProjectPage(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    protected void openPage() {

    }

    @Override
    public boolean isPageOpened() {
        try {
            return browserService.getDriver().findElement(createNewTestCaseButtonBy).isDisplayed();
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

    public WebElement getTestCaseName(String testCaseName) {
        return browserService.getDriver().findElement(By.xpath(testCaseNameBy.replace("remove", testCaseName)));
    }
}