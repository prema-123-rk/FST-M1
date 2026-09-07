package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Activity1 {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private long petId;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        petId = System.currentTimeMillis(); // unique id per run to avoid clashing with other test data
    }

    @Test(priority = 1)
    public void testPostPet() {
        String requestBody = "{"
                + "\"id\": " + petId + ","
                + "\"category\": { \"id\": 1, \"name\": \"Dogs\" },"
                + "\"name\": \"doggie\","
                + "\"photoUrls\": [\"https://example.com/dog.jpg\"],"
                + "\"tags\": [{ \"id\": 1, \"name\": \"friendly\" }],"
                + "\"status\": \"available\""
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/pet");

        response.then().statusCode(200);
        Assert.assertEquals(response.jsonPath().getLong("id"), petId, "Created pet id should match request");
        Assert.assertEquals(response.jsonPath().getString("name"), "doggie", "Pet name should match request");
    }

    @Test(priority = 2, dependsOnMethods = "testPostPet")
    public void testGetPet() {
        Response response = given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}");

        response.then().statusCode(200);
        Assert.assertEquals(response.jsonPath().getLong("id"), petId, "Fetched pet id should match created id");
        Assert.assertEquals(response.jsonPath().getString("name"), "doggie", "Fetched pet name should match created name");
    }

    @Test(priority = 3, dependsOnMethods = "testGetPet")
    public void testDeletePet() {
        Response response = given()
                .pathParam("petId", petId)
                .when()
                .delete("/pet/{petId}");

        response.then().statusCode(200);

        // Confirm the pet is actually gone
        Response getAfterDelete = given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}");

        getAfterDelete.then().statusCode(404);
    }
}
