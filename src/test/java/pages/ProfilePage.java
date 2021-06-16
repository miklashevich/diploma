package pages;

import baseEntities.BasePage;
import core.BrowserService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.Button;

public class ProfilePage extends BasePage {

    private static final String ENDPOINT = "/user/profile";
    private static final By updateSettingsButtonBy = By.xpath("//button[contains(text(),'Update settings')]");
    private static final By updateButtonBy = By.id("fileupload");
    private final By pictureInputBy = By.xpath("//input[@id='fileupload']");


    public ProfilePage(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browserService.getDriver().get(ReadProperties.getBaseUrl() + ENDPOINT);
    }

    @Override
    protected boolean isPageOpened() {
        try {
            return browserService.getDriver().findElement(updateSettingsButtonBy).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public Button getUpdateButton() {
        return new Button(browserService.getDriver(), updateButtonBy);
    }

    public Button getUpdateSettingsButton() {
        return new Button(browserService.getDriver(), updateSettingsButtonBy);
    }

    public WebElement getPictureInput() {
        return browserService.getDriver().findElement(pictureInputBy);
    }

}