package lib;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiCoreRequests {

    @Step("Make POST-request registry user")
    public Response makePostRequestRegUser(String url, String jsonBody){
        return given()
                .filter(new AllureRestAssured())
                .body(jsonBody)
                .post(url)
                .andReturn();
    }
}
