package tests;

import com.google.gson.Gson;
import io.restassured.response.Response;
import lib.ApiCoreRequests;
import lib.Assertions;
import lib.PojoUserRegister;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class UserRegisterTest {

    private final ApiCoreRequests apiCoreRequests = new ApiCoreRequests();

    @Test
    //Создание пользователя с некорректным email - без символа @
    public void testUserRegistrationWithInvalidEmail() {

        PojoUserRegister pojoUser = new PojoUserRegister("vinkotovexample.ru", "password", "username", "firstName", "lastName");
        String jsonBody = new Gson().toJson(pojoUser);

        Response response = apiCoreRequests.makePostRequestRegUser("https://playground.learnqa.ru/api/user/", jsonBody);

        Assertions.asserResponseCodeEquals(response, 400);
        Assertions.asserResponseTextEquals(response, "Invalid email format");
        System.out.println(response.asString()); //Для отладки
        System.out.println(response.statusCode()); //Для отладки
    }

    @Test
    //Создание пользователя с очень коротким именем в один символ
    public void testUserRegistrationShortName() {
        PojoUserRegister pojoUser = new PojoUserRegister("vinkotov@example.ru", "password", "u", "firstName", "lastName");
        String jsonBody = new Gson().toJson(pojoUser);

        Response response = apiCoreRequests.makePostRequestRegUser("https://playground.learnqa.ru/api/user/", jsonBody);

        Assertions.asserResponseCodeEquals(response, 400);
        Assertions.asserResponseTextEquals(response, "The value of 'username' field is too short");
        System.out.println(response.asString()); //Для отладки
        System.out.println(response.statusCode()); //Для отладки
    }

    @Test
    //Создание пользователя с очень длинным именем - длиннее 250 символов
    public void testUserRegistrationLongName() {
        String username = "fghbirsfvcxvvbnlkmfsdvxyibujlknqwedacvyiuboinwrefd879034retgfjhbknlwedfg89234erthjbknqw3er897ybhjwerdfgbbhjwqedf6siuhklwerfdbg098uh32j4retgfhouhbjewdfgbouhbjg23wergf0987uyh345retgfuihyjgbewrdfcviuyhjb3w4erfg908uiwe4rtfgb9ioujkewrtgfhjkhgbcfujgyfhtd678";
        PojoUserRegister pojoUser = new PojoUserRegister("vinkotov@example.ru", "password", username, "firstName", "lastName");
        String jsonBody = new Gson().toJson(pojoUser);

        Response response = apiCoreRequests.makePostRequestRegUser("https://playground.learnqa.ru/api/user/", jsonBody);

        Assertions.asserResponseCodeEquals(response, 400);
        Assertions.asserResponseTextEquals(response, "The value of 'username' field is too long");
        System.out.println(response.asString()); //Для отладки
        System.out.println(response.statusCode()); //Для отладки
    }

    @ParameterizedTest
    //Создание пользователя без указания одного из полей - с помощью @ParameterizedTest необходимо проверить, что отсутствие любого параметра не дает зарегистрировать пользователя
    @CsvSource({
            "vinkotov@example.ru, , username, firstName, lastName",
            "vinkotov@example.ru, password, , firstName, lastName",
            "vinkotov@example.ru, password, username, , lastName",
            "vinkotov@example.ru, password, username, firstName, ",
            ", password, username, firstName, lastName",
    })
    public void testRegistrationErrorResponse(String email, String password, String username, String firstName, String lastName) {
        PojoUserRegister pojoUser = new PojoUserRegister(email, password, username, firstName, lastName);
        String jsonBody = new Gson().toJson(pojoUser);

        Response response = apiCoreRequests.makePostRequestRegUser("https://playground.learnqa.ru/api/user/", jsonBody);

        Assertions.asserResponseCodeEquals(response, 400);
        System.out.println(response.asString()); //Для отладки
        System.out.println(response.statusCode()); //Для отладки
    }
}