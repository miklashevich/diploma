package tests.uiTests;

import baseEntities.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import steps.LoginSteps;

@Slf4j
public class SmokeTest extends BaseTest {

    @Test
    public void firstTest() {
        LoginSteps loginSteps = new LoginSteps(browserService);
        loginSteps
                .openLoginPage()
                .loginWithCorrectCredentials()
                .openProjectsPage(false);
    }
}
