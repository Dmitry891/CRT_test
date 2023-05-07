import api.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookTest {

    private final static String URL = "http://localhost:5000/";

    Object name = "Преступление и наказание";
    Object author = "Фёдор Достоевский";
    Object year = 1886;
    Object isElectronicBook = false;
    Object id = 1;

    //Testcase ID28
    @Test
    public void toCheckSuccessUpDateBookAllFields() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecOK200());
        name = "Java. Руководство для начинающих";
        author = "Герберт Шилдт";
        isElectronicBook = true;
        year = 2022;
        Request add = new Request(isElectronicBook, year, author, name);
        Response response = given().
                body(add).
                when().contentType(ContentType.JSON).
                put("api/books/" + id).
                then().log().all().
                extract().body().as(Response.class);
        Assert.assertEquals(name, response.getBook().getName());
        Assert.assertEquals(author, response.getBook().getAuthor());
        Assert.assertEquals(isElectronicBook, response.getBook().isIsElectronicBook());
        Assert.assertEquals(year, response.getBook().getYear());
    }

    //Testcase ID29
    @Test
    public void toCheckNoSuccessUpDateBookWithoutID() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecNotFound404());
        RestAssured
                .given()
                .when()
                .put(URL + "/api/books/");
    }


    //Testcase ID30
    @Test
    public void toCheckNoSuccessUpDateBookNotExistID() {
        id = 333;
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecNotFound404());
        String error = "Book with id " + id + " not found";
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }


    //Testcase ID31
    @Test
    public void toCheckNoSuccessUpDateBookWithoutIsElectrocinBook() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        RequestWithoutIsElectronicBook add = new RequestWithoutIsElectronicBook(year, name, author);
        String error = "IsElectronicBook is required";
        ResponseAnswerText response = given().
                body(add).
                when().
                put("api/books/" + id).
                then().log().all().
                extract().body().as(ResponseAnswerText.class);
        Assert.assertEquals(error, response.getError());

    }

    //Testcase ID32
    @Test
    public void toCheckNoSuccessUpDateBookWithoutAuthorField() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        RequestWithoutAuthor add1 = new RequestWithoutAuthor(isElectronicBook, year, name);
        String error = "Author is required";
        ResponseAnswerText response = given().
                body(add1).
                when().
                put("api/books/" + id).
                then().log().all().
                extract().body().as(ResponseAnswerText.class);
        Assert.assertEquals(error, response.getError());

    }

    //Testcase ID33
    @Test
    public void toCheckNoSuccessUpDateBookWithoutName() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        RequestWithoutName add1 = new RequestWithoutName(isElectronicBook, year, author);
        String error = "Name is required";
        ResponseAnswerText response = given().
                body(add1).
                when().
                put("api/books/" + id).
                then().log().all().
                extract().body().as(ResponseAnswerText.class);
        Assert.assertEquals(error, response.getError());

    }

    //Testcase ID34
    @Test
    public void toCheckNoSuccessUpDateBookWithoutYear() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        RequestWithoutYear add1 = new RequestWithoutYear(isElectronicBook, author, name);
        String error = "Year is required";
        ResponseAnswerText response = given().
                body(add1).
                when().
                put("api/books/" + id).
                then().log().all().
                extract().body().as(ResponseAnswerText.class);
        Assert.assertEquals(error, response.getError());
    }

    //Testcase ID35
    @Test
    public void NoSuccessUpdateBookYearString() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Year must be Int type";
        year = "1866";
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }


    //Testcase ID36
    @Test
    public void NoSuccessUpdateBookYearFloat() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Year must be Int type";
        Object year = 1862.01;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID37 (Bug) По условию задачи поле "year" должно быть типа int
    @Test
    public void NoSuccessUpdateBookYearBoolean() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Year must be Int type";
        year = true;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID38
    @Test
    public void NoSuccessUpdateBookYearNull() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Year must be Int type";
        year = null;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID39
    @Test
    public void NoSuccessUpdateBookIsElectronicBookInt() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "IsElectronicBook must be Bool type";
        Object isElectronicBook = 123;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID40
    @Test
    public void NoSuccessUpdateBookIsElectronicBookFloat() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "IsElectronicBook must be Bool type";
        Object isElectronicBook = 1.234;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID41
    @Test
    public void NoSuccessUpdateBookIsElectronicBookString() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "IsElectronicBook must be Bool type";
        Object isElectronicBook = "true";
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID42
    @Test
    public void NoSuccessUpdateBookIsElectronicBookNull() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "IsElectronicBook must be Bool type";
        Object isElectronicBook = null;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID43
    @Test
    public void NoSuccessUpdateBookAuthorInt() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Author must be String type (Unicode)";
        Object author = 123;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID44
    @Test
    public void NoSuccessUpdateBookAuthorFloat() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Author must be String type (Unicode)";
        Object author = 1.234;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID45
    @Test
    public void NoSuccessUpdateBookAuthorBoolean() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Author must be String type (Unicode)";
        Object author = true;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID46
    @Test
    public void NoSuccessUpdateBookAuthorNull() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Author must be String type (Unicode)";
        Object author = null;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID47
    @Test
    public void NoSuccessUpdateBookNameInt() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name must be String type (Unicode)";
        Object name = 123;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID48
    @Test
    public void NoSuccessUpdateBookNameFloat() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name must be String type (Unicode)";
        name = 1.234;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID49
    @Test
    public void NoSuccessUpdateBookNameBoolean() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name must be String type (Unicode)";
        name = true;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID50
    @Test
    public void NoSuccessUpdateBookNameNull() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name must be String type (Unicode)";
        name = null;
        toCheckFieldTypePut(isElectronicBook, year, author, name, error, id);
    }

    //Testcase ID51
    @Test
    public void NoSuccessUpdateWithoutBody() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        RestAssured
                .given()
                .when()
                .put(URL + "/api/books/" + id);
    }

    public static void toCheckFieldTypePut(Object isElectronicBook, Object year, Object author, Object name, String error, Object id) {
        Request add = new Request(isElectronicBook, year, author, name);
        ResponseAnswerText response = given().
                body(add).
                when().
                put("/api/books/" + id).
                then().log().all().
                extract().body().as(ResponseAnswerText.class);
        Assert.assertEquals(error, response.getError());
    }
}



