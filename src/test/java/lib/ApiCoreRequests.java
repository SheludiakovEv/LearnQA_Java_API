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

    @Step("Make POST-request login user")
    public Response makePostRequestLoginUser(String url, String jsonBody){
        return given()
                .filter(new AllureRestAssured())
                .body(jsonBody)
                .post(url)
                .andReturn();
    }

    @Step("Make Get-request user info")
    public Response makeGetUserInfoById(String url, String header, String cookie){
        return given()
                .filter(new AllureRestAssured())
                .headers("x-csrf-token", header)
                .cookie("auth_sid", cookie)
                .get(url)
                .andReturn();
    }

    @Step("Make PUT-request edit user")
    public Response makePutEditUser(String url, String header, String cookie, String jsonBody){
        return given()
                .filter(new AllureRestAssured())
                .headers("x-csrf-token", header)
                .cookie("auth_sid", cookie)
                .body(jsonBody)
                .post(url)
                .andReturn();
    }
}
