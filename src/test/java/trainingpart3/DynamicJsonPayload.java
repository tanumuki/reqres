package trainingpart3;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import trainingpart2.AddPayload;

import static io.restassured.RestAssured.given;

public class DynamicJsonPayload {




    @Test(dataProvider = "BooksData")
    public static void testAddBook(String isbn, String aisle){


        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().header("Content-Type", "application/json").log().all().body(AddPayload.addBookJson(isbn, aisle)).when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        System.out.println("Response "+response);
        //Try this
        // Extract the ID in order to delete or get book
        //use jsonpath

        JsonPath js = new JsonPath(response);
        String bookId= js.getString("ID");
        System.out.println("Book Id is "+bookId);

        //Validation1 :add validation for addition of book to DB
        //Validation2: add validation when the book with same aisle/isbn is being added again
    }
    //GET book
    public static void getBook(){

    }

    //Delete Book
    public static void deleteBook(){

    }



    @DataProvider(name = "BooksData")
    public static Object[][] getData(){
        //array
        return new Object[][] {{"abcdefg","1234"},{"abc","1"}, {"xyz", "2"} } ;
    }


}
