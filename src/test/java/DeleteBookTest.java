import api.ResponseAnswerText;
import api.Specs;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookTest {

    private final static String URL = "http://localhost:5000/api/books/";

    Object id;

    //Testcase52
    @Test
    public void DeleteBookByValidId() {
        id = 2;
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecOK200());
//        String error = "\"result\": true";
//        toCheckFieldTypeDelete(id, error);
        RestAssured.given().
                when().
                delete(URL + id).
                then().log().all();
    }

    //Testcase53
    @Test
    public void DeleteBookByNotValidId() {
        id = 333;
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecNotFound404());
        String error = "Book with id " + id + " not found";
        toCheckFieldTypeDelete(id, error);
    }

    //Testcase54
    @Test
    public void DeleteBookByWithoutID() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecNotFound404());
        RestAssured.given().
                when().
                delete(URL).
                then().log().all();
    }


    public static void toCheckFieldTypeDelete(Object id, String error) {
        ResponseAnswerText response = given().
                when().
                delete(URL + id).
                then().log().all().
                extract().body().as(ResponseAnswerText.class);
        Assert.assertEquals(error, response.getError());
    }

}
