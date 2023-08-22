import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RunWith(Parameterized.class)
public class LoginTest {

    private String login;
    private String password;

    public LoginTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getLoginData() {
        return Arrays.asList(new Object[][]{
                {"super_admin", "123456"},
                {"super_admin", "123456789"},
                {"super_admin", "qwerty"},
                {"super_admin", "password"},
                {"super_admin", "1234567"},
                {"super_admin", "12345678"},
                {"super_admin", "12345"},
                {"super_admin", "iloveyou"},
                {"super_admin", "111111"},
                {"super_admin", "123123"},
                {"super_admin", "abc123"},
                {"super_admin", "qwerty123"},
                {"super_admin", "1q2w3e4r"},
                {"super_admin", "admin"},
                {"super_admin", "qwertyuiop"},
                {"super_admin", "654321"},
                {"super_admin", "555555"},
                {"super_admin", "lovely"},
                {"super_admin", "7777777"},
                {"super_admin", "welcome"},//Верный пароль
                {"super_admin", "888888"},
                {"super_admin", "princess"},
                {"super_admin", "dragon"},
                {"super_admin", "password1"},
                {"super_admin", "123qwe"},
        });
    }

    @Test
    public void testLogin() {
        Response response1 = RestAssured
        .given()
                .contentType("application/json")
                .body("{\"login\":\"" + login + "\",\"password\":\"" + password + "\"}")
                .when()
                .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                .andReturn();

        response1.prettyPrint();
        String cookie = response1.getCookie("auth_cookie");
        System.out.println(cookie);

        Map<String, String> cookies = new HashMap<>();
        if(cookie != null) {
            cookies.put("auth_cookie", cookie);
        }

        Response response2 = RestAssured
                .given()
                .cookies(cookies)
                .post("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                .andReturn();

        response2.print();
    }
}