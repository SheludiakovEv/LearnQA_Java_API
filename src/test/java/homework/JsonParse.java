package homework;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

public class JsonParse {

    //Задание №5
    @Test
    public  void getJson(){
        JsonPath response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        response.prettyPrint();
        String message = response.getString("messages[1].message");
        System.out.println("\nВыводим второе сообщение:" + message);
    }
}
