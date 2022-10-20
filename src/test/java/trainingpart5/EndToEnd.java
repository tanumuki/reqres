package trainingpart5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import trainingpart5.response.EtoEResponse;
import trainingpart5.validators.Validator;

import static io.restassured.RestAssured.given;

public class EndToEnd {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public static void test() throws JsonProcessingException {
        // TODO Auto-generated method stub
//SSL
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://reqres.in")
                .setContentType(ContentType.JSON).build();

        //set json payload

        EToEPojo e = new EToEPojo();
        e.setName("tanu");
        e.setJob("sdet");

        RequestSpecification reqLogin = given().log().all().spec(req).body(e);
        Response loginResponse = reqLogin.when().post("/api/users").then().log().all().extract().response();

        System.out.println("login " + loginResponse.asString());

        Assert.assertEquals(loginResponse.getStatusCode(), 201);

        EtoEResponse pojoValue= objectMapper.readValue(loginResponse.asString(), EtoEResponse.class);
        SoftAssert sa = new SoftAssert();
        String name=  pojoValue.getName();
        System.out.println("name "+name);
        String job =pojoValue.getJob();
        System.out.println("job "+job);
        new Validator().validate(pojoValue, sa);



    }
}
