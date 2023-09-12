package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.pages.HomePage;
import ui.pages.LoginPage;

import java.time.Duration;
@Tag("login")

public class LoginPageTest {
    private WebDriver driver;
    private static final String BASE_URL = "https://donika-eood.inv.bg";
    private static final long WAIT = 5;

    @BeforeEach
    public void BeforeEachTest(TestInfo testInfo){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT));
        driver.get(BASE_URL);
        driver.manage().window().minimize();
        System.out.println("Test: " + testInfo.getDisplayName());
    }
    @AfterEach
    public void afterEachTest(){
        if(driver != null){
            driver.quit();
        }
    }
    @Test
    @Tag("positive")
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials(){
        WebElement heading = driver.findElement(By.xpath("//div[@id='wellcome']/h2"));
        Assertions.assertEquals("Donika EOOD",heading.getText());
        System.out.println("Enter username:" + "donika.minkova@abv.bg");
        WebElement emailFeald = driver.findElement(By.id("loginusername"));
        emailFeald.sendKeys("donika.minkova@abv.bg");
        System.out.println("Enter userpassword:" + "116856");
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys("116856");
        System.out.println("Click login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        WebElement userPanel = driver.findElement(By.cssSelector(".selenium-button-userpanel"));
        Assertions.assertEquals("donika.minkova@abv.bg", userPanel.getText());
    }
    @Test
    @Tag("negative")
    @DisplayName("Cant login with invalid credentials")
    public void cantLoginWithInvalidCredentials(){
        WebElement heading = driver.findElement(By.xpath("//div[@id='wellcome']/h2"));
        Assertions.assertEquals("Donika EOOD",heading.getText());
        System.out.println("Enter username:" + "donika.minkova@abv.bg");
        WebElement emailFeald = driver.findElement(By.id("loginusername"));
        emailFeald.sendKeys("donika.minkova@abv.bg");
        System.out.println("Enter userpassword:" + "1111112342");
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys("1111112342");
        System.out.println("Click login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        WebElement error = driver.findElement(By.id("error"));
        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", error.getText().trim());
    }
    @Test
    @Tag("negative")
    @DisplayName("Cant login with blank credentials")
    public void cantLoginWithBlankCredentials(){
        WebElement heading = driver.findElement(By.xpath("//div[@id='wellcome']/h2"));
        Assertions.assertEquals("Donika EOOD",heading.getText());
        System.out.println("Click login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        WebElement error = driver.findElement(By.id("error"));
        Assertions.assertEquals("Моля, попълнете вашия email", error.getText().trim());
    }
    @Test
    @Tag("positive")
    @DisplayName("Can login with valid credentials and logout")
    public void canLoginAndLogout(){
        WebElement heading = driver.findElement(By.xpath("//div[@id='wellcome']/h2"));
        Assertions.assertEquals("Donika EOOD",heading.getText());
        System.out.println("Enter username:" + "donika.minkova@abv.bg");
        WebElement emailFeald = driver.findElement(By.id("loginusername"));
        emailFeald.sendKeys("donika.minkova@abv.bg");
        System.out.println("Enter userpassword:" + "116856");
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys("116856");
        System.out.println("Click login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        WebElement userPanel = driver.findElement(By.cssSelector(".selenium-button-userpanel"));
        Assertions.assertEquals("donika.minkova@abv.bg", userPanel.getText());
        System.out.println("Click Logout");
        userPanel.click();
        WebElement logoutLink = driver.findElement(By.cssSelector(".selenium-button-logout"));
        logoutLink.click();
        WebElement successMassage = driver.findElement(By.id("okmsg"));
        Assertions.assertEquals("Вие излязохте от акаунта си.", successMassage.getText().trim());
        WebElement loginHeading = driver.findElement(By.xpath("//h1"));
        Assertions.assertEquals("Вход в inv.bg", loginHeading.getText());
    }
    @Test
    @Disabled
    @DisplayName("Can reset password")
    public void canResetPassword(){
        WebElement forgottenPassLink = driver.findElement(By.id("newpass2"));
        forgottenPassLink.click();
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("donika.minkova@abv.bg");
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='alert selenium-message alert-success sticky']"));
        Assertions.assertEquals("На e-mail адреса Ви беше изпратен линк, чрез който можете да смените паролата си.", successMessage.getText());
    }
    @Test
    @DisplayName("Can login using POM")
    public void canLoginWithValidCredentialsPOM(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login("donika.minkova@abv.bg", "116856");
        HomePage homePage = new HomePage(driver);
        Assertions.assertEquals("donika.minkova@abv.bg", homePage.getLoggedUser());

    }
}
