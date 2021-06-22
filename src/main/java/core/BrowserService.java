package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.Waits;

@Slf4j
public class BrowserService {

    private WebDriver driver;
    private DriverManagerType driverManagerType;
    private Waits waits;

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
                driverManagerType = DriverManagerType.FIREFOX;
                WebDriverManager.getInstance(driverManagerType).setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-gpu");
                firefoxOptions.addArguments("--start-maximized");

                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                log.error("Wrong browser type selected");
                break;
        }

        this.waits = new Waits(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Waits getWait() {
        return waits;
    }
}
