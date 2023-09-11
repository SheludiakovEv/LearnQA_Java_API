package tests;

import com.google.gson.Gson;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import lib.ApiCoreRequests;
import lib.Assertions;
import lib.BaseTestCase;
import lib.PojoUserLogin;
import org.junit.Test;

public class UserGetTest extends BaseTestCase {

    private final ApiCoreRequests apiCoreRequests = new ApiCoreRequests();

    @Feature("Login user")
    @Test
    public void testLogin() {

        PojoUserLogin pojoUserLogin = new PojoUserLogin("vinkotov@example.com", "1234");
        String jsonBody = new Gson().toJson(pojoUserLogin);

        Response responseLoginUser = apiCoreRequests.makePostRequestLoginUser("https://playground.learnqa.ru/api/user/login", jsonBody);

        String header = this.getHeader(responseLoginUser, "x-csrf-token");
        String cookie = this.getCookie(responseLoginUser, "auth_sid");

        System.out.println(header);//Для отладки
        System.out.println(cookie);//Для отладки

        Response responseGetUser = apiCoreRequests.makeGetUserInfoById("https://playground.learnqa.ru/api/user/1", header, cookie);

        Assertions.assertJsonHasField(responseGetUser, "username");
        Assertions.assertHasNotField(responseGetUser, "firstName");
        Assertions.assertHasNotField(responseGetUser, "lastName");
        Assertions.assertHasNotField(responseGetUser, "email");

        String req = responseGetUser.prettyPrint();//Для отладки
        System.out.println(req);//Для отладки
    }
}