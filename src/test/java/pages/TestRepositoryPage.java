package pages;

import baseEntities.BasePage;
import core.BrowserService;
import core.ReadProperties;
import org.openqa.selenium.By;
import wrappers.Button;
import wrappers.UIElement;


public class TestRepositoryPage extends BasePage {
    //private static final String ENDPOINT = "";
    private static final By createNewTestCaseButtonBy = By.xpath("//a[contains(text(),'Create new case')]");
    private static final By testRepositoryBy =By.xpath("//h1[contains(text(),'Test repository')]");

    public TestRepositoryPage(BrowserService browserService, boolean openPageByUrl) {
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

    public Button createNewTestCaseButton() {
        return new Button(browserService.getWait().presenceOfElementLocated(createNewTestCaseButtonBy)); }
    public UIElement testRepository() {
        return   browserService.getWait().presenceOfElementLocated(testRepositoryBy); }

}