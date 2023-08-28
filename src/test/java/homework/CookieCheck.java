package homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CookieCheck {

    //Задание №11
String regex = "^\\w+_\\w+$";

    @Test
    public void cookieTest() {
        Response response1 = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        String header = response1.getCookie("HomeWork");
        Assert.assertTrue(header.matches(regex));
        Assert.assertEquals(header, "hw_value");
        System.out.println(header);
    }
}