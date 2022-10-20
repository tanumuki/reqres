package part5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import part5.pojo.EtoEPojo;
import trainingpart5.response.EtoEResponse;

import static io.restassured.RestAssured.given;

public class EndToEnd {


    //1.Get the response
    //2.Create the request pojo (
    //3.Create the response pojo

    private static final ObjectMapper objectMapper= new ObjectMapper();

    @Test
    public static void testEndToEnd() throws JsonProcessingException {

        RequestSpecification requestSpecification= new RequestSpecBuilder().setBaseUri("https://reqres.in/").setContentType(ContentType.JSON).build();
        //set json payload

        EtoEPojo etoEPojo = new EtoEPojo();
        etoEPojo.setName("Tanu");
        etoEPojo.setJob("SDET");

        RequestSpecification  reqlogin = given().log().all().spec(requestSpecification).body(etoEPojo);
                Response response = reqlogin.when().post("/api/users").then().log().all().extract().response();
        System.out.println("Create Response "+response.asString() );

        //parsing the values for validation
        //Gson gson = Gson();

           EtoEResponse etoEResponse= objectMapper.readValue(response.asString(), EtoEResponse.class);
        Assert.assertEquals(etoEResponse.getJob(), etoEPojo.getJob(), "Both are different");
        Assert.assertTrue(etoEResponse.getJob().equals("SDET"), "Both are different");



    }
}
