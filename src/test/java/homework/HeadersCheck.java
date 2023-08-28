package homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class HeadersCheck {

    //Задание №12
    String secret = "Some secret value";

    @Test
    public void cookieTest() {
        Response response1 = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/homework_header")
                .andReturn();

        String headers = response1.getHeader("x-secret-homework-header");
        Assert.assertEquals("Значение заголовка не отличается", headers, secret);
        System.out.println(headers);
    }
}