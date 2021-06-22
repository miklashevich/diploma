package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import core.ReadProperties;
import pages.LoginPage;

public class LoginSteps extends BaseStep {

    private LoginPage loginPage;

    public LoginSteps(BrowserService browserService) {
        super(browserService);
    }

    public LoginSteps openLoginPage() {
        loginPage = new LoginPage(browserService, true);
        return this;
    }

    public LoginPage loginWithIncorrectCredentials(String email, String password) {
        loginPage.getEmailInput().sendKeys(email);
        loginPage.getPasswordInput().sendKeys(password);
        loginPage.getLoginButton().click();

        return new LoginPage(browserService, false);
    }

    public ProjectsSteps loginWithCorrectCredentials() {
        loginPage.getEmailInput().sendKeys(ReadProperties.getUsername());
        loginPage.getPasswordInput().sendKeys(ReadProperties.getPassword());
        loginPage.getLoginButton().click();

        return new ProjectsSteps(browserService);
    }
}
