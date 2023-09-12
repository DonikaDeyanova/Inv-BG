package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@Tag("calculator")
public class DummyTest {
    @Test
    @Disabled("This is blocked because of https://pragmaticbg.atlassian.net/INV-12313")
    @Tag("positive")
    @Tag("calculator")
    @Tag("ui")
    @DisplayName("Can sum positive numbers")
    public void canSumNumbers(){
        Assertions.assertEquals(4, 2+2);
    }
    @Test
    @DisplayName("Can navigate to Login page")
    public void canNavigateToLoginPage(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://st2016.inv.bg");
        WebElement heading = driver.findElement(By.tagName("h1"));
        Assertions.assertEquals("Вход в inv.bg", heading.getText());
        driver.quit();
    }
}
