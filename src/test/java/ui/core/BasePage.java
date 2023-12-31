package ui.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    private WebDriver driver;
    protected static final String BASE_URI = System.getProperty("baseUri", "https://donika-eood.inv.bg");
    public BasePage (WebDriver driver){
        this.driver = driver;
    }
    protected void click(By by){
        System.out.println("Clicking:");
        driver.findElement(by).click();
    }
    protected void click(By by, String desc){
        System.out.println("Clicking " + desc);
        driver.findElement(by).click();
    }
    protected void type (By by, String text){
        System.out.println("Typing:" + text);
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }
    protected void navigate (String url){
        System.out.println("Navigated to:" + BASE_URI + url);
        driver.get(BASE_URI + url);
    }
    protected String getText(By by){
        String text = driver.findElement(by).getText().strip();
        System.out.println("Text found:" + text);
        return text;
    }

}
