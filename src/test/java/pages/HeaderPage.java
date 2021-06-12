package pages;

import baseEntities.BasePage;
import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class HeaderPage extends BasePage {

    private By projectsBy = By.xpath("//a[contains(concat(' ', span, ' '), 'Projects')]");

    public HeaderPage(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }


    public WebElement getProjects() {
        return browserService.getDriver().findElement(projectsBy);
    }


}
