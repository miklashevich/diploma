package pages;

import baseEntities.BasePage;
import core.BrowserService;
import core.ReadProperties;
import org.openqa.selenium.By;
import wrappers.Button;
import wrappers.UIElement;

public class CreateProjectPage extends BasePage {
    private static final String ENDPOINT = "/project/create";
    private static final By createProjectButtonBy = By.xpath("//button[contains(text(),'Create project')]");
    private static final By projectNameBy = By.id("inputTitle");
    private static final By projectCodeBy = By.id("inputCode");
    private static final By descriptionBy = By.id("inputDescription");
    private static final By selectTypeBy = By.name("access_type");
    private static final By privateAccessTypeBy = By.id("private-access-type");
    private static final By publicAccessTypeBy = By.id("public-access-type");

    public CreateProjectPage(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    protected void openPage() {
        browserService.getDriver().get(ReadProperties.getBaseUrl() + ENDPOINT);
    }

    @Override
    protected boolean isPageOpened() {
        try {
            return browserService.getDriver().findElement(createProjectButtonBy).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public Button createProjectButton() {
        return new Button(browserService.getWait().presenceOfElementLocated(createProjectButtonBy)); }
    public UIElement getProjectNameInputBy() {
        return   browserService.getWait().presenceOfElementLocated(projectNameBy); }
    public UIElement getProjectCodeInputBy() {
        return   browserService.getWait().presenceOfElementLocated(projectCodeBy); }

    public UIElement getDescriptionInputBy() {
        return   browserService.getWait().presenceOfElementLocated(descriptionBy); }

    public UIElement privateAccessTypeInput() {
        return   browserService.getWait().presenceOfElementLocated(privateAccessTypeBy); }
    public UIElement publicAccessTypeInput () {
        return   browserService.getWait().presenceOfElementLocated(publicAccessTypeBy); }
}
