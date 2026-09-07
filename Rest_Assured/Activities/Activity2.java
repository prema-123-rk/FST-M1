package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Activity2 {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private String username;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        username = "testuser" + System.currentTimeMillis(); // unique username per run to avoid clashing with other test data
    }

    @Test(priority = 1)
    public void testPostUser() {
        String requestBody = "{"
                + "\"id\": " + System.currentTimeMillis() + ","
                + "\"username\": \"" + username + "\","
                + "\"firstName\": \"John\","
                + "\"lastName\": \"Doe\","
                + "\"email\": \"john.doe@example.com\","
                + "\"password\": \"password123\","
                + "\"phone\": \"1234567890\","
                + "\"userStatus\": 1"
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/user");

        response.then().statusCode(200);
        Assert.assertEquals(response.jsonPath().getInt("code"), 200, "Expected success code 200 in response body");
    }

    @Test(priority = 2, dependsOnMethods = "testPostUser")
    public void testGetUser() {
        Response response = given()
                .pathParam("username", username)
                .when()
                .get("/user/{username}");

        response.then().statusCode(200);
        Assert.assertEquals(response.jsonPath().getString("username"), username, "Fetched username should match created username");
        Assert.assertEquals(response.jsonPath().getString("firstName"), "John", "Fetched firstName should match created value");
    }

    @Test(priority = 3, dependsOnMethods = "testGetUser")
    public void testDeleteUser() {
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete("/user/{username}");

        response.then().statusCode(200);

        // Confirm the user is actually gone
        Response getAfterDelete = given()
                .pathParam("username", username)
                .when()
                .get("/user/{username}");

        getAfterDelete.then().statusCode(404);
    }
}
