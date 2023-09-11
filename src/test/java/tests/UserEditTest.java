package tests;

import com.google.gson.Gson;
import io.restassured.response.Response;
import lib.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserEditTest extends BaseTestCase {

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

    @Test
    public void editNonAuth() {

        PojoUserRegister pojoUser = new PojoUserRegister(faker.internet().emailAddress(), password, userName, firstName, userName);
        String jsonBody = new Gson().toJson(pojoUser);

        Response editUser = apiCoreRequests.makePutEditUser("https://playground.learnqa.ru/api/user/" + userId, "", "", jsonBody);

        Assertions.asserResponseCodeEquals(editUser, 404);
    }

    @Test
    public void editAnotherUser() {

        //LOGIN TEST

        PojoUserLogin pojoUserLogin = new PojoUserLogin(email, password);
        String jsonBody1 = new Gson().toJson(pojoUserLogin);

        Response responseLoginUser = apiCoreRequests.makePostRequestLoginUser("https://playground.learnqa.ru/api/user/login", jsonBody1);

        header = this.getHeader(responseLoginUser, "x-csrf-token");
        cookie = this.getCookie(responseLoginUser, "auth_sid");

        System.out.println(header);//Для отладки
        System.out.println(cookie);//Для отладки

        //EDIT TEST

        PojoUserRegister pojoUser = new PojoUserRegister("vitalyexample.com", password, userName, firstName, userName);
        String jsonBody = new Gson().toJson(pojoUser);

        Response editUser = apiCoreRequests.makePutEditUser("https://playground.learnqa.ru/api/user/" + userId, header, cookie, jsonBody);

        Assertions.asserResponseCodeEquals(editUser, 400);
        Assertions.asserResponseTextEquals(editUser, "Invalid email format");
    }

    @Test
    public void editIncorrectUser() {

        //LOGIN TEST

        PojoUserLogin pojoUserLogin = new PojoUserLogin(email, password);
        String jsonBody1 = new Gson().toJson(pojoUserLogin);

        Response responseLoginUser = apiCoreRequests.makePostRequestLoginUser("https://playground.learnqa.ru/api/user/login", jsonBody1);

        header = this.getHeader(responseLoginUser, "x-csrf-token");
        cookie = this.getCookie(responseLoginUser, "auth_sid");

        System.out.println(header);//Для отладки
        System.out.println(cookie);//Для отладки

        //EDIT TEST

        PojoUserRegister pojoUser = new PojoUserRegister(email, password, faker.name().username(), firstName, userName);
        String jsonBody = new Gson().toJson(pojoUser);

        Response editUser = apiCoreRequests.makePutEditUser("https://playground.learnqa.ru/api/user/78", header, cookie, jsonBody);

        Assertions.asserResponseCodeEquals(editUser, 400);
        Assertions.asserResponseTextEquals(editUser, "Users with email " + "'" + email + "'" + " already exists");
    }

    @Test
    public void editShortNameUser() {

        //LOGIN TEST

        PojoUserLogin pojoUserLogin = new PojoUserLogin(email, password);
        String jsonBody1 = new Gson().toJson(pojoUserLogin);

        Response responseLoginUser = apiCoreRequests.makePostRequestLoginUser("https://playground.learnqa.ru/api/user/login", jsonBody1);

        header = this.getHeader(responseLoginUser, "x-csrf-token");
        cookie = this.getCookie(responseLoginUser, "auth_sid");

        System.out.println(header);//Для отладки
        System.out.println(cookie);//Для отладки

        //EDIT TEST

        PojoUserRegister pojoUser = new PojoUserRegister(email, password, "b", firstName, userName);
        String jsonBody = new Gson().toJson(pojoUser);

        Response editUser = apiCoreRequests.makePutEditUser("https://playground.learnqa.ru/api/user/78", header, cookie, jsonBody);

        Assertions.asserResponseCodeEquals(editUser, 400);
        Assertions.asserResponseTextEquals(editUser, "The value of 'username' field is too short");
    }
}