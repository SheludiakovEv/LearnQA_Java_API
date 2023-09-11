package tests;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import lib.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserDeleteTest extends BaseTestCase {

    String header;
    String cookie;
    public String userId;
    public String email;
    public String password;
    public String lastName;
    public String firstName;
    public String userName;
    private final ApiCoreRequests apiCoreRequests = new ApiCoreRequests();
    Faker faker = new Faker();

    @BeforeEach
    //Предварительно создали пользователя
    public void createUser() {

        PojoUserRegister pojoUser = new PojoUserRegister(faker.internet().emailAddress(), faker.internet().password(), faker.name().username(), faker.name().firstName(), faker.name().lastName());
        String jsonBody = new Gson().toJson(pojoUser);

        Response createRandomUser = apiCoreRequests.makePostRequestRegUser("https://playground.learnqa.ru/api/user/", jsonBody);

        userId = createRandomUser.jsonPath().get("id").toString();
        email = pojoUser.getEmail();
        password = pojoUser.getPassword();
        lastName = pojoUser.getLastName();
        firstName = pojoUser.getFirstName();
        userName = pojoUser.getUsername();

        System.out.println("Успешно создан пользователь: " + userId);
        System.out.println(email);
        System.out.println(password);
    }

    @DisplayName("Попытка удаления пользователя с id =2")
    @Description("Доп описание")
    @Story("Попытка удаления пользователя с id =2")
    @Feature("Delete user")
    @Test
    public void deleteUserId2(){
        //LOGIN TEST

        PojoUserLogin pojoUserLogin = new PojoUserLogin("vinkotov@example.com", "1234");
        String jsonBody = new Gson().toJson(pojoUserLogin);

        Response responseLoginUser = apiCoreRequests.makePostRequestLoginUser("https://playground.learnqa.ru/api/user/login", jsonBody);

        header = this.getHeader(responseLoginUser, "x-csrf-token");
        cookie = this.getCookie(responseLoginUser, "auth_sid");

        System.out.println(header);//Для отладки
        System.out.println(cookie);//Для отладки

        //DELETE USER

        Response deleteUserId2 = apiCoreRequests.makeDeleteUser("https://playground.learnqa.ru/api/user/2",header, cookie);

        Assertions.asserResponseCodeEquals(deleteUserId2, 400);
        Assertions.asserResponseTextEquals(deleteUserId2, "Please, do not delete test users with ID 1, 2, 3, 4 or 5.");
    }

    @DisplayName("Успешное удаление пользователя")
    @Description("Доп описание")
    @Story("Успешное удаление пользователя")
    @Feature("Delete user")
    @Test
    public void successfulDeleteUser(){
        //LOGIN TEST

        PojoUserLogin pojoUserLogin = new PojoUserLogin(email, password);
        String jsonBody1 = new Gson().toJson(pojoUserLogin);

        Response responseLoginUser = apiCoreRequests.makePostRequestLoginUser("https://playground.learnqa.ru/api/user/login", jsonBody1);

        header = this.getHeader(responseLoginUser, "x-csrf-token");
        cookie = this.getCookie(responseLoginUser, "auth_sid");

        System.out.println(header);//Для отладки
        System.out.println(cookie);//Для отладки

        //DELETE USER

        Response deleteUser = apiCoreRequests.makeDeleteUser("https://playground.learnqa.ru/api/user/" + userId,header, cookie);

        Assertions.asserResponseCodeEquals(deleteUser, 200);

        //GET USER ID

        Response getUserId = apiCoreRequests.makeGetUserInfoById("https://playground.learnqa.ru/api/user/" + userId,header, cookie);

        Assertions.asserResponseCodeEquals(getUserId, 404);
    }

    @DisplayName("Попытка удаления пользователя под другим юзером")
    @Description("Доп описание")
    @Story("Попытка удаления пользователя под другим юзером")
    @Feature("Delete user")
    @Test
    public void anotherUserDelete(){
        //LOGIN TEST

        PojoUserLogin pojoUserLogin = new PojoUserLogin(email, password);
        String jsonBody1 = new Gson().toJson(pojoUserLogin);

        Response responseLoginUser = apiCoreRequests.makePostRequestLoginUser("https://playground.learnqa.ru/api/user/login", jsonBody1);

        header = this.getHeader(responseLoginUser, "x-csrf-token");
        cookie = this.getCookie(responseLoginUser, "auth_sid");

        System.out.println(header);//Для отладки
        System.out.println(cookie);//Для отладки

        //DELETE USER

        Response deleteUserId = apiCoreRequests.makeDeleteUser("https://playground.learnqa.ru/api/user/79549",header, cookie);

        Assertions.asserResponseCodeEquals(deleteUserId, 400);
    }
}
