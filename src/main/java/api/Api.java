package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import model.Authorization;
import model.User;
import io.restassured.http.Header;
import io.restassured.response.Response;
import constants.Path;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class Api {

    private final Header contentTypeHeader = new Header(CONTENT_TYPE, APPLICATION_JSON.getMimeType());

    public Api() {
        RestAssured.baseURI = Path.BASE_URL;
    }

    @Step("Создание пользователя")
    public String createUser(User user) {
        Response response = given()
                .header(contentTypeHeader)
                .body(user)
                .when()
                .post(Path.CREATE_USER_PATH);

        return response.getBody().jsonPath().getString("accessToken");
    }

    @Step("Аутентификация пользователя")
    public String loginUser(Authorization authorization) {
        Response response = given()
                .header(contentTypeHeader)
                .body(authorization)
                .when()
                .post(Path.LOGIN_USER_PATH);
        return response.getBody().jsonPath().getString("accessToken");
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        Header authHeader = new Header(AUTHORIZATION, accessToken);
        given()
                .header(authHeader)
                .when()
                .delete(Path.AUTH_USER_PATH);
    }
}
