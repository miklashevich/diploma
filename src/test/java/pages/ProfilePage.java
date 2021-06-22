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
    private static final By updateButtonBy = By.id("update-logo");
    private static final By pictureInputBy = By.xpath("//input[@id='fileupload']");
    private static final By profileImageBy = By.id("project-image");
    private static final By alertMessageBy = By.xpath("//*[text()=' Profile data was successfully updated.']");
    private static final By positionInputTitleBy = By.id("inputRole");


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
            return browserService.getDriver().findElement(updateSettingsButtonBy).isEnabled();
        } catch (Exception ex) {
            return false;
        }
    }

    public Button getUpdateButton() {
        return new Button(browserService.getDriver(), updateButtonBy);
    }

    public By getUpdateButtonBy() {
        return updateButtonBy;
    }

    public Button getUpdateSettingsButton() {
        return new Button(browserService.getDriver(), updateSettingsButtonBy);
    }

    public WebElement getPictureInput() {
        return browserService.getDriver().findElement(pictureInputBy);
    }

    public WebElement getProfileImage() {
        return browserService.getWait().waitForVisibility(profileImageBy);
    }
    public WebElement getAlertMessage() {
        return browserService.getWait().waitForVisibility(alertMessageBy);
    }
    public WebElement getPositionInputTitle() {
        return browserService.getDriver().findElement(positionInputTitleBy);
    }
}