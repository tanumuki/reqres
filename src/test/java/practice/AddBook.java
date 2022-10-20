package practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddBook {


    @Test
    public static void test(){


        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response =given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(AddPlace.addPayload()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
                .body("scope", equalTo("APP")).header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();

        System.out.println("Response "+response);
        JsonPath jsonPath = new JsonPath(response);
        String place_id = jsonPath.getString("place_id");
        System.out.println("place id "+place_id);

        //update
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("{\n" +
                "\"place_id\":\""+place_id+"\",\r\n" +
                "\"address\":\"773sdsdw winter walk, USA\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}").when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

    }
}
