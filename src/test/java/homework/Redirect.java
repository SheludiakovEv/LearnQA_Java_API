package homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Redirect {

    //Задание №6
    @Test
    public  void redirectTest(){
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();

        String url = response.getHeader("Location");
        System.out.println("Адрес на который редиректит указанные URL:" + url);
    }
}
