import api.ResponseAnswerText;
import api.Specs;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetBookTest {

    private final static String URL = "http://localhost:5000/api/books";

    Object id = 1;

    //Testcase ID_1
    @Test
    public void GetBooks() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecOK200());
        RestAssured
                .given()
                .when()
                .get(URL)
                .then().log().all();
    }

    //Testcase ID_2
    @Test
    public void GetBookByValidId() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecOK200());
        RestAssured
                .given()
                .when()
                .get(URL + "/" + id)
                .then().log().all();
    }

    //Testcase ID_3
    @Test
    public void GetBookByNotValidId() {
        id = 333;
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecNotFound404());
        String error = "Book with id " + id + " not found";
        toCheckFieldTypeGet(id, error);
    }

    //Testcase ID_4
    @Test
    public void GetBookWithoutID() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecNotFound404());
        RestAssured
                .given()
                .when()
                .get(URL + "/");
    }

    public static void toCheckFieldTypeGet(Object id, String error) {
        ResponseAnswerText response = given().
                when().
                delete(URL + "/" + id).
                then().log().all().
                extract().body().as(ResponseAnswerText.class);
        Assert.assertEquals(error, response.getError());
    }
}