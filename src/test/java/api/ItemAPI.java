package api;

import api.dto.Item;
import api.dto.ItemList;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ItemAPI extends Request {
    private static final String ENDPOINT = "/items";


    public ItemAPI(String token){
        super(token);

    }
    public Response createItem(Item item){
        String body = GSON.toJson(item);
        return post(ENDPOINT, body);
    }
    public Response deleteItem(int id){
        return delete(ENDPOINT + "/" + id);
    }
    public Response getItem(int id){
       return get(ENDPOINT + "/" + id);
    }

    public Response updateItem(int id, Item updatedItem){
        String body = GSON.toJson(updatedItem);
        return patch(ENDPOINT + "/" + id, body);
    }

    public Response getItems(){
        return get(ENDPOINT);
    }

    public static void main(String[] args) {
        ItemAPI itemAPI = new ItemAPI("");
        Item item = Item.builder().build();
        Response createResp = itemAPI.createItem(item);
    }
    @Step("Delete all existing items")
    public void deleteAll(){
        String body = getItems().asString();
        ItemList items = GSON.fromJson(body, ItemList.class);
        items.getItems().forEach(item -> deleteItem(item.getId()));
    }
}
