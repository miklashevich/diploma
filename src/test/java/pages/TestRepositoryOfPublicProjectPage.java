package pages;

import baseEntities.BasePage;
import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.Button;
import wrappers.UIElement;

import java.util.List;


public class TestRepositoryOfPublicProjectPage extends BasePage {

    private static final By createNewTestCaseButtonInSuiteBy = By.id("create-case-button");
    private static final By createNewTestCaseButtonNoSuiteBy =
            By.xpath("//*[@class = 'text-center mt-5']/a[.= 'Create new suite']");
    private static final By testRepositoryTitleBy = By.xpath("//h1[contains(text(),'Test repository')]");
    private static final By testRepositoryNameBy = By.xpath("//p[@class = 'header']");
    private static final String testCaseNameBy = "//*[@class = 'case-row-title' and contains(text(), 'remove')]";
    private static final By searchTestCaseInputBy = By.className("form-control");
    private static final By suiteCasesPreviewPanelBy = By.className("preview-header");
    private static final By deleteTestCaseButtonBy = By.xpath("//*[@class = 'preview']//*[contains(text(), 'Delete')]");
    private static final By confirmDeleteTestCaseButtonBy =
            By.xpath("//*[@class = 'modal-footer']//*[contains(text(), 'Delete')]");
    private static final By noResultsTextMessageBy = By.xpath("//*[@class = 'mt-3']//child :: span");
    private static final By validationAlertMessage =
            By.xpath("//*[@class = 'flash-message']//*[@class='alert-message']");

    public TestRepositoryOfPublicProjectPage(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    protected void openPage() {

    }

    @Override
    public boolean isPageOpened() {
        try {
            return browserService.getDriver().findElement(testRepositoryTitleBy).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public Button getCreateNewTestCaseButton() {
        return new Button(browserService.getDriver(), createNewTestCaseButtonInSuiteBy);
    }

    public Button getCreateNewTestCaseButtonNoSuiteBy() {
        return new Button(browserService.getDriver(), createNewTestCaseButtonNoSuiteBy);
    }

    public Button getDeleteTestCaseButton() {
        return new Button(browserService.getDriver(), deleteTestCaseButtonBy);
    }

    public By getDeleteTestCaseButtonBy() {
        return deleteTestCaseButtonBy;
    }

    public Button getConfirmDeleteTestCaseButton() {
        return new Button(browserService.getDriver(), confirmDeleteTestCaseButtonBy);
    }

    public By getConfirmDeleteTestCaseButtonBy() {
        return confirmDeleteTestCaseButtonBy;
    }

    public WebElement getNoResultsTextMessage() {
        return browserService.getWait().waitForVisibility(noResultsTextMessageBy);
    }

    public WebElement getSearchTestCaseInput() {
        return browserService.getWait().waitForVisibility(searchTestCaseInputBy);
    }

    public WebElement getSuiteCasesPreviewDiv() {
        return browserService.getWait().waitForVisibility(suiteCasesPreviewPanelBy);
    }

    public WebElement getTestRepositoryTitle() {
        return browserService.getWait().waitForVisibility(testRepositoryTitleBy);
    }

    public WebElement getTestRepositoryName() {
        return browserService.getWait().waitForVisibility(testRepositoryNameBy);
    }

    public WebElement getTestCaseName(String testCaseName) {
        return browserService
                .getDriver()
                .findElement(By.xpath(testCaseNameBy.replace("remove", testCaseName)));
    }

    public By getTestCaseNameBy(String testCaseName) {
        return By.xpath(testCaseNameBy.replace("remove", testCaseName));
    }

    public List<WebElement> getAllTestCasesByName(String testCaseName) {
        return browserService.getDriver().findElements(By.xpath(testCaseNameBy.replace("remove", testCaseName)));
    }

    public WebElement getValidationAlertMessage() {
        return browserService.getDriver().findElement(validationAlertMessage);
    }
}