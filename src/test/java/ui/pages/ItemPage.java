package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

public class ItemPage extends BasePage {
    private static final String URL = "/objects";

    private static final By pageHeadingLocator = By.xpath("//div[@id='headline2']/h2");
    public static final By nameLocator = By.name("name");
    public static final By submitButtonLocator = By.name("do_submit");
    public static final By successMassageLocator = By.id("okmsg");
    public static final By emptyListMassageLocator = By.id("emptylist");
    public static final By createButtonLocator = By.cssSelector(".newbtn.selenium-add-item");

    public ItemPage(WebDriver driver){
        super(driver);
    }
    public void navigate(){
        navigate();
    }
    public String pageHeading(){
        return getText(pageHeadingLocator);
    }
    public String successMassage(){
        return getText(successMassageLocator);
    }
    public String emptyListMassage(){
        return getText(emptyListMassageLocator);
    }
    public void clickCreateItemButton(){
        click(createButtonLocator,"Clicking Create Button");
    }
    public void enterName(String name){
        type(nameLocator,name);
    }
    public void save(){
        click(submitButtonLocator, "Save button");
    }
    public void createItem(String name){
        clickCreateItemButton();
        enterName(name);
        save();
    }
}
