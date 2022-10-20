package trainingpart1;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetBook {


    @Test
    public void GetBooksDetails() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
        // Get the RequestSpecification of the request to be sent to the server.
        RequestSpecification httpRequest = RestAssured.given();
        // specify the method type (GET) and the parameters if any.
        //In this case the request does not take any parameters
        Response response = httpRequest.request(Method.GET);
       Headers headers= response.headers();
        for(Header header : headers) {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }

        // Print the status and message body of the response received from the server
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());

        //validations
        int statusCode= response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Status codes don't match");

        //validate status line
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK",  "Status line is different from expected!");

        //Validate headers for Content-Type, server type, content length
       // Assert.assertEquals();

        ResponseBody body = response.getBody();
        System.out.println("Response Body is "+body.prettyPrint());







    }

    @Test
    public void WeatherMessageBody()
    {
        RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();

        JsonPath js= response.jsonPath();
        String city = js.getString("City");



        // By using the ResponseBody.asString() method, we can convert the  body
        // into the string representation.
        System.out.println("Response Body is: " + body.asString());

        //Validation , check if hyderabad is present
        Assert.assertTrue(body.asString().contains("hyderabad"));
        //validation,check lower case
       // Assert.assertEquals( );
        //Check temperature character
        Assert.assertEquals(city, "Hyderabad", "");




    }

    @Test
    public void WeatherMessageBody2()
    {
        RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();

        // To check for sub string presence get the Response body as a String.
        // Do a String.contains
        String bodyAsString = body.asString();
        Assert.assertEquals(bodyAsString.contains("Hyderabad") /*Expected value*/, true /*Actual Value*/, "Response body contains Hyderabad");
    }
}
