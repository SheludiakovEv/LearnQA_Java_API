package lib;

import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Assertions {

    public static void asserResponseTextEquals(Response Response, String expectedAnswer){
        assertEquals(
                expectedAnswer,
                Response.asString(),
                "Incorrect message text"
        );
    }

    public static void asserResponseCodeEquals(Response Response, int expectedStatusCode){
        assertEquals(
                expectedStatusCode,
                Response.statusCode(),
                "Incorrect status code"
        );
    }
}
