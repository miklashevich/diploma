package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.Wait;

@Slf4j
public class BrowserService {

    private WebDriver driver;
    private DriverManagerType driverManagerType;
    private Wait wait;

    public BrowserService() {
        switch (ReadProperties.getBrowser().toLowerCase()) {
            case "chrome":
                driverManagerType = DriverManagerType.CHROME;
                WebDriverManager.getInstance(driverManagerType).setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--start-maximized");

                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                break;
            default:
                log.error("Wrong browser type selected");
                break;
        }

        this.wait = new Wait(this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Wait getWait() {
        return wait;
    }
}
