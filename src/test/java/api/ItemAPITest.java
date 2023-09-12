package api;

import api.dto.Credentials;
import api.dto.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

public class ItemAPITest {
    private String token = "";
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @BeforeEach
    public void beforeEach(){
        Credentials credentials = new Credentials("donika.minkova@abv.bg", "116856", "donika-eood");
        LoginAPI loginAPI = new LoginAPI("");
        token = loginAPI.obtainToken(credentials);
    }
    @Test
    @Tag("api")
    @DisplayName("Can create new item")
    public void canCreateNewItem(){
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder().build();
        Response createResp = itemAPI.createItem(item);
        Assertions.assertEquals(201,createResp.statusCode());
        int id = createResp.then().extract().jsonPath().get("id");
        Response getResp = itemAPI.getItem(id);
        Assertions.assertEquals(200, getResp.statusCode());
        Item deserializedItem = GSON.fromJson(getResp.body().asString(), Item.class);
        Assertions.assertEquals(item.getName(),deserializedItem.getName());
        Assertions.assertEquals(item.getPrice(),deserializedItem.getPrice());
        Assertions.assertEquals(item.getPrice_for_quantity(),deserializedItem.getPrice_for_quantity());
        Assertions.assertEquals("BGN", deserializedItem.getCurrency());

    }
    @Test
    @Tag("api")
    @DisplayName("Can get all items")
    public void canGetAllItems(){
        ItemAPI itemAPI = new ItemAPI(token);
        Response createResp = itemAPI.getItems();
        Assertions.assertEquals(200,createResp.statusCode());
    }
    @Test
    @Tag("api")
    @DisplayName("Can get single item")
    public void canGetSingleItem(){
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder().build();
        Response createResp = itemAPI.createItem(item);
        Assertions.assertEquals(201,createResp.statusCode());
        int id = createResp.then().extract().jsonPath().getInt("id");
        Response getResp = itemAPI.getItem(id);
        Assertions.assertEquals(200,getResp.statusCode());
    }
    @Test
    @Tag("api")
    @DisplayName("Can delete single item")
    public void canDeleteSingleItem(){
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder().build();
        Response createResp = itemAPI.createItem(item);
        Assertions.assertEquals(201,createResp.statusCode());
        int id = createResp.then().extract().jsonPath().getInt("id");
        Response deleteResp = itemAPI.deleteItem(id);
        Assertions.assertEquals(204,deleteResp.statusCode());
    }
//    @Test
//    @Tag("api")
//    @Tag("update")
//    @Disabled("This is blocked because of bug №001")
//    @DisplayName("Can update item")
//    public void canUpdateItem(){
//        ItemAPI itemAPI = new ItemAPI(token);
//        Item item = new Item("Coffee_" + LocalDateTime.now(),10.50f,"kg.", 1, "BGN"); тук трябва май да е = Item.builder().build();
//        Response createResp = itemAPI.createItem(item);
//        Assertions.assertEquals(201,createResp.statusCode());
//        int id = createResp.then().extract().jsonPath().getInt("id");
//        Item updatedItem = new Item("Updated_Coffee_" + LocalDateTime.now(),12.50f,"kg.", 2, "BGN");
//        Response updateResp = itemAPI.updateItem(id, updatedItem);
//        Assertions.assertEquals(200,updateResp.statusCode());
//    }
}
