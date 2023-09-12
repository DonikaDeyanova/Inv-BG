package ui;

import api.ItemAPI;
import api.LoginAPI;
import api.dto.Credentials;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.pages.HomePage;
import ui.pages.ItemPage;
import ui.pages.LoginPage;

import java.time.Duration;

@Tag("item")
public class ItemPageTest {
    private WebDriver driver;
    private String token;
    private static final String BASE_URL = "https://donika-eood.inv.bg";
    private static final long WAIT = 5;
    @BeforeEach
    public void BeforeEachTest(TestInfo testInfo){
        LoginAPI loginAPI = new LoginAPI("");
        Credentials credentials = new Credentials("donika.minkova@abv.bg", "116856", "donika-eood");
        token = loginAPI.obtainToken(credentials);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT));
        driver.get(BASE_URL);
        driver.manage().window().minimize();
        System.out.println("Test: " + testInfo.getDisplayName());
    }
    @AfterEach
    public void afterEachTest() {
        if(driver != null){
            driver.quit();
        }
    }
    @Test
    @Tag("ui")
    @DisplayName("Can delete existing item")
    public void canDeleteExistingItem(){

    }
    @Test
    @DisplayName("Can create item")
    public void canCreateItem(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login("donika.minkova@abv.bg", "116856");
        HomePage homePage = new HomePage(driver);
        homePage.clickItemsMenu();
        ItemPage itemPage = new ItemPage(driver);
        Assertions.assertEquals("Артикули", itemPage.pageHeading());
        itemPage.createItem("QAComplete27");
        Assertions.assertEquals("Артикулът е добавен успешно.",itemPage.successMassage());
        ItemAPI itemAPI = new ItemAPI(token);
        itemAPI.deleteAll();
    }
    @Test
    @DisplayName("Correct massage is displayed when no items exist")
    public void correctMassageIsDisplayedWhenNoItemsExist(){
        ItemAPI itemAPI = new ItemAPI(token);
        itemAPI.deleteAll();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login("donika.minkova@abv.bg", "116856");
        HomePage homePage = new HomePage(driver);
        homePage.clickItemsMenu();
        ItemPage itemPage = new ItemPage(driver);
        Assertions.assertEquals("Артикули", itemPage.pageHeading());
        Assertions.assertEquals("Не са намерени артикули, отговарящи на зададените критерии.", itemPage.emptyListMassage());
    }
    @Test
    @DisplayName("Can search item")
    public void canSearchItem(){
    }
}
