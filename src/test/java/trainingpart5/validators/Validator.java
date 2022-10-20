package trainingpart5.validators;

import org.testng.asserts.SoftAssert;
import trainingpart5.response.EtoEResponse;

import static org.testng.Assert.assertEquals;

public class Validator {



    public void validate(EtoEResponse response, SoftAssert softAssert){


        System.out.println("Id" +response.getId());
        response.getName();
        //softAssert.assertEquals();



    }
}
