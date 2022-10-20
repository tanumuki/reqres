package trainingpart2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CrudApisTest {


    @Test
    public static void testAddPlace(){
        //ADD place API -->> validate if Add place API is working as expected

        //given ->> input details
        //when  ->>submit the API -> resource , http method
        //then   ->> validate the response

        RestAssured.baseURI = "https://rahulshettyacademy.com";
       String response= given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(AddPayload.addPlaceJson()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
                .body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();


            //TC2    //Update place API - >> update place with new address

        System.out.println("response "+response);
        //JSON PATH

        JsonPath jsonPath = new JsonPath(response);
        String placeId= jsonPath.getString("place_id");
        System.out.println("place id is "+placeId);

        //new address
        String newAddress = "Zonasha,Bangalore";

        given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").when().put("/maps/api/place/update/json").then().assertThat().log().all()
                .statusCode(200).body("msg",equalTo("Address successfully updated"));


        //GET address

       String getPlaceReponse= given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId).when()
                .get("/maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response().asString();


        System.out.println("get place response "+getPlaceReponse);

        JsonPath jsonPath1 = new JsonPath(getPlaceReponse);
        String actualAddress = jsonPath1.getString("address");
        Assert.assertEquals(actualAddress,newAddress);
        //testing frameworks - testng/junit








    }

}
