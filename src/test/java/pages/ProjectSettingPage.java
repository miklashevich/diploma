package pages;

import baseEntities.BasePage;
import core.BrowserService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.Button;

public class ProjectSettingPage extends BasePage {

    private static final By projectCodeInputBy = By.id("inputCode");
    private static final By updateSettingButtonBy = By.xpath("//button[contains(text(),'Update settings')]");
    private static final By alertMessageBy = By.xpath("//span[contains(text(),' Project settings were successfully updated!')]");


    public ProjectSettingPage(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    protected void openPage() {
    }

    @Override
    protected boolean isPageOpened() {
        try {
            return browserService.getDriver().findElement(updateSettingButtonBy).isEnabled();
        } catch (Exception ex) {
            return false;
        }
    }

    public Button getUpdateSettingButton() {
        return new Button(browserService.getDriver(), updateSettingButtonBy);
    }

    public WebElement getProjectCodeInput() {
        return browserService.getDriver().findElement(projectCodeInputBy);
    }
    public WebElement getAlertMessage() {
        return browserService.getWait().waitForVisibility(alertMessageBy);
    }
}
