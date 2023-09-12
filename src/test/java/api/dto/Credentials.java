package api.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Credentials {

    private String email;
    private String password;
    private String domain;


    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Credentials myCredentials = new Credentials("donika.minkova@abv.bg", "116856", "donika-eood");
        String credentialsJson = gson.toJson(myCredentials);
        System.out.println("Serialized object:");
        System.out.println(credentialsJson);
        Credentials deserializedCredentials = gson.fromJson(credentialsJson, Credentials.class);
        System.out.println("Deserialized fields:");
        System.out.println(deserializedCredentials.email);
        System.out.println(deserializedCredentials.password);
        System.out.println(deserializedCredentials.domain);
    }

}
