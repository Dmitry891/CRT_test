import api.*;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AddBookTest {
    private final static String URL = "http://localhost:5000";

    Object name = "Программирование на Java для начинающих";
    Object author = "Майк МакГрат";
    Object year = 2016;
    Object isElectronicBook = true;

    //Testcase ID5
    @Test
    public void successAddBookByNameFieldOnly() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecCreated201());
        author = "";
        isElectronicBook = false;
        year = 0;
        Request add = new Request("Программирование на Java для начинающих");
        Response response = given().
                body(add).
                when().
                post("/api/books").
                then().log().all().
                extract().body().as(Response.class);
        Assert.assertEquals(name, response.getBook().getName());
        Assert.assertEquals(author, response.getBook().getAuthor());
        Assert.assertEquals(isElectronicBook, response.getBook().isIsElectronicBook());
        Assert.assertEquals(year, response.getBook().getYear());
    }

    //Testcase ID6
    @Test
    public void NoSuccessAddBookByNameFieldIntType() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name must be String type (Unicode)";
        name = 123;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID7
    @Test
    public void NoSuccessAddBookByNameFieldFloat() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name must be String type (Unicode)";
        name = 1.234;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID8
    @Test
    public void NoSuccessAddBookByNameFieldBooleanType() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name must be String type (Unicode)";
        name = true;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID9
    @Test
    public void NoSuccessAddBookByNameFieldNull() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name must be String type (Unicode)";
        name = null;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID10
    @Test
    public void successAddTestWithAllFields() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecCreated201());
        Request add = new Request(isElectronicBook, year, author, name);
        Response response = given().
                body(add).
                when().
                post("/api/books").
                then().log().all().
                extract().body().as(Response.class);
        Assert.assertEquals(name, response.getBook().getName());
        Assert.assertEquals(author, response.getBook().getAuthor());
        Assert.assertEquals(isElectronicBook, response.getBook().isIsElectronicBook());
        Assert.assertEquals(year, response.getBook().getYear());
    }

    //Testcase ID11
    @Test
    public void NoSuccessAddBookWithoutNameField() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name is required";
        RequestWithoutName add = new RequestWithoutName();
        ResponseAnswerText response = given().
                body(add).
                when().
                post("/api/books").
                then().log().all().
                extract().body().as(ResponseAnswerText.class);
        Assert.assertEquals(error, response.getError());
    }

    //Testcase ID12 (Bug) Согласно условиям задачи данное поле должно принамать только значения типа boolean
    @Test
    public void NoSuccessAddBookByIsElectronicBookInt() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "IsElectronicBook must be Bool type";
        Object isElectronicBook = 123;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID13 (Bug) Согласно условиям задачи данное поле должно принамать только значения типа boolean
    @Test
    public void NoSuccessAddBookByIsElectronicBookFloat() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "IsElectronicBook must be Bool type";
        isElectronicBook = 1.234;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID14 (Bug) Согласно условиям задачи данное поле должно принамать только значения типа boolean
    @Test
    public void NoSuccessAddBookByIsElectronicBookString() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "IsElectronicBook must be Bool type";
        isElectronicBook = "true";
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID15 (Bug) Согласно условиям задачи данное поле должно принамать только значения типа boolean
    @Test
    public void NoSuccessAddBookByIsElectronicBookNull() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "IsElectronicBook must be Bool type";
        isElectronicBook = null;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID16 (Bug) Согласно условиям задачи данное поле должно принамать только значения типа int
    @Test
    public void NoSuccessAddBookByYearFloat() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Year must be int type";
        year = 2016.01;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID17 (Bug) Согласно условиям задачи данное поле должно принамать только значения типа int
    @Test
    public void NoSuccessAddBookByYearString() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Year must be int type";
        year = "2016";
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID18 (Bug) Согласно условиям задачи данное поле должно принамать только значения типа int
    @Test
    public void NoSuccessAddBookByYearBoolean() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Year must be int type";
        year = true;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID19 (Bug) Согласно условиям задачи данное поле должно принамать только значения типа int
    @Test
    public void NoSuccessAddBookByYearNull() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Year must be int type";
        year = null;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID20 (Bug) Согласно условиям задачи данное поле должно принамать только значения типа String
    @Test
    public void NoSuccessAddBookByAuthorInt() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name must be String type (Unicode)";
        author = 123;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID21 (Bug) Согласно условиям задачи данное поле должно принамать только значения типа String
    @Test
    public void NoSuccessAddBookByAuthorFloat() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name must be String type (Unicode)";
        author = 1.234;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID22 (Bug) Согласно условиям задачи данное поле должно принамать только значения типа String
    @Test
    public void NoSuccessAddBookByAuthorBoolean() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name must be String type (Unicode)";
        author = true;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }

    //Testcase ID23 (Bug) Согласно условиям задачи данное поле должно принамать только значения типа String
    @Test
    public void NoSuccessAddBookByAuthorNull() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        String error = "Name must be String type (Unicode)";
        author = null;
        toCheckFieldTypePost(isElectronicBook, year, author, name, error);
    }


    //Testcase ID24
    @Test
    public void SuccessAddBookWithNameAndOnlyOneFieldAuthor() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecCreated201());
        isElectronicBook = false;
        year = 0;
        Request add = new Request(name);
        add.setAuthor(author);
        Response response = given().
                body(add).
                when().
                post("/api/books").
                then().log().all().
                extract().body().as(Response.class);
        Assert.assertEquals(name, response.getBook().getName());
        Assert.assertEquals(author, response.getBook().getAuthor());
        Assert.assertEquals(isElectronicBook, response.getBook().isIsElectronicBook());
        Assert.assertEquals(year, response.getBook().getYear());
    }

    //Testcase ID25
    @Test
    public void SuccessAddBookWithNameAndOnlyOneFieldYear() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecCreated201());
        isElectronicBook = false;
        author = "";
        Request add = new Request(name);
        add.setYear(year);
        Response response = given().
                body(add).
                when().
                post("/api/books").
                then().log().all().
                extract().body().as(Response.class);
        Assert.assertEquals(name, response.getBook().getName());
        Assert.assertEquals(author, response.getBook().getAuthor());
        Assert.assertEquals(isElectronicBook, response.getBook().isIsElectronicBook());
        Assert.assertEquals(year, response.getBook().getYear());
    }

    //Testcase ID26
    @Test
    public void SuccessAddBookWithNameAndOnlyOneFieldIsElectronicBook() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecCreated201());
        year = 0;
        author = "";
        Request add = new Request(name);
        add.setIsElectronicBook(isElectronicBook);
        Response response = given().
                body(add).
                when().
                post("/api/books").
                then().log().all().
                extract().body().as(Response.class);
        Assert.assertEquals(name, response.getBook().getName());
        Assert.assertEquals(author, response.getBook().getAuthor());
        Assert.assertEquals(isElectronicBook, response.getBook().isIsElectronicBook());
        Assert.assertEquals(year, response.getBook().getYear());
    }

    //Testcase ID27
    @Test
    public void NoSuccessWithoutBody() {
        Specs.installSpec(Specs.requestSpec(URL), Specs.responseSpecBad400());
        RestAssured
                .given()
                .when()
                .post(URL + "/api/books");
    }

    public static void toCheckFieldTypePost(Object isElectronicBook, Object year, Object author, Object name, String error) {
        Request add = new Request(isElectronicBook, year, author, name);
        ResponseAnswerText response = given().
                body(add).
                when().
                post("/api/books").
                then().log().all().
                extract().body().as(ResponseAnswerText.class);
        Assert.assertEquals(error, response.getError());
    }

}
