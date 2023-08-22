import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LongTime {

    //Задание №8
    @Test
    public  void tokenTest() throws InterruptedException {
        JsonPath response1 = RestAssured
                .given()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        response1.prettyPrint();
        String token = response1.get("token");
        int second = response1.get("seconds");
        int mi = second * 1000;
        System.out.println(mi);
        System.out.println(token);

        Map<String, String> params = new HashMap<>();
        params.put("token", token);

        Response response2 = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .andReturn();

        response2.prettyPrint();

        Thread.sleep(mi);

        JsonPath response3 = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        response3.prettyPrint();
        String result = response3.get("result");
        System.out.println(result);
    }
}
