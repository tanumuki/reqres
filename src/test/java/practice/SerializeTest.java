package practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTest {



    @Test
    public void test(){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        AddPlacePojo p =new AddPlacePojo();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("Frontline house");
        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        LocationPojo l =new LocationPojo();
        l.setLat(-38.383494);
        l.setLng(33.427362);

        p.setLocation(l);

        Response res=given().log().all().queryParam("key", "qaclick123")
                .body(p)
                .when().post("/maps/api/place/add/json").
                then().assertThat().statusCode(200).extract().response();

        String responseString=res.asString();
        System.out.println(responseString);

    }


}
