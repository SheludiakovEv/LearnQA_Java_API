package homework;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserAgentTest {

    //Задание №13
    @ParameterizedTest
    @ValueSource(strings =
            {"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299",
            "Mozilla/5.0 (iPad; CPU OS 13_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/91.0.4472.77 Mobile/15E148 Safari/604.1",
            "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36 Edg/91.0.100.0",
            "Mozilla/5.0 (iPad; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1"})

    public void userAgentTest(String expectedUserAgent) {
        Response response1 = RestAssured
                .given()
                .contentType("application/json")
                .header("user-agent", expectedUserAgent)
                .when()
                .get("https://playground.learnqa.ru/ajax/api/user_agent_check")
                .thenReturn();

        response1.prettyPrint();
        JsonPath jsonPath = response1.jsonPath();

        switch (expectedUserAgent) {
            case "Mozilla/5.0 (Linux; U; Android 4.0.2; en-us; Galaxy Nexus Build/ICL53F) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30":
                Assert.assertEquals(jsonPath.getString("platform"), "Mobile");
                Assert.assertEquals(jsonPath.getString("browser"), "No");
                Assert.assertEquals(jsonPath.getString("device"), "Android");
                break;
            case "Mozilla/5.0 (iPad; CPU OS 13_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/91.0.4472.77 Mobile/15E148 Safari/604.1":
                Assert.assertEquals(jsonPath.getString("platform"), "Mobile");
                Assert.assertEquals(jsonPath.getString("browser"), "Chrome");
                Assert.assertEquals(jsonPath.getString("device"), "iOS");
                break;
            case "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)":
                Assert.assertEquals(jsonPath.getString("platform"), "Googlebot");
                Assert.assertEquals(jsonPath.getString("browser"), "Unknown");
                Assert.assertEquals(jsonPath.getString("device"), "Unknown");
                break;
            case "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36 Edg/91.0.100.0":
                Assert.assertEquals(jsonPath.getString("platform"), "Web");
                Assert.assertEquals(jsonPath.getString("browser"), "Chrome");
                Assert.assertEquals(jsonPath.getString("device"), "No");
                break;
            case "Mozilla/5.0 (iPad; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1":
                Assert.assertEquals(jsonPath.getString("platform"), "Mobile");
                Assert.assertEquals(jsonPath.getString("browser"), "No");
                Assert.assertEquals(jsonPath.getString("device"), "iPhone");
                break;
        }
    }
}