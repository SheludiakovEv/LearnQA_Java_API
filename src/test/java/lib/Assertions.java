package lib;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Assertions {

    public static void asserResponseTextEquals(Response Response, String expectedAnswer) {
        assertEquals(
                expectedAnswer,
                Response.asString(),
                "Incorrect message text"
        );
    }

    public static void asserResponseCodeEquals(Response Response, int expectedStatusCode) {
        assertEquals(
                expectedStatusCode,
                Response.statusCode(),
                "Incorrect status code"
        );
    }

    public static void assertJsonHasField(Response Response, String expectedFieldName) {
        Response.then().assertThat().body("$", hasKey(expectedFieldName));
    }

    public static void assertHasNotField(Response Response, String expectedFieldName){
        Response.then().assertThat().body("$", not(hasKey(expectedFieldName)));
    }
}
