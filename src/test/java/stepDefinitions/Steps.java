package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class Steps {

    public static final String BASE_URI="https://bookstore.toolsqa.com";
    public static final String USERNAME= "TOOLSQA-Test";
    public static final String PASSWORD= "Test@@123";
    public static final String USER_ID ="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";

    public static Response response;
    public static String token;
    public static String jsonString;
    public static String bookId;

    @Given("User is authorized")
    public void user_is_authorized() {
        RestAssured.baseURI=BASE_URI;
        RequestSpecification request= RestAssured.given();

        request.header("Content-Type","application/json");
        response= request.body("{ \"userName\": \"" + USERNAME + "\", \"password\": \""+PASSWORD+"\"}").
                post("/Account/v1/GenerateToken");
        String jsonString = response.asString();
        token = JsonPath.from(jsonString).get("token");
    }
    @Given("list of books are available")
    public void list_of_books_are_available() {
    RestAssured.baseURI=BASE_URI;
    RequestSpecification request= RestAssured.given();
    response=request.get("/BookStore/v1/Books");

    jsonString= response.asString();
    List<Map<String, String>> books= JsonPath.from(jsonString).get("books");
    Assert.assertTrue(books.size()>0);

    bookId= books.get(0).get("isbn");
    }
    @When("User add a book to list")
    public void user_add_a_book_to_list() {
    RestAssured.baseURI= BASE_URI;
    RequestSpecification request=RestAssured.given();
    request.header("Content-Type","application/json").
            header("Authentication","Bearer"+token);
    response=request.body("{\"userId\":\""+USER_ID+"\","+"\"collectionOfIsbn\":[{\"isbn\": \""+bookId+"\"}]}").
            post("/BookStore/v1/Books" );
    }
    @Then("The book is added to list")
    public void the_book_is_added_to_list() {
    Assert.assertEquals(201, response.getStatusCode());
    }

    @When("User remove a book from list")
    public void user_remove_a_book_from_list() {
    RestAssured.baseURI= BASE_URI;
    RequestSpecification request= RestAssured.given();
    request.header("Content-Type","application/json").
            header("Authentication","Bearer"+token);
    response=request.body("{\"isbn\":\""+bookId+"\",\"userId\":\""+USER_ID+"\"}").
            delete("/BookStore/v1/Books");
    }
    @Then("The book is removed")
    public void the_book_is_removed() {
    Assert.assertEquals(204,response.getStatusCode());
    }
}
