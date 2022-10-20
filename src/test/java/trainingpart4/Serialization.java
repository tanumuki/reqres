package trainingpart4;

import com.beust.ah.A;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Serialization {


    @Test
    public static void testSerialisation(){


        AddPlace addPlace = new AddPlace();
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setAccuracy(50);
        addPlace.setLanguage("French-IN");
        addPlace.setWebsite("http://google.com");
        addPlace.setName("Frontline house");
        addPlace.setPhone_number("+91) 983 893 3937");
        //location
        Location location = new Location();
        location.setLatitude(22.23);
        location.setLongitude(33.45);
        addPlace.setLocation(location);
        //types
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("test");
        addPlace.setTypes(myList);

        //request spec builder

       RequestSpecification requestSpecification= new RequestSpecBuilder().setBasePath("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification responseSpecification= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

     RequestSpecification res=  given().spec(requestSpecification).body(addPlace);

      Response response= res.when().post("/maps/api/place/add/json").then().spec(responseSpecification).extract().response();

    String resp = response.asString();
        System.out.println("Response "+resp);

//
//        RestAssured.baseURI="https://rahulshettyacademy.com";
//        Response response= given().log().all().queryParam("key", "qaclick123")
//                .body(addPlace).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
//                .extract().response();


       // System.out.println("Response "+response.asString());

    }
}
