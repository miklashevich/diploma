package pages;

import baseEntities.BasePage;
import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.Button;
import wrappers.DropDownMenu;

public class CreateTestCasePage extends BasePage {

    private static final By saveNewTestCaseButtonBy = By.id("save-case");
    private static final By titleInputBy = By.id("title");
    private static final By selectSeverityBy = By.xpath("//div[@id = 'severityGroup']//div[@class = ' css-2b097c-container']");
    private static final By selectSeverityOptionBy = By.cssSelector("[id ^= react-select-14]");
    private static final By selectPriorityBy = By.xpath("//div[@id = 'priorityGroup']//div[@class = ' css-2b097c-container']");
    private static final By selectTypeBy = By.xpath("//div[@id = 'typeGroup']//div[@class = ' css-2b097c-container']");
    private static final By selectAutomationStatusBy = By.xpath("//div[@id = 'automationStatusGroup']//div[@class = ' css-2b097c-container']");

    public CreateTestCasePage(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    protected void openPage() {

    }

    @Override
    protected boolean isPageOpened() {
        try {
            return browserService.getDriver().findElement(saveNewTestCaseButtonBy).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public WebElement getTitleInput() {
        return browserService.getDriver().findElement(titleInputBy);
    }

    public Button getSaveNewTestCaseButton() {
        return new Button(browserService.getDriver(), saveNewTestCaseButtonBy);
    }

    public WebElement getSelectSeverity() {
        return browserService.getDriver().findElement(selectSeverityBy);
    }

    public DropDownMenu getSelectSeverityOptionBy() {
        return new DropDownMenu(browserService.getDriver(), selectSeverityOptionBy);
    }

    public WebElement getSelectPriority() {
        return browserService.getDriver().findElement(selectPriorityBy);
    }

    public WebElement getSelectType() {
        return browserService.getDriver().findElement(selectTypeBy);
    }

    public WebElement getSelectAutomationStatus() {
        return browserService.getDriver().findElement(selectAutomationStatusBy);
    }
}
