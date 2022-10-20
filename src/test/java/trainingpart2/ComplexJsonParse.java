package trainingpart2;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class ComplexJsonParse {


/*
{
"dashboard": {
"purchaseAmount": 910,
"website": "rahulshettyacademy.com"
},
"courses": [
{
"title": "Selenium Python",
"price": 50,
"copies": 6
},
{
"title": "Cypress",
"price": 40,
"copies": 4
},
{
"title": "RPA",
"price": 45,
"copies": 10
}
]
}

 */

    //print no cou courses returned byAPI -done
    //Print purchase amount -done
    //print title of the first course -
    //print all course titles and their respective prices
    //print no if copies sold by RPA course
    //ASSIGNMENT////verify if sum of all course prices match with the purchase amount

    @Test
    public static void testCourse(){

        JsonPath js = new JsonPath(AddPayload.json());
        int count = Integer.parseInt(js.getString("courses.size()"));
        System.out.println("count "+count);

        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("total amoutn "+totalAmount);

       String courseTitle =  js.get("courses[2].title");
        System.out.println("course title "+courseTitle);

        for(int i =0 ;i <count ;i++){
           String title= js.get("courses["+i+"].title").toString();
          String prices= js.get("courses["+i+"].price").toString();
            System.out.println("The title is "+title+ "and the price is  "+prices);
        }



    }
}
