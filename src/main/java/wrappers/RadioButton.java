package wrappers;

import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RadioButton {

    private UIElement uiElement;
    private List<UIElement> optionsList = new ArrayList<>();
    private List<WebElement> optionsTextList;
    private WebDriver webDriver;
    private BrowserService browserService;

    public RadioButton(UIElement uiElement, By by) {
        this.uiElement = uiElement;
        List<WebElement> options = webDriver.findElements(by);
        optionsTextList = getAllOptions(options);
    }

    private List<WebElement> getAllOptions(List<WebElement> radioList) {
        List<WebElement> resultList = new ArrayList<>();
        for(WebElement webElement: radioList) {
            UIElement uiElement = new UIElement(browserService, webElement);
            optionsList.add(uiElement);
            resultList.add(uiElement.getParent(browserService).findElement(By.className("form-check")));
        }
        return resultList;
    }

    public void selectByOption(String optionName) {
        int index = optionsTextList.indexOf(optionName);
        optionsList.get(index).click();
    }
}
