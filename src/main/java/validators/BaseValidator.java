package validators;

public class BaseValidator {


    public static void validateCity(String str){
        str.matches("[a-zA-Z]{12}");
    }

    public static void validateNumerals(){

    }


}
