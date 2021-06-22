package pages;

import baseEntities.BasePage;
import baseEntities.BaseTest;
import core.BrowserService;
import core.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wrappers.Button;
import wrappers.UIElement;

public class LoginPage extends BasePage {

    private static final String ENDPOINT = "/login";
    private static final By loginButtonBy = By.id("btnLogin");
    private static final By emailInputBy = By.id("inputEmail");
    private static final By passwordInputBy = By.id("inputPassword");
    private static final By errorMessageBy = By.xpath("//div[contains(text(),'These credentials do not match our records.')]");

    public LoginPage(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browserService.getDriver().get(ReadProperties.getBaseUrl() + ENDPOINT);
    }

    @Override
    protected boolean isPageOpened() {
        try {
            return browserService.getDriver().findElement(loginButtonBy).isEnabled();
        } catch (Exception ex) {
            return false;
        }
    }

    public WebElement getEmailInput() {
        return browserService.getWait().waitForVisibility(emailInputBy);
    }

    public WebElement getPasswordInput() {
        return browserService.getWait().waitForVisibility(passwordInputBy);
    }

    public Button getLoginButton() {
        return new Button(browserService.getDriver(), loginButtonBy);
    }

    public WebElement getErrorMessage() {
        return browserService.getWait().waitForVisibility(errorMessageBy);
    }
}
