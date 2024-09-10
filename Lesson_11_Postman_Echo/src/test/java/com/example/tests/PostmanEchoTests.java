package src.test.java.com.example.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostmanEchoTests {

    @Test
    public void testGetMethod() {
        RestAssured.baseURI = "https://postman-echo.com";

        given().
                when().
                get("/get?foo1=bar1&foo2=bar2").
                then().
                statusCode(200).
                body("args.foo1", equalTo("bar1")).
                body("args.foo2", equalTo("bar2"));
    }

    @Test
    public void testPostMethod() {
        given().
                baseUri("https://postman-echo.com").
                header("Content-Type", "application/json").
                body("{\"name\":\"John\",\"age\":30}").
                when().
                post("/post").
                then().
                statusCode(200).
                body("data.name", equalTo("John")).
                body("data.age", equalTo(30));
    }

    @Test
    public void testPutMethod() {
        given().
                baseUri("https://postman-echo.com").
                header("Content-Type", "application/json").
                body("{\"name\":\"Jane\",\"age\":25}").
                when().
                put("/put").
                then().
                statusCode(200).
                body("data.name", equalTo("Jane")).
                body("data.age", equalTo(25));
    }

    @Test
    public void testPatchMethod() {
        given().
                baseUri("https://postman-echo.com").
                header("Content-Type", "application/json").
                body("{\"name\":\"Alice\",\"age\":28}").
                when().
                patch("/patch").
                then().
                statusCode(200).
                body("data.name", equalTo("Alice")).
                body("data.age", equalTo(28));
    }

    @Test
    public void testDeleteMethod() {
        given().
                baseUri("https://postman-echo.com").
                when().
                delete("/delete").
                then().
                statusCode(200);
    }
}